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
    public Response proceed(@DestinationVariable String id,RequestForm form) throws Exception {    	
    	boolean isInserted = false;
    	
    	if(isCurrentTimeBefore(form.getEndDate())){
    		isInserted = service.proceedOffer(Long.parseLong(form.getUserId()),Long.parseLong(id),
    				Long.parseLong(form.getPrice()),Integer.parseInt(form.getQuantity()));
    	}else{
    		return new ResponseError(1);
    	}
    	
    	if(isInserted){
        	return new ResponseForm(true, form.getUsername(), form.getPrice(), form.getQuantity());
        }else{
        	return new ResponseError(2);
        }
    }
    
    @MessageMapping("/purchase/{id}")
    @SendTo("/message/notify/{id}")
    public Response proceedPurchase(@DestinationVariable String id, RequestForm form) throws Exception {    	
    	boolean isInserted = false;
    	
    	if(isCurrentTimeBefore(form.getEndDate())){
    		isInserted = service.proceedPurchase(Long.parseLong(form.getUserId()),Long.parseLong(id),Long.parseLong(form.getPrice()),
    			Integer.parseInt(form.getQuantity()));
    	}else{
    		return new ResponseError(1);
    	}
    	
    	if(isInserted){
        	return new ResponseForm(true, form.getUsername(), form.getQuantity());
        }else{
        	return new ResponseError(2);
        }
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
    
    private boolean isCurrentTimeBefore(String end){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");
    	
		try {
			Date endDate = format.parse(end);
	        Date current = new Date(System.currentTimeMillis());
	        return current.before(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;
    }
	
}