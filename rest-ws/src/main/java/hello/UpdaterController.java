package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:8080","http://192.168.0.15:8080"})
//@CrossOrigin(origins = "http://192.168.0.15:8080")
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
    
    //@CrossOrigin(origins = "http://localhost:8080")
    @MessageMapping("/update/{id}")
    @SendTo("/topic/notify/{id}")
    public Response proceed(@DestinationVariable String id,RequestForm form) throws Exception {    	
    	boolean isInserted = false;
    	
    	if(isCurrentTimeBefore(form.getEndDate())){
    		isInserted = service.insertData(Long.parseLong(form.getUserId()),
        		Long.parseLong(id),Long.parseLong(form.getPrice()));
    	}else{
    		return new ResponseError(1);
    	}
    	
    	if(isInserted){
        	return new ResponseForm(true, form.getUsername(), form.getPrice());
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
	
	@RequestMapping("/accounts/alerts")
	public void getAccountAlertsNoPathVariable(HttpServletRequest request, HttpServletResponse response) {
		Thread t1 = new Thread(() ->{
			response.setContentType("text/event-stream");
			try {
				PrintWriter writer = response.getWriter();
				writer.write("data:{/'id/':3,'content/':/'tekst-Server'}");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		t1.start();
	}

	

	public void sendWhileActiveSession(HttpSession session){

		

	}
}