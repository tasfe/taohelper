var pMain={
	userNick:"",
	sessionKey:"",
	
	init:function(){
		
		if(Cookie.getCookie("userNick")==""){
			$("nick_display").innerHTML = "Haven't linked Taobao";		
			goAuthorize();
		}
		else{
			pMain.userNick = Cookie.getCookie("userNick");
			pMain.sessionKey = Cookie.getCookie("sessionKey");
			
			var tmphtml="Hello,"+pMain.userNick;
			$("nick_display").innerHTML = tmphtml;
			//alert(Cookie.getCookie("userNick"));
		}
				
	},
	
	goToHistory:function(){
		if(Cookie.getCookie("userNick")==""){		
			goAuthorize();
		}
		else{
			location.href="history.html";
		}
	}
	
};

pMain.init();