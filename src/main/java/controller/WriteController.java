package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repoistory.MessagesDAO;

@WebServlet("/write")
public class WriteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String target = req.getParameter("target");
		String pass = req.getParameter("pass");
		String body = req.getParameter("body");
		
		MessagesDAO.createMessage(target, body, pass);
		
		resp.setCharacterEncoding("UTF-8");
		resp.sendRedirect("/detail?no="+target);
	}
}
