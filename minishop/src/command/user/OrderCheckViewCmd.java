package command.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.OrderDao;

public class OrderCheckViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		
		//userId 세션 값 가져오기
		String userId= null;
		
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
					
		//로그인 체크
		if(userId== null){
			request.setAttribute("errorType", "isNotLogin");
			return "/error/user_error.jsp";	
		}
		
		OrderDao dao = new OrderDao();
		ArrayList<HashMap<String, String>> voList = dao.getOrderViewList(userId);
		
		request.setAttribute("voList", voList);
		return "/user/order_check.jsp";
		
	}//end execute method
}//end class
