package command.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import dao.ProductDao;
import vo.ProductVo;

public class ProductListCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//기능 구현
		System.out.println("ProductListCmd-Start");
		
		String categoryCode = request.getParameter("category_code");
		
		ProductDao dao = new ProductDao();
		ArrayList<ProductVo> voList= null;
		
		//카테고리 메뉴 클릭시 category_code 값에 all이 기본적으로 들어 가며, 각 카테고리 선택시에는 선택한 값이 들어감.
		if(categoryCode.equals("all")){
			voList= dao.getProductListAll();
		}else{
			voList= dao.getProductListByCtgry(categoryCode);
		}
		
		request.setAttribute("voList", voList);
		
		System.out.println("ProductListCmd-End");
		return "/product/product_list.jsp";
		
	}//end execute method
}//end class
