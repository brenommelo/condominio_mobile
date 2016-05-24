var cadController = angular.module("financeiroController", [] );
cadController.controller("financeiroController", function ($scope, $http){
    //ID PROFISSIONAL DE TESTE
    $scope.id_profissional = 1;
    
    $scope.receita= nova_receita();
    $scope.despesa = nova_despesa();

    $scope.gravarReceita = function(){
        console.log($scope.receita);
    }
    $scope.gravarDespesa = function(){
        console.log($scope.despesa);
    }


        $scope.selecionarReceita = function() {
    $('#tab1').attr('style','display: block; padding: 0')
    $('#tab2').attr('style','display: none')
    $('#tab3').attr('style','display: none')
    }
    $scope.selecionarDespesas = function() {
    $('#tab1').attr('style','display: none')
    $('#tab2').attr('style','display: block; padding: 0')
    $('#tab3').attr('style','display: none')
    }
    $scope.selecionarExtrato = function() {
    $('#tab1').attr('style','display: none')
    $('#tab2').attr('style','display: none')
    $('#tab3').attr('style','display: block; padding: 0')
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
    $scope.$on('$viewContentLoaded', function() {});
});

function nova_receita() {
    return {
        id: null,
        idSync: null,
        nome: null,
        valor: null,
        inclusao: null,
        separacaoContabil: null,
        origem: null
    };
}
function nova_despesa() {
    return {
        id: null,
        idSync: null,
        nome: null,
        valor: null,
        inclusao: null,
        separacaoContabil: null
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

