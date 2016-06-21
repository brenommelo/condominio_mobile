var cadController = angular.module("financeiroController", [] );
cadController.controller("financeiroController", function ($scope, $http){

  var urlPrincipal ="http://150.164.192.63:8080/ProSindWeb/condominioservices/";
    //ID PROFISSIONAL DE TESTE

    var usuario = JSON.parse(window.sessionStorage['usuario_logado']);
    var idCondominio = usuario.unidade[0].condominio.id;
    var condominioServidor = usuario.unidade[0].condominio;
    $scope.menssagem = {exibir:false, texto:'Aguarde....', status:400};
    $scope.receita= nova_receita();
    $scope.despesa = nova_despesa();
    $scope.resultado_extrato = [];
    $scope.unidades = [];

    $scope.listarUnidades = function(){

     $http.post(urlPrincipal+"configuracao/retornar_unidades", condominioServidor)
     .success(function(data) {
      
        var result = JSON.parse(data.data);
        $scope.unidades = result;

    }).error(function(data,status,error,config){
       console.log('erro');
   });
}
$scope.listarUnidades();
$scope.gravarReceita = function(){
    console.log($scope.receita);
    $scope.receita.condominio.id =idCondominio;

    if($scope.receita.unidade.id==null&&$scope.receita.nome==null){
       $scope.menssagem = {exibir:true, texto:'Informe a origem ou a descrição da receita!', status:400};
   } else {
     $http.post(urlPrincipal+"financeiro/salvar_receita", $scope.receita)
     .success(function(data) {
      $scope.menssagem = {exibir:true, texto:data.mensagem, status:data.status};
      $scope.receita= nova_receita();
  }).error(function(data,status,error,config){

      $scope.menssagem = {exibir:true, texto:'Erro ao salvar! Verifique sua conexão com a internet!', status:400};
      
  });
}
}
$scope.gravarDespesa = function(){
        // console.log($scope.despesa);
        $scope.despesa.condominio.id =idCondominio;
        $http.post(urlPrincipal+"financeiro/salvar_despesa", $scope.despesa)
        .success(function(data) {
          $scope.menssagem = {exibir:true, texto:data.mensagem, status:data.status};
          if(data.erro){
            
              $scope.despesa = nova_despesa();
          }
      }).error(function(data,status,error,config){

          $scope.menssagem = {exibir:true, texto:'Erro ao salvar! Verifique sua conexão com a internet!', status:400};
          
      });
  }

  $scope.retornarReceitas = function(){
   $scope.receita.condominio.id =idCondominio;
   $http.post(urlPrincipal+"financeiro/consultar_receita", $scope.receita)
   .success(function(data) {
    var result = JSON.parse(data.data);
                // $scope.introducao_data = data;
                $scope.resultado_receita=result;
                
            }).error(function(data,status,error,config){
                console.log("erro");
            });
        }
        $scope.retornarDespesas = function(){
           $scope.despesa.condominio.id =idCondominio;
           $http.post(urlPrincipal+"financeiro/consultar_despesa", $scope.despesa)
           .success(function(data) {
               var result = JSON.parse(data.data);
                // $scope.introducao_data = data;
                $scope.resultado_despesa =result;
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
            
            
            $scope.retornarReceitas();
            $scope.retornarDespesas();
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
        $scope.gerar_relatorio();
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
        unidade: {id:null},
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

