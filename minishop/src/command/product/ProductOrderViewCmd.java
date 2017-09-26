package command.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.ProductDao;
import dao.UserDao;
import vo.ProductVo;
import vo.UserVo;

public class ProductOrderViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("productorderview");
		
		HttpSession session= request.getSession();
		String userId= null;
		
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
				
		//로그인 체크
		if(userId== null){
			request.setAttribute("errorType", "isNotLogin");
			return "/error/product_error.jsp";	
		}
		
		
		String productCode= request.getParameter("productCode");
		String orderQnty= request.getParameter("orderQnty");
		
		//상품정보 가져오기
		ProductDao dao= new ProductDao();
		ProductVo vo= dao.getProduct(productCode);
		
		request.setAttribute("vo", vo);
		request.setAttribute("orderQnty", orderQnty);
		
		
		//사용자정보 가져오기
		UserDao dao2= new UserDao();
		UserVo vo2= dao2.getUserInfo(userId);
		
		request.setAttribute("vo2", vo2);
		
		
		System.out.println("productorderview.end");
		return "/user/product_order.jsp";
		
	}//end execute method
}//end class
