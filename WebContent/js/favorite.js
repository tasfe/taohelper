var pFavorite={
	curpage:0, // 0 means favorite products
	productArr:new Array(),
	priceTrendArr:new Array(),
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
		if(pFavorite.curpage==0){
			tmphtmlUpper = '<table class="bought-table">';
			for(var i=0;i<pFavorite.productArr.length;i++){
				var tmpitem = pFavorite.productArr[i];
				var itemurl = pFavorite.itemBaseUrl+tmpitem.numIid;
				var thumpicurl = tmpitem.itemImgs[0].url+pFavorite.thumbSuffix;
				
				tmphtmlUpper += '<tr id="item'+tmpitem.numIid+'" class="order-bd last" onclick="pFavorite.drawChart('+i+')">'
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
	
	/**
	 * 获取用户收藏的商品列表
	 */
	
	getFavoriteProduct:function(){
		var sessionKey = Cookie.getCookie("sessionKey");
		if(sessionKey==""){
			goGetSessionKey();
			return;
		}
		var msg="sessionKey="+sessionKey+"&nick="+Cookie.getCookie("userNick")+"&method=getFavorite";
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
//			pFavorite.writeRecord();
			pFavorite.getPriceTrend();
		});
				
	},
	
	/**
	 * 在后台的数据库中记录所收藏商品的价格，并且在preference中增加记录
	 */
	 writeRecord:function(){
	 	for(var i=0;i<pFavorite.productArr.length;i++){
	 		var tmpitem = pFavorite.productArr[i];
	 		var judge=(Cookie.getCookie("price"+tmpitem.numIid)=="");
	 		if(true)
	 		{
	 			var msg="item_id="+tmpitem.numIid+"&price="+tmpitem.price+"&method=createFavoritePrice";
	 			getdata("favoriteitem",msg,function(xmlHttp){
	 				if(xmlHttp.readyState==4&&xmlHttp.status==200){
	 					//Cookie.addCookie("price"+tmpitem.numIid,1,0);
	 				}
	 			});
	 		}	 		
	 		
	 	}
	 	
	 },
	 
	 /**
	  * get price trend of the favorite products
	  */
	  getPriceTrend:function(){
	  	var sessionKey = Cookie.getCookie("sessionKey");
		if(sessionKey==""){
			goGetSessionKey();
			return;
		}
		var msg="sessionKey="+sessionKey+"&userNick="+Cookie.getCookie("userNick");
		getdata("pricetrend",msg,function(xmlHttp){
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				var res = xmlHttp.responseText;
				var jsonobj = myeval(res);
				for(var index in jsonobj){
					if(jsonobj[index]!=null){
						pFavorite.priceTrendArr.push(jsonobj[index]);
					}
				}
				
			}
			pFavorite.drawChart(0);
		});
	  },
	  
	  drawChart:function(index){
	  	var product = pFavorite.productArr[index];
	  	var numIid = product.numIid;
	  	var name = product.title;
	  	
	  	if(pFavorite.priceTrendArr.length>0){	  		
	  		
	  		var hisprice = pFavorite.priceTrendArr[index].historyPrice;
	  		var priceData = new Array();
	  		for(var date in hisprice){
	  			if(hisprice[date]){
	  				priceData.push([date,hisprice[date]]);
	  			}
	  		}  			
				
			var myChart = new JSChart("th_chart_container", "line");
			myChart.setDataArray(priceData);
			myChart.setSize(840,550);
			myChart.setTitle(name+'价格变化趋势');
			myChart.setAxisNameX("日期");
			myChart.setAxisNameY('价格');
			myChart.draw();
	  		
	  	}
	  },
};

pFavorite.init();