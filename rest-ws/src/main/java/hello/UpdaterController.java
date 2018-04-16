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

@CrossOrigin(origins = {"http://192.168.0.15:8080"})
@RestController
public class UpdaterController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    AuctionProcessingService service;

    @RequestMapping("/greeting")
    public ResponseForm greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new ResponseForm(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @MessageMapping("/update/{id}")
    @SendTo("/topic/notify/{id}")
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
    @SendTo("/topic/notify/{id}")
    public Response proceedPurchase(@DestinationVariable String id, RequestForm form) throws Exception {    	
    	boolean isInserted = false;
    	
    	if(isCurrentTimeBefore(form.getEndDate())){
    		isInserted = service.proceedPurchase(Long.parseLong(form.getUserId()),Long.parseLong(id),
    				Long.parseLong(form.getPrice()),Integer.parseInt(form.getQuantity()));
    	}else{
    		return new ResponseError(1);
    	}
    	
    	if(isInserted){
        	return new ResponseForm(true, form.getUsername(), form.getQuantity());
        }else{
        	return new ResponseError(2);
        }
    }
    
    private boolean isCurrentTimeBefore(String end){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh.mm.ss");
    	
    	Date endDate = null;
		try {
			endDate = format.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Date current = new Date(System.currentTimeMillis());
        return current.before(endDate);
    }
	
}