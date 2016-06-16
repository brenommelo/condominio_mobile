
angular.module('MainApp',
    ['ui.router',
    'MainController',
    'cadastroController',
    'EspecialistaController',
    'financeiroController',
    'solicitacaoController',
    'notificacaoController',
    'configuracaoController'
    ])

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider

    .state('/',{
        url: "/",
        templateUrl: 'views/index.html',
        controller: "MainController"
    }).state('financas',{
        url: "/financas",
        templateUrl: 'views/financeiro.html',
        controller: "financeiroController",
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
        controller: "solicitacaoController",
           resolve:{
            init :function(){
                if('abrirModal' in window){abrirModal();}
            }
        }
    }).state('configuracao',{
        url: "/configuracao",
        templateUrl: 'views/configuracao.html',
        controller: "configuracaoController",
         resolve:{
            init :function(){
                if('abrirModal' in window){abrirModal();}
            }
        }

    }).state('especialista_notificacoes',{
        url: "/notificacoes",
        templateUrl: 'views/notificacoes.html',
        controller: "notificacaoController"

    }).state('login',{
        url: "/login",
        templateUrl: 'views/login.html',
        controller: "MainController"

    });
  $urlRouterProvider.otherwise('/login');
}).run(function($rootScope, $urlRouter, $state, $usuarioSessao) {
        $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
            // Halt state change from even starting
            // console.log(toState);
            if (toState.name != 'login' && toState.name != 'cadastro') {
                if ($usuarioSessao.usuarioLogado() == null) {
                    event.preventDefault();
                    $state.go('login', {});
                };
            }
        });
}).factory('$usuarioSessao', function(){
    return{
        usuarioLogado: function(){
            var user = window.sessionStorage['usuario_logado'];
            if (user != undefined) {
                return JSON.parse(window.sessionStorage['usuario_logado']);
            }else{
                return null;
            }
        },
        sair: function(){
            window.sessionStorage.removeItem('usuario_logado');
        }
    }
})
