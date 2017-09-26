package command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.UserDao;
import vo.UserVo;

public class UserModifyActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��� ����
		System.out.println("UserModifyActionCmd-Start");
		HttpSession session= request.getSession();
		
		String userId= null;
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		
		}else{
			//�α��� �Ǿ� ���� ������ ȸ�� ���� ����  �Ұ���
			return "/error/access_error.jsp";
		}
		

		UserDao dao= new UserDao();
		UserVo vo= dao.getUserInfo(userId);	//���� ���� �ҷ���
		
		//���� ��й�ȣ Ȯ��
		int result= dao.UserAuth(userId, request.getParameter("currentUserPassword"));
		
			//��й�ȣ Ʋ��
			if(result== 0){
				request.setAttribute("errorType", "authError");
				return "/error/user_error.jsp";
			
			}else if(result== -1){
				//�����ͺ��̽��� ������ �Է� ���� ���� ���� �߻��� ó��
				request.setAttribute("errorType", "authFail");
				return "/error/fatal_error.jsp";	
			}
		

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
			request.setAttribute("successType", "modify");
			System.out.println("UserModifyActionCmd-End");
			return "/error/success.jsp";	
		}
		
	}//end execute method
}//end class
