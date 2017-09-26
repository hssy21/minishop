package command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.UserDao;
import vo.UserVo;

public class UserModifyViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��� ����
		System.out.println("UserModifyViewCmd-Start");
		
		HttpSession session= request.getSession();
		
		String userId= null;
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		
		}else{
			//�α��� �Ǿ� ���� ������ ȸ�� ���� ���� �������� ���� �Ұ���
			return "/error/access_error.jsp";
		}
		
		UserDao dao= new UserDao();
		UserVo vo= dao.getUserInfo(userId);
		
		request.setAttribute("vo", vo);
		
		return "/user/user_modify.jsp";
		
	}//end execute method
}//end class
