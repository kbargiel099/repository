package com.auctions.system.module.mail_manager;

import java.util.Locale;

public interface MailManager {

    public void sendMail(String emailAddress,String firstname, Locale locale);
    
}
