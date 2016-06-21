(function () {
    'use strict';

    load_config();

     $('.datepicker').pickadate({
        max: new Date(),
        min: new Date(1900, 0, 1),
        monthsFull: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
        monthsShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
        weekdaysFull: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
        weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
        showMonthsShort: undefined,
        showWeekdaysFull: undefined,
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 120, // Creates a dropdown to control month
        closeOnSelect: true,
        closeOnClear: true,
        today: 'Hoje',
        clear: 'Limpar',
        close: 'Fechar',
        format: 'dd/mm/yyyy'
    });

    $('.collapsible').collapsible({
        accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
    });

    $('#ubs_inpt').hide();




    init_voice_input();
})();

function load_config(){

    $('select').material_select();
    // $("select[required]").css({display: "inline", height: 0, padding: 0, width: 0});
    $('.modal-trigger').leanModal();
    $('ul.tabs').tabs();
    config_campos_obrigatorios();
    // $('.wizard').on('click', function () {
    //     var tab = $('li>a.active');
    //     var pagina = $('li>a.active').attr('data-page');
    //     var direcao = $(this).attr('data-direction');
    //     var nextTab = $("li>a[data-page='" + (direcao === 'right' ? parseInt(pagina)+1 : parseInt(pagina)-1) + "']");
    //     $('ul.tabs').tabs('select_tab', nextTab.attr('href').replace('#', ''));
    // });
}

function config_campos_obrigatorios(){
    var msg_padrao = "Campo obrigatório!";
    $('[data-required], [required]').each(function(){
        $(this).attr('required', 'required');
        $(this).on('invalid', function(){
            var msg = $(this).data('required');
            if (msg == null) {
                msg = msg_padrao;
            };
            this.setCustomValidity(msg);
        });
        $(this).on('input', function(){
            this.setCustomValidity("");
        });
    })
}

    function validar_campos(object, validar_id){
      if (validar_id == undefined) {
        validar_id = false;
      };
      for(var att in object){
        if ( att == 'id' && !validar_id ) { continue; };
        if ( att == 'idSync' || att == 'inclusao') { continue; };
        if ( object[att] == null || object[att] == "" ) {
          exibir_mensagem_alerta("Preencha os campos obrigatorios");
          return false;
        }else if(typeof(object[att]) == 'object'){
          var retorno = validar_campos(object[att], true);
          if (!retorno) {
            return false;
          };
        }
      }
      return true;
    }
