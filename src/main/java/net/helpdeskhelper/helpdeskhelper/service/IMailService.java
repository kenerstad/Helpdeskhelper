package net.helpdeskhelper.helpdeskhelper.service;

public interface IMailService {

	void sendSimpleMessage(String to, String subject, String text);
	void sendMessageToSupport(Object body);
}
