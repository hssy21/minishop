package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import util.db.JdbcUtil;
import vo.OrderVo;

public class OrderDao {
		
	//주문조회
	public ArrayList<HashMap<String, String>> getOrderViewList(String userId){
		ArrayList<HashMap<String, String>> list= new ArrayList<>();
		
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "SELECT product_code, product_image1, product_name, order_code, order_count, order_amount, order_date, order_state"
				+ " FROM products p, orders o"
				+ " where user_id_fk=? and o.product_code_pk=p.product_code";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setString(1, userId);
			rs= pstmt.executeQuery();
			
			while(rs.next()){
				HashMap<String, String> map= new HashMap<>();
				
				map.put("productCode", rs.getString("product_code"));
				map.put("productImage1", rs.getString("product_image1"));
				map.put("productName", rs.getString("product_name"));
				map.put("orderCode", rs.getString("order_code"));
				map.put("orderCount", Integer.toString(rs.getInt("order_count")));
				map.put("orderAmount", Integer.toString(rs.getInt("order_amount")));
				map.put("orderDate", rs.getString("order_date"));
				map.put("orderState", Integer.toString(rs.getInt("order_state")));
				
				list.add(map);
			}
			return list;
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
			return null;
	}

	
	//주문하기
	public int order(OrderVo vo, int priceSale){
		System.out.println("OrderDao.orderAdd.start");
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;

		try{
			
			String sql= "INSERT INTO orders"
					+ " (order_code, user_id_fk, product_code_pk, order_count, buyer_name, buyer_address1, buyer_address2, buyer_phone,"
					+ " buyer_email, rcpt_name, rcpt_address1, rcpt_address2, rcpt_phone, ship_message, order_amount, order_state, order_date)"
					+ " VALUES"
					+ " (order_code_seq.nextval,    ?, ?, ?, ?,    ?, ?, ?, ?, ?,    ?, ?, ?, ?, ?,    0, sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, vo.getOrderCode());
			pstmt.setString(1, vo.getUserIdFk());
			pstmt.setString(2, vo.getProductCodePk());
			pstmt.setInt(3, vo.getOrderCount());
			pstmt.setString(4, vo.getBuyerName());
			
			pstmt.setString(5, vo.getBuyerAddress1());
			pstmt.setString(6, vo.getBuyerAddress2());
			pstmt.setString(7, vo.getBuyerPhone());
			pstmt.setString(8, vo.getBuyerEmail());
			pstmt.setString(9, vo.getRcptName());
			
			pstmt.setString(10, vo.getRcptAddress1());
			pstmt.setString(11, vo.getRcptAddress2());
			pstmt.setString(12, vo.getRcptPhone());
			pstmt.setString(13, vo.getShipMessage());
			pstmt.setInt(14, vo.getOrderAmount());
		
			System.out.println("OrderDao.orderAdd.end");
			return pstmt.executeUpdate();
				
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return -1;
	}
	
	//주문 관리 상세
	public HashMap<String, String> orderDetailView(String orderCode){
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
//		System.out.println("orderDao.orderDetailView.strat");
//		System.out.println(orderCode);
		
		String sql = "select order_code, user_id_fk, product_code_pk, order_count,"
				+ " buyer_name, buyer_address1, buyer_address2, buyer_phone, buyer_email, rcpt_name, rcpt_address1, rcpt_address2,"
				+ " rcpt_phone, ship_message, order_amount, order_state, order_date, product_image1, product_name, product_message, price_sale"
				+ " from orders o, products p where o.product_code_pk = p.product_code and order_code = ?";	
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,orderCode);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				HashMap<String, String> map= new HashMap<>();
				map.put("orderCode", rs.getString("order_code"));
				map.put("userIdFk", rs.getString("user_id_fk"));
				map.put("productCodePk", rs.getString("product_code_pk"));
				map.put("orderCount", rs.getString("order_count"));
				map.put("buyerName", rs.getString("buyer_name"));
				map.put("buyerAddress1", rs.getString("buyer_address1"));
				map.put("buyerAddress2", rs.getString("buyer_address2"));
				map.put("buyerPhone", rs.getString("buyer_phone"));
				map.put("buyerEmail", rs.getString("buyer_email"));
				map.put("rcptName", rs.getString("rcpt_name"));
				map.put("rcptAddress1", rs.getString("rcpt_address1"));
				map.put("rcptAddress2", rs.getString("rcpt_address2"));
				map.put("rcptPhone", rs.getString("rcpt_phone"));
				map.put("shipMessage", rs.getString("ship_message"));
				map.put("orderAmount", rs.getString("order_amount"));
				map.put("orderState", rs.getString("order_state"));
				map.put("orderDate", rs.getString("order_date"));
				map.put("productImage1", rs.getString("product_image1"));
				map.put("productName", rs.getString("product_name"));
				map.put("productMessage", rs.getString("product_message"));
				map.put("priceSale", rs.getString("price_sale"));
				
				System.out.println("orderDetailView.work");
				return map;
				
			}		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		System.out.println("orderDao.orderDetailView.end");
		return null;
	}
	
	
	//주문 관리 수정
	public int orderMofiyAction(OrderVo voList){
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		
		System.out.println("orderDao.orderMofiyAction.strat");
		System.out.println("배송상태번호 : " + voList.getOrderState());
		System.out.println("전화1 : " + voList.getBuyerPhone());
		System.out.println("전화2 : " + voList.getRcptPhone());
		int result = 0;
		try{
			String sql = "UPDATE orders"
					+ " SET order_amount =?, buyer_name =?, buyer_address1 =?, buyer_address2 =?, buyer_email =?,"
					+ " buyer_phone =?, rcpt_name =?, rcpt_address1 =?, rcpt_address2 =?, rcpt_phone =?,"
					+ " ship_message =?, order_state=?, order_count =?"
					+ " WHERE order_code =?";	
				
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,voList.getOrderAmount());
			pstmt.setString(2, voList.getBuyerName());
			pstmt.setString(3, voList.getBuyerAddress1());
			pstmt.setString(4, voList.getBuyerAddress2());
			pstmt.setString(5, voList.getBuyerEmail());
			pstmt.setString(6, voList.getBuyerPhone());
			pstmt.setString(7, voList.getRcptName());
			pstmt.setString(8, voList.getRcptAddress1());
			pstmt.setString(9, voList.getRcptAddress2());
			pstmt.setString(10, voList.getRcptPhone());
			pstmt.setString(11, voList.getShipMessage());
			pstmt.setInt(12, voList.getOrderState());
			pstmt.setInt(13,  voList.getOrderCount());
			pstmt.setString(14, voList.getOrderCode());				
			
			return pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		System.out.println("orderDao.orderMofiyAction.end");
		System.out.println("Result : " + result);
		return -1;
	}
	
	
	
	public ArrayList<HashMap<String, String>> getOrderManageView(String orderState){
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;

		System.out.println("orderDao.orderManageView.strat");
		try{
		String sql = "SELECT product_name, buyer_name, price_sale, order_count, order_amount, order_code"
				+ " FROM products p, orders o"
				+ " WHERE p.product_code=o.product_code_pk and order_state=?";	
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, orderState);
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			HashMap<String, String> map= new HashMap<>();
			map.put("productName", rs.getString("product_name"));
			map.put("buyerName", rs.getString("buyer_name"));
			map.put("priceSale", rs.getString("price_sale"));
			map.put("orderCount", rs.getString("order_count"));
			map.put("orderAmount", rs.getString("order_amount"));
			map.put("orderCode", rs.getString("order_code"));
			
			list.add(map);	
			System.out.println("orderManageView.work");
		}		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		System.out.println("orderDao.orderManageView.end");
		return list;
	}
	
	
	
}//class end
