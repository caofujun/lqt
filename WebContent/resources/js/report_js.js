var __printWindow;
var __timer;
function __print(url) {

	var divObj = document.createElement("DIV");
	document.body.appendChild(divObj);
	divObj.style.display = "none";
	var formObj = document.createElement("FORM");
	divObj.appendChild(formObj);
	var previewExists = false;
//	retrieve previous window instance
//	var previousPrintWindow = window.open( '',
//	Constants.WINDOW_PRINT_PREVIEW,
//	'location=no,scrollbars=yes,dependent=yes' );
	var previousPrintWindow = window.open('', 'birtPrint',
	'location=no,scrollbars=yes,dependent=yes');
	try {
//		if the window didn't exist, then window.open() has opened an empty
//		window
		var previousBodyElement = previousPrintWindow.document
		.getElementsByTagName("body")[0];
		if (previousBodyElement
				&& trim(previousBodyElement.innerHTML).length > 0) {
			previewExists = true;
		}
	} catch (e) {
//		access denied is thrown if the previous preview window contains a PDF
//		content
		previewExists = true;
	}

	if (previewExists) {
//		workaround for Bugzilla Bug 227937
//		window.setTimeout( function () { alert(
//		Constants.error.printPreviewAlreadyOpen ) }, 0 );
		window.setTimeout(function() {
			alert('打印预览窗口已打开。')
		}, 0);
		return false;
	} else {
//		use the created window as current window
		this.__printWindow = previousPrintWindow;
	}
//	if (!__isIE()){
//	use onload event for the callback when page is loaded
//	Event.observe( this.__printWindow, 'load',
//	this.__cb_print.bindAsEventListener( this ), false );

//	}

	var data = url.split('?')[1].split('&');
	for ( var i = 0; i < data.length; i++) {
		var inp = document.createElement("input");
		inp.type = 'hidden';
		inp.name = data[i].split('=')[0];
		inp.value = data[i].split('=')[1];
		formObj.appendChild(inp);
	}

	formObj.action = encodeURI(url.split('?')[0]);
	formObj.method = "post";
	formObj.target = "birtPrint";
	formObj.submit();

//	if ( ( BrowserUtility.__isIE() && this.__printFormat != 'pdf' ) ||
//	BrowserUtility.__isSafari() )

	if (__isIE() || __isSafari()) {

		this.__timer = window.setTimeout(this.__cb_waitPreviewLoaded, 1000);
//		this.__timer = window.setTimeout( this.__cb_waitPreviewLoaded, 1000
//		);
	} else {

		__cb_print();
	}
}

function __cb_waitPreviewLoaded() {

	window.clearTimeout(this.__timer);

	try {
		if (!this.__printWindow || this.__printWindow.closed) {
			return;
		}

		if (!this.__printWindow.document
				|| this.__printWindow.document.readyState != "complete") {
//			wait a little longer
			this.__timer = window.setTimeout(this.__cb_waitPreviewLoaded
					.bindAsEventListener(this), 1000);
		} else {
			this.__cb_print();
		}
	} catch (error) {
//		IE throws a permission denied exception if the user closes
//		the window too early. In this case ignore the exception.
	}
}

function __cb_print() {

	try {
		if (!this.__printWindow || this.__printWindow.closed) {
			return;
		}

		var err = this.__printWindow.document.getElementById("birt_errorPage");
		if (err && err.innerHTML != '') {
			return;
		}

//		Call the browser's print dialog (async)
		if (this.__printFormat == 'pdf') // Mozilla only
		{
//			Mozilla needs some delay after loading PDF
			this.__printWindow.setTimeout("window.print();", 1000);
		} else {
//			defer call to let the window draw its content
//			(Firefox Bugzilla bug 213666)
			this.__printWindow.setTimeout("window.print();", 0);
		}
	} catch (error) {
//		IE throws a permission denied exception if the user closes
//		the window too early. In this case ignore the exception.
	}
}

function __isIE() {
	var userAgent = navigator.userAgent.toLowerCase();
	var useIFrame;
	if (userAgent.indexOf('msie') > -1) {
//		Internet Explorer
		return true;

	} else {
		return false;
	}
}

function __isSafari() {
	return navigator.appVersion.match(/Safari/) != null;
}

function trim(str) {
	return rtrim(ltrim(str));
}

function ltrim(str) {
	return str.replace(/^\s*/, '');
}

//trim right blanks
function rtrim(str) {
	return str.replace(/\s*$/, '');
}

//报表导出 
function __export(url) {
	var iframeReportObj = $('<iframe name="report_iframe1" id="report_iframe1" style="display:none;"/>');
	var formReportObj = $('<form target="report_iframe1" id="report_form1" style="display:none;" name="report_form1" method="POST" action="'
			+ url.split('?')[0] + '" />');

	var data = url.split('?')[1].split('&');
	for ( var i = 0; i < data.length; i++) {
		$('<input name="' + data[i].split('=')[0] + '" type="hidden"/>').val(
				data[i].split('=')[1]).appendTo(formReportObj);
	}
	$('body').append(iframeReportObj);
	$('body').append(formReportObj);

//	此行必须在submit前，用于回收内存
	iframeReportObj.on('load', function() {
		iframeReportObj.remove();
		iframeReportObj = null;
		formReportObj.remove();
		formReportObj = null;
		var iFrameDiv = document.getElementById("report_iframe1");
		iFrameDiv.parentNode.removeChild(iFrameDiv);
	});
	formReportObj.submit();
}