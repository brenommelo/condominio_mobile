var mainCtrl = angular.module("MainController", [] );
mainCtrl.run(function($rootScope) {
    $rootScope.globalFoo = function() {
        // alert("I'm global foo!");
    };
});
mainCtrl.controller("MainController", function ($scope, $http, $usuarioSessao, $state){
 var urlPrincipal ="http://150.164.192.63:8080/ProSindWeb/condominioservices/";

 $scope.solicitacao = "Solicitações";
 $scope.financeiro = "Financeiro";
 $scope.notificacoes = "Notificações";
 $scope.cadastros = "Cadastros";
 $scope.configuracao = "Configuração";
 $scope.inadimplencia = "Inadimplência";
 $scope.user =get_user();

 $scope.menssagem = {exibir:false, texto:"Aguarde ..."};

 $scope.get_db = function() {
    if ($scope.db == null) {
        $scope.db = window.sqlitePlugin.openDatabase({name:"cuidado_idoso.db", location: 'default'});
    }

    return $scope.db;
}

$scope.destroy_db = function(){
    if (confirm("Deseja apagar a base de dados")) {
        window.sqlitePlugin.deleteDatabase({name: 'cuidado_idoso.db', location: 'default'}, function(){exibir_mensagem('banco apagado com sucesso')}, function(){exibir_mensagem('Falha ao apagar banco')});
        $scope.init_db();
    };
}

$scope.init_db = function(){
    try {
        $http.get('controllers/sql/scripts/create_tables.json')
        .success(function(data) {
            create_tables(data.sql, [], function(){}, function(){exibir_mensagem('Falha ao criar banco')});
            exibir_mensagem('Banco criado com sucesso');
        })
        .error(function(data,status,error,config){
            exibir_mensagem('Error: ' + error);
        });
    }catch(ex){
        console.log(ex);
    }
}

$scope.login = function(){
        // console.log($scope.user);

        $http.post(urlPrincipal+"usuario/login", $scope.user)
        .success(function(data) {
         
            if(data.mensagem =='Sucesso!'){
               window.sessionStorage['usuario_logado'] = data.data;
               $state.go('/');
           }
           $scope.menssagem = {exibir:true, texto:data.mensagem, status:data.status};
     
       }).error(function(data,status,error,config){
           $scope.menssagem = {exibir:true, texto:'Erro ao salvar! Verifique sua conexão com a internet!'};
       });
   }
});

function get_user(){
    return {
        usuario: '',
        senha: '',
    }
}

function create_tables(sql, args, success_callback, error_callback){
    try {
        var db = window.sqlitePlugin.openDatabase({name:"cuidado_idoso.db", location: 'default'});
        db.transaction(function(transaction) {
            for (var i = 0; i < sql.length; i++) {
                transaction.executeSql(sql[i], args,
                            function(tx, result){ //success
                                success_callback(tx, result);
                            },function(error){ // error
                                error_callback(error);
                                db.close();
                            }, function() {
                                db.close(function() {});
                            });
            };
        });
    } catch(error){
        alert(error.message);
    }
}

function exibir_mensagem(msg){
    Materialize.toast('<span class="flow-text">' + msg + '</span>', 3000, "orange darken-3");
}
