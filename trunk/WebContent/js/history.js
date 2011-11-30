
var pHistory={	
	curpage : 0, //the index of sub page. 0 means amount statistics, 1 means category analysis, 2 means limit set
	
	init:function(){
		if(Cookie.getCookie("userNick")=="") {
			goAuthorize();
			return;
		}
		
		pHistory.drawPage();
		
	},
	
	drawPage:function(){
		
		if(pHistory.curpage==0){//金额
		
			pHistory.goAmountStat();	
		}
		else if(pHistory.curpage == 1){//类别
			
			pHistory.goCategoryAnalysis();
		}
		else if(pHistory.curpage == 2){//预算
			
			pHistory.goSetConsumptionLimit();
			
		}
	},
	
	
	//金额
	goAmountStat:function(){
		//Cookie.addCookie("sessionKey","4112931d4fc320a46ec32e06d6b0bcb1d59009AEHO2g6bb2903040751");
		$("Layer6").style.background="url('img/submenu_hover.jpg')";
		$("Layer6").style.backgroundSize="200px,100px";
		var msg="sessionKey="+Cookie.getCookie("sessionKey");
		getdata("moneyinmonth",msg,function(xmlHttp){
			if(xmlHttp.readyState == 4 && xmlHttp.status ==200){
				var res = xmlHttp.responseText;
				var jsonObj = myeval(res);
				
				var moneyData = new Array();
				for(var i=1;i<=12;i++){
					if(jsonObj[i]){
						moneyData.push([i,jsonObj[i]]);
					}	
				}
				var myChart = new JSChart("th_chart_container", "line");
				myChart.setDataArray(moneyData);
				myChart.setSize(840,550);
				myChart.draw();
			}
		});
		
	},
	
	//类别
	goCategoryAnalysis:function(){
		$("th_chart_container").innerHTML="Loading graph....";
		var msg="sessionKey="+Cookie.getCookie("sessionKey");
		getdata("moneyincat",msg,function(xmlHttp){
			if(xmlHttp.readyState == 4 && xmlHttp.status ==200){
				var res = xmlHttp.responseText;
				var jsonObj = myeval(res);
				
				var catData = new Array();
				for(var cat in jsonObj){
					if(jsonObj[cat]){
						
						catData.push([cat,jsonObj[cat]]);
					}
				}
				
				var myChart = new JSChart("th_chart_container", "bar");
				myChart.setDataArray(catData);
				myChart.setSize(840,550);
				myChart.draw();
			}
		});
		
	},
	
	//预算	
	goSetConsumptionLimit:function(){
		var msg = "nick="+Cookie.getCookie("userNick")+"&method=getBudget";
		getdata("budget",msg,function(xmlHttp){
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				var res = xmlHttp.responseText;
				
				var jsonObj = myeval(res);
				var tmphtml="";
				if(jsonObj.budget==-1) {
					tmphtml="<div>您尚未为本月设置预算！</div>";
					tmphtml += "<div>设置本月预算:"+"<input id='budget_input' type='text' style='width:200px;'/></div>";
					tmphtml += "<div><input type='button' value='提交' onclick='pHistory.submitBudget()'/></div>";
				}
				else{
					tmphtml="<div>您本月的预算为: "+jsonObj.budget+"</div>";
					tmphtml += "<div>设置新的预算:"+"<input id='budget_input' type='text' style='width:200px;'/></div>";
					tmphtml += "<div><input type='button' value='提交' onclick='pHistory.submitBudget()'/></div>";
				}
				
				$("th_chart_container").innerHTML = tmphtml;
			}
			
		});
		
		
		//$("th_chart_container").innerHTML = tmphtml;
	},
	
	submitBudget:function(){
		var budgetNum = $('budget_input').value;
		if(isNaN(budgetNum)){
			alert("请输入合法的数字!");
			return;
		}
		var msg = "nick="+Cookie.getCookie("userNick")+"&method=createBudget&limit="+budgetNum;
		getdata("budget",msg,function(xmlHttp){
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				var res = xmlHttp.responseText;
				if(res == Constants.MSG_SUCCESS){
					alert("成功设置预算额度!");
					pHistory.goSetConsumptionLimit();
				}
				else alert("设置额度失败!");
			}
		});
	}
};

pHistory.init();



