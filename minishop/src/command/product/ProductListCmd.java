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
		
		//��� ����
		System.out.println("ProductListCmd-Start");
		
		String categoryCode = request.getParameter("category_code");
		
		ProductDao dao = new ProductDao();
		ArrayList<ProductVo> voList= null;
		
		//ī�װ� �޴� Ŭ���� category_code ���� all�� �⺻������ ��� ����, �� ī�װ� ���ýÿ��� ������ ���� ��.
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
