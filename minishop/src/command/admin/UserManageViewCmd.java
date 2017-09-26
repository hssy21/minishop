package command.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.UserDao;
import vo.UserVo;

public class UserManageViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��� ����
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
		
		
		
		UserDao dao= new UserDao();
		ArrayList<UserVo> voList= dao.getUserList();
		
		request.setAttribute("voList", voList);
		
		return "/admin/user_manage.jsp";
	}//end execute method
}//end class
