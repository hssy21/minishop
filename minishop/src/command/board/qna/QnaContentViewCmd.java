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
		
		//기능 구현
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
				
				//게시글 번호 파라미터 임의 조작으로 인한 데이터베이스 오류 방지
				if(qnaIdx <= 0 || dao.getNextIdx() <= qnaIdx){
					request.setAttribute("errorType", "accessError");
					return "/error/access_error.jsp";
				}
				
				
				//게시글 내용 request 객체에 저장
				vo= dao.getBoard(qnaIdx);
				
				//삭제된 게시글인지 확인
				if(vo.getQnaAvailable()==0){
					request.setAttribute("errorType", "isDeleted");
					return "/error/access_error.jsp";
				}
				
				
				//개행 및 띄어쓰기가 html내에서 제대로 표시되도록 변경	
				vo.setQnaTitle(strUtil.conToView(vo.getQnaTitle()));
				vo.setQnaContent(strUtil.conToView(vo.getQnaContent()));
				
				request.setAttribute("vo", vo);
				
				
				// 로그인한 사용자가 글쓴이인지 확인 (수정/삭제 버튼 enable/disable)
				boolean isWriter= false;
				if(userId != null && userId.equals(vo.getQnaWriter())){
					isWriter= true;
				}
				
				request.setAttribute("isWriter", isWriter);
				
				
				//조회수 1 증가
				dao.addHit(qnaIdx);
				
				
				System.out.println("QnaContentViewCmd-End");
				return "/board/qna_view.jsp";
				
	}//end execute method
}//end class
