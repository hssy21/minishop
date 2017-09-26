package command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import command.Command;
import dao.ProductDao;
import vo.ProductVo;

public class ProductAddActionCmd implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		
		String userId= null;
		
		if(session.getAttribute("userId")!= null){
			userId= (String) session.getAttribute("userId");
		}
		
		//�����ڰ� �ƴ� ��� ��� �Ұ�
		if(!userId.equals("admin")){
			request.setAttribute("errorType", "isNotAdmin");
			return "/error/admin_error.jsp";
		}
		
		
		// ������ ����� ������ ���
		// String savePath = "D:/Projects/workspace/projectName/WebContent/�����̸�";
		String savePath = request.getServletContext().getRealPath("/resources/images/products");
		System.out.println("������: " + savePath); 
		
		// ���� ũ�� 15MB�� ����
		int sizeLimit = 1024*1024*15;
		
		//  �� request ��ü,               �� ����� ���� ���,       �� ���� �ִ� ũ��,    �� ���ڵ� ���,       �� ���� �̸��� ���ϸ� ���� ó��
		// (HttpServletRequest request, String saveDirectory, int sizeLimit, String encoding, FileRenamePolicy policy)
		// �Ʒ��� ���� MultipartRequest�� ������ ���ָ� ������ ���ε� �ȴ�.(���� ��ü�� ���ε� �Ϸ�)
		System.out.println("1");
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		
		System.out.println("2");
		
		// MultipartRequest�� ���۹��� �����͸� �ҷ��´�.
		// enctype�� "multipart/form-data"�� �����ϰ� submit�� �����͵��� request��ü�� �ƴ� MultipartRequest��ü�� �ҷ��;� �Ѵ�.
		 
		// ���۹��� �����Ͱ� ������ ��� getFilesystemName()���� ���� �̸��� �޾ƿ� �� �ִ�.
		// String fileName = multi.getFilesystemName("m_file");
		 
		// ���ε��� ������ ��ü ��θ� DB�� �����ϱ� ����
		// String m_fileFullPath = savePath + "/" + fileName;
		
		ProductVo vo = new ProductVo();
		vo.setCategory1CodeFk(multi.getParameter("category1CodeFk"));
		vo.setProductName(multi.getParameter("productName"));
		vo.setProductCode(multi.getParameter("productCode"));
		vo.setProductCompany(multi.getParameter("productCompany"));
		vo.setPriceRetail(Integer.parseInt(multi.getParameter("priceRetail")));
		vo.setPriceSale(Integer.parseInt(multi.getParameter("priceSale")));
		vo.setProductQnty(Integer.parseInt(multi.getParameter("productQnty")));
		vo.setProductImage1(multi.getFilesystemName("productImages1"));
		vo.setProductImage2(multi.getFilesystemName("productImages2"));
		vo.setProductMessage(multi.getParameter("productMessage"));
		
		ProductDao dao = new ProductDao();
		int result = dao.productWrite(vo);
		
		return "/product/product_add.jsp";
	}//end execute method
}//end class
