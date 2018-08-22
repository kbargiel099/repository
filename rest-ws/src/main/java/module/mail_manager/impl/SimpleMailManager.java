package module.mail_manager.impl;

import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import model.MailProperties;
import module.mail_manager.MailManager;
import module.mail_manager.MailType;

public class SimpleMailManager implements MailManager {
	
    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    @Override
    public void sendMail(String emailAddress) {
     	try {
     		//templateMessage.setFrom("Aukcje.pl");
     		templateMessage.setTo(emailAddress);
            mailSender.send(templateMessage);
        }
        catch(MailException ex) {
            System.err.println(ex.getMessage());            
        }
    }
    
    @Override
    public void sendMultiple(List<MailProperties> emails, MailType type) {
    	for(MailProperties properties : emails){
    		setTemplate(type, properties.getAuctionName());
    		sendMail(properties.getEmailAddress());
    	}
    }
    
    public void setTemplate(MailType type, String data){
     	setMessageDetails(type, data);
    }
    
    private void setMessageDetails(MailType type, String data){
    	switch(type){
    	case REGISTRATION :
         	templateMessage.setSubject("Rejestracja");
         	templateMessage.setText("Dziękujemy za rejestrację w serwisie");
         	break;
    	case FINISHED_AUCTION :
         	templateMessage.setSubject("Aukcja zakończona");
         	templateMessage.setText("Twoja aukcja: " + data +" została właśnie zakończona. Zaloguj się na portalu i zobacz szczegóły.");
         	break;
    	case NEW_OFFER :
         	templateMessage.setSubject("Twoja oferta została przebita");
         	templateMessage.setText("Twoja oferta w aukcji: " + data +" została właśnie przebita. Zaloguj się na portalu i zobacz szczegóły.");
         	break;
    	}
    }
}
