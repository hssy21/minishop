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

public class QnaUpdateActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//기능 구현
		System.out.println("QnaUpdateActionCmd-Start");
		
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
		
			
		//로그인 체크
		if(userId== null){
			request.setAttribute("errorType", "isNotLogin");
			return "/error/board_error.jsp";	
		}
		
		//게시글 번호 파라미터 임의 조작으로 인한 데이터베이스 오류 방지
		if(qnaIdx <= 0 || dao.getNextIdx() <= qnaIdx){
			request.setAttribute("errorType", "invalidContent");
			return "/error/access_error.jsp";
		}
		
		
		//글 작성자가 아닌 경우 글수정 금지
		QnaBoardVo vo= dao.getBoard(qnaIdx);
		
		if(!userId.equals(vo.getQnaWriter())){
			request.setAttribute("errorType", "notAuth");
			return "/error/board_error.jsp";
		}
		
		//입력 값 중 비어있는 값이 있는 경우 체크
		if(request.getParameter("qnaTitle")== null || request.getParameter("qnaContent")== null
				|| request.getParameter("qnaTitle").equals("") || request.getParameter("qnaContent").equals("")){
			request.setAttribute("errorType", "isNull");
			return "/error/board_error.jsp";
			
		}
		
		
		//XSS 스크립트 제거
		StrUtil strUtil= new StrUtil();
		
		String qnaTitle= strUtil.cleanXSS(request.getParameter("qnaTitle"));
		String qnaContent= strUtil.cleanXSS(request.getParameter("qnaContent"));
		
		//글 업데이트
		int result= dao.update(qnaIdx, qnaTitle, qnaContent);
		
		
		if(result== -1){
			request.setAttribute("errorType", "updateFail");
			return "/error/fatal_error.jsp";
			
		}else{
			System.out.println("QnaUpdateActionCmd-End");
			return "QnaContentView.do?qnaIdx="+qnaIdx;	
		
		}
				
	}//end execute method
}//end class
