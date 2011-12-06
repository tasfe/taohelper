var pCurrent={	
	curpage : 0,
	curTradeArr:new Array(),
	
	init:function(){
		if(Cookie.getCookie("userNick")=="") {
			goAuthorize();
			return;
		}
		
		pCurrent.getCurrentTrades();
	},
	
	drawPage:function(){
		var tmphtmlUpper="";
		
		if(pCurrent.curTradeArr.length==0){
			tmphtmlUpper ="<div style='text-align:center;width:100%;'>您当前没有正在进行的交易！</div>";
		}
		else{		
			tmphtmlUpper = '<table class="bought-table">';
			for(var i=0;i<pCurrent.curTradeArr.length;i++){
				var tmpitem = pCurrent.curTradeArr[i];
				var itemurl = Constants.itemBaseUrl+tmpitem.numIid;
				var thumpicurl = tmpitem.picPath+Constants.thumbSuffix;
				
				tmphtmlUpper += '<tr id="item'+tmpitem.numIid+'" class="order-bd last" onclick="pCurrent.getLosgisticsInfo('+i+')">'
								+'<td class="baobei" colspan="2"><a target="_blank" hidefocus="true" title="查看宝贝详情" href="'+itemurl+'" class="pic s50"><img alt="查看宝贝详情" src="'+thumpicurl+'"></a>'
									+'<span class="desc"><a class="baobei-name" target="_blank" href="#">'+tmpitem.title+'</a></span></td>'
	//								+'<td class="spec">'+tmpitem.nick+'</td>'
								+'<td><div class="spec"><span>'+tmpitem.title+'</span></div></td>'
								+'<td class="price">价格:'+tmpitem.payment+'</td>'
								+'<td class="price">运费:'+tmpitem.postFee+'</td>'
	//							+'<td class="amount" rowspan><span style="font-size:14px;font-style:bold;color:grey">上架时间</span><span>'+convertTimeFormat(tmpitem.created)+'</span></td>'
								;					
			}		
			tmphtmlUpper += "</table>";
		}
			
		$("upperpart_display").innerHTML = tmphtmlUpper;
	},
	
	getCurrentTrades:function(){
		var sessionKey=Cookie.getCookie("sessionKey");
		if(sessionKey==""){
			goAuthorize();
			return;
		}
		var msg="sessionKey="+sessionKey;
		getdata("currenttrade",msg,function(xmlHttp){
			if(xmlHttp.readyState == 4 && xmlHttp.status ==200){
				var res = xmlHttp.responseText;
				var jsonObj = myeval(res);
				for(var index in jsonObj)
				{
					if(jsonObj[index]){
						pCurrent.curTradeArr.push(jsonObj[index]);
					}
				}
				pCurrent.drawPage();
				pCurrent.getLosgisticsInfo(0);
//				alert(jsonObj.length);
			}
		});
	},
	
	writePreference:function(){
		
	},
	
	getLosgisticsInfo:function(index){
		if(pCurrent.curTradeArr.length>0&&pCurrent.curTradeArr[index]){
			var tmpitem = pCurrent.curTradeArr[index];
			
			var msg = "sellerNick="+tmpitem.sellerNick+"&tid="+tmpitem.tid;
			getdata("logistics",msg,function(xmlHttp){
				if(xmlHttp.readyState ==4 && xmlHttp.status == 200){
					var res = xmlHttp.responseText;
					
					var tmphtml = '<div class="logis_info">';
					if(res == Constants.MSG_FAIL){
						tmphtml += '当前货物尚无物流信息.';
					}
					else{
						var jsonObj = myeval(res);
						var company = jsonObj.company;
						var transitionInfo = jsonObj.transitStepInfo;
						
						tmphtml += '<div class="title">物流信息</div>';
						tmphtml += '<div style="margin-left:35px">';
						tmphtml += '<span class="logis_dt">物流公司:</span>'+company+'<br/>';
						tmphtml += '<span class="logis_dt">运单号码:</span>'+jsonObj.outId+'<br/>';
						tmphtml += '<span class="logis_dt">物流跟踪:</span><br/><div style="margin-left:35px;">';
						for(var i=0;i<transitionInfo.length;i++){
							tmphtml += transitionInfo[i].statusTime+' '+transitionInfo[i].statusDesc+'<br/>';
						}
						tmphtml += '</div></div>';
					}
					tmphtml += '</div>';	
					
					$("lowerpart_display").innerHTML = tmphtml;
				}
			});
		}
		else {
			$("lowerpart_display").innerHTML = "<div style='text-align:center;width:100%'>您当前没有正在进行的交易！</div>";
		}
		
	}
}

pCurrent.init();