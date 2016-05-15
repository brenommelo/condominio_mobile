var cadController = angular.module("cadastroController", [] );
cadController.controller("cadastroController", function ($scope, $http){
    //ID PROFISSIONAL DE TESTE
    $scope.id_profissional = 1;

    $scope.script = null;
    $scope.page = null;
    $scope.ivcf_pts = 0;
    $scope.ivcf_progress = 0;

    $scope.telefones = [];
    $scope.consulta = nova_consulta();
    $scope.telefone = novo_telefone();
    $scope.paciente = novo_paciente();
    $scope.atividades_vd = novas_atividades_vd();
    $scope.cognicao_humor = nova_cognicao_humor();
    $scope.comorbidades = nova_comorbidade();
    $scope.mobilidade = nova_mobilidade();
    $scope.medida = nova_medida();

    $scope.introducao_data = [];
    $scope.pacientes = test_pacientes();
    $scope.listaNotificacoes = test_notificacoes();

    include('controllers/sql/sql-builder.js');

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

    $scope.get_db = function() {
        if ($scope.db == null) {
            $scope.db = window.sqlitePlugin.openDatabase({name:"cuidado_idoso.db", location: 'default'});
        }

        return $scope.db;
    }

   

    $scope.tab_financas_click = function(){
      
    }

    $scope.adicionar_telefone = function(){
        if (validaTelefone($scope.telefone)) {
            $scope.telefones.push($scope.telefone);
            $scope.telefone = novo_telefone();
        } else {
            exibir_mensagem_alerta('Telefone inválido!')
        }
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

function novo_paciente() {
    return {
        id: null,
        idSync: null,
        nome: null,
        sexo: 'M',
        inclusao: null,
        modificacao: null,
        nascimento: null,
        idade: null,
        centro_saude: null,
        cuidador_nome: null,
        cuidador_telefone: null,
        modificado: 0
    };
}

function novo_telefone() {
    return {
        id: null,
        idSync: null,
        numero: null,
        inclusao: null,
        modificacao: null,
        modificado: 0,
        id_paciente: null
    };
}

function novas_atividades_vd() {
    return {
        id: null,
        idSync: null,
        inclusao: null,
        modificacao: null,
        deixou_de_fazer_compras: 'nao',
        deixou_de_controlar_dinheiro: 'nao',
        deixou_de_fazer_trab_domesticos: 'nao',
        deixou_de_tomar_banho_sozinho: 'nao',
        modificado: 0,
        id_consulta: null
    }
}

function nova_consulta () {
    return {
        id: null,
        idsync: null ,
        inclusao: null,
        modificacao: null,
        modificado: 0,
        id_profissional: null,
        id_paciente: null
    }
}

function nova_cognicao_humor(){
    return{
        id: null,
        idSync: null,
        inclusao: null,
        modificacao: null,
        relato_de_esquecimento: 'nao',
        esquecimento_piorando: 'nao',
        esquecimento_impede_atividades: 'nao',
        desanimo_tristeza_ultimo_mes: 'nao',
        perda_interesse_atividades_prazerosas: 'nao',
        modificado: 0,
        id_consulta: null
    }
}

function nova_comorbidade(){
    return {
        id: null,
        idSync: null,
        inclusao: null,
        modificacao: null,
        has : false,
        dislipidemia : false,
        diabetes : false,
        tabagista_ativo : false,
        ex_tabagista : false,
        etilismo_ativo : false,
        etilismo_previo : false,
        avc_previo : false,
        osteosporose : false,
        osteoartrose_joelhos_quadril : false,
        dpoc : false,
        asma : false,
        insuficiencia_cardiaca : false,
        arritmia : false,
        sincope_previa : false,
        dor_cronica : false,
        transtorno_psquiatrico : false,
        doencas_reumatologicas : false,
        outros : null,
        internacao_recente: 'nao',
        reducao_acuidade_visual : 'nao',
        reducao_acuidade_auditiva : 'nao',
        med_numero : null,
        med_diureticos : 'nao',
        med_psicotropicos : 'nao',
        med_benzodiazepinicos : false,
        med_antidepressivos : false,
        med_neurolepticos : false,
        med_vitamina_d : 'nao',
        med_carbonato_calcio : 'nao',
        modificado: 0,
        id_consulta: null
    }
}

function nova_mobilidade(){
    return {
        id: null,
        idSync: null,
        inclusao: null,
        modificacao: null,
        dificuldade_caminhar: 'nao',
        quedas_anteriores: 'nenhuma',
        incapaz_elevar_bracos: 'nao',
        incapaz_manusear_pequenos_obj: 'nao',
        incontinencia_urinaria: 'nao',
        hipotensao_postural: 'nao',
        perda_ponderal_nao_intencional: 'nao',
        modificado: 0,
        id_consulta: null
    }
}

function nova_medida(){
    return {
        id: null,
        idSync: null,
        inclusao: null,
        modificacao: null,
        peso: null,
        altura: null,
        imc: null,
        velocidade_marcha: null,
        circunferencia_panturrilha: null,
        medo_cair: 5,
        autoconfianca: 5,
        autopercepcao: 'excelente',
        informante: 'paciente',
        modificado: 0,
        id_consulta: null
    }
}

function test_pacientes(){
    return [{
        "id":1,
        "nome":"Maria do Carmo",
        "ivcf":"22",
        "sexo": "F"
    },{
        "id":2,
        "nome":"João da Silva",
        "ivcf":"15",
        "sexo": "M"
    },{
        "id":3,
        "nome":"Pedro Motta",
        "ivcf":"20",
        "sexo": "M"
    },{
        "id":4,
        "nome":"José Ribeiro",
        "ivcf":"35",
        "sexo": "M"
    }
    ];
}

function test_notificacoes(){
    return [{
        id: 1,
        audio: true,
        descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin consequat dictum ultricies. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam erat volutpat. Nunc imperdiet turpis at metus suscipit tristique. Donec eu rhoncus arcu. Nulla sed cursus velit, nec dapibus ligula. Suspendisse accumsan vel nisi sit amet rutrum. Nam elementum tortor nec mauris mollis, nec suscipit augue tempor. Vestibulum faucibus erat vel ex hendrerit, ut facilisis libero tempor. Nam sodales iaculis dignissim. Ut sed nulla in ante condimentum dictum. In hac habitasse platea dictumst. Cras pulvinar molestie libero, at cursus diam gravida eu. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Maecenas a lorem erat. Nam quis felis sit amet dolor volutpat facilisis.",
        tipo: {
            id: 3,
            codigo: 'O',
            descricao: 'Outro'
        },
        paciente: {
            id: 1,
            nome: "Maria do Carmo",
            ivcf: "22",
            sexo: "F"
        }
    },{
        id: 2,
        audio: true,
        descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin consequat dictum ultricies. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam erat volutpat. Nunc imperdiet turpis at metus suscipit tristique. Donec eu rhoncus arcu. Nulla sed cursus velit, nec dapibus ligula. Suspendisse accumsan vel nisi sit amet rutrum. Nam elementum tortor nec mauris mollis, nec suscipit augue tempor. Vestibulum faucibus erat vel ex hendrerit, ut facilisis libero tempor. Nam sodales iaculis dignissim. Ut sed nulla in ante condimentum dictum. In hac habitasse platea dictumst. Cras pulvinar molestie libero, at cursus diam gravida eu. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Maecenas a lorem erat. Nam quis felis sit amet dolor volutpat facilisis.",
        tipo: {
            id: 2,
            codigo: 'Q',
            descricao: 'Queda'
        },
        paciente: {
            id: 2,
            nome: "João da Silva",
            ivcf: "15",
            sexo: "M"
        }
    },{
        id: 3,
        audio: false,
        descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin consequat dictum ultricies. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam erat volutpat. Nunc imperdiet turpis at metus suscipit tristique. Donec eu rhoncus arcu. Nulla sed cursus velit, nec dapibus ligula. Suspendisse accumsan vel nisi sit amet rutrum. Nam elementum tortor nec mauris mollis, nec suscipit augue tempor. Vestibulum faucibus erat vel ex hendrerit, ut facilisis libero tempor. Nam sodales iaculis dignissim. Ut sed nulla in ante condimentum dictum. In hac habitasse platea dictumst. Cras pulvinar molestie libero, at cursus diam gravida eu. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Maecenas a lorem erat. Nam quis felis sit amet dolor volutpat facilisis.",
        tipo: {
            id: 2,
            codigo: 'Q',
            descricao: 'Queda'
        },
        paciente: {
            nome: "Pedro Motta",
            ivcf: "20",
            sexo: "M"
        }
    },{
        id: 4,
        audio: false,
        descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin consequat dictum ultricies. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam erat volutpat. Nunc imperdiet turpis at metus suscipit tristique. Donec eu rhoncus arcu. Nulla sed cursus velit, nec dapibus ligula. Suspendisse accumsan vel nisi sit amet rutrum. Nam elementum tortor nec mauris mollis, nec suscipit augue tempor. Vestibulum faucibus erat vel ex hendrerit, ut facilisis libero tempor. Nam sodales iaculis dignissim. Ut sed nulla in ante condimentum dictum. In hac habitasse platea dictumst. Cras pulvinar molestie libero, at cursus diam gravida eu. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Maecenas a lorem erat. Nam quis felis sit amet dolor volutpat facilisis.",
        tipo: {
            id: 1,
            codigo: 'H',
            descricao: 'Hospitalização'
        },
        paciente: {
            nome: "José Ribeiro",
            ivcf: "35",
            sexo: "M"
        }
    }];
}

function include(destination) {
    var e=window.document.createElement('script');
    e.setAttribute('src',destination);
    window.document.body.appendChild(e);
}

function execute_sql(sql, args, success_callback, error_callback, db){
    try{
        if (db == undefined) {
            db = window.sqlitePlugin.openDatabase({name:"cuidado_idoso.db", location: 'default'});
        }

        db.transaction(
            function(transaction){
                transaction.executeSql(sql, args,
                   function(tx, result){ //success
                        success_callback(tx, result);
                    },function(error){ // error
                        error_callback(error);
                    }, function() {
                    }
                );
            }
        );
    } catch (error) {}
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

