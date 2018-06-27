var id = Liferay.ThemeDisplay.getUserId();

function getMessages(url){
	sendRequest(url,function(data){
		clearMessages();
		var messages = data.messages;
		for(var i=0;i<data.messages.length;i++){
			if(messages[i].senderId == id){
				addMessageAsSender('messages',messages[i].message);
			}else{
				addMessageAsReceiver('messages',messages[i].message);
			}
		}
	});
}

function addMessageAsSender(id,message){
	var row = document.createElement("tr");
	var col = document.createElement("td");
	var span = document.createElement("span");
	span.classList.add("pull-right");
	span.innerHTML = message;
	col.appendChild(span);
	row.appendChild(col);
	var element = document.getElementById(id);
	element.querySelector('table tbody').appendChild(row);
}

function addMessageAsReceiver(id,message){
	var row = document.createElement("tr");
	var col = document.createElement("td");
	var span = document.createElement("span");
	span.classList.add("pull-left");
	span.innerHTML = message;
	col.appendChild(span);
	row.appendChild(col);
	var element = document.getElementById(id);
	element.querySelector('table tbody').appendChild(row);
}

function clearMessages(){
	jQuery('#messages table tbody').html('');
}