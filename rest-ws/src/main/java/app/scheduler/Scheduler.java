package app.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.service.AuctionProcessService;
import module.mail_manager.MailType;
import module.mail_manager.impl.SimpleMailManager;

@Component
public class Scheduler {

    private final int hour = 60 * 600000;
	
    @Autowired
    AuctionProcessService service;
    
    @Autowired
    SimpleMailManager mailManager;
    
    @Scheduled(fixedRate = hour)
    public void scheduleTaskWithFixedRate() {
    	System.out.println("END AUCTIONS");
    	mailManager.sendMultiple(service.markAuctionsFinished(), MailType.FINISHED_AUCTION);
    }

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}
