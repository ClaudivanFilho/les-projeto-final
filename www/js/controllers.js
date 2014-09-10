angular.module('starter.controllers', [])

.controller('MainCtrl', function ($scope) {
    $scope.recipes = [];
    $scope.ing = "";
    $scope.ingredientes = [];
    $scope.selected = "";
    $scope.setSelected = function(rec) {
        $scope.selected = rec;
    }
    $scope.setRecipes = function(recps) {
      $scope.recipes = recps;
    }
})

.controller('DashCtrl', function ($scope, $location) {

})

.controller('RecipesCtrl', function ($scope, $http, $location, Recipes) {
    $scope.addIng = function(ing) {
      if (ing !== "") {
        $scope.ingredientes.push(ing);
        var myEl = document.getElementById('ingrediente');
        myEl.value = "";
      }
    }
    $scope.delIng = function(index) {
        $scope.ingredientes.splice(index,1);
    }
    $scope.clearIngs = function(ing) {
      if (ing !== "") {
        $scope.$parent.ingredientes.length = 0;
      }
    }
    $scope.send = function(type) {
        var ings = "";
        for (var $i in $scope.ingredientes) {
          if (ings === "") {
            ings += $scope.ingredientes[$i];
          } else {
            ings += "," + $scope.ingredientes[$i];
          }
        }
        if (type == true) {
            $scope.$parent.setRecipes(Recipes.allRestricted(ings));
            $location.path('tab/search');
        } else {
            $http({
                    method: 'GET',
                    url: 'http://oquecomer.herokuapp.com/api/receitas?ingredientes=' + ings}).
                success(function (data, status) {
                    Recipes.setAll(data);
                    $scope.$parent.setRecipes(data);
                    $location.path('tab/search');
                }).
                error(function (data, status) {
                    alert('Falha na Conexão com a Internet');
                }
            );
        }
    }
})
.controller('SearchCtrl', function ($scope, $http, $location, Recipes) {
    $scope.predicate = 'nota';
    $scope.reverse = true;
    $scope.show = function(index) {
        $scope.$parent.setSelected(Recipes.get(index))
        $location.path('tab/recipe/' + index);
    }
    $scope.getNota = function(rct) {
        var sum = 0;
        for (var $index in rct.notas) {
            sum += rct.notas[$index].nota;
        }
        if (rct.notas.length == 0) {
            return 'não possui';
        }
        return (sum/rct.notas.length);
    }
})
.controller('RecipeDetailCtrl', function ($http, $scope, $stateParams, Recipes) {
    $scope.recipe = Recipes.get($stateParams.recipeId);
    $scope.sendNota = function(id, nota_receita) {
            $http({
                    method: 'GET',
                    url: 'http://oquecomer.herokuapp.com/api/setNota?nota=' + nota_receita
                        + '&receita_id=' + id
                    }).
                success(function (data, status) {
                    alert('Nota enviada com sucesso');
                }).
                error(function (data, status) {
                    alert('Falha na Conexão com a Internet');
                }
            );
    }
    $scope.getNota = function(rct) {
        var sum = 0;
        for (var $index in rct.notas) {
            sum += rct.notas[$index].nota;
        }
        if (rct.notas.length == 0) {
            return 'não possui';
        }
        return (sum/rct.notas.length);
    }
});
