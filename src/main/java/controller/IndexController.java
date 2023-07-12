package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.animal.AnimalResponse;
import data.sido.SidoResponse;
import util.AnimalAPI;
import util.SidoAPI;

@WebServlet("/index")
public class IndexController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SidoResponse sidoResponse = SidoAPI.getSidos();
		if (sidoResponse != null) {
			req.setAttribute("sidos", sidoResponse.getBody().getItems().getItem());			
		}
		int p = 1;
		// 축종코드 : 개 => "417000", 고양이 => "422400", 기타 => "429900"
		String upkind = req.getParameter("upkind");
		String upr_cd = req.getParameter("upr_cd");
		String pageNo = req.getParameter("pageNo");
		if (pageNo != null) {
			p = Integer.parseInt(pageNo);			
		}
		String bgnde = req.getParameter("bgnde");
		if (bgnde != null)
			bgnde = bgnde.replace("-", "");			
		String endde = req.getParameter("endde");
		if (endde != null)
			endde = endde.replace("-", "");
		AnimalResponse animalResponse = 
				AnimalAPI.getAnimals(upkind, upr_cd, pageNo, bgnde, endde);
		if (animalResponse != null) {
			req.setAttribute("datas", animalResponse.getBody().getItems().getItem());
			req.setAttribute("total", animalResponse.getBody().getTotalCount());
			int tot = animalResponse.getBody().getTotalCount();
			req.setAttribute("lastPageNo", tot/12 + (tot%12 > 0 ? 1 : 0));
			int end = (int) Math.ceil(p / 10.0) * 10;
			int start = end - 9;
			int lastPage = tot/12 + (tot%12 > 0 ? 1 : 0);
			end = end > lastPage ? lastPage : end;
			req.setAttribute("start", start);
			req.setAttribute("end", end);
			boolean existPrev = p >=11;
			boolean existNext = tot > end;
			req.setAttribute("existPrev", existPrev);
			req.setAttribute("existNext", existNext);
		}
		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
	}
}
