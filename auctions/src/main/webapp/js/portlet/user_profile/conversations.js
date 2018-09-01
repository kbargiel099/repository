var id = Liferay.ThemeDisplay.getUserId();

function getMessages(url){
	sendRequest(url,function(data){
		clearMessages();
		var messages = data.messages;
		for(var i=0;i<data.messages.length;i++){
			if(messages[i].senderId == id){
				addMessageSender('messages',messages[i]);
			}else{
				addMessageReceiver('messages',messages[i]);
			}
		}
		let elem = document.getElementById('messages');
		elem.scrollTo(0, elem.scrollHeight);
	});
}

function addMessageSender(id, value){
	var row = document.createElement("tr");
	var col = document.createElement("td");
	
	var divDate = document.createElement("div");
	divDate.classList.add('pull-left');
	divDate.style.padding = '10px';
	var spanDate = document.createElement("span");
	spanDate.style.fontSize = '12px';
	spanDate.innerHTML = getDate(value.createDate);
	divDate.appendChild(spanDate);
	col.appendChild(divDate);
	
	var div = document.createElement("div");
	div.style.backgroundColor = '#80bfff';
	div.style.maxWidth = '60%';
	div.style.float = 'right';
	div.style.borderRadius = '10px';
	div.style.minHeight = '30px';
	div.style.textAlign = 'center';
	div.style.wordWrap = 'break-word';
	div.style.padding = '10px';
	var span = document.createElement("span");
	span.style.color = 'white';
	span.innerHTML = value.message;
	div.appendChild(span);
	col.appendChild(div);
	row.appendChild(col);
	var element = document.getElementById(id);
	element.querySelector('table tbody').appendChild(row);
}

function addMessageReceiver(id, value){
	var row = document.createElement("tr");
	var col = document.createElement("td");
	
	var divDate = document.createElement("div");
	divDate.classList.add('pull-right');
	divDate.style.padding = '10px';
	var spanDate = document.createElement("span");
	spanDate.style.fontSize = '12px';
	spanDate.innerHTML = getDate(value.createDate);
	divDate.appendChild(spanDate);
	col.appendChild(divDate);
	
	var div = document.createElement("div");
	div.style.backgroundColor = '#a6a6a6';
	div.style.maxWidth = '60%';
	div.style.float = 'left';
	div.style.borderRadius = '10px';
	div.style.minHeight = '30px';
	div.style.textAlign = 'center';
	div.style.wordWrap = 'break-word';
	div.style.padding = '10px';
	var span = document.createElement("span");
	span.style.color = 'white';
	span.innerHTML = value.message;
	div.appendChild(span);
	col.appendChild(div);
	row.appendChild(col);
	var element = document.getElementById(id);
	element.querySelector('table tbody').appendChild(row);
}

function getDate(date){
	let d = date != null ? new Date(date) : new Date();
	let day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate();
	let month = (d.getMonth()+1) < 10 ? '0' + (d.getMonth()+1) : (d.getMonth()+1);
	let hours = d.getHours() < 10 ? '0' + d.getHours() : d.getHours();
	let min = d.getMinutes() < 10 ? '0' + d.getMinutes() : d.getMinutes();
	return day + '-' + month + ' ' + hours + ':' + min;
}

function clearMessages(){
	jQuery('#messages table tbody').html('');
}