package com.keepthinker.example.spring.web;

import com.keepthinker.example.spring.web.model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

/**
 * If you want make a servlet managed by spring, you have to define 
 * your own DispatcherServlet-like servlet as a proxy.
 * @author keshengkai
 */
public class AServlet extends HttpServlet{
	private ApplicationContext context;
	private Person personRequest;
	private Person personSession;

	//	private static Logger logger = Logger.getLogger(AServlet.class);
	private static final long serialVersionUID = -8216808290861737670L;
	@Override
	@RequestMapping(value = "/a")
	protected void service(HttpServletRequest request, HttpServletResponse response){
		StringBuilder stringBuilder = new StringBuilder(2<<7);
		stringBuilder.append("remote host : ").append(request.getRemoteHost()).append("\n");
		personRequest = context.getBean("personRequest",Person.class);
		personSession = context.getBean("personSession",Person.class);
		stringBuilder.append("person in request, id : ").append(personRequest.getId()).append("\n");
		stringBuilder.append("person in sesssion, id : ").append(personSession.getId()).append("\n你好！\n");
		stringBuilder.append("attributeName : ");
		Enumeration<String> e = request.getServletContext().getAttributeNames();
		while(e.hasMoreElements()){
			stringBuilder.append("\n").append(e.nextElement());
		}

		write(response, stringBuilder.toString());
	}
	@Override
	public void init() throws ServletException {
		super.init();
		context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());//(ApplicationContext)getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	}

	private void write(HttpServletResponse response, String content){
		response.setContentType("text/plain;charset=utf-8"); 
		Writer writer = null;
		try {
			writer = response.getWriter();
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
