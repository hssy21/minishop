package command.board.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import dao.QnaBoardDao;
import vo.QnaBoardVo;

public class QnaReplyViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//기능 구현
		int qnaIdx= Integer.parseInt(request.getParameter("qnaIdx"));
		
		QnaBoardDao dao= new QnaBoardDao();
		QnaBoardVo vo= dao.getBoard(qnaIdx);
		
		vo.setQnaContent(vo.getQnaContent() + "\r\n" + "---------------------------------------------------" + "\r\n" );
		
		request.setAttribute("vo", vo);
		
		return "/board/qna_reply.jsp";
		
	}//end execute method
}//end class
