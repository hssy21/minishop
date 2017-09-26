package command.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import dao.ProductDao;
import vo.ProductVo;

public class ProductSearchCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	System.out.println("productSearchCmd.strat");
	String search = request.getParameter("search");
	ProductDao dao = new ProductDao();
	ArrayList<ProductVo> voList = new ArrayList<ProductVo>();
	System.out.println("ctgryName =" + request.getParameter("ctgryName"));
	System.out.println("search =" + request.getParameter("search"));
	String sqlSub = "";
	if (request.getParameter("ctgryName")==null || request.getParameter("ctgryName").equals("모두")) {
		System.out.println("request.getParameter(nonctgryName) ok");
		voList = dao.getSearchAllList(search);
	}else{
		System.out.println("request.getParameter(ctgryName) ok");
		String ctgryName = request.getParameter("ctgryName");
		voList = dao.getSearchAllCtgryList(search, ctgryName);
	}

	//(request.getParameter("serchctgry")).equals("모두") || 
	
	
	
	//ArrayList<ProductVo> vo = dao.productList(categoryCode);
	request.setAttribute("voList", voList);
	System.out.println("productlistcmd.end");
	return "/product/product_search.jsp";
		 
	}//end execute method
}//end class
