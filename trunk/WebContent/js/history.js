
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
		
		var myData = new Array([10, 20], [15, 10], [20, 30], [25, 10], [30, 5]);
		var myChart = new JSChart("th_chart_container", "line");
		myChart.setDataArray(myData);
		myChart.draw();
	},
	
	goCategoryAnalysis:function(){
		var myData = new Array(['unit', 20], ['unit two', 10], ['unit three', 30],['other unit', 10], ['last unit', 30]);
		var myChart = new JSChart("th_chart_container", "bar");
		myChart.setDataArray(myData);
		myChart.draw();
	},
	
		
	goSetConsumptionLimit:function(){
		
		var tmphtml="Set you limit here:";
		
		$("th_chart_container").innerHTML = tmphtml;
	}
};

pHistory.init();



