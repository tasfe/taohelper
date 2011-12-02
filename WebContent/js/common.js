
Request = {
    QueryString: function (item) {
        var svalue = location.search.match(new RegExp("[\?\&]" + item + "=([^\&]*)(\&?)", "i"));
        return svalue ? svalue[1] : svalue;
    }
};

Cookie = {
	
	
	addCookie:function(objName,objValue,objHours){//���cookie
	    var str = objName + "=" + escape(objValue);
	    if(objHours==0||null==objHours) objHours=365*24;
	    var date = new Date();
	    var ms = objHours*3600*1000;
	    date.setTime(date.getTime() + ms);
	    str += "; expires=" + date.toGMTString();    
	    document.cookie = str;
 	},

	getCookie:function(objName){//��ȡָ����Ƶ�cookie��ֵ
	    var arrStr = document.cookie.split("; ");
	    for(var i = 0;i < arrStr.length;i ++){
	     var temp = arrStr[i].split("=");
	     if(temp[0] == objName) return unescape(temp[1]);
	    }
	    return "";
	},

	 delCookie:function(name){//Ϊ��ɾ��ָ����Ƶ�cookie�����Խ������ʱ���趨Ϊһ���ȥ��ʱ��
		var date = new Date();
		date.setTime(date.getTime() - 10000);
		document.cookie = name + "=a; expires=" + date.toGMTString();
	}
};

Constants = {
	MSG_SUCCESS:"msg_success",
	MSG_FAIL:"msg_fail"
};

function getdata(url, msg, callback){
    var xmlHttp;
    if(window.ActiveXObject){
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }else if(window.XMLHttpRequest){
        xmlHttp=new XMLHttpRequest();
    }
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState==4){
            callback(xmlHttp); 
        }
    };
    xmlHttp.open("POST",url,true);
    xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
    xmlHttp.send(msg);
}

function myeval(str){
	return eval('('+str+')');
}

function $(name){
	return document.getElementById(name);
}

function convertTimeFormat(time_stamp)
{
    var myDate = new Date();
    var curTime=myDate.getTime();
    
    // time_stamp=time_stamp.replace(/-/g, "/")
    // var oldTime = new Date(time_stamp);
    var oldTime = convertTime(time_stamp);
    var oldTimeDate=oldTime.getFullYear()+"-"+(oldTime.getMonth()+1)+"-"+oldTime.getDate();
    var timeInterval=(curTime-oldTime.getTime())/1000/60;

    timeInterval = Math.floor(timeInterval);
    if(timeInterval<60)
    {
        if(timeInterval<=0) return "just now";
        else return timeInterval+" min"+(timeInterval==1?"":"s")+ " ago";
    }
    else
    {
        timeInterval = Math.floor(timeInterval/60);
        if(timeInterval<24) return timeInterval+" hour"+(timeInterval==1?"":"s")+ " ago";
        else
        {
            timeInterval = Math.floor(timeInterval/24);
            if(timeInterval<7) return timeInterval+" day"+(timeInterval==1?"":"s")+ " ago";
            else return oldTimeDate;
        }
    }   
}

function convertTime(datetime){
    var d = new Date();
    var gmtHours = -d.getTimezoneOffset()/60;
    var diffHours=8-gmtHours;
    datetime=new Date(datetime.replace(/-/g, "/"));
    var localTime=new Date(datetime.valueOf()-diffHours*60*60*1000);
    return localTime;
}

function goAuthorize(){
	var msg="";
	getdata("authorize",msg,function(xmlHttp){
		if(xmlHttp.readyState==4 && xmlHttp.status==200){
			var res = xmlHttp.responseText;
			
			if(res != ""){
				//window.open(res,"taobao-authorize","width=611,height=300");
				//res = unescape(res);
				location.href=res;
			}
		}
	});
}

function goGetSessionKey(){
	var url = "http://container.open.taobao.com/container?appkey=12390550&encode=utf-8";
	window.open(url,"taobao-authorize","width=0,height=0");
}