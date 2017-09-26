package command.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.ProductDao;

public class ProductManageViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("productmanagerviewcmd_start");
		
		HttpSession session= request.getSession();
		
		String userId= null;
		
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
		
		//관리자가 아닌 경우 사용 불가
		if(!userId.equals("admin")){
			request.setAttribute("errorType", "isNotAdmin");
			return "/error/admin_error.jsp";
		}
		
		ProductDao dao = new ProductDao();
		ArrayList<HashMap<String, String>> list = dao.adminProuctList();
		
		request.setAttribute("voList", list);
		
		System.out.println("productmanagerviewcmd_end");
		return "/admin/product_manage.jsp";
		
	}//end execute method
}//end class
