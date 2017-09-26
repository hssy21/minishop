package command.board.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.NoticeBoardDao;
import vo.NoticeBoardVo;

public class NoticeDeleteActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//기능 구현
		System.out.println("NoticeDeleteActionCmd-Start");
		HttpSession session= request.getSession();
		
		String userId= null;
		int noticeIdx= 0;
		NoticeBoardDao dao= new NoticeBoardDao();
		
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
		
		if(request.getParameter("noticeIdx")!= null){
			noticeIdx= Integer.parseInt(request.getParameter("noticeIdx"));
		}
		
			
		//로그인 체크
		if(userId== null){
			request.setAttribute("errorType", "isNotLogin");
			return "/error/board_error.jsp";	
		}
		
		//게시글 번호 파라미터 임의 조작으로 인한 데이터베이스 오류 방지
		if(noticeIdx <= 0 || dao.getNextIdx() <= noticeIdx){
			request.setAttribute("errorType", "invalidContent");
			return "/error/access_error.jsp";
		}
		
		
		//글 작성자가 아닌 경우 글삭제 금지
		NoticeBoardVo vo= dao.getBoard(noticeIdx);
		
		if(!userId.equals(vo.getNoticeWriter())){
			request.setAttribute("errorType", "notAuth");
			return "/error/board_error.jsp";
		}
		
		//글 삭제
		int result= dao.delete(noticeIdx);
		
		if(result== -1){
			request.setAttribute("errorType", "deleteFail");
			return "/error/fatal_error.jsp";
		
		}else{
			System.out.println("NoticeDeleteActionCmd-End");
			return "NoticeList.do";	
		
		}
		
	}//end execute method
}//end class
