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
    $('.modal-trigger').leanModal();
    $('ul.tabs').tabs();
    // $('.wizard').on('click', function () {
    //     var tab = $('li>a.active');
    //     var pagina = $('li>a.active').attr('data-page');
    //     var direcao = $(this).attr('data-direction');
    //     var nextTab = $("li>a[data-page='" + (direcao === 'right' ? parseInt(pagina)+1 : parseInt(pagina)-1) + "']");
    //     $('ul.tabs').tabs('select_tab', nextTab.attr('href').replace('#', ''));
    // });
}

function config_campos_obrigatorios(){
    $('[data-required]').each(function(){
        $(this).attr('required', 'required');
        $(this).on('invalid', function(){
            this.setCustomValidity($(this).data('required'));
        });
        $(this).on('input', function(){
            this.setCustomValidity("");
        });
    })
}
