package hello;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import module.Code;
import module.exception.TimeException;
import module.mail_manager.MailType;
import module.mail_manager.impl.SimpleMailManager;

@CrossOrigin(origins = {"http://192.168.0.15:8080"})
@RestController
public class UpdaterController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    AuctionProcessingService service;
    
    @Autowired
    SimpleMailManager mailManager;

    @RequestMapping("/greeting")
    public ResponseForm greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new ResponseForm(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/sendMail")
    public ResponseForm sendMail(@RequestParam(value="address") String address) {
    	System.out.println("address send mail: " + address);
    	mailManager.setTemplate(MailType.REGISTRATION, "Krystian");
    	mailManager.sendMail(address);
        return new ResponseForm(counter.incrementAndGet(),
                            String.format(template, address));
    }
    
    @MessageMapping("/update/{id}")
    @SendTo("/message/notify/{id}")
    public Response proceedOffer(@DestinationVariable String id,RequestForm form) throws Exception {   
    	try{
	    	if(isCurrentTimeBefore(form.getEndDate())){
	    		service.proceedOffer(form, id);
	    		mailManager.sendMultiple(service.getMailProperties(
	    				Long.parseLong(id), Long.parseLong(form.getUserId())), MailType.NEW_OFFER);
	    	}
    	}catch(TimeException e){
    		e.printStackTrace();
    		return new ResponseError(Code.TIMEOUT);
    	}catch(Exception e){
    		e.printStackTrace();
    		return new ResponseError(Code.ERROR);
    	}
    	
    	return new ResponseForm(true, form.getUsername(), form.getPrice(), form.getQuantity());
    }
    
    @MessageMapping("/purchase/{id}")
    @SendTo("/message/notify/{id}")
    public Response proceedPurchase(@DestinationVariable String id, RequestForm form) throws Exception {    	
    	try{
	    	if(isCurrentTimeBefore(form.getEndDate())){
	    		service.proceedPurchase(form, id);
	    		mailManager.sendMultiple(service.getMailPropertiesPurchase(Long.parseLong(id)), MailType.PURCHASE);
	    	}
    	}catch(TimeException e){
    		e.printStackTrace();
    		return new ResponseError(Code.TIMEOUT);
    	}catch(Exception e){
    		e.printStackTrace();
    		return new ResponseError(Code.ERROR);
    	}
    	
    	return new ResponseForm(true, form.getUsername(), form.getQuantity());
    }
    
    @MessageMapping("/conversation/{id}")
    @SendTo("/message/{id}")
    public Response proceedConversation(@DestinationVariable String id, MessageRequestForm form) throws Exception {    	
        
    	Date createDate = new Date();
    	boolean isInserted = service.createChatMessage(Long.parseLong(form.getSenderId()),Long.parseLong(id),
    				form.getMessage(),createDate);

    	if(isInserted){
            return new MessageResponse(true,form.getSenderId(),form.getSenderName(),form.getMessage(), createDate.toString());
    	}else{
    		return new ResponseError(1);
    	}
    	
    }
    
    private boolean isCurrentTimeBefore(String end) throws TimeException{
    	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");
    	
		try {
			Date endDate = format.parse(end);
	        Date current = new Date(System.currentTimeMillis());
	        
	        if(!current.before(endDate)) throw new TimeException("Aukcja została już zakoczona");
	        return true;
	        
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;
    }
	
}