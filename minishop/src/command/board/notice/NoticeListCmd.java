package command.board.notice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import dao.NoticeBoardDao;
import vo.NoticeBoardVo;

public class NoticeListCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("NoticeListCmd-Start");
		
		//���� ������ ����, ������ ù �������� ����
		int pageNumber= 1;
		if (request.getParameter("pageNumber") != null){
			pageNumber= Integer.parseInt(request.getParameter("pageNumber"));
		}
		request.setAttribute("pageNumber", pageNumber);
		
		//�����ͺ��̽����� �Խ��� �� ����Ʈ�� �޾ƿ�
		NoticeBoardDao dao= new NoticeBoardDao();
		ArrayList<NoticeBoardVo> voList= dao.getList(pageNumber);
		
		
		request.setAttribute("voList", voList);
		
		//������ ���� ����� ���� ���� �������� �ִ��� Ȯ��
		boolean isNextPage= dao.nextPage(pageNumber+1);
		request.setAttribute("isNextPage", isNextPage);
		
		
		System.out.println("NoticeListCmd-End");
		return "/board/notice_board.jsp";
		
	}//end execute method
}//end class
