package command.board.qna;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import dao.QnaBoardDao;
import vo.QnaBoardVo;

public class QnaListCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("QnaListCmd-Start");
		
		//현재 페이지 설정, 없으면 첫 페이지로 설정
		int pageNumber= 1;
		if (request.getParameter("pageNumber") != null){
			pageNumber= Integer.parseInt(request.getParameter("pageNumber"));
		}
		request.setAttribute("pageNumber", pageNumber);
		
		//데이터베이스에서 게시판 글 리스트를 받아옴
		QnaBoardDao dao= new QnaBoardDao();
		ArrayList<QnaBoardVo> voList= dao.getList(pageNumber);
		
		request.setAttribute("voList", voList);
		
		//페이지 선택 기능을 위해 다음 페이지가 있는지 확인
		boolean isNextPage= dao.nextPage(pageNumber+1);
		request.setAttribute("isNextPage", isNextPage);
		
		
		System.out.println("QnaListCmd-End");
		return "/board/qna_board.jsp";
		
	}//end execute method
}//end class
