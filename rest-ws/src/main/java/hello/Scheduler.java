package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final int hour = 60 * 600000;
	
    @Autowired
    AuctionProcessingService service;
    
    @Scheduled(fixedRate = hour)
    public void scheduleTaskWithFixedRate() {
    	System.out.println("END AUCTIONS");
    	service.markAuctionsEnded();
    }

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}
