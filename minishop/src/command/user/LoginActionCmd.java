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
		
		//��� ����
		System.out.println("LoginActionCmd-Start");
		
		HttpSession session= request.getSession();
		
		
		//�α��εǾ� �ִ��� üũ
		if(session.getAttribute("userId")!= null){
			
			//�̷̹α��� �Ǿ� �ִ� ��� ���� ���, �̹� �α��εǾ� �ִ°�� �α������� ������ �� �ִ� �޴��� �����.
			return "/error/access_error.jsp";	
		}
		
		String userId= request.getParameter("userId");
		String userPassword= request.getParameter("userPassword");
		
		UserDao dao= new UserDao();
		int result= dao.UserAuth(userId, userPassword);
		
		//�α��� ����
		if(result== 1){
			session.setAttribute("userId", userId);
			System.out.println("LoginActionCmd-End");
			return "/main.do";
		
		//�α��� ����
		}else if(result== 0){
			request.setAttribute("errorType", "loginError");
			return "/error/user_error.jsp";
		
		}else if(result== -1){
			//�����ͺ��̽��� ������ �Է� ���� ���� ���� �߻��� ó��
			request.setAttribute("errorType", "authFail");
			return "/error/fatal_error.jsp";
			
		}
		
		return null;
		
	}//end execute method
}//end class
