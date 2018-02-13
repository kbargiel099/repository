var stompClient = null;

function setConnected(connected) {
	jQuery("#connect").prop("disabled", connected);
	jQuery("#disconnect").prop("disabled", !connected);
    if (connected) {
    	jQuery("#conversation").show();
    }
    else {
    	jQuery("#conversation").hide();
    }
    jQuery("#greetings").html("");
}

function connect() {
    var socket = new SockJS('http://localhost:8143/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            jQuery("#auction-notify ul").append("<li>" + JSON.parse(greeting.body).content + " przebił </li>")
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': jQuery("#name").val()}));
}

$(function () {
	jQuery("form").on('submit', function (e) {
        e.preventDefault();
    });
	jQuery( "#connect" ).click(function() { connect(); });
	jQuery( "#disconnect" ).click(function() { disconnect(); });
	jQuery( "#send" ).click(function() { sendName(); });
});