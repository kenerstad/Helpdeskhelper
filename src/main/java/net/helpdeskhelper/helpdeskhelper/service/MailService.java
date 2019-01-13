package net.helpdeskhelper.helpdeskhelper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService{
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	private MailContentService maiLContent;
 
    public void sendSimpleMessage(
    		String to, String subject, String text) {
       
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        try {
	    	emailSender.send(message);
	    } catch (MailException e) {
	        System.out.println("Mail exception: " +e);
	    }
        
    }
    
    public void sendMessage(
    		String to, String subject, String body) {
    	
    	 MimeMessagePreparator message = mimeMessage -> {
    	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
    	        String content = maiLContent.build(body);
    	        messageHelper.setFrom("sample@dolszewski.com");
    	        messageHelper.setTo(to);
    	        messageHelper.setSubject("Helpdeskhelper helping you");
    	        messageHelper.setText(content, true);
    	    };
    	    try {
    	    	emailSender.send(message);
    	    } catch (MailException e) {
    	    	System.out.println("Mail exception: " +e);
    	    }
    	
    }

}
