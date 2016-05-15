(function () {
	'use strict';

	$('.modal-trigger').on('click', function () {
		$('#modal_detail').openModal();
	});

	$('select').material_select();

	$('.collapsible').collapsible({
  		accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
    });

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
})();