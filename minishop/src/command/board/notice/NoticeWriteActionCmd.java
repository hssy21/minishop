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

public class NoticeWriteActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//기능 구현
		System.out.println("NoticeWriteActionCmd-Start");
		
		HttpSession session= request.getSession();
		StrUtil strUtil= new StrUtil();
		
		//userId는 로그인 되었을 경우 생성되는 세션 속성 값
		String userId= null;
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
		
		//로그인이 되어있지 않은 경우 글 쓰기 불가
		if(userId== null){
			request.setAttribute("errorType", "isNotLogin");
			return "/error/board_error.jsp";
			
		}
		
		//관리자가 아닌 경우 글 쓰기 불가
		if(!userId.equals("admin")){
			request.setAttribute("errorType", "isNotAdmin");
			return "/error/board_error.jsp";
		}
		
		//로그인이 되어 있는 경우
		NoticeBoardVo vo= new NoticeBoardVo();
		vo.setNoticeTitle(request.getParameter("noticeTitle"));
		vo.setNoticeContent(request.getParameter("noticeContent"));
		
		//제목, 내용 둘 중 하나가 비어있는지 체크
		if(vo.getNoticeTitle()== null || vo.getNoticeContent()== null){
			request.setAttribute("errorType", "isNull");
			return "/error/board_error.jsp";
			
		}
		
		//제목, 내용 모두 작성되었다면 데이터베이스에 글 등록
		NoticeBoardDao dao= new NoticeBoardDao();

		//XSS 스크립트 제거
		vo.setNoticeTitle(strUtil.cleanXSS(vo.getNoticeTitle()));
		vo.setNoticeContent(strUtil.cleanXSS(vo.getNoticeContent()));
		
		//글 등록 시도
		int result= dao.write(vo.getNoticeTitle(), vo.getNoticeContent(), userId);
		
		if (result == -1){
			//데이터베이스에 데이터 입력 과정 에서 오류 발생시 처리
			request.setAttribute("errorType", "writeFail");
			return "/error/fatal_error.jsp";
			
		}else {
			//글 정상 등록
			System.out.println("NoticeWriteActionCmd-End");
			return "NoticeList.do";
			
		}	
		
	}//end execute method
}//end class
