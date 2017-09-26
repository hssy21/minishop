package command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.UserDao;

public class LoginActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//기능 구현
		System.out.println("LoginActionCmd-Start");
		
		HttpSession session= request.getSession();
		
		
		//로그인되어 있는지 체크
		if(session.getAttribute("userId")!= null){
			
			//이미로그인 되어 있는 경우 에러 출력, 이미 로그인되어 있는경우 로그인으로 접근할 수 있는 메뉴가 사라짐.
			return "/error/access_error.jsp";	
		}
		
		String userId= request.getParameter("userId");
		String userPassword= request.getParameter("userPassword");
		
		UserDao dao= new UserDao();
		int result= dao.UserAuth(userId, userPassword);
		
		//로그인 성공
		if(result== 1){
			session.setAttribute("userId", userId);
			System.out.println("LoginActionCmd-End");
			return "/main.do";
		
		//로그인 실패
		}else if(result== 0){
			request.setAttribute("errorType", "loginError");
			return "/error/user_error.jsp";
		
		}else if(result== -1){
			//데이터베이스에 데이터 입력 과정 에서 오류 발생시 처리
			request.setAttribute("errorType", "authFail");
			return "/error/fatal_error.jsp";
			
		}
		
		return null;
		
	}//end execute method
}//end class
