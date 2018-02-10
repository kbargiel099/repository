package hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response
    		,@RequestParam(value="name", defaultValue="World") String name) {
    	
        return "Update" + name;
    }

	/*@RequestMapping("/accounts/alerts")
	public SseEmitter getAccountAlertsNoPathVariable(HttpSession session) {
		SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
		Thread t1 = new Thread(() ->{
					System.out.println("Sending");
					try{
						emitter.send(new Greeting(counter.incrementAndGet(),"tekst-Server"),MediaType.TEXT_EVENT_STREAM);
					}catch(ClientAbortException cae){
						cae.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				emitter.complete();
		});
		t1.start();
		return emitter;
	}*/
	
	@RequestMapping("/accounts/alerts")
	public void getAccountAlertsNoPathVariable(HttpServletRequest request, HttpServletResponse response) {
		Thread t1 = new Thread(() ->{
					response.setContentType("text/event-stream");
					try {
						PrintWriter writer = response.getWriter();
						writer.write("data:{/'id/':3,'content/':/'tekst-Server'}");
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		});
		t1.start();
	}

	

	public void sendWhileActiveSession(HttpSession session){

		

	}
}