package net.helpdeskhelper.helpdeskhelper.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public interface IClientInfoService {

	void getClientInfo(HttpServletRequest req) throws IOException;
}
