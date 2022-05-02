package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;


public class PurchaseDAO {
	
	public PurchaseDAO() {
	}
	
	public void insertPurchase(Purchase purchase) {
		
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		
		System.out.println("=============================");
		try {
		String sql = "INSERT INTO Transaction VALUES (seq_transaction_tran_no.NEXTVAL,?,?,?,?,?,?,?,?,SYSDATE,?)";
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		stmt.setString(2, purchase.getBuyer().getUserId());
		stmt.setString(3, purchase.getPaymentOption());
		stmt.setString(4, purchase.getReceiverName());
		stmt.setString(5, purchase.getReceiverPhone());
		stmt.setString(6, purchase.getDivyAddr());
		stmt.setString(7, purchase.getDivyRequest());
		stmt.setString(8, purchase.getTranCode());
		stmt.setString(9, purchase.getDivyDate().replace("-", ""));
		
		System.out.println(" 쿼리 전송");
		int result = stmt.executeUpdate();

		if (result == 1) {
			System.out.println(" 쿼리 전송 성공");
		}
		
		}catch(Exception e){
		e.printStackTrace();
		}finally {			
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			System.out.println("PurchaseDAO InsertPurchase end");
		}
	}		
	
	public Purchase findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		Product product = null;
		Purchase purchase = null;
		
		try {
		
		String sql = "SELECT * "
					+"FROM users u, product p, transaction t "
					+"WHERE u.user_id = t.buyer_id AND p.prod_no = t.prod_no AND t.tran_no = ?";
		
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			user = new User();
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setPassword(rs.getString("password"));
			user.setRole(rs.getString("role"));
			user.setPhone(rs.getString("cell_phone"));
			user.setAddr(rs.getString("addr"));
			user.setEmail(rs.getString("email"));
			user.setRegDate(rs.getDate("reg_date"));
			
			product = new Product();
			product.setProdNo(rs.getInt("PROD_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setRegDate(rs.getDate("REG_DATE"));
			
			purchase = new Purchase();
			purchase.setBuyer(user);
			purchase.setPurchaseProd(product);
			purchase.setDivyAddr(rs.getString("divy_addr"));
			purchase.setDivyDate(rs.getString("divy_date"));
			purchase.setDivyRequest(rs.getString("divy_request"));
			purchase.setOrderDate(rs.getDate("order_date"));
			purchase.setPaymentOption(rs.getString("payment_option"));
			purchase.setReceiverName(rs.getString("receiver_name"));
			purchase.setReceiverPhone(rs.getString("receiver_phone"));
			purchase.setTranCode(rs.getString("tran_status_code"));
			purchase.setTranNo(rs.getInt("tran_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return purchase;
	}
	
	public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception{
		System.out.println("==========PurchaseDao getPurchaseList 시작=========");
		Map<String, Object> map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * "
				+ "FROM transaction "
				+ "WHERE buyer_id = '"+userId+"'";
				
				
		
			int totalCount = this.getTotalCount(sql);
			
			sql = makeCurrentPageSql(sql, search);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			System.out.println("12313123123123123");
			System.out.println(search);
			
			List<Purchase> list = new ArrayList<Purchase>();
			while(rs.next()) {
				Purchase purchase = new Purchase();
				Product product = new Product();
				User user = new User();
				
				product.setProdNo(rs.getInt("prod_no"));
//				product.setProdName(rs.getString("prod_name"));
//				product.setFileName(rs.getString("image_file"));
				
				purchase.setPurchaseProd(product);
				user.setUserId(rs.getString("buyer_id"));
				purchase.setBuyer(user); 
				System.out.println("================Purchase List ================");
				
				purchase.setTranNo(rs.getInt("tran_no"));
				purchase.setReceiverName(rs.getString("receiver_name"));
				purchase.setReceiverPhone(rs.getString("receiver_phone"));
				purchase.setDivyDate(rs.getString("dlvy_date"));
				purchase.setDivyRequest(rs.getString("dlvy_request"));
				purchase.setDivyAddr(rs.getString("demailaddr"));
				purchase.setOrderDate(rs.getDate("order_data"));
				purchase.setTranCode(rs.getString("tran_status_code").trim());
				purchase.setPaymentOption(rs.getString("payment_option").trim());
				
				System.out.println("================Purchase List ================"+purchase);
				list.add(purchase);
				
			}
			
			map.put("totalCount", new Integer(totalCount));
			System.out.println("list.size() : "+ list.size());
			map.put("list", list);
			System.out.println("map().size() : "+ map.size());

			rs.close();
			pstmt.close();
			con.close();
				
			return map;
			
		
	}
	
	public Map<String, Object> getSaleList(Search search) {
		
//		String sql = "SELECT * FROM transaction WHERE buyer_id ='" + buyerId+"'";
		
		
		return null;
	}
	
	public void updatePurchase(Purchase purchase) {
		
		System.out.println("Dao getSaleList start===================");
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			
			String sql = "UPDATE transation "
						+"SET payment_option =?, receiver_name=?, receiver_phone=?, dlvy_addr=?, "
						+"dlvy_request=?, dlvy_date=? "
						+"WHERE tran_no=? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchase.getPaymentOption());
			pstmt.setString(2, purchase.getReceiverName());
			pstmt.setString(3, purchase.getReceiverPhone());
			pstmt.setString(4, purchase.getDivyAddr());
			pstmt.setString(5, purchase.getDivyRequest());
			pstmt.setString(6, purchase.getDivyDate());
			pstmt.setInt(7, purchase.getTranNo());
			
			int rs = pstmt.executeUpdate();
			if(rs == 1) {
				System.out.println("=========insert success=======");
			}
			System.out.println("=============insert fail==============");
			
		}catch(Exception e){
			
		} finally {			
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void updateTranCode(Purchase purchase) {
		System.out.println("=====Dao updateTranCode start======");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			String sql = "UPDATE transaction "
						+"SET "
						+"tran_status_code=? "
						+"WHERE tran_no=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, purchase.getTranCode());
			pstmt.setInt(2, purchase.getTranNo());
			
			int r = pstmt.executeUpdate();
			if(r == 1) {
				System.out.println("Update 성공");
				System.out.println("PDAO updateTranCode end");
			} else {
				System.out.println("Update 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("UserDAO :: make SQL :: "+ sql);	
		
		return sql;
	}
}
