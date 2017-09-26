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

public class QnaContentViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��� ����
				System.out.println("QnaContentViewCmd-Start");
				HttpSession session= request.getSession();
				
				String userId= null;
				if(session.getAttribute("userId")!= null){
					userId= (String) session.getAttribute("userId");
				}
				
				int qnaIdx= 0;
				if(request.getParameter("qnaIdx")!= null){
					qnaIdx= Integer.parseInt(request.getParameter("qnaIdx"));
				}
				
				StrUtil strUtil= new StrUtil();
				QnaBoardDao dao= new QnaBoardDao();
				QnaBoardVo vo= new QnaBoardVo();
				
				//�Խñ� ��ȣ �Ķ���� ���� �������� ���� �����ͺ��̽� ���� ����
				if(qnaIdx <= 0 || dao.getNextIdx() <= qnaIdx){
					request.setAttribute("errorType", "accessError");
					return "/error/access_error.jsp";
				}
				
				
				//�Խñ� ���� request ��ü�� ����
				vo= dao.getBoard(qnaIdx);
				
				//������ �Խñ����� Ȯ��
				if(vo.getQnaAvailable()==0){
					request.setAttribute("errorType", "isDeleted");
					return "/error/access_error.jsp";
				}
				
				
				//���� �� ���Ⱑ html������ ����� ǥ�õǵ��� ����	
				vo.setQnaTitle(strUtil.conToView(vo.getQnaTitle()));
				vo.setQnaContent(strUtil.conToView(vo.getQnaContent()));
				
				request.setAttribute("vo", vo);
				
				
				// �α����� ����ڰ� �۾������� Ȯ�� (����/���� ��ư enable/disable)
				boolean isWriter= false;
				if(userId != null && userId.equals(vo.getQnaWriter())){
					isWriter= true;
				}
				
				request.setAttribute("isWriter", isWriter);
				
				
				//��ȸ�� 1 ����
				dao.addHit(qnaIdx);
				
				
				System.out.println("QnaContentViewCmd-End");
				return "/board/qna_view.jsp";
				
	}//end execute method
}//end class
