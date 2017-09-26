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
		
		//��� ����
		System.out.println("NoticeWriteActionCmd-Start");
		
		HttpSession session= request.getSession();
		StrUtil strUtil= new StrUtil();
		
		//userId�� �α��� �Ǿ��� ��� �����Ǵ� ���� �Ӽ� ��
		String userId= null;
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
		
		//�α����� �Ǿ����� ���� ��� �� ���� �Ұ�
		if(userId== null){
			request.setAttribute("errorType", "isNotLogin");
			return "/error/board_error.jsp";
			
		}
		
		//�����ڰ� �ƴ� ��� �� ���� �Ұ�
		if(!userId.equals("admin")){
			request.setAttribute("errorType", "isNotAdmin");
			return "/error/board_error.jsp";
		}
		
		//�α����� �Ǿ� �ִ� ���
		NoticeBoardVo vo= new NoticeBoardVo();
		vo.setNoticeTitle(request.getParameter("noticeTitle"));
		vo.setNoticeContent(request.getParameter("noticeContent"));
		
		//����, ���� �� �� �ϳ��� ����ִ��� üũ
		if(vo.getNoticeTitle()== null || vo.getNoticeContent()== null){
			request.setAttribute("errorType", "isNull");
			return "/error/board_error.jsp";
			
		}
		
		//����, ���� ��� �ۼ��Ǿ��ٸ� �����ͺ��̽��� �� ���
		NoticeBoardDao dao= new NoticeBoardDao();

		//XSS ��ũ��Ʈ ����
		vo.setNoticeTitle(strUtil.cleanXSS(vo.getNoticeTitle()));
		vo.setNoticeContent(strUtil.cleanXSS(vo.getNoticeContent()));
		
		//�� ��� �õ�
		int result= dao.write(vo.getNoticeTitle(), vo.getNoticeContent(), userId);
		
		if (result == -1){
			//�����ͺ��̽��� ������ �Է� ���� ���� ���� �߻��� ó��
			request.setAttribute("errorType", "writeFail");
			return "/error/fatal_error.jsp";
			
		}else {
			//�� ���� ���
			System.out.println("NoticeWriteActionCmd-End");
			return "NoticeList.do";
			
		}	
		
	}//end execute method
}//end class
