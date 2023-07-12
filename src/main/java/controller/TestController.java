package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.QueryStringBuilder;

@WebServlet("/test")
public class TestController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String upr_cd = "6210000";
		String upkind = "244000";
		String pageNo = "1";
		String bgnde = "20230410";
		String endde = null;
		
		Map<String, String> params = new HashMap<>();
		if (upr_cd != null)params.put("upr_cd", upr_cd);
		if (upkind != null)params.put("upkind", upkind);
		if (pageNo != null)params.put("pageNo", pageNo);
		if (bgnde != null)params.put("bgnde", bgnde);
		if (endde != null)params.put("endde", endde);
		
		String query = QueryStringBuilder.bulid(params);
		System.out.println(query);
	}
}
