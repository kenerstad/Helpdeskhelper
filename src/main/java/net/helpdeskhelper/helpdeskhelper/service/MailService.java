package net.helpdeskhelper.helpdeskhelper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


/*
 * Class that Implements Spring email to construct & send email messages.
 * Uses project MailContentService class to include custom content in message.
 */
@Service
public class MailService implements IMailService{
	
	@Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	private MailContentService mailContent;
	
	@Value("${mail.subject}")
	private String subject;
	
	@Value("${mail.from}")
	private String from;
	
	@Value("${mail.to}")
	private String to;
 
	/*
	 * Builds simple email with recipient, subject & plain text.
	 */
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
    
    /*
     * Builds email message using MimeMessagePreparator, MimeMessageHelper to
     * define from, to, subject and content. The content is built with help of
     * MailContentService class object.
     */
    public void sendMessageToSupport(Object body) {
    	
    	 MimeMessagePreparator message = mimeMessage -> {
    	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
    	        String content = mailContent.build(body);
    	        messageHelper.setFrom(from);
    	        messageHelper.setTo(to);
    	        messageHelper.setSubject(subject);
    	        messageHelper.setText(content, true);
    	    };
    	    try {
    	    	emailSender.send(message);
    	    } catch (MailException e) {
    	    	System.out.println("Mail exception: " +e);
    	    }
    	
    }

}
