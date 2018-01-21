package com.auctions.system.module.mail_manager.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import com.auctions.system.module.mail_manager.MailManager;

public class SimpleMailManager implements MailManager {

	//@Autowired
	//ReloadableResourceBundleMessageSource messageSrc;
	
    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    @Override
    public void sendMail(String emailAddress,String firstname, Locale locale) {
    	
     	templateMessage.setTo(emailAddress);
     	//templateMessage.setText(messageSrc.getMessage("registration.msg", new Object[]{firstname}, locale));
     	templateMessage.setText("Witamy w serwisie");
        try{
            this.mailSender.send(templateMessage);
        }
        catch(MailException ex) {
            System.err.println(ex.getMessage());            
        }
    }
}
