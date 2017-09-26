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
		
		//��� ����
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
		
			
		//�α��� üũ
		if(userId== null){
			request.setAttribute("errorType", "isNotLogin");
			return "/error/board_error.jsp";	
		}
		
		//�Խñ� ��ȣ �Ķ���� ���� �������� ���� �����ͺ��̽� ���� ����
		if(noticeIdx <= 0 || dao.getNextIdx() <= noticeIdx){
			request.setAttribute("errorType", "invalidContent");
			return "/error/access_error.jsp";
		}
		
		
		//�� �ۼ��ڰ� �ƴ� ��� �ۻ��� ����
		NoticeBoardVo vo= dao.getBoard(noticeIdx);
		
		if(!userId.equals(vo.getNoticeWriter())){
			request.setAttribute("errorType", "notAuth");
			return "/error/board_error.jsp";
		}
		
		//�� ����
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
