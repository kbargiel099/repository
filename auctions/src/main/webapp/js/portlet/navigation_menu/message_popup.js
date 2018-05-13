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
                	var message = jQuery('#send-message-input').val();
                	sendForm(id,message);
                	addMessageAsSender(id,message);
                	jQuery('#send-message-input').val("");
                });
                currentPopupId = id;
                sendRequest(url,loadMessagesCallback);
            }
            
            var loadMessagesCallback = function(data){
    			if(data.success == true){
    				var messages = JSON.parse(data.messages);
    				
    				for(var i=0;i<messages.length;i++){
    					console.log(messages[i]);
    					addMessageAsReceiver(currentPopupId,messages[i].message);
    				}
					markMessagesAsRead(jQuery('#markMessagesAsReadUrl').val(),currentPopupId);
    			}
    			else
    				alert("Wystapil blad");
            };
            
            function markMessagesAsRead(url,userId){
            	jQuery.ajax({
            		"url":url + '&userId=' + userId,
            		"type":"GET",
            		"success": function(data){
            			console.log(data);
            			if(data.success == true){
            				
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
            				alert("Wystapil blad");
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
                        			addMessageAsReceiver(res.senderId,res.message);
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
                    });
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
            	element.querySelector('.popup-messages table tbody').appendChild(row);
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
            	element.querySelector('.popup-messages table tbody').appendChild(row);
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
          
            