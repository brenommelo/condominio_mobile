// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
// angular.module('MainApp', ['ionic', 'PacienteController', 'EspecialistaController'])
angular.module('MainApp', ['ui.router', 'MainController', 'cadastroController', 'EspecialistaController'])



.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider

    .state('/',{
        url: "/",
        templateUrl: 'views/index.html',
        controller: "MainController"
        // animation: 'route_next'
    }).state('financas',{
        url: "/financas",
        templateUrl: 'views/financeiro.html',
        controller: "EspecialistaController",
           resolve:{
            init :function(){
                if('abrirModal' in window){abrirModal();}
            }
        }
        // animation: 'route_next'
    }).state('cadastro',{
        url: "/cadastro",
        templateUrl: 'views/cadastro.html',
        controller: "cadastroController",
           resolve:{
            init :function(){
                if('abrirModal' in window){abrirModal();}
            }
        }
        // animation: 'route_next'
    }).state('especialista_notificacoes',{
        url: "/notificacoes",
        templateUrl: 'views/notificacoes.html',
        controller: "EspecialistaController"
        // animation: 'route_next'
    });
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/');
});
