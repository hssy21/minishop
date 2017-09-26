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
		
		//관리자가 아닌 경우 사용 불가
		if(!userId.equals("admin")){
			request.setAttribute("errorType", "isNotAdmin");
			return "/error/admin_error.jsp";
		}
		
		
		// 파일이 저장될 서버의 경로
		// String savePath = "D:/Projects/workspace/projectName/WebContent/폴더이름";
		String savePath = request.getServletContext().getRealPath("/resources/images/products");
		System.out.println("저장경로: " + savePath); 
		
		// 파일 크기 15MB로 제한
		int sizeLimit = 1024*1024*15;
		
		//  ↓ request 객체,               ↓ 저장될 서버 경로,       ↓ 파일 최대 크기,    ↓ 인코딩 방식,       ↓ 같은 이름의 파일명 방지 처리
		// (HttpServletRequest request, String saveDirectory, int sizeLimit, String encoding, FileRenamePolicy policy)
		// 아래와 같이 MultipartRequest를 생성만 해주면 파일이 업로드 된다.(파일 자체의 업로드 완료)
		System.out.println("1");
		MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		
		System.out.println("2");
		
		// MultipartRequest로 전송받은 데이터를 불러온다.
		// enctype을 "multipart/form-data"로 선언하고 submit한 데이터들은 request객체가 아닌 MultipartRequest객체로 불러와야 한다.
		 
		// 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
		// String fileName = multi.getFilesystemName("m_file");
		 
		// 업로드한 파일의 전체 경로를 DB에 저장하기 위함
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
