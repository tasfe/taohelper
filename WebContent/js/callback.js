var userNick = Request.QueryString("taobao_user_nick");
if(userNick&&userNick!=""){
	Cookie.addCookie("userNick",userNick,0);
	
	var tmphtml = "<div>You have successfully been authorized!</div>"
					+"<div onclick='self.close();'>Click here to close window</div>";
	//alert("authorize successfully!");
}
var session = Request.QueryString("session");
if(session&&session!=""){
	Cookie.addCookie("sessionKey",session,0);
}
session = Request.QueryString("top_session");
if(session && session!=""){
	Cookie.addCookie("sessionKey",session,0);
	//alert("session 获取成功!"+session);
}