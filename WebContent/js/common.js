
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
	MSG_FAIL:"msg_fail",
	
	itemBaseUrl:"http://item.taobao.com/item.htm?id=",
	thumbSuffix:"_sum.jpg"
};

Common = {
	srcUrl : ""	,
	logOut : function(){
		Cookie.delCookie("userNick");
		Cookie.delCookie("sessionKey");
		window.location.reload();
	}
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
function parseDate(str){     
  if(typeof str == 'string'){     
    var results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);     
    if(results && results.length>3)     
      return new Date(parseInt(results[1]),parseInt(results[2]) -1,parseInt(results[3]));      
    results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);     
    if(results && results.length>6)     
      return new Date(parseInt(results[1]),parseInt(results[2]) -1,parseInt(results[3]),parseInt(results[4]),parseInt(results[5]),parseInt(results[6]));      
    results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);     
    if(results && results.length>7)     
      return new Date(parseInt(results[1]),parseInt(results[2]) -1,parseInt(results[3]),parseInt(results[4]),parseInt(results[5]),parseInt(results[6]),parseInt(results[7]));      
  }     
  return null;     
}

function formatDate(v){     
   if(typeof v=='string') v = parseDate(v);     
  if(v instanceof Date){     
    var y = v.getFullYear();     
    var m = v.getMonth() + 1;     
    var d = v.getDate();     
    var h = v.getHours();     
    var i = v.getMinutes();     
    var s = v.getSeconds();     
    var ms = v.getMilliseconds();        
    if(ms>0) return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s + '.' + ms;     
    if(h>0 || i>0 || s>0) return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s;     
    return m + '-' + d;     
  }     
  return '';     
}   

function strCut(str,len){
	var tmpstr="";
	if(str.length>len) tmpstr = str.substr(0,len);
	else tmpstr=str;
	return tmpstr;
}

function goAuthorize(src_url){
	Cookie.addCookie("srcUrl",src_url,0.05);
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
	window.open(url,"taobao-authorize","width=60,height=60");
}