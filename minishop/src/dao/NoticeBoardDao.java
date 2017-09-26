package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.db.JdbcUtil;
import vo.NoticeBoardVo;

public class NoticeBoardDao {

	/*CREATE TABLE NOTICE_BOARD(
	 NOTICE_IDX NUMBER(10),
	 NOTICE_TITLE VARCHAR2(200),
	 NOTICE_CONTENT VARCHAR(3000),
	 NOTICE_WRITER VARCHAR2(20),
	 NOTICE_DATE DATE,
	 NOTICE_HIT NUMBER(10),
	 NOTICE_AVAILABLE NUMBER(2),
	 CONSTRAINT NOTICE_NOTICE_ID_PK PRIMARY KEY (NOTICE_IDX)
	)*/
	
	//다음에 등록될 글 번호를 리턴
	public int getNextIdx(){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "SELECT notice_idx FROM notice_board ORDER BY notice_idx DESC";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt("notice_idx") + 1;
			}else{
				return 1; //첫 번째 게시물인 경우
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return -1; //데이터베이스 오류
		
	}//getNextIdx method end
	
	
	//게시판 목록 리턴
	public ArrayList<NoticeBoardVo> getList(int pageNumber){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "SELECT rnum, notice_idx, notice_title, notice_content, notice_writer, notice_date, notice_hit, notice_available"
				+ " FROM (SELECT rownum as rnum, notice_idx, notice_title, notice_content, notice_writer, notice_date, notice_hit, notice_available" 
				+ " FROM (SELECT notice_idx, notice_title, notice_content, notice_writer, notice_date, notice_hit, notice_available"
				+ " FROM notice_board where notice_available=1 ORDER BY notice_idx DESC))"
				+ " WHERE rnum between ? and ?";
		
		ArrayList<NoticeBoardVo> voList= new ArrayList<>();
		
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1, (pageNumber - 1) * 10 + 1);
			pstmt.setInt(2, pageNumber * 10);

			rs= pstmt.executeQuery();
			
			while (rs.next()){
				NoticeBoardVo vo= new NoticeBoardVo();
				vo.setNoticeIdx(rs.getInt("notice_idx"));
				vo.setNoticeTitle(rs.getString("notice_title"));
				vo.setNoticeContent(rs.getString("notice_content"));
				vo.setNoticeWriter(rs.getString("notice_writer"));
				vo.setNoticeDate(rs.getString("notice_date"));
				vo.setNoticeHit(rs.getInt("notice_hit"));
				vo.setNoticeAvailable(rs.getInt("notice_available"));
				
				voList.add(vo);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return voList;
		
	}//getList method end
	
	
	//매개변수로 주어진 페이지번호의 다음 페이지가 있는지 확인 후 true/false 리턴
	public boolean nextPage(int pageNumber){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "SELECT * FROM notice_board WHERE notice_idx<? AND notice_available=1";
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextIdx()-(pageNumber-1) * 10);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				return true;
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return false;
		
	}//nextPage method end
	
	
	//게시글 글 작성
	public int write(String noticeTitle, String noticeContent, String noticeWriter){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		
		String SQL= "INSERT INTO notice_board VALUES (?, ?, ?, ?, sysdate, 0, 1)";
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextIdx());
			pstmt.setString(2, noticeTitle);
			pstmt.setString(3, noticeContent);
			pstmt.setString(4, noticeWriter);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return -1; //데이터베이스 오류
		
	}//write method end
	
	
	//게시글 글 내용을 리턴
	public NoticeBoardVo getBoard(int noticeIdx){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "SELECT * FROM notice_board WHERE notice_idx= ?";
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1, noticeIdx);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				NoticeBoardVo vo= new NoticeBoardVo();
				vo.setNoticeIdx(rs.getInt("notice_idx"));
				vo.setNoticeTitle(rs.getString("notice_title"));
				vo.setNoticeContent(rs.getString("notice_content"));
				vo.setNoticeWriter(rs.getString("notice_writer"));
				vo.setNoticeDate(rs.getString("notice_date"));
				vo.setNoticeHit(rs.getInt("notice_hit"));
				vo.setNoticeAvailable(rs.getInt("notice_available"));
				return vo;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return null;
	}//getBoard method end
	
	
	//게시글 삭제
	public int delete(int noticeIdx){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "UPDATE notice_board SET notice_available=0 WHERE notice_idx=?";
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1, noticeIdx);
	
			return pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return -1; //데이터베이스 오류
	}//delete method end
	
	
	//게시글 수정
	public int update(int noticeIdx, String noticeTitle, String noticeContent){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "UPDATE notice_board SET notice_title=?, notice_content=? WHERE notice_idx=?";
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setString(1, noticeTitle);
			pstmt.setString(2, noticeContent);
			pstmt.setInt(3, noticeIdx);
	
			return pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return -1; //데이터베이스 오류
	}
	
	
	//조회수 추가
	public void addHit(int noticeIdx){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "UPDATE notice_board SET notice_hit=notice_hit+1 where notice_idx=?";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1,  noticeIdx);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}//addHit method end
	
	
	//공지사항 게시글 수 조회
	public String getTotalNotice(String NoticeCount) {
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL = "select count(*) as cnt from notice_board";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			rs.next();
			NoticeCount = rs.getString("cnt"); //1 , count(*)
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return NoticeCount;
	}
	
	
}//class end
