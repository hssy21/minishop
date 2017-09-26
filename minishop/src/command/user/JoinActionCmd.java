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
		
		//기능 구현
		System.out.println("JoinActionCmd-Start");
		
		HttpSession session= request.getSession();
		
		//로그인되어 있는지 체크
		if(session.getAttribute("userId")!= null){

			//이미로그인 되어 있는 경우 에러 출력, 이미 로그인되어 있는경우 회원가입으로 접근할 수 있는 메뉴가 사라짐.
			return "/error/access_error.jsp";
		}
		
		//회원가입 폼에서 받아온 데이터를 VO 클래스에 삽입
		UserVo vo= new UserVo();
		
		vo.setUserId(request.getParameter("userId"));
		vo.setUserPassword(request.getParameter("userPassword1"));
		vo.setUserName(request.getParameter("userName"));
		vo.setUserAddress1(request.getParameter("userAddress1"));
		vo.setUserAddress2(request.getParameter("userAddress2"));
		vo.setUserPhone(request.getParameter("userPhone"));
		vo.setUserEmail(request.getParameter("userEmail"));

		
		//비밀번호 1,2 일치 여부 체크
		if(!vo.getUserPassword().equals(request.getParameter("userPassword2"))){
			request.setAttribute("errorType", "passwordNotSame");
			return "/error/user_error.jsp";
			
		}

		//입력 되지 않은 값이 있는지 체크
		if(vo.getUserId().equals("") || vo.getUserPassword().equals("") ||
				vo.getUserName().equals("") || vo.getUserAddress1().equals("") ||
				vo.getUserAddress2().equals("") || vo.getUserPhone().equals("") ||
				vo.getUserEmail().equals("")){
			
			request.setAttribute("errorType", "isNull");
			return "/error/user_error.jsp";
		}
		
		//'-' 처리
		if(vo.getUserPhone().contains("-")){
			request.setAttribute("errorType", "phone");
			return "/error/user_error.jsp";
			
		}

		UserDao dao= new UserDao();
		
		//아이디 중복 체크
		if(dao.isId(vo.getUserId())== 1){
			request.setAttribute("errorType", "isDuplicated");
			return "/error/user_error.jsp";
			
		}
		
		//회원 데이터베이스 등록
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
