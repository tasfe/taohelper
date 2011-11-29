var pMain={
	userNick:"",
	sessionKey:"",
	
	init:function(){
		
		if(Cookie.getCookie("userNick")==""){		
			goAuthorize();
		}
		else{
			pMain.userNick = Cookie.getCookie("userNick");
			pMain.sessionKey = Cookie.getCookie("sessionKey");
			
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