var pMain={
	userNick:"",
	sessionKey:"",
	
	init:function(){
//		Cookie.addCookie("userNick","");
		var userNick = Cookie.getCookie("userNick");
		if(Cookie.getCookie("userNick")==""){
			$("nick_display").innerHTML = "<span style='decoration:underline;' onclick='goAuthorize(\"index.html\");'>Haven't linked Taobao</span>";		
			
		}
		else{
			pMain.userNick = Cookie.getCookie("userNick");
			pMain.sessionKey = Cookie.getCookie("sessionKey");
			
			var tmphtml="Hello,"+pMain.userNick+"  <a href='#' onclick='Common.logOut()'>Log out</a>";
			$("nick_display").innerHTML = tmphtml;
			//alert(Cookie.getCookie("userNick"));
		}
				
	},
	
	goToHistory:function(){
		if(Cookie.getCookie("userNick")==""){		
			goAuthorize("history.html");
		}
		else{
			location.href="history.html";
		}
	},
	
	goToCurrent:function(){
		if(Cookie.getCookie("userNick")==""){		
			goAuthorize("current.html");
		}
		else{
			location.href="current.html";
		}
	},
	
	goToFavorite:function(){
		if(Cookie.getCookie("userNick")==""){		
			goAuthorize("favorite.html");
		}
		else{
			location.href="favorite.html";
		}
	},
	
	goToRecommend:function(){
		if(Cookie.getCookie("userNick")==""){		
			goAuthorize("recommend.html");
		}
		else{
			location.href="recommend.html";
		}
	}
	
};

pMain.init();