var pFavorite={
	curpage:0, // 0 means favorite products
	productArr:new Array(),
	itemBaseUrl:"http://item.taobao.com/item.htm?id=",
	thumbSuffix:"_sum.jpg",
	
	init:function(){
		if(Cookie.getCookie("userNick")=="") {
			goAuthorize();
			return;
		}
		
		pFavorite.getFavoriteProduct();
		
	},
	drawPage:function(){
		var tmphtmlUpper="";
		var tmphtmlLower="";
		if(pFavorite.curpage==0){
			tmphtmlUpper = '<table class="bought-table">';
			for(var i=0;i<pFavorite.productArr.length;i++){
				var tmpitem = pFavorite.productArr[i];
				var itemurl = pFavorite.itemBaseUrl+tmpitem.numIid;
				var thumpicurl = tmpitem.itemImgs[0].url+pFavorite.thumbSuffix;
				
				tmphtmlUpper += '<tr id="item'+tmpitem.numIid+'" class="order-bd last">'
								+'<td class="baobei" colspan="2"><a target="_blank" hidefocus="true" title="查看宝贝详情" href="'+itemurl+'" class="pic s50"><img alt="查看宝贝详情" src="'+thumpicurl+'"></a>'
									+'<span class="desc"><a class="baobei-name" target="_blank" href="#">'+tmpitem.title+'</a></span></td>'
//								+'<td class="spec">'+tmpitem.nick+'</td>'
								+'<td><div class="spec"><span>'+tmpitem.nick+'</span></div></td>'
								+'<td class="price">价格:'+tmpitem.price+'</td>'
								+'<td class="amount" rowspan><span style="font-size:14px;font-style:bold;color:grey">上架时间</span><span>'+convertTimeFormat(tmpitem.created)+'</span></td>'
								;
			}					
								
			tmphtmlUpper += "</table>";
			
			
		}
		
		
		$("upperpart_display").innerHTML = tmphtmlUpper;
	},
	
	
	
	getFavoriteProduct:function(){
		var sessionKey = Cookie.getCookie("sessionKey");
		if(sessionKey==""){
			goGetSessionKey();
			return;
		}
		var msg="sessionKey="+sessionKey+"&nick="+Cookie.getCookie("userNick");
		getdata("favoriteitem",msg,function(xmlHttp){
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				var res = xmlHttp.responseText;
				var jsonobj = myeval(res);
				for(var index in jsonobj){
					if(jsonobj[index]!=null){
						pFavorite.productArr.push(jsonobj[index]);
					}
				}
				
			}
			pFavorite.drawPage();
		});
				
	},
};

pFavorite.init();