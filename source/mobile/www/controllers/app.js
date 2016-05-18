
angular.module('MainApp', ['ui.router', 'MainController', 'cadastroController', 'EspecialistaController'])

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider

    .state('/',{
        url: "/",
        templateUrl: 'views/index.html',
        controller: "MainController"
    }).state('financas',{
        url: "/financas",
        templateUrl: 'views/financeiro.html',
        controller: "EspecialistaController",
           resolve:{
            init :function(){
                if('abrirModal' in window){abrirModal();}
            }
        }

    }).state('cadastro',{
        url: "/cadastro",
        templateUrl: 'views/cadastro.html',
        controller: "cadastroController",
           resolve:{
            init :function(){
                if('abrirModal' in window){abrirModal();}
            }
        }

    }).state('solicitacao',{
        url: "/solicitacao",
        templateUrl: 'views/solicitacao.html',
        controller: "cadastroController",
           resolve:{
            init :function(){
                if('abrirModal' in window){abrirModal();}
            }
        }

    }).state('especialista_notificacoes',{
        url: "/notificacoes",
        templateUrl: 'views/notificacoes.html',
        controller: "EspecialistaController"

    });
  $urlRouterProvider.otherwise('/');
});
