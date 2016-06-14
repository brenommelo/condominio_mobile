var cadController = angular.module("financeiroController", [] );
cadController.controller("financeiroController", function ($scope, $http){

      var urlPrincipal ="http://150.164.192.63:8080/ProSindWeb/condominioservices/";
    //ID PROFISSIONAL DE TESTE
    var idCondominio = 1;

    $scope.receita= nova_receita();
    $scope.despesa = nova_despesa();
    $scope.resultado_extrato = [];

    $scope.gravarReceita = function(){
        // console.log($scope.receita);
        $scope.receita.condominio.id =idCondominio;
           $http.post(urlPrincipal+"financeiro/salvar_receita", $scope.receita)
            .success(function(data) {
                // $scope.introducao_data = data;
                console.log('sucesso');
             }).error(function(data,status,error,config){
                console.log("erro");
            });
    }
    $scope.gravarDespesa = function(){
        // console.log($scope.despesa);
         $scope.despesa.condominio.id =idCondominio;
           $http.post(urlPrincipal+"financeiro/salvar_despesa", $scope.despesa)
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

    $scope.gerar_relatorio = function(){
        $scope.resultado_extrato = [];
        Math.random();
        for (var i = 0; i < 10; i++) {
            var credito = Math.random() < 0.5;
            $scope.resultado_extrato.push({
                descricao: credito ? 'Credito': 'Debito',
                data: new Date(),
                valor: (Math.random() * 100) * (credito ? 1: -1),
            });
        };
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
    $scope.$on('$viewContentLoaded', function() {});
});

function nova_receita() {
    return {
        nome: null,
        valor: null,
        separacaoContabil:{id:null},
        unidade: null,
        realizacao:null,
        condominio:{id:null}
    };
}
function nova_despesa() {
    return {
        nome: null,
        valor: null,
        realizacao:null,
        separacaoContabil:{id:null},
        condominio:{id:null}
    };
}


function include(destination) {
    var e=window.document.createElement('script');
    e.setAttribute('src',destination);
    window.document.body.appendChild(e);
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

