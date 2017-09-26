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

public class QnaWriteActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��� ����
		System.out.println("QnaWriteActionCmd-Start");
		
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
		
		//�α����� �Ǿ� �ִ� ���
		QnaBoardVo vo= new QnaBoardVo();
		vo.setQnaTitle(request.getParameter("qnaTitle"));
		vo.setQnaContent(request.getParameter("qnaContent"));
		
		//����, ���� �� �� �ϳ��� ����ִ��� üũ
		if(vo.getQnaTitle()== null || vo.getQnaContent()== null){
			request.setAttribute("errorType", "isNull");
			return "/error/board_error.jsp";
			
		}
		
		//����, ���� ��� �ۼ��Ǿ��ٸ� �����ͺ��̽��� �� ���
		QnaBoardDao dao= new QnaBoardDao();

		//XSS ��ũ��Ʈ ����
		vo.setQnaTitle(strUtil.cleanXSS(vo.getQnaTitle()));
		vo.setQnaContent(strUtil.cleanXSS(vo.getQnaContent()));
		
		//�� ��� �õ�
		int result= dao.write(vo.getQnaTitle(), vo.getQnaContent(), userId);
		
		if (result == -1){
			//�����ͺ��̽��� ������ �Է� ���� ���� ���� �߻��� ó��
			request.setAttribute("errorType", "writeFail");
			return "/error/fatal_error.jsp";
			
		}else {
			//�� ���� ���
			System.out.println("QnaWriteActionCmd-End");
			return "QnaList.do";
			
		}
		
	}//end execute method
}//end class
