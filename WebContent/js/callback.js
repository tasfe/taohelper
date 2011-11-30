var userNick = Request.QueryString("taobao_user_nick");
if(userNick&&userNick!=""){
	Cookie.addCookie("userNick",userNick,0);
	
	var tmphtml = "<div>You have successfully been authorized!</div>"
					+"<div style='width:100%;text-align:center'><div onclick='self.close();' class='button_div'>Close</div></div>";
	//alert("authorize successfully!");
	$("msg_display").innerHTML = tmphtml;
}
var session = Request.QueryString("session");
if(session&&session!=""){
	Cookie.addCookie("sessionKey",session,0);
}
session = Request.QueryString("top_session");
if(session && session!=""){
	Cookie.addCookie("sessionKey",session,0);
	//alert("session ��ȡ�ɹ�!"+session);
}