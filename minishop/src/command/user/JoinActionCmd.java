package command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.UserDao;
import vo.UserVo;

public class JoinActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��� ����
		System.out.println("JoinActionCmd-Start");
		
		HttpSession session= request.getSession();
		
		//�α��εǾ� �ִ��� üũ
		if(session.getAttribute("userId")!= null){

			//�̷̹α��� �Ǿ� �ִ� ��� ���� ���, �̹� �α��εǾ� �ִ°�� ȸ���������� ������ �� �ִ� �޴��� �����.
			return "/error/access_error.jsp";
		}
		
		//ȸ������ ������ �޾ƿ� �����͸� VO Ŭ������ ����
		UserVo vo= new UserVo();
		
		vo.setUserId(request.getParameter("userId"));
		vo.setUserPassword(request.getParameter("userPassword1"));
		vo.setUserName(request.getParameter("userName"));
		vo.setUserAddress1(request.getParameter("userAddress1"));
		vo.setUserAddress2(request.getParameter("userAddress2"));
		vo.setUserPhone(request.getParameter("userPhone"));
		vo.setUserEmail(request.getParameter("userEmail"));

		
		//��й�ȣ 1,2 ��ġ ���� üũ
		if(!vo.getUserPassword().equals(request.getParameter("userPassword2"))){
			request.setAttribute("errorType", "passwordNotSame");
			return "/error/user_error.jsp";
			
		}

		//�Է� ���� ���� ���� �ִ��� üũ
		if(vo.getUserId().equals("") || vo.getUserPassword().equals("") ||
				vo.getUserName().equals("") || vo.getUserAddress1().equals("") ||
				vo.getUserAddress2().equals("") || vo.getUserPhone().equals("") ||
				vo.getUserEmail().equals("")){
			
			request.setAttribute("errorType", "isNull");
			return "/error/user_error.jsp";
		}
		
		//'-' ó��
		if(vo.getUserPhone().contains("-")){
			request.setAttribute("errorType", "phone");
			return "/error/user_error.jsp";
			
		}

		UserDao dao= new UserDao();
		
		//���̵� �ߺ� üũ
		if(dao.isId(vo.getUserId())== 1){
			request.setAttribute("errorType", "isDuplicated");
			return "/error/user_error.jsp";
			
		}
		
		//ȸ�� �����ͺ��̽� ���
		int result= dao.join(vo);
		
		if(result== -1){
			request.setAttribute("errorType", "joinFail");
			return "/error/fatal_error.jsp";
			
			/*}else{
			session.setAttribute("userId", vo.getUserId());
			System.out.println("JoinActionCmd-End");
			return "/main.do";	
		}*/      
		
		}else{
			session.setAttribute("userId", vo.getUserId());
			System.out.println("JoinActionCmd-End");
			request.setAttribute("successType", "join");
			return "/error/success.jsp";   
		}
			
	}//end execute method
}//end class
