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

public class NoticeContentViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//기능 구현
		System.out.println("NoticeContentViewCmd-Start");
		HttpSession session= request.getSession();
		
		String userId= null;
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
		
		int noticeIdx= 0;
		if(request.getParameter("noticeIdx")!= null){
			noticeIdx= Integer.parseInt(request.getParameter("noticeIdx"));
		}
		
		StrUtil strUtil= new StrUtil();
		NoticeBoardDao dao= new NoticeBoardDao();
		NoticeBoardVo vo= new NoticeBoardVo();
		
		//게시글 번호 파라미터 임의 조작으로 인한 데이터베이스 오류 방지
		if(noticeIdx <= 0 || dao.getNextIdx() <= noticeIdx){
			request.setAttribute("errorType", "accessError");
			return "/error/access_error.jsp";
		}
		
		
		//게시글 내용 request 객체에 저장
		vo= dao.getBoard(noticeIdx);
		
		
		//개행 및 띄어쓰기가 html내에서 제대로 표시되도록 변경	
		vo.setNoticeTitle(strUtil.conToView(vo.getNoticeTitle()));
		vo.setNoticeContent(strUtil.conToView(vo.getNoticeContent()));
		
		request.setAttribute("vo", vo);
		
		
		// 로그인한 사용자가 글쓴이인지 확인 (수정/삭제 버튼 enable/disable)
		boolean isWriter= false;
		if(userId != null && userId.equals(vo.getNoticeWriter())){
			isWriter= true;
		}
		
		request.setAttribute("isWriter", isWriter);
		
		
		//조회수 1 증가
		dao.addHit(noticeIdx);
		
		
		System.out.println("NoticeContentViewCmd-End");
		return "/board/notice_view.jsp";
		
	}//end execute method
}//end class
