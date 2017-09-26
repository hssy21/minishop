package command.admin;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.OrderDao;

public class OrderDetailViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		
		String userId= null;
		
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
		
		//�����ڰ� �ƴ� ��� ��� �Ұ�
		if(!userId.equals("admin")){
			request.setAttribute("errorType", "isNotAdmin");
			return "/error/admin_error.jsp";
		}
		
		
		//��� ����
		String orderCode = request.getParameter("orderCode");
		System.out.println(orderCode);

		OrderDao orderDao = new OrderDao();
		HashMap<String, String> order = orderDao.orderDetailView(orderCode);
		
		request.setAttribute("order", order);
		return "/admin/order_detail.jsp";
		
		
	}//end execute method
}//end class
