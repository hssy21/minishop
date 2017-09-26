package command.board.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import dao.NoticeBoardDao;
import vo.NoticeBoardVo;

public class NoticeUpdateViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//기능 구현
		int noticeIdx= Integer.parseInt(request.getParameter("noticeIdx"));
		
		NoticeBoardDao dao= new NoticeBoardDao();
		NoticeBoardVo vo= dao.getBoard(noticeIdx);
		
		request.setAttribute("vo", vo);
		
		return "/board/notice_update.jsp";
		
	}//end execute method
}//end class
