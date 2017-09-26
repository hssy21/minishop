package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDao;
import vo.ProductVo;

public class MainViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("MainViewCmd-Start");
			
		ProductDao dao = new ProductDao();
		
		//NEW ITEM (신규 탑6)
		ArrayList<ProductVo> newItem= dao.getNewItemList();
		
		request.setAttribute("newItem", newItem);
		
		
		//HOT ITEM (판매량 탑6)
		ArrayList<ProductVo> hotItem= dao.getHotItemList();
		
		request.setAttribute("hotItem", hotItem);
		
		
		System.out.println("MainViewCmd-End");
		return "/main.jsp";
	}

}
