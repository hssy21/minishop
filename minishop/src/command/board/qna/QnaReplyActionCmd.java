package command.board.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.QnaBoardDao;
import util.str.StrUtil;
import vo.QnaBoardVo;

public class QnaReplyActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��� ����
		System.out.println("QnaReplyActionCmd-Start");
		
		HttpSession session= request.getSession();
		
		String userId= null;
		int qnaIdx= 0;
		QnaBoardDao dao= new QnaBoardDao();
		
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
		
		if(request.getParameter("qnaIdx")!= null){
			qnaIdx= Integer.parseInt(request.getParameter("qnaIdx"));
		}
		
			
		//�α��� üũ
		if(userId== null){
			request.setAttribute("errorType", "isNotLogin");
			return "/error/board_error.jsp";	
		}
		
		//�Խñ� ��ȣ �Ķ���� ���� �������� ���� �����ͺ��̽� ���� ����
		if(qnaIdx <= 0 || dao.getNextIdx() <= qnaIdx){
			request.setAttribute("errorType", "invalidContent");
			return "/error/access_error.jsp";
		}
		
		//�����ڰ� �ƴ� ��� ��� ���� �Ұ�
		if(!userId.equals("admin")){
			request.setAttribute("errorType", "isNotAdmin");
			return "/error/access_error.jsp";
		}
		
		
		//�Է� �� �� ����ִ� ���� �ִ� ��� üũ
		if(request.getParameter("qnaTitle")== null || request.getParameter("qnaContent")== null
				|| request.getParameter("qnaTitle").equals("") || request.getParameter("qnaContent").equals("")){
			request.setAttribute("errorType", "isNull");
			return "/error/board_error.jsp";
			
		}
		
		
		//XSS ��ũ��Ʈ ����
		StrUtil strUtil= new StrUtil();
		
		String qnaTitle= strUtil.cleanXSS(request.getParameter("qnaTitle"));
		String qnaContent= strUtil.cleanXSS(request.getParameter("qnaContent"));
		
		int qnaGroup= Integer.parseInt(request.getParameter("qnaGroup"));
		int qnaStep= Integer.parseInt(request.getParameter("qnaStep"));
		int qnaIndent= Integer.parseInt(request.getParameter("qnaIndent"));
		
		//�� ������Ʈ
		int result= dao.reply(qnaIdx, qnaTitle, qnaContent, userId, qnaGroup, qnaStep, qnaIndent);
		
		if(result== -1){
			request.setAttribute("errorType", "replyFail");
			return "/error/fatal_error.jsp";
			
		}else{
			System.out.println("QnaReplyActionCmd-End");
			return "QnaList.do";	
		
		}
		
	}//end execute method
}//end class
