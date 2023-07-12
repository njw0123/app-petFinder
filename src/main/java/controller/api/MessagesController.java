package controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data.vo.Message;
import repoistory.MessagesDAO;

@WebServlet("/api/messages")
public class MessagesController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain;charset=utf-8");
		String no = req.getParameter("no");
		List<Message> li = MessagesDAO.readMessage(no);
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		out.println(gson.toJson(li));
	}
}
