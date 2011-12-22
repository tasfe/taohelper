
var pHistory={	
	curpage : 0, //the index of sub page. 0 means amount statistics, 1 means category analysis, 2 means limit set
	budgetData : new Array(),
	
	init:function(){
		if(Cookie.getCookie("userNick")=="") {
			goAuthorize("history.html");
			return;
		}
		
		pHistory.drawPage();
		
	},
	
	drawPage:function(){
		
		if(pHistory.curpage==0){//金额
		
			pHistory.getAllBudgets();	
		}
		else if(pHistory.curpage == 1){//类别
			
			pHistory.goCategoryAnalysis();
		}
		else if(pHistory.curpage == 2){//预算
			
			pHistory.goSetConsumptionLimit();
			
		}
	},
	
	getAllBudgets:function(){
		var msg = "userNick="+Cookie.getCookie("userNick")+"&method=getAllBudgets";
		getdata("budget",msg,function(xmlHttp){
			if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
				var res = xmlHttp.responseText;
				
				var jsonObj = myeval(res);
				for(var month in jsonObj){
					if(month){
						pHistory.budgetData.push([month,jsonObj[month]]);
					}
				}
				pHistory.goAmountStat();
			}
		});
	},
	
	//金额
	goAmountStat:function(){
		//Cookie.addCookie("sessionKey","4112931d4fc320a46ec32e06d6b0bcb1d59009AEHO2g6bb2903040751");
		pHistory.setSubmenuHover("Layer6");
		
		var sessionKey=Cookie.getCookie("sessionKey");
		if(sessionKey==""){
			goAuthorize("history.html");
			return;
		}
		var msg="sessionKey="+sessionKey;
		getdata("moneyinmonth",msg,function(xmlHttp){
			if(xmlHttp.readyState == 4 && xmlHttp.status ==200){
				var res = xmlHttp.responseText;
				var jsonObj = myeval(res);
				var hascurrentmonth=false;
				var moneyData = new Array();
				for(var i in jsonObj){
					if(jsonObj[i]){
						if(i==(new Date().getMonth()+1)) hascurrentmonth=true;
						moneyData.push([i,jsonObj[i]]);
						
					}
				}
				var recordMoneyData = new Array();
				for(var i=0;i<moneyData.length;i++){
					recordMoneyData.push([moneyData[i][0],moneyData[i][1]]);
				}
				if(!hascurrentmonth) moneyData.push([new Date().getMonth()+1,0]);
				
				var myChart = new JSChart("th_chart_container", "line");
				myChart.setDataArray(moneyData,'line_1');
				for (var i = 0; i < moneyData.length; i++) {
						myChart.setTooltip([moneyData[i][0], "本月消费 "+moneyData[i][1] + "元","line_1"]);
				}
				var tmpBudgetData = new Array();
				//以moneydata为标准对budgetdata进行校正
				for(var i=0;i<recordMoneyData.length;i++){
					if(recordMoneyData[i][0]==pHistory.budgetData[i][0]){
						tmpBudgetData.push(pHistory.budgetData[i]);
						continue;
					}
					else
					{
						tmpBudgetData.push([recordMoneyData[i][0],pHistory.getBudgetByMonth(recordMoneyData[i][0])]);
					}
				}
				
				myChart.setDataArray(tmpBudgetData,'line_2');
				myChart.setLineColor('#FF0000', 'line_2');
				
				for (var i = 0; i < tmpBudgetData.length; i++) {
					if(tmpBudgetData[i][1]>0)myChart.setTooltip([tmpBudgetData[i][0], "本月预算 "+tmpBudgetData[i][1] + "元","line_2"]);
					else myChart.setTooltip([tmpBudgetData[i][0], "本月预算尚未设置 ","line_2"]);
				}			
				
				myChart.setSize(840,550);
				
				myChart.setAxisPaddingLeft(70);
				myChart.setAxisPaddingRight(70);
				myChart.setAxisPaddingBottom(40);
				
				myChart.setTitle('每月消费金额统计');
				myChart.setAxisNameX("月份");
				myChart.setAxisNameY('消费金额');
				myChart.draw();
			}
		});
		
	},
	
	//
	getBudgetByMonth:function(month){
		for(var i=0;i<pHistory.budgetData.length;i++){
			if(pHistory.budgetData[i][0]==month) return pHistory.budgetData[i][1];
		}
		return 0;
	},
	
	//类别
	goCategoryAnalysis:function(){
		pHistory.setSubmenuHover("Layer7");
		$("th_chart_container").innerHTML="Loading graph....";
		var sessionKey=Cookie.getCookie("sessionKey");
		if(sessionKey==""){
			goAuthorize("history.html");
			return;
		}
		var msg="sessionKey="+sessionKey;
		getdata("moneyincat",msg,function(xmlHttp){
			if(xmlHttp.readyState == 4 && xmlHttp.status ==200){
				var res = xmlHttp.responseText;
				var jsonObj = myeval(res);
				
				var catData = new Array();
				for(var cat in jsonObj){
					if(jsonObj[cat]){
						
						catData.push([strCut(cat,8),jsonObj[cat]]);
					}
				}
				
				var myChart = new JSChart("th_chart_container", "bar");
				myChart.setDataArray(catData);
				myChart.setSize(840,550);
				myChart.setAxisWidth(1);
				myChart.setAxisPaddingLeft(70);
				myChart.setAxisPaddingRight(70);
				myChart.setAxisPaddingBottom(40);
				myChart.setTitle('消费类型统计');
				myChart.setAxisNameX("类型");
				myChart.setAxisNameY('消费金额');
				myChart.draw();
			}
		});
		
	},
	
	//预算	
	goSetConsumptionLimit:function(){
		pHistory.setSubmenuHover("Layer8");
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
	
	setSubmenuHover:function(id){
		$("Layer6").style.backgroundImage="";
		$("Layer7").style.backgroundImage="";
		$("Layer8").style.backgroundImage="";
		
		$(id).style.backgroundImage="url('img/submenu1.png')";
		$(id).style.backgroundSize="200px,70px";
		$(id).style.backgroundRepeat="no-repeat center";
		
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



