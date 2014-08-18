angular.module('starter.services', [])

.factory('Recipes', function($http) {
  var recipes = [
  ];

  return {
    all: function(ings) {
      $http({
            method: 'GET',
            url: 'http://oquecomer.herokuapp.com/api/receitas?ingredientes=' + ings}).
        success(function (data, status) {
            recipes = data;
        }).
        error(function (data, status) {
            alert('Falha na Conexão com a Internet');
        });
        return recipes;
    },
    get: function(recipeId) {
      for ($rec in recipes) {
          if (recipes[$rec].id == recipeId) {
              return recipes[$rec];
          }
      }
    },
    allRestricted: function(ings) {
      $http({
            method: 'GET',
            url: 'http://oquecomer.herokuapp.com/api/receitasrestritas?ingredientes=' + ings}).
        success(function (data, status) {
            recipes = data;
        }).
        error(function (data, status) {
            alert('Falha na Conexão com a Internet');
        });
        return recipes;
    }
  }
})
.factory('RecipesRestricted', function($http) {
  var recipes = [
  ];

  return {
    all: function(ings) {
      $http({
            method: 'GET',
            url: 'http://oquecomer.herokuapp.com/api/receitasrestritas?ingredientes=' + ings}).
        success(function (data, status) {
            recipes = data;
        }).
        error(function (data, status) {
            alert('Falha na Conexão com a Internet');
        });
        return recipes;
    },
    get: function(recipeId) {
      for ($rec in recipes) {
          if (recipes[$rec].id == recipeId) {
              return recipes[$rec];
          }
      }
    }

  }
});