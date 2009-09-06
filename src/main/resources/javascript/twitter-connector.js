var yqlQueryUrl = 'http://query.yahooapis.com/v1/public/yql?q=';
var base64Dir = 'http://confluence.atlassian.com/download/attachments/148766904/base64.js';
var opendataDir = 'http://confluence.atlassian.com/download/attachments/148766904/';
var confluenceStatusUpdateUrl = "/plugins/servlet/userstatusupdate-confluence"
var temp="";
function getTwitterUpdates(flag, username, password, count, element)
{
	temp = "";
    var tableName="use '"+opendataDir+"twitter.friends.timeline.xml"+"' as friendsUpdates;";
	var	query= tableName+"select * from friendsUpdates where username='"+username+"' and password='"+password+"' and count="+count;
	temp += yqlQueryUrl + query +"&format=json&callback=?";
    element = "#"+element;
	$.getJSON(temp, function(data){
			var results = data.query.results.result;
			var outputText = "<table>";
			var objectJson;
			if(typeof results != "[objects]")
			{
				objectJson = eval(results);
			}
			else
			{
				objectJson = results;
			}
			for(var i=0; i<objectJson.length; i++)
			{
				outputText += "<tr><td><img src='"+
				objectJson[i].user.profile_image_url+"'/></td><td>"+objectJson[i].user.name+"<br>"+objectJson[i].text+"</td></tr>";
			}
            outputText += "</table>";
            jQuery(element).html(outputText);
		});
}
function setTwitterUpdates(username, password, status, targetElement, confluenceUsername, confluenceStatusFlag)
{
	temp = "";
	var tableName="use '"+opendataDir+"twitter.user.status.xml' as myUpdate; ";
	var query = tableName + "insert into myUpdate(username, password, status) values('"+username+"','"+password+"','"+status+"')";
	temp += yqlQueryUrl + query + "&callback=?";
	jQuery.getJSON(temp, function(json){
		var successFlag = json.query.created;
		jQuery("#tempVariable",document).html(successFlag);
		checkedInsertedData();
	});
    if(confluenceStatusFlag == 1)
    {
        setConfluenceStatus(confluenceUsername, status);
    }
}
function setConfluenceStatus(username, status)
{
    var urlUpdateConfluenceStatus = confluenceStatusUpdateUrl+"?username="+username+"&status="+status;
    jQuery.getJSON(urlUpdateConfluenceStatus, function(successflag){
    });
}
function checkedInsertedData()
{
	var tempTargetValue = jQuery("#tempVariable",document).html();
	if(tempTargetValue == "")
	{
		setTimeout("checkedInsertedData()", 100);
	}
	else
	{
		jQuery("#tempVariable",document).html("");
		jQuery("#temporaryOutput",document).html("");
		popupHtml('/plugins/servlet/socialnetworking-twitter')
	}
}
function checkFetchedData()
{
	var tempTargetValue = jQuery("#grabbedHtml",document).html();
	if(tempTargetValue == "")
	{
		setTimeout("checkFetchedData()", 100);
	}
	else{
		var updateFormNTwitterText = jQuery("#boxy-twitter").html();
		updateFormNTwitterText += jQuery("#grabbedHtml").html();
		jQuery.facebox(updateFormNTwitterText);
		jQuery("#grabbedHtml",document).html("");
	}
}
function popupHtml(file)
{
    alert('tester');
	jQuery.facebox(function(){
		jQuery.get(file, function(data) {
			var textData = jQuery(data).appendTo("#temporaryOutput");
			checkFetchedData();
		});
	});
}