package command.product;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.OrderDao;
import vo.OrderVo;

public class ProductOrderActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		
		String userId= null;
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
			
		//로그인 체크
		if(userId== null){
			request.setAttribute("errorType", "isNotLogin");
			return "/error/user_error.jsp";	
		}
		
		
		//전화번호 필터링
		System.out.println("productOrderActionCmd.start");
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
		
		
		//주문 총액 계산
		int priceSale = Integer.parseInt((String)request.getParameter("priceSale"));
		int orderAmount= Integer.parseInt(request.getParameter("orderCount")) * priceSale + 5000;
/*		System.out.println("buyerPhone vale : " + buyerPhone);
		System.out.println("rcptPhone vale : " + rcptPhone);*/
		
		
		//OrderVo 객체 생성
		OrderVo vo = new OrderVo();
		//ORDER_CODE : SEQUENCE
		vo.setUserIdFk(userId);
		vo.setProductCodePk(request.getParameter("productCode"));
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
		vo.setOrderAmount(orderAmount);
		//ORDER_STATE : DEFAULT 0
		//ORDER_DATE : SYSDATE
		
		OrderDao dao = new OrderDao();
		int result = dao.order(vo, priceSale);
		
		
		System.out.println("productordercationcmd.end");
		return "/main.do";
		
	}//end execute method
}//end class
