package net.helpdeskhelper.helpdeskhelper.service;

public interface IMailService {

	void sendSimpleMessage(String to, String subject, String text);
	void sendMessage(String to, String subject, String body);
}
