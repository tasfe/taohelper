var pCurrent={	
	curpage : 0,
	curTradeArr:new Array(),
	
	init:function(){
		if(Cookie.getCookie("userNick")=="") {
			goAuthorize();
			return;
		}
		
		pCurrent.getCurrentTrades();
	},
	
	drawPage:function(){
		
	},
	
	getCurrentTrades:function(){
		var sessionKey=Cookie.getCookie("sessionKey");
		if(sessionKey==""){
			goGetSessionKey();
			return;
		}
		var msg="sessionKey="+sessionKey;
		getdata("currenttrade",msg,function(xmlHttp){
			if(xmlHttp.readyState == 4 && xmlHttp.status ==200){
				var res = xmlHttp.responseText;
				var jsonObj = myeval(res);
				
				alert(jsonObj.length);
			}
		});
	}
}

pCurrent.init();