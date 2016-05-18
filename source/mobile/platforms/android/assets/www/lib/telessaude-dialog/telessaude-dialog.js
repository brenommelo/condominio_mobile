(function() {
	var openDialog = function (element) {
		element.addEventListener('click', function (event) {
		    dialog.showModal();
		});
	}

	var closeDialog = function (dlg) {
		dlg.close();
	}

	var btnCloseDlg = function (element, dlg) {
		element.addEventListener('click', function (event) {
		    dlg.close();
		});
	}

	var dialog_open = getAllElementsWithAttribute('data-dialog-open');
	var dialog_close = getAllElementsWithAttribute('data-dialog-close');
	var dialog = document.getElementById('telessaude-dialog');

	if (dialog) {
		dialog.addEventListener('click', function (event) {
		    var rect = dialog.getBoundingClientRect();
		    var isInDialog=(rect.top <= event.clientY && event.clientY <= rect.top + rect.height
		      && rect.left <= event.clientX && event.clientX <= rect.left + rect.width);
		    if (!isInDialog) {
		        closeDialog(dialog);
		    }
		});
	}

	for (var i = 0; i < dialog_open.length; i++) {
		openDialog(dialog_open[i]);
	};

	for (var i = 0; i < dialog_close.length; i++) {
		btnCloseDlg(dialog_close[i], dialog);
	};
})();