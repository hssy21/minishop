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
		
		//기능 구현
		System.out.println("UserModifyActionCmd-Start");
		HttpSession session= request.getSession();
		
		String userId= null;
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		
		}else{
			//로그인 되어 있지 않으면 회원 정보 수정  불가능
			return "/error/access_error.jsp";
		}
		

		UserDao dao= new UserDao();
		UserVo vo= dao.getUserInfo(userId);	//이전 정보 불러옴
		
		//현재 비밀번호 확인
		int result= dao.UserAuth(userId, request.getParameter("currentUserPassword"));
		
			//비밀번호 틀림
			if(result== 0){
				request.setAttribute("errorType", "authError");
				return "/error/user_error.jsp";
			
			}else if(result== -1){
				//데이터베이스에 데이터 입력 과정 에서 오류 발생시 처리
				request.setAttribute("errorType", "authFail");
				return "/error/fatal_error.jsp";	
			}
		

		//새 비밀번호 1,2 일치 여부 체크
		if(!request.getParameter("newUserPassword1").equals(request.getParameter("newUserPassword2"))){
			request.setAttribute("errorType", "passwordNotSame");
			return "/error/user_error.jsp";
			
		}
		
		//새 비밀번호는 입력된 경우에만 변경
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
