angular.module('starter.controllers', [])

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

.controller('RecipesCtrl', function ($scope, $http, Recipes) {
    $scope.ings = "";
    $scope.recipes = [];

    $scope.send = function(ings) {
        $scope.recipes = Recipes.all(ings);
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