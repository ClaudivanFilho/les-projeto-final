angular.module('starter.controllers', [])

.controller('MainCtrl', function ($scope) {
    $scope.recipes = [];
    $scope.ing = "";
    $scope.ingredientes = [];
    $scope.setRecipes = function(recps) {
      $scope.recipes = recps;
    }
})

.controller('DashCtrl', function ($scope, $location, OpenFB) {

    $scope.facebookLogin = function () {
        OpenFB.login('email,publish_stream').then(
            function () {
                $location.path('/tab/account');
            },
            function () {
                alert('OpenFB login failed');
            });
    };

})

.controller('RecipesCtrl', function ($scope, $http, $location, Recipes) {
    $scope.predicate = 'nota';
    $scope.reverse = true;
    $scope.addIng = function(ing) {
      if (ing !== "") {
        $scope.ingredientes.push(ing);
        var myEl = document.getElementById('ingrediente');
        myEl.value = "";
      }
    }
    $scope.removeIng = function(ing) {
      if (ing !== "") {
        $scope.ingredientes.pop(ing);
        //var myEl = document.getElementById('ingrediente');
       // myEl.value = "";
      }
    }
    $scope.clearIngs = function(ing) {
      if (ing !== "") {
        $scope.ingredientes = [];
        //var myEl = document.getElementById('ingrediente');
       // myEl.value = "";
      }
    }
    $scope.clearRecipe = function() {
        $scope.recipes = [];
        //var myEl = document.getElementById('ingrediente');
       // myEl.value = "";

    }
    $scope.send = function() {
        var ings = "";
        for (var $i in $scope.ingredientes) {
          if (ings === "") {
            ings += $scope.ingredientes[$i];
          } else {
            ings += "," + $scope.ingredientes[$i];
          }
        }
        $scope.$parent.setRecipes(Recipes.all(ings));
    }
    $scope.show = function(index) {
        $location.path('tab/recipe/' + index);
        //#/tab/recipe/{{$index}}
    }
    $scope.sendRestricted = function() {
        var ings = "";
        for (var $i in $scope.ingredientes) {
          if (ings === "") {
            ings += $scope.ingredientes[$i];
          } else {
            ings += "," + $scope.ingredientes[$i];
          }
        }
        $scope.$parent.setRecipes(Recipes.allRestricted(ings));
    }
})

.controller('RestrictedCtrl', function ($scope, $http, Recipes) {
    $scope.addIng = function(ing) {
      if (ing !== "") {
        $scope.ingredientes.push(ing);
        var myEl = document.getElementById('ingrediente');
        myEl.value = "";
      }
    }
    $scope.send = function() {
        var ings = "";
        for (var $i in $scope.ingredientes) {
          if (ings === "") {
            ings += $scope.ingredientes[$i];
          } else {
            ings += "," + $scope.ingredientes[$i];
          }
        }
        $scope.$parent.setRecipes(Recipes.all(ings));
    }
})

.controller('RecipeDetailCtrl', function ($scope, $stateParams, Recipes) {
    $scope.recipe = Recipes.get($stateParams.recipeId);
})

.controller('AccountCtrl', function ($scope, OpenFB) {
    OpenFB.get('/me').success(function (user) {
        $scope.user = user;
    });
})
//== Ainda nao implementado
.controller('ShareCtrl', function ($scope, OpenFB) {

    $scope.item = {};

    $scope.share = function () {
        OpenFB.post('/me/feed', $scope.item)
            .success(function () {
                $scope.status = "This item has been shared on OpenFB";
            })
            .error(function(data) {
                alert(data.error.message);
            });
    };

});