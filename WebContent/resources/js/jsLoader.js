/**
 * jsLoader 同步加载JS，并执行回调
 * @param {type} url JS的url
 * @param {type} callback 加载后的回调
 * @param {type} objName 可选，如果对象存在，则不重新加载JS
 * @param {type} force 可选，objName 对象存在时，是否强制重新加载
 * @returns {Boolean}
 */
jsLoader = function(url, callback, objName, force) {
    force = force || false;
    // 执行回调
    var _run = function() {
        callback();
    };
    // 加载JS
    var _loader = function() {
        var header = document.getElementsByTagName('head')[0],
                js = document.createElement('script');
        js.setAttribute('type', 'text/javascript');
        js.setAttribute('charset', 'utf-8');
        js.setAttribute('src', url + '?' + Math.random());
        //js.setAttribute('src', url);
        js.onload = js.onreadystatechange = function() {
            if (!this.readyState || this.readyState === "loaded" || this.readyState === "complete") {
            	_run();
            	js.onload = js.onreadystatechange = null;//清内存，防止IE memory leaks
    		}
        };
        header.appendChild(js);
    };
    // main
    
    if (typeof objName !== 'undefined' && force == false) {
        _run();
    } else {
        _loader();
    }
    return true;
};

function getQueryString(url,name) {
	   var urls = {};
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(url.indexOf('?')+1);
	      strs = str.split("&");
	      for(var i = 0; i < strs.length; i ++) {
	    	 var q = strs[i].split('=');
	    	 if (name && name==q[0]){
	    		 return q[1];
	    	 }else{
	    		urls[q[0]] = q[1]; 
	    	 }
	      }
	   }
	   return urls;
}