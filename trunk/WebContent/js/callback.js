var userNick = Request.QueryString("taobao_user_nick");
var session_auth = Request.QueryString("session"); //use when authorize
var session = Request.QueryString("top_session"); // use when session key is expired

if(userNick==""&&session=="")
{
	var tmphtml = "<div style='width:100%;text-align:center;font-style:bold'>Authorize failed!</div>"
					+"<div style='width:100%;text-align:center'><div onclick='self.close();' class='button_div'>Close Here</div></div>";
	//alert("authorize successfully!");
	$("msg_display").innerHTML = tmphtml;
}
else{
	if(userNick&&userNick!=""){
		Cookie.addCookie("userNick",userNick,0);
		
		var tmphtml = "<div style='width:100%;text-align:center;font-style:bold'>Authorized successfully!The page will jump to previous page in <span id='counttime'>5</span> s.</div>"
						+"<div style='width:100%;text-align:center'><div onclick='goToMain()' class='button_div'>Return</div></div>";
		//alert("authorize successfully!");
		$("msg_display").innerHTML = tmphtml;
		setTimeout("goToMain()",1000);
	}
	
	if(session_auth&&session_auth!=""){
		Cookie.addCookie("sessionKey",session_auth,0.5);
	}
	
	if(session && session!=""){
		Cookie.addCookie("sessionKey",session,0.5);
//		self.close();
	}	
}

function goToMain(){
	var curtime = parseInt($("counttime").innerText);
	var newtime = curtime - 1;
	if(newtime==0) location.href=Cookie.getCookie("srcUrl");
	else{
		$("counttime").innerText = newtime;
		setTimeout("goToMain()",1000);
	}
	
}