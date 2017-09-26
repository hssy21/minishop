package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import util.db.JdbcUtil;
import vo.ProductVo;

public class ProductDao {

	public ArrayList<ProductVo> orderCheckView(String userIdPk){
		System.out.println("productDao.orderCheckView.strat");
		System.out.println(userIdPk);
		
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String sql = "select product_code_pk, product_image1, product_name, product_message, price_sale, order_amount, order_state"
					+ " from orders o, products p where o.product_code_pk = p.product_code and o.user_id_fk =?";	
			
		try{	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userIdPk);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				ProductVo vo= new ProductVo();
				vo.setProductCode(rs.getString("product_Code"));
				vo.setProductName(rs.getString("product_Name"));
				vo.setProductCompany(rs.getString("product_Company"));
				vo.setPriceRetail(rs.getInt("price_Retail"));
				vo.setPriceSale(rs.getInt("price_Sale"));
				vo.setProductQnty(rs.getInt("product_Qnty"));
				vo.setProductImage1(rs.getString("product_Image1"));
				vo.setProductImage2(rs.getString("product_Image2"));
				vo.setProductMessage(rs.getString("product_Message"));
				vo.setProductDate(rs.getString("product_Date"));
				vo.setCategory1CodeFk(rs.getString("category1_Code_Fk"));
				
				list.add(vo);	
				System.out.println("orderCheckView.work");
			}		
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		System.out.println("productDao.orderCheckView.end");
		return list;
	}
	
	
	//상품정보 가져오기
	public ProductVo getProduct(String productCode){
		System.out.println("productDao.adminProductList.strat");
		System.out.println(productCode);
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String sql = "select * from products where product_code = ?";
		
		try{	
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productCode);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				ProductVo vo= new ProductVo();
				vo.setProductCode(rs.getString("product_code"));
				vo.setProductName(rs.getString("product_name"));
				vo.setProductCompany(rs.getString("product_company"));
				vo.setPriceRetail(rs.getInt("price_retail"));
				vo.setPriceSale(rs.getInt("price_sale"));
				vo.setProductQnty(rs.getInt("product_qnty"));
				vo.setProductImage1(rs.getString("product_image1"));
				vo.setProductImage2(rs.getString("product_image2"));
				vo.setProductMessage(rs.getString("product_message"));
				vo.setProductDate(rs.getString("product_date"));
				vo.setCategory1CodeFk(rs.getString("category1_code_fk"));
				
				System.out.println("dao.list_work");
				return vo;

			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	
	
	//전체 카테고리 상품 리스트 가져오기
	public ArrayList<ProductVo> getProductListAll(){
		ArrayList<ProductVo> voList= new ArrayList<>();
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL="SELECT * FROM products";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			
			while(rs.next()){
				ProductVo vo= new ProductVo();
				vo.setProductCode(rs.getString("product_code"));
				vo.setCategory1CodeFk(rs.getString("category1_code_fk"));
				vo.setProductName(rs.getString("product_name"));
				vo.setProductCompany(rs.getString("product_company"));
				vo.setPriceRetail(rs.getInt("price_retail"));
				vo.setPriceSale(rs.getInt("price_sale"));
				vo.setProductQnty(rs.getInt("product_qnty"));
				vo.setProductImage1(rs.getString("product_image1"));
				vo.setProductImage2(rs.getString("product_image2"));
				vo.setProductMessage(rs.getString("product_message"));
				vo.setProductDate(rs.getString("product_date"));
			
				voList.add(vo);
			}
			return voList;
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return null;
		
	}//end getProductListAll method
	
	
	//특정 카테고리에 해당하는 리스트 가져오기
	public ArrayList<ProductVo> getProductListByCtgry(String category_code){
		ArrayList<ProductVo> voList = new ArrayList<ProductVo>();
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "SELECT * FROM products WHERE category1_code_fk=?";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setString(1, category_code);
			rs= pstmt.executeQuery();
			
			while(rs.next()){
				ProductVo vo= new ProductVo();
				vo.setProductCode(rs.getString("product_code"));
				vo.setCategory1CodeFk(rs.getString("category1_code_fk"));
				vo.setProductName(rs.getString("product_name"));
				vo.setProductCompany(rs.getString("product_company"));
				vo.setPriceRetail(rs.getInt("price_retail"));
				vo.setPriceSale(rs.getInt("price_sale"));
				vo.setProductQnty(rs.getInt("product_qnty"));
				vo.setProductImage1(rs.getString("product_image1"));
				vo.setProductImage2(rs.getString("product_image2"));
				vo.setProductMessage(rs.getString("product_message"));
				vo.setProductDate(rs.getString("product_date"));
				
				voList.add(vo);
			}
			return voList;
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return null;
		
	}//getProductListByCtgry method end
	

	public ArrayList<HashMap<String, String>> adminProuctList(){
		System.out.println("productDao.adminProductList.strat");
		
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String sql = "SELECT product_name, product_message, product_image1, product_code FROM products";	

		try{
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				HashMap<String, String> map= new HashMap<>();
				map.put("productName", rs.getString("product_name"));
				map.put("productImage1", rs.getString("product_image1"));
				map.put("productMessage", rs.getString("product_message"));
				map.put("productCode", rs.getString("product_code"));
				
				list.add(map);

				System.out.println("dao.list_work");
			}
			return list;
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return list;
	}
	
	
	//상품 등록
	public int productWrite(ProductVo vo){
		System.out.println("productDao.prodctWrite.start");
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		int result =0;
		try{
			String sql = "insert into products (product_code, CATEGORY1_CODE_FK, product_name, product_company,"
					+ "price_retail, price_sale, product_qnty, product_image1, product_image2, product_message, product_date)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";	
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getProductCode());
			pstmt.setString(2, vo.getCategory1CodeFk());
			pstmt.setString(3, vo.getProductName());
			pstmt.setString(4, vo.getProductCompany());
			pstmt.setInt(5, vo.getPriceRetail());
			pstmt.setInt(6, vo.getPriceSale());
			pstmt.setInt(7, vo.getProductQnty());
			pstmt.setString(8, vo.getProductImage1());
			pstmt.setString(9, vo.getProductImage2());
			pstmt.setString(10, vo.getProductMessage());
			
			result = pstmt.executeUpdate();
				
			System.out.println("productwrite.end");	
				
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
			
		return result;
	}
	
	
	//NEW ITEM 리스트 반환 (등록일 순)
	public ArrayList<ProductVo> getNewItemList(){
		ArrayList<ProductVo> voList= new ArrayList<>();
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL="SELECT * FROM products ORDER BY product_date desc";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			
			while(rs.next()){
				ProductVo vo= new ProductVo();
				vo.setProductCode(rs.getString("product_code"));
				vo.setCategory1CodeFk(rs.getString("category1_code_fk"));
				vo.setProductName(rs.getString("product_name"));
				vo.setProductCompany(rs.getString("product_company"));
				vo.setPriceRetail(rs.getInt("price_retail"));
				vo.setPriceSale(rs.getInt("price_sale"));
				vo.setProductQnty(rs.getInt("product_qnty"));
				vo.setProductImage1(rs.getString("product_image1"));
				vo.setProductImage2(rs.getString("product_image2"));
				vo.setProductMessage(rs.getString("product_message"));
				vo.setProductDate(rs.getString("product_date"));
			
				voList.add(vo);
			}
			return voList;
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return null;
		
	}//end getNewItemList method
	
	
	//HOT ITEM 리스트 반환 (판매량 순)
	public ArrayList<ProductVo> getHotItemList(){
		ArrayList<ProductVo> voList= new ArrayList<>();
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL="SELECT"
				+ " p.product_code, p.category1_code_fk, p.product_name, p.product_company, p.price_retail,"
				+ " p.price_sale, p.product_qnty, p.product_image1,p.product_image2, p.product_message, p.product_date"
				+ " FROM"
				+ " products p,"
				+ " (SELECT product_code_pk, sum(order_count) as count FROM orders GROUP BY product_code_pk) o"
				+ " WHERE p.product_code=o.product_code_pk"
				+ " ORDER BY o.count DESC";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			
			while(rs.next()){
				ProductVo vo= new ProductVo();
				vo.setProductCode(rs.getString("product_code"));
				vo.setCategory1CodeFk(rs.getString("category1_code_fk"));
				vo.setProductName(rs.getString("product_name"));
				vo.setProductCompany(rs.getString("product_company"));
				vo.setPriceRetail(rs.getInt("price_retail"));
				vo.setPriceSale(rs.getInt("price_sale"));
				vo.setProductQnty(rs.getInt("product_qnty"));
				vo.setProductImage1(rs.getString("product_image1"));
				vo.setProductImage2(rs.getString("product_image2"));
				vo.setProductMessage(rs.getString("product_message"));
				vo.setProductDate(rs.getString("product_date"));
			
				voList.add(vo);
			}
			return voList;
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return null;
		
	}//end getHotItemList method
	
	
	
	//상품 검색 기능.
	public ArrayList<ProductVo> getSearchAllList(String search){
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		ArrayList<ProductVo> voList = new ArrayList<ProductVo>();
		System.out.println("productDao.searchList.strat");
		System.out.println("serch : " + search);
		try{
		String sql = "select product_name, price_Sale, price_Retail, product_image1, product_Qnty, product_Code, product_Message from products where product_Name like '%'||?||'%' or Product_Message like '%'||?||'%' ";
		//or ProductMessage like ?
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, search);
		pstmt.setString(2, search);
		rs = pstmt.executeQuery();
		while(rs.next()){
			ProductVo vo = new ProductVo(); 
			vo.setProductName(rs.getString("product_name"));
			vo.setProductQnty(rs.getInt("product_Qnty"));
			vo.setProductImage1(rs.getString("product_image1"));
			vo.setProductCode(rs.getString("product_Code"));
			vo.setPriceRetail(rs.getInt("price_Retail"));
			vo.setPriceSale(rs.getInt("price_Sale"));
			vo.setProductMessage(rs.getString("product_Message"));
					
			voList.add(vo);	
			System.out.println("orderCheckView.work");
		}		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		System.out.println("productDao.orderCheckView.end");
		return voList;
	}
	
	
	
	//카테고리 설정 검색 기능. 
	public ArrayList<ProductVo> getSearchAllCtgryList(String search, String ctgryName){
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		ArrayList<ProductVo> voList = new ArrayList<ProductVo>();
		System.out.println("productDao.getSearchAllCtgryList.strat");
		System.out.println("search : " + search);
		System.out.println("ctgryName : " + ctgryName);
		try{
		String sql = "select product_name, price_Sale, price_Retail, product_image1, product_Qnty, product_Code, product_Message from products where (product_Name like '%'||?||'%' and category1_code_fk = (select ctgry_Code from category1 where ctgry_Name = ?))  or (Product_Message like '%'||?||'%' and category1_code_fk = (select ctgry_Code from category1 where ctgry_Name = ?)) ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, search);
		pstmt.setString(2, ctgryName);
		pstmt.setString(3, search);
		pstmt.setString(4, ctgryName);
		
		rs = pstmt.executeQuery();
		while(rs.next()){
			ProductVo vo = new ProductVo(); 
			vo.setProductName(rs.getString("product_name"));
			vo.setProductQnty(rs.getInt("product_Qnty"));
			vo.setProductImage1(rs.getString("product_image1"));
			vo.setProductCode(rs.getString("product_Code"));
			vo.setPriceRetail(rs.getInt("price_Retail"));
			vo.setPriceSale(rs.getInt("price_Sale"));
			vo.setProductMessage(rs.getString("product_Message"));
			voList.add(vo);	
		}		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		System.out.println("productDao.getSearchAllCtgryList.end");
		return voList;
	}
	
	//상품 총 갯수 조회
	public String getTotalProducts(String ProductCount) {
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL = "select count(*) as cnt from products";
		
	try{
		pstmt= conn.prepareStatement(SQL);
		rs= pstmt.executeQuery();
		rs.next();
		ProductCount = rs.getString("cnt"); //1 , count(*)
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return ProductCount;
	}

}//class end
