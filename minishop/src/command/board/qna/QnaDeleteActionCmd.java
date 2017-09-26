package command.board.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.QnaBoardDao;
import vo.QnaBoardVo;

public class QnaDeleteActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��� ����
		System.out.println("QnaDeleteActionCmd-Start");
		HttpSession session= request.getSession();
		
		String userId= null;
		int qnaIdx= 0;
		int qnaGroup= 0;
		int qnaStep= 0;
		QnaBoardDao dao= new QnaBoardDao();
		
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
		
		if(request.getParameter("qnaIdx")!= null){
			qnaIdx= Integer.parseInt(request.getParameter("qnaIdx"));
		}
		
		if(request.getParameter("qnaGroup")!= null){
			qnaGroup= Integer.parseInt(request.getParameter("qnaGroup"));
		}
		
		if(request.getParameter("qnaStep")!= null){
			qnaStep= Integer.parseInt(request.getParameter("qnaStep"));
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
		
		
		//�� �ۼ��� �Ǵ� �����ڰ� �ƴ� ��� �ۻ��� ����
		QnaBoardVo vo= dao.getBoard(qnaIdx);
		
		if(!userId.equals(vo.getQnaWriter()) && !userId.equals("admin")){
			request.setAttribute("errorType", "notAuth");
			return "/error/board_error.jsp";
		}
		
		//�� ����
		int result= dao.delete(qnaIdx, qnaGroup, qnaStep);
		
		if(result== -1){
			request.setAttribute("errorType", "deleteFail");
			return "/error/fatal_error.jsp";
		
		}else{
			System.out.println("QnaDeleteActionCmd-End");
			return "QnaList.do";	
		
		}
		
	}//end execute method
}//end class
