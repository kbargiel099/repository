package app.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.service.AuctionUpdaterService;
import module.mail_manager.MailType;
import module.mail_manager.impl.SimpleMailManager;

@Component
public class Scheduler {

    private final int FREQUENCY = 3000000; 
	
    @Autowired
    AuctionUpdaterService service;
    
    @Autowired
    SimpleMailManager mailManager;
    
    @Scheduled(fixedRate = FREQUENCY)
    public void scheduleTaskWithFixedRate() {
    	mailManager.sendMultiple(service.markAuctionsFinished(), MailType.FINISHED_AUCTION);
    }

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}
