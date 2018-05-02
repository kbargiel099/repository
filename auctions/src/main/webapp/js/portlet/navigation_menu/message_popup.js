			//this function can remove a array element.
            Array.remove = function(array, from, to) {
                var rest = array.slice((to || from) + 1 || array.length);
                array.length = from < 0 ? array.length + from : from;
                return array.push.apply(array, rest);
            };
        
            //this variable represents the total number of popups can be displayed according to the viewport width
            var total_popups = 0;
            
            //arrays of popups ids
            var popups = [];
        
            //var clients = [];
            
            //this is used to close a popup
            function close_popup(id)
            {
                for(var iii = 0; iii < popups.length; iii++)
                {
                    if(id == popups[iii])
                    {
                        //Array.remove(popups, iii);
                        
                        document.getElementById(id).style.display = "none";
                        
                        //calculate_popups();
                        
                        return;
                    }
                }   
            }
        

            //displays the popups. Displays based on the maximum number of popups that can be displayed on the current viewport width
            function display_popups()
            {
                var right = 220;
                
                var iii = 0;
                for(iii; iii < total_popups; iii++)
                {
                    if(popups[iii] != undefined)
                    {
                        var element = document.getElementById(popups[iii]);
                        element.style.right = right + "px";
                        right = right + 320;
                        element.style.display = "block";
                    }
                }
                
                for(var jjj = iii; jjj < popups.length; jjj++)
                {
                    var element = document.getElementById(popups[jjj]);
                    element.style.display = "none";
                }
            }
            
            //creates markup for a new popup. Adds the id to popups array.
            function register_popup(id, name, url)
            {
                var isExists = false;
                for(var iii = 0; iii < popups.length; iii++)
                {   
                    //already registered. Bring it to front.
                    if(name == popups[iii])
                    {
                        //Array.remove(popups, iii);
                    
                        //popups.unshift(name);
                        
                        //calculate_popups();
                    	display_popups();
                        isExists = true;
                        console.log("zawiera");
                        return;
                    }
                }               
                
                if(isExists){
                	console.log("isexists true");
                	return;
                }
                console.log("dalszy ciąg");
                var element = '<div class="popup-box chat-popup" id="'+ name +'">';
                element = element + '<div class="popup-head">';
                element = element + '<div class="popup-head-left">'+ name +'</div>';
                element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ name +'\');">&#10005;</a></div>';
                element = element + '<div style="clear: both"></div></div><div class="popup-messages"><table><tbody></tbody></table></div>';
                element = element + '<div class="popup-inputs">';
                element = element + '<div class="pull-left col-xs-9"><input class="chat-input" type="text" id="send-message-input"></div>';
                element = element + '<div class="col-xs-2"><a class="chat-button" href="javascript:void(0);" id="send-message-button">Wyślij</a></div></div></div>';
                
                document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element;  
        
                popups.unshift(name);
                        
                calculate_popups();
                
                console.log(popups);
                
                jQuery('#send-message-button').click(function(){
                	var message = jQuery('#send-message-input').val();
                	sendForm(id,message);
                	addMessageAsSender(name,message);
                });
                
            	jQuery.ajax({
            		"url":url,
            		"type":"GET",
            		"success": function(data){
            			if(data.success == true){
            				var messages = JSON.parse(data.messages);
            				
            				for(var i=0;i<messages.length;i++){
            					console.log(messages[i]);
            					addMessageAsReceiver(name,messages[i].message);
            				}
        					connectForChat(id,name);
            			}
            			else
            				alert("Wystapil blad");
            		}
            	});
                
            }
            
            function connectForChat(receiverId,popupMessagesId){
                var socketForChat = new SockJS('http://192.168.0.15:8143/notification');
            	stompClientChat = Stomp.over(socketForChat);
            	stompClientChat.connect({}, function (frame) {
            		stompClientChat.subscribe('/message/' + userId, function (data) {
                    	var res = JSON.parse(data.body);
                    	console.log("Odpowiedz z serwera");
                    	console.log(res);
                    	if(res.success == true){
                    		
                    		//if(senderClientChat){
                    		//	addMessageAsSender(popupMessagesId,res.message);
                    			senderClientChat = false;
                    			isWaitChat = false;
                    		//}else{
                    			addMessageAsReceiver(popupMessagesId,res.message);
                    		//}
                    		
                    	//}else if(senderClientChat == true){
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
            //calculate the total number of popups suitable and then populate the toatal_popups variable.
            function calculate_popups()
            {
                var width = window.innerWidth;
                if(width < 540)
                {
                    total_popups = 1;
                }
                else
                {
                    width = width - 200;
                    //320 is width of a single popup box
                    total_popups = parseInt(width/320);
                }
                
                display_popups();
                
            }
            
            //recalculate when window is loaded and also when window is resized.
            window.addEventListener("resize", calculate_popups);
            window.addEventListener("load", calculate_popups);
          
            