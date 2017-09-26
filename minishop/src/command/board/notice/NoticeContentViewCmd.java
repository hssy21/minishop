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
		
		//��� ����
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
		
		//�Խñ� ��ȣ �Ķ���� ���� �������� ���� �����ͺ��̽� ���� ����
		if(noticeIdx <= 0 || dao.getNextIdx() <= noticeIdx){
			request.setAttribute("errorType", "accessError");
			return "/error/access_error.jsp";
		}
		
		
		//�Խñ� ���� request ��ü�� ����
		vo= dao.getBoard(noticeIdx);
		
		
		//���� �� ���Ⱑ html������ ����� ǥ�õǵ��� ����	
		vo.setNoticeTitle(strUtil.conToView(vo.getNoticeTitle()));
		vo.setNoticeContent(strUtil.conToView(vo.getNoticeContent()));
		
		request.setAttribute("vo", vo);
		
		
		// �α����� ����ڰ� �۾������� Ȯ�� (����/���� ��ư enable/disable)
		boolean isWriter= false;
		if(userId != null && userId.equals(vo.getNoticeWriter())){
			isWriter= true;
		}
		
		request.setAttribute("isWriter", isWriter);
		
		
		//��ȸ�� 1 ����
		dao.addHit(noticeIdx);
		
		
		System.out.println("NoticeContentViewCmd-End");
		return "/board/notice_view.jsp";
		
	}//end execute method
}//end class
