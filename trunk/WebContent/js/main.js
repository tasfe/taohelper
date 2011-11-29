var pMain={
	userNick:"",
	sessionKey:"",
	
	init:function(){
		if(userNick==""&&Cookie("userNick")==""){
			
			goAuthorize();
			
		}
		
	},
	
	goToHistory:function(){
		
		alert("history");
	}
	
};

pMain.init();