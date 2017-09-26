package command.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.OrderDao;
import vo.OrderVo;

public class OrderManageViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		
		//결제 미완
		OrderDao orderDao = new OrderDao();
		ArrayList<HashMap<String, String>> voList = orderDao.getOrderManageView("0");
		
		request.setAttribute("voList", voList);
		
		
		//결제 완료
		OrderDao orderDao2 = new OrderDao();
		ArrayList<HashMap<String, String>> voList2 = orderDao.getOrderManageView("1");
		
		request.setAttribute("voList2", voList2);
		
		
		return "/admin/order_manage.jsp";
		
	}//end execute method
}//end class
