package com.keepthinker.example.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

@WebServlet(value = "/servlet")
public class MyServlet extends HttpServlet{

	private static final long serialVersionUID = 4390336285005381815L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("myname", "Shawn ke");
		resp.setStatus(200);
		OutputStream os = resp.getOutputStream();
		OutputStreamWriter w = new OutputStreamWriter(os);
		w.write("hello");
		w.flush();
		
	}
}
