var pRecom={
	
	recomProductArr:new Array(),
	
	init:function(){
		if(Cookie.getCookie("userNick")=="") {
			goAuthorize("recommend.html");
			return;
		}
		pRecom.getRecommendProduct();
	},
	
	drawPage:function(){
		
		var tmphtml = "";
		if(pRecom.recomProductArr.length == 0){
			
		}
		else{
			tmphtml = '<table class="bought-table">';
			for(var i = 0;i< pRecom.recomProductArr.length;i ++){
				var tmpitem = pRecom.recomProductArr[i];
				var itemurl = tmpitem.detailUrl;
				var thumpicurl = tmpitem.picUrl+Constants.thumbSuffix;
				
				tmphtml += '<tr id="item'+tmpitem.numIid+'" class="order-bd last" onclick="pFavorite.drawChart('+i+')">'
								+'<td class="baobei" colspan="2"><a target="_blank" hidefocus="true" title="查看宝贝详情" href="'+itemurl+'" class="pic s50"><img alt="查看宝贝详情" src="'+thumpicurl+'"></a>'
									+'<span class="desc"><a class="baobei-name" target="_blank" href="#">'+tmpitem.title+'</a></span></td>'
//								+'<td class="spec">'+tmpitem.nick+'</td>'
								+'<td><div class="spec"><span>'+tmpitem.nick+'</span></div></td>'
								+'<td class="price">价格:'+tmpitem.price+'</td>'
								+'<td class="amount" rowspan><span style="font-size:14px;font-style:bold;color:grey">上架时间</span><span>'+convertTimeFormat(tmpitem.delistTime)+'</span></td>'
								;			
			}
			tmphtml += "</table>";
		}
		$("lowerpart_display").innerHTML = tmphtml;
		
	},
	
	getRecommendProduct:function(){
		var msg = "userNick="+Cookie.getCookie("userNick");
		getdata("recommend",msg,function(xmlHttp){
			if(xmlHttp.readyState ==4 && xmlHttp.status == 200){
				var res = xmlHttp.responseText;
				
				var jsonObj = myeval(res);
				for(var index in jsonObj){
					if(jsonObj[index]){
						pRecom.recomProductArr.push(jsonObj[index]);
					}
				}
				pRecom.drawPage();
				
			}
		});
	},

};
pRecom.init();