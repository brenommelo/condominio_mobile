var cadController = angular.module("configuracaoController", [] );
cadController.controller("configuracaoController", function ($scope, $http){
     var urlPrincipal ="http://150.164.192.63:8080/ProSindWeb/condominioservices/";



     var usuario = JSON.parse(window.sessionStorage['usuario_logado']);
var sindico = usuario.perfil.sindico;
var idCondominio = usuario.unidade[0].condominio.id;
var idUnidade =  usuario.unidade[0].id;



    $scope.menssagem = {exibir:false, texto:"Aguarde ..."};
    $scope.configuracoes = novaConfiguracao();


    $scope.salvarConfiguracao = function(){
        $scope.menssagem.exibir = true;
               
                  $scope.configuracoes.condominio.id = idCondominio;
               
        $http.post(urlPrincipal+"configuracao/salvar_configuracoes", $scope.configuracoes)
            .success(function(data) {
                // $scope.introducao_data = data;
                 $scope.menssagem = {exibir:true, texto:"Carregado com sucesso!"};
             }).error(function(data,status,error,config){
                $scope.menssagem = {exibir:true, texto:"Erro! Verifique sua conexão com a internet."};
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
    $scope.load_scripts_padrao();
    // Quando o angular finalizar o carregamento o metodo abaixo e executado
    $scope.$on('$viewContentLoaded', function() {});
});


function novaConfiguracao() {
    return {
        id: null,
        inclusao: null,
        vencimento: null,
        pagamentoAntecipado: null,
        juros:null,
        multa:null,
        descontoPagamentoAntecipado:null,
        rateioDespesas:null,
        condominio:{id:null}
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

