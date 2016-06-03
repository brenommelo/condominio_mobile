var cadController = angular.module("solicitacaoController", [] );
cadController.controller("solicitacaoController", function ($scope, $http){
     var urlPrincipal ="http://150.164.192.63:8080/ProSindWeb/condominioservices/";
     $scope.id_profissional = 1;
     var sindico = false;
     var idCondominio = 1;
     var idUnidade = 1;
    $scope.solicitacao =novaSolicitacao();


    $scope.salvarSolicitacao = function(){
                  $scope.solicitacao.solicitacaoSindico = sindico;
                  $scope.solicitacao.condominio.id = idCondominio;
                  $scope.solicitacao.unidade.id = idUnidade;
        $http.post(urlPrincipal+"notificacao/salvar_notificacao", $scope.solicitacao)
            .success(function(data) {
                // $scope.introducao_data = data;
                console.log('sucesso');
             }).error(function(data,status,error,config){
                console.log("erro");
            });
       
    }

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

function novaSolicitacao() {
    return {
        id: null,
        inclusao: null,
        descricao: null,
        solicitacaoSindico: false,
        unidade:{id:0},
        condominio:{id:0},
        tipoNotificacao:{id:0}
    };
}

function include(destination) {
    var e=window.document.createElement('script');
    e.setAttribute('src',destination);
    window.document.body.appendChild(e);
}


function validaTelefone (tel) {
    if (tel === undefined || tel === null || tel.numero === null) {
        return false;
    }

    var patt = /\d?\(?(\d{2})?\)?\d{4,5}\-?\d{4,5}/;

    var str = '';

    if (typeof tel === 'object') {
        str = tel.numero.replace(/ /g, '');
    } else {
        str = tel.replace(/ /g, '');
    }

    return patt.test(str);
}

function validaString (arg) {
    return arg !== undefined && arg !== null && arg.replace(' ', '') !== '';
}

function validaInteger (arg) {
    return arg !== undefined && arg !== null && arg >= 0;
}


function validaCampo (campo, success, unsuccess) {
    var result = false;
    switch ((typeof campo).toLowerCase()) {
        case 'string' :
            result = validaString(campo);
            if (result) {
                if (success !== undefined) {
                    success();
                    break;
                }
            } else {
                if (unsuccess !== undefined) {
                    unsuccess();
                    break;
                }
            }

            return result;
        case 'number' :
            result = validaInteger(campo);
            if (result) {
                if (success !== undefined) {
                    success();
                    break;
                }
            } else {
                if (unsuccess !== undefined) {
                    unsuccess();
                    break;
                }
            }

            return result;
        case 'object' :
            if (campo === null) {
                unsuccess();
            }
            //continuar metodo

            break;
    }
}

function habilitaTab (nome) {
    $("li.tab[name='" + nome + "']").removeClass('disabled');
}

function exibir_mensagem_alerta(msg){
    Materialize.toast('<span class="flow-text">'+msg+'</span>', 3000, "orange darken-3");
}

