package net.helpdeskhelper.helpdeskhelper.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public interface IClientInfoService {

	String getClientInfo(HttpServletRequest req) throws IOException;
}
