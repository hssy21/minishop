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
		
		//���� ������ ����, ������ ù �������� ����
		int pageNumber= 1;
		if (request.getParameter("pageNumber") != null){
			pageNumber= Integer.parseInt(request.getParameter("pageNumber"));
		}
		request.setAttribute("pageNumber", pageNumber);
		
		//�����ͺ��̽����� �Խ��� �� ����Ʈ�� �޾ƿ�
		QnaBoardDao dao= new QnaBoardDao();
		ArrayList<QnaBoardVo> voList= dao.getList(pageNumber);
		
		request.setAttribute("voList", voList);
		
		//������ ���� ����� ���� ���� �������� �ִ��� Ȯ��
		boolean isNextPage= dao.nextPage(pageNumber+1);
		request.setAttribute("isNextPage", isNextPage);
		
		
		System.out.println("QnaListCmd-End");
		return "/board/qna_board.jsp";
		
	}//end execute method
}//end class
