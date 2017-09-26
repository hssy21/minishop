package command.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.UserDao;

public class UserCheckActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//기능 구현
		System.out.println("UserCheckActionCmd-Start");
		
		HttpSession session = request.getSession();
		
		//response.setContentType("text/xml;charset=utf-8");
		
		//로그인되어 있는지 체크
		if(session.getAttribute("userId")!= null){
			//이미로그인 되어 있는 경우 에러 출력, 이미 로그인되어 있는경우 회원가입으로 접근할 수 있는 메뉴가 사라짐.
			return "/error/access_error.jsp";
		}
		
		//회원 중복 체크
		String userId = request.getParameter("userId");
		
		UserDao dao = new UserDao();
		int result = dao.isId(userId);

		String ajaxMessage= null;
		if(result==1){
			ajaxMessage= "cant";
		}else{
			ajaxMessage= "can";
		}
		
		request.setAttribute("ajaxMessage", ajaxMessage);
		
		System.out.println("UserCheckActionCmd-End");
		return "/ajax.jsp";
		
	}//end execute method
}//end class
