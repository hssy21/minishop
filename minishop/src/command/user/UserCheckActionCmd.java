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
		
		//��� ����
		System.out.println("UserCheckActionCmd-Start");
		
		HttpSession session = request.getSession();
		
		//response.setContentType("text/xml;charset=utf-8");
		
		//�α��εǾ� �ִ��� üũ
		if(session.getAttribute("userId")!= null){
			//�̷̹α��� �Ǿ� �ִ� ��� ���� ���, �̹� �α��εǾ� �ִ°�� ȸ���������� ������ �� �ִ� �޴��� �����.
			return "/error/access_error.jsp";
		}
		
		//ȸ�� �ߺ� üũ
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
