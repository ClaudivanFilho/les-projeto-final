angular.module('starter', ['ionic', 'starter.controllers', 'starter.services'])

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
    .state('tab.search', {
      url: '/search',
      views: {
        'tab-recipes': {
          templateUrl: 'templates/search-result.html',
          controller: 'SearchCtrl'
        }
      }
    })

  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/tab/dash');

});

