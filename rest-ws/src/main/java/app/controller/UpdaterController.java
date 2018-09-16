package app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.service.AuctionProcessingService;
import model.MessageRequestForm;
import model.MessageResponse;
import model.RequestForm;
import model.Response;
import model.ResponseError;
import model.ResponseForm;
import model.SendEmailResponse;
import module.Code;
import module.exception.TimeException;
import module.mail_manager.MailType;
import module.mail_manager.impl.SimpleMailManager;

@CrossOrigin(origins = {"http://192.168.0.15:8080"})
@RestController
public class UpdaterController {
    
    @Autowired
    AuctionProcessingService service;
    
    @Autowired
    SimpleMailManager mailManager;
    
    @RequestMapping("/sendMail")
    public Response sendMail(@RequestParam(value="address") String address) {
    	SendEmailResponse response = new SendEmailResponse();
    	
    	try{
        	mailManager.setTemplate(MailType.REGISTRATION, "Krystian");
        	mailManager.sendMail(address);
        	response.setSuccess(true);
    	}catch(Exception e){
    		e.printStackTrace();
    		response.setSuccess(false);
    	}
    	
    	return response;
    }
    
    @MessageMapping("/update/{id}")
    @SendTo("/message/notify/{id}")
    public Response proceedOffer(@DestinationVariable String id,RequestForm form) {   
    	try{
	    	if(isCurrentTimeBefore(form.getEndDate())){
	    		service.proceedOffer(form, id);
	    		mailManager.sendMultiple(service.getMailProperties(
	    				Long.parseLong(id), Long.parseLong(form.getUserId())), MailType.NEW_OFFER);
	    	}
    	}catch(TimeException e){
    		e.printStackTrace();
    		return new ResponseError(Code.AUCTION_FINISHED);
    	}catch(Exception e){
    		e.printStackTrace();
    		return new ResponseError(Code.ERROR);
    	}
    	
    	return new ResponseForm(form);
    }
    
    @MessageMapping("/purchase/{id}")
    @SendTo("/message/notify/{id}")
    public Response proceedPurchase(@DestinationVariable String id, RequestForm form) {    	
    	try{
	    	if(isCurrentTimeBefore(form.getEndDate())){
	    		service.proceedPurchase(form, id);
	    		mailManager.sendMultiple(service.getMailPropertiesPurchase(Long.parseLong(id)), MailType.PURCHASE);
	    	}
    	}catch(TimeException e){
    		e.printStackTrace();
    		return new ResponseError(Code.AUCTION_FINISHED);
    	}catch(Exception e){
    		e.printStackTrace();
    		return new ResponseError(Code.ERROR);
    	}
    	
    	return new ResponseForm(form);
    }
    
    @MessageMapping("/conversation/{id}")
    @SendTo("/message/{id}")
    public Response proceedConversation(@DestinationVariable String id, MessageRequestForm form) throws Exception {
    	try {
	    	service.createChatMessage(form);
    	}catch(Exception e){
    		e.printStackTrace();
    		return new ResponseError(Code.ERROR);
    	}

        return new MessageResponse(form);
    }
    
    private boolean isCurrentTimeBefore(String end) throws TimeException, ParseException{
    	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
    	
		Date endDate = format.parse(end);
	    Date current = new Date(System.currentTimeMillis());
	        
	    if(!current.before(endDate)) {
	    	throw new TimeException("Aukcja została już zakonczona");
	    }
	    
	    return true;
    }
	
}