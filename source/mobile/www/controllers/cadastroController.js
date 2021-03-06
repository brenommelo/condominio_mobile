var cadController = angular.module("cadastroController", [] );
cadController.controller("cadastroController", function ($scope, $http){
   var urlPrincipal ="http://150.164.192.63:8080/ProSindWeb/condominioservices/";
   $scope.id_profissional = 1;
   var condominioId = 1;
   $scope.menssagem = {exibir:false, texto:'Erro ao salvar!', status:'400'};
   $scope.codigoCondominio = 1;

   $scope.pessoa =novaPessoa();
   $scope.condominio =novaCondominio();
   $scope.apartamento = novoApartamento();
   $scope.apartamentos = [];

   // Controla o passo do wizard
   $scope.passo_cadastro = 2;

    $scope.salvarCondominio = function(){
        console.log($scope.condominio);
        if (!validar_campos($scope.condominio)) {
          return false;
        };
        // $http.post(urlPrincipal+"cadastro/salvar_condominio", $scope.condominio)
        // .success(function(retorno) {
        //     $scope.menssagem = {exibir:true, texto:retorno.mensagem, status:retorno.status};
        //     if(retorno.status = 200){
                $scope.habilitar_proximo_passo(2);
        //         $scope.pessoa =novaPessoa();
        //     }
        // }).error(function(data,status,error,config){
        //     $scope.menssagem = {exibir:true, texto:'Erro ao salvar! Verifique sua conexão com a internet!'};
        // });
    }

   $scope.salvarPessoa = function(){
        console.log($scope.pessoa);
         if (!validar_campos($scope.pessoa)) {
          return false;
        };
        // $http.post(urlPrincipal+"cadastro/salvar_pessoa", $scope.pessoa)
        // .success(function(retorno) {
        //     $scope.menssagem = {exibir:true, texto:retorno.mensagem, status:retorno.status};
        //     if(retorno.status = 200){
                $scope.habilitar_proximo_passo(3);
        //         $scope.pessoa =novaPessoa();
        //     }
        // }).error(function(data,status,error,config){
        //     $scope.menssagem = {exibir:true, texto:'Erro ao salvar! Verifique sua conexão com a internet!'};
        // });
    }

    $scope.salvarApartamentos = function(){
        console.log($scope.apartamentos);
        // $http.post(urlPrincipal+"cadastro/salvar_unidades", $scope.apartamentos)
        // .success(function(retorno) {
        //     $scope.menssagem = {exibir:true, texto:retorno.mensagem, status:retorno.status};
        //     if(retorno.status = 200){
        //         $scope.pessoa =novaPessoa();
        //     }
        // }).error(function(data,status,error,config){
        //     $scope.menssagem = {exibir:true, texto:'Erro ao salvar! Verifique sua conexão com a internet!'};
        // });
    }

    $scope.adicionarApartametno = function(){
       $scope.apartamento.condominio.id =$scope.codigoCondominio;
       $scope.apartamentos.push($scope.apartamento);
       $scope.apartamento = novoApartamento();

   }

   $scope.habilitar_proximo_passo = function(passo){
        $scope.passo_cadastro = passo;
        setTimeout(function(){
            $('a[data-page='+passo+']').click();
        }, 400);
   }

   // $scope.selecionarPessoa = function() {
   //      $('#tab1').attr('style','display: block; padding: 0');
   //      $('#tab2').attr('style','display: none');
   //      $('#tab3').attr('style','display: none');
   //      $scope.menssagem = {exibir:false, texto:'Erro ao salvar!', status:'400'};

   //  }
   //  $scope.selecionarApartamento = function() {
   //      $('#tab1').attr('style','display: none');
   //      $('#tab2').attr('style','display: none');
   //      $('#tab3').attr('style','display: block; padding: 0');
   //      $scope.menssagem = {exibir:false, texto:'Erro ao salvar!', status:'400'};

   //  }
   //  $scope.selecionarCondominio = function() {
   //      $('#tab1').attr('style','display: none');
   //      $('#tab2').attr('style','display: block; padding: 0');
   //      $('#tab3').attr('style','display: none');
   //      $('#ctrl_tab1').attr
   //      $scope.menssagem = {exibir:false, texto:'Erro ao salvar!', status:'400'};
   //  }


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
        emailResponsavel: null,
        condominio:{id:null}
    };
}
function novaPessoa() {
    return {
        nome: null,
        cpf: null,
        nascimento: null,
        sexo: 'M',
        telefone: null,
        perfil:{id:null},
        usuario:{email:null, senha:null}

    };
}
function novaCondominio () {
    return {
        id: null,
        idSync: null,
        inclusao: null,
        nome: null,

        endereco:{
           cep: null,
           rua: null,
           numero: null,
           bairro: null,
           municipio:{
            id:null
        }
    },
    estado:{id:null},
    tipoCondominio:{id:null}
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

