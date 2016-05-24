var cadController = angular.module("cadastroController", [] );
cadController.controller("cadastroController", function ($scope, $http){
    $scope.id_profissional = 1;
    $scope.pessoa =novaPessoa();
    $scope.condominio =novaCondominio();
    $scope.apartamentos = [];
    $scope.apartamento = novoApartamento();

    $scope.salvarPessoa = function(){
        console.log($scope.pessoa)
    }
    $scope.salvarCondominio = function(){
        console.log($scope.condominio);
    }
    $scope.salvarApartamentos = function(){
        console.log($scope.apartamentos);
    }

$scope.adicionarApartametno = function(){
    $scope.apartamentos.push($scope.apartamento);
     $scope.apartamento = novoApartamento();
    
    }
    
    $scope.selecionarPessoa = function() {
    $('#tab1').attr('style','display: block; padding: 0')
    $('#tab2').attr('style','display: none')
    $('#tab3').attr('style','display: none')

    }
    $scope.selecionarApartamento = function() {
    $('#tab1').attr('style','display: none')
    $('#tab2').attr('style','display: none')
    $('#tab3').attr('style','display: block; padding: 0')

    }
    $scope.selecionarCondominio = function() {
    $('#tab1').attr('style','display: none')
    $('#tab2').attr('style','display: block; padding: 0')
    $('#tab3').attr('style','display: none')

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

function novoApartamento() {
    return {
        id: null,
        idSync: null,
        inclusao: null,
        nome: null,
        fracaoIdeal: null,
        cpf: null
    };
}function novaPessoa() {
    return {
        id: null,
        idSync: null,
        inclusao: null,
        nome: null,
        cpf: null,
        nascimento: null,
        sexo: 'M',
        email: null,
        senha: null,
        telefone: null
    };
}
function novaCondominio () {
    return {
        id: null,
        idSync: null,
        inclusao: null,
        nome: null,
        cep: null,
        rua: null,
        numero: null,
        bairro: null,
        cidade: null,
        estado: null,
        juros: null,
        multa: null,
        tipo:{nome:null}
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

