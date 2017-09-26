package command.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import dao.ProductDao;
import vo.ProductVo;

public class ProductViewCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("productorViewCmd.start");
		
		String productCode = request.getParameter("productCode");
		
		ProductDao dao = new ProductDao();
		ProductVo vo = dao.getProduct(productCode);
		
		request.setAttribute("vo", vo);

		System.out.println("productViewCmd.end");
		return "/product/product_detail.jsp";
		
	}//end execute method
}//end class
