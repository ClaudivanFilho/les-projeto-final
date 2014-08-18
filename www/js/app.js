angular.module('starter', ['ionic', 'starter.controllers', 'starter.services', 'openfb'])
.run(function ($rootScope, $state, $ionicPlatform, $window, OpenFB) {
    // Facebook AppId and CallBack
    OpenFB.init('594340457341054', 'http://192.168.1.12:8100/oauthcallback.html');
})

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider
    .state('tab', {
      url: "/tab",
      abstract: true,
      templateUrl: "templates/tabs.html"
    })
    .state('tab.dash', {
      url: '/dash',
      views: {
        'tab-dash': {
          templateUrl: 'templates/tab-dash.html',
          controller: 'DashCtrl'
        }
      }
    })
    .state('tab.recipes', {
      url: '/recipes',
      views: {
        'tab-recipes': {
          templateUrl: 'templates/tab-recipes.html',
          controller: 'RecipesCtrl'
        }
      }
    })
    .state('tab.recipes-detail', {
      url: '/recipe/:recipeId',
      views: {
        'tab-recipes': {
          templateUrl: 'templates/recipe-detail.html',
          controller: 'RecipeDetailCtrl'
        }
      }
    })
    .state('tab.account', {
      url: '/account',
      views: {
        'tab-account': {
          templateUrl: 'templates/tab-account.html',
          controller: 'AccountCtrl'
        }
      }
    })
    // TO DO  === Ainda nao implemetado
    .state('app.share', {
        url: "/share",
        views: {
            'menuContent': {
                templateUrl: "templates/share.html",
                controller: "ShareCtrl"
            }
        }
    })

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/tab/dash');

});

