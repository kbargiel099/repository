			jQuery(document).ready(function(){
				if(Liferay.ThemeDisplay.isSignedIn()){
					connectForChat();
				}
			});        
            //this variable represents the total number of popups can be displayed according to the viewport width
            var total_popups = 0;
            var smallWidth = 640;
            var popups = [];
            var currentPopupId;
            var popupWidth =  window.innerWidth > smallWidth ? 320 : window.outerWidth;
            //var popupWidth = window.innerWidth > 640 ? 25 : 100;
        	var right = window.innerWidth < smallWidth ? 0 : 220;
            //var right = window.innerWidth < 640 ? 0 : 15;
            
            function close_popup(id)
            {
                for(var iii = 0; iii < popups.length; iii++)
                {   
	                if(id == popups[iii])
	                {
	                    Array.remove(popups, iii);
	                    document.getElementById(id).remove();
	                }
                }
            }
        
            function display_popups()
            {
                console.log("right " + right);
                console.log("popupWidth " + popupWidth);
                var iii = 0;
                for(iii; iii < total_popups; iii++)
                {
                    if(popups[iii] != undefined)
                    {
                        var element = document.getElementById(popups[iii]);
                        element.style.right = right + "px";
                        right = right + popupWidth;
                        element.style.display = "block";
                        console.log('show');
                    }
                }
                
                for(var jjj = iii; jjj < popups.length; jjj++)
                {
                    var element = document.getElementById(popups[jjj]);
                    element.style.display = "none";
                    console.log('hide');
                }
            }
            
            function register_popup(id, name, url)
            {
                close_popup(id);           
                
                console.log("dalszy ciąg");
                var element = '<div class="popup-box chat-popup" id="'+ id +'">';
                element = element + '<div class="popup-head">';
                element = element + '<div class="popup-head-left">'+ name +'</div>';
                element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ id +'\');">&#10005;</a></div>';
                element = element + '<div style="clear: both"></div></div><div class="popup-messages"><table><tbody></tbody></table></div>';
                element = element + '<div class="popup-inputs">';
                element = element + '<div class="pull-left col-xs-9"><input class="chat-input" type="text" id="send-message-input"></div>';
                element = element + '<div class="col-xs-2"><a class="chat-button" href="javascript:void(0);" id="send-message-button">Wyślij</a></div></div></div>';
                
                jQuery('body').append(element);
                popups.unshift(id);
                calculate_popups();
                
                console.log(popups);
                
                jQuery('#send-message-button').click(function(){
                	var message = jQuery('.chat-input').val();
                	sendForm(id,message);
                	addMessageAsSender(id,message);
                	jQuery('.chat-input').val("");
    				let elem = document.getElementsByClassName('popup-messages')[0];
    				elem.scrollTo(0, elem.scrollHeight);
                });
                currentPopupId = id;
                sendRequest(url,loadMessagesCallback);
            }
            
            var loadMessagesCallback = function(data){
    			if(JSON.parse(data.success) == true){
    				var messages = data.messages;
    				
    				for(var i=0;i<messages.length;i++){
    					console.log(messages[i]);
    					addMessageAsReceiver(currentPopupId,messages[i].message,messages[i].createDate);
    				}
    				if(messages.length > 0)
    					markMessagesAsRead(jQuery('#markMessagesAsReadUrl').val(),currentPopupId);
    				
    				let elem = document.getElementsByClassName('popup-messages')[0];
    				elem.scrollTo(0, elem.scrollHeight);
    			}
    			else
    				alert("Wystapil blad loadMessagesCallback");
            };
            
            function markMessagesAsRead(url,userId){
            	jQuery.ajax({
            		"url":url + '&userId=' + userId,
            		"type":"GET",
            		"success": function(data){
            			console.log(data);
            			if(JSON.parse(data.success) == true){
            				
            				var list = document.getElementById('notification-list');
            				var pp = document.getElementById(notificationListIdPrefix + userId);
            				list.removeChild(pp);
            				if(list.getElementsByTagName('li').length == 0){
            					var elem = document.createElement('li');
            					elem.id = 'no-message-elem';
            					elem.innerHTML = '<a href="javascript:void(0);">Brak nowych wiadomości</a>';
            					list.appendChild(elem);
            				}

            			}
            			else
            				alert("Wystapil blad markMessagesAsRead");
            		}
            	});
            }
            
            function connectForChat(){
                var socketForChat = new SockJS('http://192.168.0.15:8143/notification');
            	stompClientChat = Stomp.over(socketForChat);
            	stompClientChat.connect({}, function (frame) {
            		stompClientChat.subscribe('/message/' + userId, function (data) {
                    	var res = JSON.parse(data.body);
                    	console.log("Odpowiedz z serwera");
                    	console.log(res);
                    	if(res.success == true){
                    		
                			//senderClientChat = false;
                			//isWaitChat = false;
                			
                			for(var i=0;i<popups.length;i++){
                				if(popups[i] == res.senderId){
                        			addMessageAsReceiver(res.senderId,res.message,null);
                				}
                			}
                			
                			var isSenderExist = false;
                			var list = document.getElementById('notification-list');
                			var elements = list.getElementsByTagName('li');
                			for(var i=0;i<elements.length;i++){
                				if(elements[i].id == notificationListIdPrefix + res.senderId){
                					isSenderExist = true;
                				}
                			}
                			
                			if(!isSenderExist){
                				if(!isVisible(document.getElementById(res.senderId))){
                					var li = document.createElement('li');
                					li.id = notificationListIdPrefix + res.senderId;
                					li.appendChild(createChatLink(res.senderId,res.senderName,'Wiadomość od'));
                					list.appendChild(li);
                					
                					document.getElementById('no-message-elem').remove();
                					jQuery('#notify-view-element').addClass('mail_notify');
                				}
                			}
                    		
                    	}else{
                    		responsiveNotify("Wystąpił błąd");
            	        	senderClientChat = false;
            	        	isWaitChat = false;
                    	}
        				let elem = document.getElementsByClassName('popup-messages')[0];
        				elem.scrollTo(0, elem.scrollHeight);
                    });
                });
            }
       
            
            function addMessageAsSender(id,message){
            	var row = document.createElement("tr");
            	var col = document.createElement("td");
            	
            	var divDate = document.createElement("div");
            	divDate.classList.add('pull-left');
            	divDate.style.padding = '10px';
            	var spanDate = document.createElement("span");
            	spanDate.style.fontSize = '12px';
            	spanDate.innerHTML = getDate(null);
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
            	span.innerHTML = message;
            	div.appendChild(span);
            	col.appendChild(div);
            	row.appendChild(col);
            	var element = document.getElementById(id);
            	element.querySelector('.popup-messages table tbody').appendChild(row);
            }
            
            function addMessageAsReceiver(id, message, date){
            	var row = document.createElement("tr");
            	var col = document.createElement("td");
            	
            	var divDate = document.createElement("div");
            	divDate.classList.add('pull-right');
            	divDate.style.padding = '10px';
            	var spanDate = document.createElement("span");
            	spanDate.style.fontSize = '12px';
            	spanDate.innerHTML = getDate(date);
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
            	span.innerHTML = message;
            	div.appendChild(span);
            	col.appendChild(div);
            	row.appendChild(col);
            	var element = document.getElementById(id);
            	element.querySelector('.popup-messages table tbody').appendChild(row);
            }

            function getDate(date){
            	let d = date != null ? new Date(date) : new Date();
            	let day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate();
            	let month = (d.getMonth()+1) < 10 ? '0' + (d.getMonth()+1) : (d.getMonth()+1);
            	let hours = d.getHours() < 10 ? '0' + d.getHours() : d.getHours();
				let min = d.getMinutes() < 10 ? '0' + d.getMinutes() : d.getMinutes();
				return day + '-' + month + ' ' + hours + ':' + min;
            }
            
            function calculate_popups()
            {
            	//popupWidth =  window.innerWidth < 640 ? 200 : 320;
            	right = window.innerWidth < smallWidth ? 0 : 220;
                // var right = window.innerWidth < 640 ? 0 : 15;
                var width = window.innerWidth;
                if(width < smallWidth)
                {
                    total_popups = 1;
                }
                else
                {
                    width = width - 200;
                    total_popups = parseInt(width/popupWidth);
                }
                console.log("total " + total_popups);
                display_popups();
                
            }
            
            function isVisible(element) {
            	if(element != null){
            		var style = window.getComputedStyle(element);
                	return (style.display != 'none')
            	}
            	return false;
            }

            window.addEventListener("resize", calculate_popups);
            window.addEventListener("load", calculate_popups);
          
            