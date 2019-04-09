package net.helpdeskhelper.helpdeskhelper.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface IClientInfoService {

	List<String> getClientInfo(HttpServletRequest req) throws IOException;
}
