var pFavorite={
	curpage:0, // 0 means favorite products
	productArr:new Array(),
	priceTrendArr:new Array(),
	favor_item_index:0,
	favor_item_num:3,
	
	init:function(){
		if(Cookie.getCookie("userNick")=="") {
			goAuthorize("favorite.html");
			return;
		}
		
		pFavorite.getFavoriteProduct();
		
	},
	drawPage:function(){
		var tmphtmlUpper="";
//		tmphtmlUpper = '<div class="leftarrow"><img src="img/leftarrow.png" style="width:30px;height:30px"></img></div>'
//						+'<div class="rightarrow"><img src="img/rightarrow.png" style="width:30px;height:30px"></img></div>';
		if(pFavorite.curpage==0){
//			pFavorite.favor_item_index=0;
			tmphtmlUpper = pFavorite.drawFavorItemHtml();		
		}
		
		
		$("upperpart_display").innerHTML = tmphtmlUpper;
		$("left_arrow").style.visibility="visible";
		$("right_arrow").style.visibility="visible";
	},
	
	drawFavorItemHtml:function(){
		var tmphtml = '<table class="bought-table">';
			for(var i=pFavorite.favor_item_index;i<pFavorite.productArr.length&&i<pFavorite.favor_item_index+pFavorite.favor_item_num;i++){
				var tmpitem = pFavorite.productArr[i];
				var itemurl = Constants.itemBaseUrl+tmpitem.numIid;
				var thumpicurl = tmpitem.itemImgs[0].url+Constants.thumbSuffix;
				
				tmphtml += '<tr id="item'+tmpitem.numIid+'" class="order-bd last" onclick="pFavorite.drawChart('+i+')">'
								+'<td class="baobei" colspan="2"><a target="_blank" hidefocus="true" title="查看宝贝详情" href="'+itemurl+'" class="pic s50"><img alt="查看宝贝详情" src="'+thumpicurl+'"></a>'
									+'<span class="desc"><a style="font-size:14px" target="_blank" href="'+itemurl+'">'+tmpitem.title+'</a></span></td>'
//								+'<td class="spec">'+tmpitem.nick+'</td>'
								+'<td><div class="spec"><span>'+tmpitem.nick+'</span></div></td>'
								+'<td class="price">价格:'+tmpitem.price+'</td>'
								+'<td class="amount" rowspan><span style="font-size:14px;font-style:bold;color:grey">上架时间</span><span>'+convertTimeFormat(tmpitem.created)+'</span></td>'
								;					
			}		
		tmphtml += "</table>";
		return tmphtml;
	},
	
	goLeft:function(){
		if(pFavorite.favor_item_index<3){
			$("left_arrow").style.visibility="hidden";
		}
		else{
			pFavorite.favor_item_index -= 3;
			pFavorite.drawPage();
		}
	},
	
	goRight:function(){
		if(pFavorite.favor_item_index+3>pFavorite.productArr.length){
			$("right_arrow").style.visibility="hidden";
		}
		else{
			pFavorite.favor_item_index += 3;
			pFavorite.drawPage();
		}
	},
	
	/**
	 * 获取用户收藏的商品列表
	 */
	
	getFavoriteProduct:function(){
		var sessionKey = Cookie.getCookie("sessionKey");
		if(sessionKey==""){
			goAuthorize("favorite.html");
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
			pFavorite.writeRecord();
			pFavorite.writePreference();
			pFavorite.getPriceTrend();
		});
				
	},
	
	/**
	 * 在后台的数据库中记录所收藏商品的价格
	 */
	 writeRecord:function(){
	 	for(var i=0;i<pFavorite.productArr.length;i++){
	 		var tmpitem = pFavorite.productArr[i];
	 		var judge=(Cookie.getCookie("price"+tmpitem.numIid)=="");
	 		if(judge)
	 		{
	 			Cookie.addCookie("price"+tmpitem.numIid,1,365*24);
	 			var msg="item_id="+tmpitem.numIid+"&price="+tmpitem.price+"&method=createFavoritePrice";
	 			getdata("favoriteitem",msg,function(xmlHttp){
	 				if(xmlHttp.readyState==4&&xmlHttp.status==200){
	 					
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
			goAuthorize("favorite.html");
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
	  		if(priceData.length == 0){
	  			 $("th_chart_container").innerHTML = "无该商品的价格信息!";
	  			 return;
	  		}
	  		else if(priceData.length == 1){
	  			 $("th_chart_container").innerHTML = "该商品的价格信息不足,请一天之后查看!";
	  			 return;
	  		}
			priceData.sort(function(a,b){if (a[0]==b[0]) return 0; return (a[0]>b[0])?1:-1;});
			
			
			var myChart = new JSChart("th_chart_container", "line");
			myChart.setDataArray(priceData);
			
			var changeflag=0;
			
			for (var i = 0; i < priceData.length; i++) {
				myChart.setTooltip([priceData[i][0], "价格 "+priceData[i][1] + "元"])
				changeflag += priceData[i][1]-priceData[0][1];
			}
			
			if(changeflag == 0)
			{
				myChart.setIntervalStartY(0);
			}
			
			myChart.setAxisPaddingLeft(100);
			myChart.setAxisPaddingRight(120);
			myChart.setAxisPaddingBottom(50);
			
			myChart.setSize(840,550);
			myChart.setTitle(name+'价格变化趋势');
			myChart.setAxisNameX("日期");
			myChart.setAxisNameY('价格');
//			myChart.setBackgroundImage('img/chart_bg.jpg');
			myChart.draw();
	  		
	  	}
	  	
	  },
	/**
	 * 在preference中增加记录
	 */
	writePreference:function(){
		for(var i=0;i<pFavorite.productArr.length;i++){
	 		var tmpitem = pFavorite.productArr[i];

	 		if(true)
	 		{
	 			var msg="cid="+tmpitem.cid+"&userNick="+Cookie.getCookie("userNick");
	 			getdata("preference",msg,function(xmlHttp){
	 				if(xmlHttp.readyState==4&&xmlHttp.status==200){
	 					
	 				}
	 			});
	 		}	 		
	 		
	 	}
	},
};

pFavorite.init();