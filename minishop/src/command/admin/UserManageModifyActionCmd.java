package command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.UserDao;
import vo.UserVo;

public class UserManageModifyActionCmd implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��� ����
		System.out.println("UserManageModifyActionCmd-Start");
		
		HttpSession session= request.getSession();
		
		String sessionId= null;
		
		if(session.getAttribute("userId")!= null){
			sessionId= (String) session.getAttribute("userId");
		}
		
		//�����ڰ� �ƴ� ��� ��� �Ұ�
		if(!sessionId.equals("admin")){
			request.setAttribute("errorType", "isNotAdmin");
			return "/error/admin_error.jsp";
		}
		
		
		String userId= null;
		
		if(request.getParameter("userId")!= null){
			userId= request.getParameter("userId");
		
		}else{			
			return "/error/access_error.jsp";
		}
		
		UserDao dao= new UserDao();
		UserVo vo= dao.getUserInfo(userId);	//���� ���� �ҷ���
		
		System.out.println(request.getParameter("newUserPassword1"));
		System.out.println(request.getParameter("newUserPassword2"));
		
		//�� ��й�ȣ 1,2 ��ġ ���� üũ
		if(!request.getParameter("newUserPassword1").equals(request.getParameter("newUserPassword2"))){
			request.setAttribute("errorType", "passwordNotSame");
			return "/error/user_error.jsp";
			
		}
		
		//�� ��й�ȣ�� �Էµ� ��쿡�� ����
		if(!request.getParameter("newUserPassword1").equals("")){
			vo.setUserPassword(request.getParameter("newUserPassword1"));
		}
		
		vo.setUserName(request.getParameter("userName"));
		vo.setUserAddress1(request.getParameter("userAddress1"));
		vo.setUserAddress2(request.getParameter("userAddress2"));
		vo.setUserPhone(request.getParameter("userPhone"));
		vo.setUserEmail(request.getParameter("userEmail"));
		
		int result2= dao.modify(vo);
		
		if(result2== -1){
			request.setAttribute("errorType", "modifyFail");
			return "/error/fatal_error.jsp";
			
		}else{
			request.setAttribute("errorType", "modifyUserSuccess");
			System.out.println("UserManageModifyActionCmd-End");
			return "/error/admin_error.jsp";	
		}
		
	
	}//end excute method
}//end class
