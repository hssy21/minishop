package command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.NoticeBoardDao;
import dao.ProductDao;
import dao.QnaBoardDao;
import dao.UserDao;

public class AdminMainViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//기능 구현
		System.out.println("AdminMainViewCmd-Start");
		
		HttpSession session= request.getSession();
		
		String userId= null;
		
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
		
		//관리자가 아닌 경우 사용 불가
		if(!userId.equals("admin")){
			request.setAttribute("errorType", "isNotAdmin");
			return "/error/admin_error.jsp";
		}
		

		//회원count
		UserDao dao = new UserDao();
		String UserCount = dao.getTotalUsers("UserCount");
		
		//상품count
		ProductDao pao = new ProductDao();
		String ProductCount = pao.getTotalProducts("ProductCount");
		
		//공지게시글count
		NoticeBoardDao nao = new NoticeBoardDao();
		String NoticeCount = nao.getTotalNotice("NoticeCount");
		
		//Q&A게시글count
		QnaBoardDao qao = new QnaBoardDao();
		String QnaCount = qao.getTotalQna("QnaCount");
		
		//Q&A게시글delete
		QnaBoardDao deo = new QnaBoardDao();
		String delCount = deo.getDeleteTotalQna("delCount");
		
		request.setAttribute("UserCount", UserCount);
		request.setAttribute("ProductCount", ProductCount);
		request.setAttribute("NoticeCount", NoticeCount);
		request.setAttribute("QnaCount", QnaCount);
		request.setAttribute("delCount", delCount);

		return "/admin/admin_main.jsp";
		
	}//end execute method
}//end class
