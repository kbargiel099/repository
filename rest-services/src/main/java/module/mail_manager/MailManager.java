package module.mail_manager;

import java.util.List;

import model.MailProperties;

public interface MailManager {
		
    public void sendMail(String emailAddress);
    
    public void sendMultiple(List<MailProperties> emails, MailType type);
    
}
