var especController = angular.module("notificacaoController", ['ngDialog'] );
especController.controller("notificacaoController", function ($scope, $http, ngDialog){
   var urlPrincipal ="http://150.164.192.63:8080/ProSindWeb/condominioservices/";
   $scope.solicitacao = novaSolicitacao();
   
   var usuario = JSON.parse(window.sessionStorage['usuario_logado']);
   var idCondominio = usuario.unidade[0].condominio.id;
   var sindico = usuario.perfil.sindico;
   // $scope.notificacaoCompleta = novaSolicitacao();

   retornar_notificacoes();

    $scope.openConfirm = function (not) {
        $scope.notificacaoCompleta = not;
                ngDialog.openConfirm({
                    template: 'modalDialogId',
                    className: 'ngdialog-theme-default',
                    scope: $scope
                }).then(function (value) {
                    console.log('Botao confirmar ', value);
                }, function (reason) {
                    console.log('Botao cancelar ', reason);
                });
            };


   function retornar_notificacoes(){
     $scope.solicitacao.solicitacaoSindico = sindico;
     $scope.solicitacao.condominio.id = idCondominio;
     
     $http.post(urlPrincipal+"notificacao/retornar_notificacoes", $scope.solicitacao)
     .success(function(data) {
        var result = JSON.parse(data.data);

        $scope.listaNotificacoes = result;

            }).error(function(data,status,error,config){
                console.log("erro");
            });
        }

        function novaSolicitacao() {
            return {
                id: null,
                inclusao: null,
                descricao: null,
                solicitacaoSindico: false,
                
                condominio:{id:0}
            };
        }

    // $scope.listaNotificacoes = test_notificacoes();
    // $scope.listaNotificacoes = retornar_notificacoes();

    $scope.set_scripts = function(script){
        $scope.page = script;
    }

    $scope.load_scripts_padrao = function(){
        $.getScript('resources/js/scrip_padrao.js');
    }
    

    $scope.load_data = function(){
        try{
            $http.get('controllers/dados/dados_introducao.json')
            .success(function(data) {
                $scope.introducao_data = data;
            }).error(function(data,status,error,config){
                alert(error);
            });
        }catch(ex){
        }
    }

    $scope.load_data();
    // Quando o angular finalizar o carregamento o metodo abaixo e executado
    $scope.$on('$viewContentLoaded', function() {});
});

// function test_notificacoes(){
//     return [{
//         id: 1,
//         audio: true,
//         descricao: "Deu ruim.",
//         prioridade: {
//             prioridade: 3,
//             codigo: 'U',
//             descricao: 'Outro'
//         },
//         tipoNotificacao: {
//             prioridade: 1,
//             codigo: 'N',
//             descricao: 'Outro'
//         }
//     },
//     {
//         id: 2,
//         audio: true,
//         descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin consequat dictum ultricies. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam erat volutpat. Nunc imperdiet turpis at metus suscipit tristique. Donec eu rhoncus arcu. Nulla sed cursus velit, nec dapibus ligula. Suspendisse accumsan vel nisi sit amet rutrum. Nam elementum tortor nec mauris mollis, nec suscipit augue tempor. Vestibulum faucibus erat vel ex hendrerit, ut facilisis libero tempor. Nam sodales iaculis dignissim. Ut sed nulla in ante condimentum dictum. In hac habitasse platea dictumst. Cras pulvinar molestie libero, at cursus diam gravida eu. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Maecenas a lorem erat. Nam quis felis sit amet dolor volutpat facilisis.",
//         tipoNotificacao: {
//             prioridade: 2,
//             codigo: 'Q',
//             descricao: 'Queda'
//         }

//     },
//     {
//         id: 3,
//         audio: false,
//         descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin consequat dictum ultricies. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam erat volutpat. Nunc imperdiet turpis at metus suscipit tristique. Donec eu rhoncus arcu. Nulla sed cursus velit, nec dapibus ligula. Suspendisse accumsan vel nisi sit amet rutrum. Nam elementum tortor nec mauris mollis, nec suscipit augue tempor. Vestibulum faucibus erat vel ex hendrerit, ut facilisis libero tempor. Nam sodales iaculis dignissim. Ut sed nulla in ante condimentum dictum. In hac habitasse platea dictumst. Cras pulvinar molestie libero, at cursus diam gravida eu. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Maecenas a lorem erat. Nam quis felis sit amet dolor volutpat facilisis.",
//         tipoNotificacao: {
//             prioridade: 2,
//             codigo: 'Q',
//             descricao: 'Queda'
//         }
//     },
//     {
//         id: 4,
//         audio: false,
//         descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin consequat dictum ultricies. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam erat volutpat. Nunc imperdiet turpis at metus suscipit tristique. Donec eu rhoncus arcu. Nulla sed cursus velit, nec dapibus ligula. Suspendisse accumsan vel nisi sit amet rutrum. Nam elementum tortor nec mauris mollis, nec suscipit augue tempor. Vestibulum faucibus erat vel ex hendrerit, ut facilisis libero tempor. Nam sodales iaculis dignissim. Ut sed nulla in ante condimentum dictum. In hac habitasse platea dictumst. Cras pulvinar molestie libero, at cursus diam gravida eu. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Maecenas a lorem erat. Nam quis felis sit amet dolor volutpat facilisis.",
//         tipoNotificacao: {
//             prioridade: 1,
//             codigo: 'H',
//             descricao: 'Hospitalização'
//         }
//     }];
// }

function include(destination) {
    var e=window.document.createElement('script');
    e.setAttribute('src',destination);
    window.document.body.appendChild(e);
}

function exibir_mensagem_alerta(msg){
    Materialize.toast('<span class="flow-text">'+msg+'</span>', 3000, "orange darken-3");
}

