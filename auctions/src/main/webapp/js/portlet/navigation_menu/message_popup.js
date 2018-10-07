			jQuery(document).ready(function(){
				if(Liferay.ThemeDisplay.isSignedIn()){
					connectForChat();
				}
			});        
            var total_popups = 0;
            var smallWidth = 640;
            var popups = [];
            var currentPopupId;
            var popupWidth =  window.innerWidth > smallWidth ? 320 : window.outerWidth;
        	var right = window.innerWidth < smallWidth ? 0 : 220;
            
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
                var iii = 0;
                for(iii; iii < total_popups; iii++)
                {
                    if(popups[iii] != undefined)
                    {
                        var element = document.getElementById(popups[iii]);
                        element.style.right = right + "px";
                        right = right + popupWidth;
                        element.style.display = "block";
                    }
                }
                
                for(var jjj = iii; jjj < popups.length; jjj++)
                {
                    var element = document.getElementById(popups[jjj]);
                    element.style.display = "none";
                }
            }
            
            function register_popup(id, name, url)
            {
                close_popup(id);           
                
                var element = '<div class="popup-box chat-popup" id="'+ id +'">';
                element = element + '<div class="popup-head">';
                element = element + '<div class="popup-head-left">'+ name +'</div>';
                element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ id +'\');">&#10005;</a></div>';
                element = element + '<div style="clear: both"></div></div><div class="popup-messages"><table><tbody></tbody></table></div>';
                element = element + '<div class="popup-inputs">';
                element = element + '<div class="pull-left col-xs-9"><input class="chat-input" type="text" id="send-message-input"></div>';
                element = element + '<div class="col-xs-2"><a class="chat-button" href="javascript:void(0);" id="send-message-button">';
                element = element + Liferay.Language.get('send') + '</a></div></div></div>';
                
                jQuery('body').append(element);
                popups.unshift(id);
                calculate_popups();
                                
                jQuery('#send-message-button').click(function(){
                	var message = jQuery('.chat-input').val();
                	sendForm(id,message);
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
    				
    				if(messages.length > 0) {
    					markMessagesAsRead(jQuery('#markMessagesAsReadUrl').val(),currentPopupId);
    				}
    				
    				let elem = document.getElementsByClassName('popup-messages')[0];
    				elem.scrollTo(0, elem.scrollHeight);
    			}
    			else {
    				responsiveNotify(Liferay.Language.get('error.msg'));
    			}
            };
            
            function markMessagesAsRead(url,userId){
            	jQuery.ajax({
            		"url":url + '&userId=' + userId,
            		"type":"GET",
            		"success": function(data){
            			if(JSON.parse(data.success) == true){
            				
            				var list = document.getElementById('notification-list');
            				var pp = document.getElementById(notificationListIdPrefix + userId);
            				list.removeChild(pp);
            				
            				if(list.getElementsByTagName('li').length == 0){
            					var elem = document.createElement('li');
            					elem.id = 'no-message-elem';
            					elem.innerHTML = '<a href="javascript:void(0);">'+ Liferay.Language.get('no.new.messages') +'</a>';
            					list.appendChild(elem);
            				}

            			}
            			else {
            				alert(Liferay.Language.get('error.msg'));
            			}
            		}
            	});
            }
            
            function connectForChat(){
                var socketForChat = new SockJS(jQuery('#restServiceEndpoint').val());
            	stompClientChat = Stomp.over(socketForChat);
            	stompClientChat.connect({}, function (frame) {
            		stompClientChat.subscribe('/message/' + userId, function (data) {
                    	var res = JSON.parse(data.body);
                    	if(res.success == true){
                			
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
                					li.appendChild(createChatLink(res.senderId, res.senderName, Liferay.Language.get('message.from.label')));
                					list.appendChild(li);
                					
                					document.getElementById('no-message-elem').remove();
                					jQuery('#notify-view-element').addClass('mail_notify');
                				}
                			}
                    		
                    	}else{
                    		responsiveNotify(Liferay.Language.get('error.msg'));
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
            
            function calculate_popups()
            {
            	right = window.innerWidth < smallWidth ? 0 : 220;
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
          
            