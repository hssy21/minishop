package command.board.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.NoticeBoardDao;
import util.str.StrUtil;
import vo.NoticeBoardVo;

public class NoticeUpdateActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//기능 구현
		System.out.println("NoticeUpdateActionCmd-Start");
		
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
		
		//관리자가 아닌 경우 글 쓰기 불가
		if(!userId.equals("admin")){
			request.setAttribute("errorType", "isNotAdmin");
			return "/error/board_error.jsp";
		}
		
		//게시글 번호 파라미터 임의 조작으로 인한 데이터베이스 오류 방지
		if(noticeIdx <= 0 || dao.getNextIdx() <= noticeIdx){
			request.setAttribute("errorType", "invalidContent");
			return "/error/access_error.jsp";
		}
		
		
		//글 작성자가 아닌 경우 글수정 금지
		NoticeBoardVo vo= dao.getBoard(noticeIdx);
		
		if(!userId.equals(vo.getNoticeWriter())){
			request.setAttribute("errorType", "notAuth");
			return "/error/board_error.jsp";
		}
		
		//입력 값 중 비어있는 값이 있는 경우 체크
		if(request.getParameter("noticeTitle")== null || request.getParameter("noticeContent")== null
				|| request.getParameter("noticeTitle").equals("") || request.getParameter("noticeContent").equals("")){
			request.setAttribute("errorType", "isNull");
			return "/error/board_error.jsp";
			
		}
		
		
		//XSS 스크립트 제거
		StrUtil strUtil= new StrUtil();
		
		String noticeTitle= strUtil.cleanXSS(request.getParameter("noticeTitle"));
		String noticeContent= strUtil.cleanXSS(request.getParameter("noticeContent"));
		
		//글 업데이트
		int result= dao.update(noticeIdx, noticeTitle, noticeContent);
		
		
		if(result== -1){
			request.setAttribute("errorType", "updateFail");
			return "/error/fatal_error.jsp";
			
		}else{
			System.out.println("NoticeUpdateActionCmd-End");
			return "NoticeContentView.do?noticeIdx="+noticeIdx;	
		
		}

		
	}//end execute method
}//end class
