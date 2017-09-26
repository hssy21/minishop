package command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.UserDao;
import vo.UserVo;

public class UserManageModifyViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//기능 구현
		System.out.println("UserModifyViewCmd-Start");
	
		String userId= null;
		
		System.out.println("request.getParameter(userId)" + request.getParameter("userId"));
		
		if(request.getParameter("userId")!= null){
			userId= request.getParameter("userId");
		
		}else{			
			return "/error/access_error.jsp";
		}
		
		
		UserDao dao= new UserDao();
		UserVo vo= dao.getUserInfo(userId);
		request.setAttribute("vo", vo);
		
		return "/admin/user_manage_detail.jsp";
		
	}//end execute method
}//end class
