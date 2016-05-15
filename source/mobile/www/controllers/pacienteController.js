var pacienteController = angular.module("pacienteController", [] );
pacienteController.controller("pacienteController", function ($scope, $window, $http){
    $scope.ocorrencia = nova_ocorrencia();
    include('controllers/sql/sql-builder.js');
     //ID PACIENTE DE TESTE
    $scope.id_paciente = 1;

    $scope.listaMensagens = [{
            id: 1,
            audio: true,
            descricao: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin consequat dictum ultricies. Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam erat volutpat. Nunc imperdiet turpis at metus suscipit tristique. Donec eu rhoncus arcu. Nulla sed cursus velit, nec dapibus ligula. Suspendisse accumsan vel nisi sit amet rutrum. Nam elementum tortor nec mauris mollis, nec suscipit augue tempor. Vestibulum faucibus erat vel ex hendrerit, ut facilisis libero tempor. Nam sodales iaculis dignissim. Ut sed nulla in ante condimentum dictum. In hac habitasse platea dictumst. Cras pulvinar molestie libero, at cursus diam gravida eu. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Maecenas a lorem erat. Nam quis felis sit amet dolor volutpat facilisis.",
            tipo: {
                id: 3,
                codigo: 'M',
                descricao: 'Motivacional'
            },
            medico: {
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
                codigo: 'E',
                descricao: 'Educacional'
            },
            medico: {
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
                codigo: 'M',
                descricao: 'Motivacional'
            },
            medico: {
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
                codigo: 'E',
                descricao: 'Educacional'
            },
            medico: {
                nome: "José Ribeiro",
                ivcf: "35",
                sexo: "M"
            }
        }
    ];

    $scope.list_orientacoes = [{
        id:"1",
        titulo:"Porque prevenir Queda?",
        conteudo:'Um terço dos idosos com idade maior ou igual 65 anos caem ao menos uma vez por ano e evoluem com duas e seis vezes mais risco de cair novamente. As injúrias relacionadas às quedas estão entre as condições mais comuns, mais mórbidas e custosas envolvendo os idosos. As quedas são responsáveis por 10 % dos atendimentos em emergência e 6% das hospitalizações dos idosos. As quedas são os principais determinantes de declínio funcional, institucionalização e restrição de atividade física e também é uma causa importante de morte nos idosos.'
    },{
        id:"2",
        titulo:"Orientações",
        conteudo:"Este é um programa de prevenção secundária de quedas através de abordagem multifatorial com programa de exercícios domiciliares e telemonitoramento. O profissional que fará a inclusão e orientação do paciente pode ser: enfermeiro, fisioterapeuta ou médico- a depender do perfil da instituição."
    },{
        id:"3",
        titulo:"Critérios de Inclusão",
        conteudo:"Serão incluídos idosos da comunidade, com idade igual ou superior a 70 anos, que tiveram uma ou mais quedas no último ano e que sejam capazes de deambular sem apoio de órteses ou de pessoas."
    }];

    $scope.abrirModal = function (label) {
        $scope.situacao = label;
    }
    
    $scope.salvar_ocorrencia = function(){
        if ($scope.ocorrencia.id === null) {
            $scope.ocorrencia.id_paciente = $scope.id_paciente;
            $scope.ocorrencia.inclusao = new Date();
            $scope.script = gerar_insert($scope.ocorrencia, 'id', 'ocorrencias');
        } 
        console.log(angular.toJson($scope.medida));
        console.log(angular.toJson($scope.script));
        
        execute_sql($scope.script.sql, $scope.script.values,
            function (tx, result) {
                exibir_mensagem_alerta('Ocorrencia salva com sucesso');
                
                var dialog = document.getElementById('telessaude-dialog');
                closeDialog(dialog);
                $scope.ocorrencia = nova_ocorrencia();

                $scope.$apply();
            }, function (error) {
                exibir_mensagem_alerta('Erro ao salvar ocorrencia');
            }
        );
    }
});

function getAllElementsWithAttribute(attribute) {
    var matchingElements = [];
    var allElements = document.getElementsByTagName('*');
    for (var i = 0, n = allElements.length; i < n; i++)
    {
        if (allElements[i].getAttribute(attribute) !== null)
        {
          // Element exists with attribute. Add to array.
          matchingElements.push(allElements[i]);
        }
    }
    return matchingElements;
}

    
    function nova_ocorrencia() {
        return {
            id: null,
            idSync: null,
            inclusao: null,
            modificacao: null,
            modificado: 0,
            observacao: null,
            atendida: 0,
            id_paciente: null
        };
    }


