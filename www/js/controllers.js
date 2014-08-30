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
})
.controller('RecipeDetailCtrl', function ($http, $scope, $stateParams, Recipes) {
    $scope.recipe = Recipes.get($stateParams.recipeId);
    $scope.sendNota = function(id, nota_receita) {
            var e = document.getElementById(nota_receita);
            var nota_receita = e.options[e.selectedIndex].value;
            $http({
                    method: 'POST',
                    url: 'http://oquecomer.herokuapp.com/setNota',
                    data: {
                    	'nota': nota_receita,
                        'receita_id': id,
                    }
                    
                    }).
                success(function (data, status) {
                	alert('Nota enviada com sucesso');
                    console.log('done');
                }).
                error(function (data, status) {
                	alert(nota_receita);
                    alert('Falha na Conexão com a Internet');
                }
            );
    }
});