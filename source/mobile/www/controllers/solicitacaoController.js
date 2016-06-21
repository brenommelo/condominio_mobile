var cadController = angular.module("solicitacaoController", [] );
cadController.controller("solicitacaoController", function ($scope, $http){
 var urlPrincipal ="http://150.164.192.63:8080/ProSindWeb/condominioservices/";

 $scope.opcoes = [
 {descricao:"Barulho",id:"1"},
 {descricao:"Manutenção",id:"2"},
 {descricao:"Duvida prestação contas",id:"4"},
 {descricao:"Duvida calculos de taxas",id:"3"}
 ];


var usuario = JSON.parse(window.sessionStorage['usuario_logado']);
var sindico = usuario.perfil.sindico;
var idCondominio = usuario.unidade[0].condominio.id;
var idUnidade =  usuario.unidade[0].id;

 $scope.solicitacao =novaSolicitacao();
 $scope.menssagem = {exibir:false, texto:"Aguarde..."};



 $scope.salvarSolicitacao = function(){
    $scope.menssagem.exibir = true;
    $scope.solicitacao.solicitacaoSindico = sindico;
    $scope.solicitacao.condominio.id = idCondominio;
    $scope.solicitacao.unidade.id = idUnidade;
    $http.post(urlPrincipal+"notificacao/salvar_notificacao", $scope.solicitacao)
    .success(function(retorno) {
        $scope.menssagem = {exibir:true, texto:retorno.mensagem, status:retorno.status};
        if(retorno.status = 200){
            $scope.solicitacao =novaSolicitacao();
        }
    }).error(function(data,status,error,config){
        $scope.menssagem = {exibir:true, texto:'Erro ao salvar! Verifique sua conexão com a internet!'};
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

