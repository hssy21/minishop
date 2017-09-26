package frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import command.admin.*;
import command.board.notice.*;
import command.board.qna.*;
import command.product.*;
import command.user.*;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String viewPage= null;
		Command command= null;
		
		String uri= request.getRequestURI();
//		String conPath= request.getContextPath();
//		String com= uri.substring(conPath.length());
		String com= "/"+uri.substring(uri.lastIndexOf('/')+1);

		System.out.println("호출한 커맨드 : " + com);
		
		

		
		
		//관리자 페이지 기능
		if(com.equals("/AdminMainView.do")){
			command= new AdminMainViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/OrderDetailView.do")){
			command= new OrderDetailViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/OrderManageView.do")){
			command= new OrderManageViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/ProductAddAction.do")){
			command= new ProductAddActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/ProductManageView.do")){
			command= new ProductManageViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/UserManageView.do")){
			command= new UserManageViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/OrderModifyAction.do")){
			command= new OrderModifyActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/UserManageModifyView.do")){
			command= new UserManageModifyViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/UserManageModifyAction.do")){
			command= new UserManageModifyActionCmd();
			viewPage= command.execute(request, response);
			
		}
		
		
		
		//공지사항 게시판 기능
		else if(com.equals("/NoticeContentView.do")){
			command= new NoticeContentViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/NoticeDeleteAction.do")){
			command= new NoticeDeleteActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/NoticeList.do")){
			command= new NoticeListCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/NoticeUpdateAction.do")){
			command= new NoticeUpdateActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/NoticeUpdateView.do")){
			command= new NoticeUpdateViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/NoticeWriteAction.do")){
			command= new NoticeWriteActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/NoticeWriteView.do")){
			viewPage= "/board/notice_write.jsp";
			
		}
		
		
		//Q&A 게시판 기능
		else if(com.equals("/QnaContentView.do")){
			command= new QnaContentViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/QnaDeleteAction.do")){
			command= new QnaDeleteActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/QnaList.do")){
			command= new QnaListCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/QnaReplyAction.do")){
			command= new QnaReplyActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/QnaReplyView.do")){
			command= new QnaReplyViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/QnaUpdateAction.do")){
			command= new QnaUpdateActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/QnaUpdateView.do")){
			command= new QnaUpdateViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/QnaWriteAction.do")){
			command= new QnaWriteActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/QnaWriteView.do")){
			viewPage= "/board/qna_write.jsp";
			
		}
		
		
		//상품 페이지 기능
		else if(com.equals("/ProductList.do")){
			command= new ProductListCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/ProductOrderAction.do")){
			command= new ProductOrderActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/ProductOrderView.do")){
			command= new ProductOrderViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/ProductView.do")){
			command= new ProductViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/ProductSearch.do")){
			command= new ProductSearchCmd();
			viewPage= command.execute(request, response);
			
		}
		
		
		//사용자 페이지 기능
		else if(com.equals("/CartView.do")){
			command= new CartViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/JoinAction.do")){
			command= new JoinActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/LoginAction.do")){
			command= new LoginActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/LogoutAction.do")){
			command= new LogoutActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/OrderCheckView.do")){
			command= new OrderCheckViewCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/UserModifyAction.do")){
			command= new UserModifyActionCmd();
			viewPage= command.execute(request, response);
			
		}else if(com.equals("/UserModifyView.do")){
			command= new UserModifyViewCmd();
			viewPage= command.execute(request, response);
			
		}
		
	
		//회원아이디 중복 체크 ajax
		else if(com.equals("/UserCheckAction.do")){
			command = new UserCheckActionCmd();
			viewPage= command.execute(request, response);

		}
		
	
		else{
			command= new MainViewCmd();
			viewPage= command.execute(request, response);
		}
		
		
		RequestDispatcher dispatcher= request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
	

}
