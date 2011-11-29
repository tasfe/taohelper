
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
		
		if(pHistory.curpage==0){//金额统计
		
			pHistory.goAmountStat();	
		}
		else if(pHistory.curpage == 1){//类型分析
			
			pHistory.goCategoryAnalysis();
		}
		else if(pHistory.curpage == 2){//消费上限设定
			
			pHistory.goSetConsumptionLimit();
			
		}
	},
	
	
	//金额统计
	goAmountStat:function(){
		//Cookie.addCookie("sessionKey","4112931d4fc320a46ec32e06d6b0bcb1d59009AEHO2g6bb2903040751");
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
	
		
	goSetConsumptionLimit:function(){
		var msg = "nick="+Cookie.getCookie("userNick")+"&method=getBudget";
		getdata("budget",msg,function(xmlHttp){
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				var res = xmlHttp.responseText;
				
				var jsonObj = myeval(res);
				var tmphtml="";
				if(jsonObj.budget==-1) {
					tmphtml="<div>You havn't set your budget for this month yet!</div>";
					tmphtml += "<div>Set here:"+"<input id='budget_input' type='text' style='width:200px;'/></div>";
					tmphtml += "<div><input type='button' value='Commit' onclick='pHistory.submitBudget()'/></div>";
				}
				else{
					tmphtml="Set you limit here:";
				}
				
				$("th_chart_container").innerHTML = tmphtml;
			}
			
		});
		
		
		//$("th_chart_container").innerHTML = tmphtml;
	},
	
	submitBudget:function(){
		var budgetNum = $('budget_input').value;
		if(isNaN(budgetNum)){
			alert("请输入合法的数字");
			return;
		}
	}
};

pHistory.init();



