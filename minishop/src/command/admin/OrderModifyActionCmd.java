package command.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.OrderDao;
import vo.OrderVo;

public class OrderModifyActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("OrderModifyActionCmd.start");
		
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
		
		String beforeBuyerPhone = request.getParameter("buyerPhone");
		String buyerPhone = "";
		StringTokenizer st = new StringTokenizer(beforeBuyerPhone, "- ");
		
		while (st.hasMoreTokens()) {
			buyerPhone += st.nextToken();
		}
		
		String beforeRcptPhone = request.getParameter("rcptPhone");
		String rcptPhone = "";
		StringTokenizer st1 = new StringTokenizer(beforeRcptPhone, "- ");
		
		while (st1.hasMoreTokens()) {
			rcptPhone += st1.nextToken();
		}
		System.out.println("buyerPhone vale : " + buyerPhone);
		System.out.println("rcptPhone vale : " + rcptPhone);
		
		

		OrderVo vo = new OrderVo();
		vo.setOrderCount(Integer.parseInt(request.getParameter("orderCount")));
		vo.setBuyerName(request.getParameter("buyerName"));
		vo.setBuyerAddress1(request.getParameter("buyerAddress1"));
		vo.setBuyerAddress2(request.getParameter("buyerAddress2"));
		vo.setBuyerPhone(buyerPhone);
		vo.setBuyerEmail(request.getParameter("buyerEmail"));
		vo.setRcptName(request.getParameter("rcptName"));
		vo.setRcptAddress1(request.getParameter("rcptAddress1"));
		vo.setRcptAddress2(request.getParameter("rcptAddress2"));
		vo.setRcptPhone(rcptPhone);
		vo.setShipMessage(request.getParameter("shipMessage"));
		vo.setOrderState(Integer.parseInt(request.getParameter("orderState")));
		vo.setOrderAmount(Integer.parseInt(request.getParameter("orderAmount")));
		vo.setOrderCode(request.getParameter("orderCode"));
		
		
		
		OrderDao dao = new OrderDao();
		int result = dao.orderMofiyAction(vo);
		
		System.out.println("OrderModifyActionCmd.end");
		return "OrderManageView.do";
		
	}//end execute method
}//end class
