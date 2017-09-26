package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.db.JdbcUtil;
import vo.QnaBoardVo;

public class QnaBoardDao {

	/*
	QNA_IDX                                   NOT NULL NUMBER(10)
 	QNA_TITLE                                          VARCHAR2(200)
 	QNA_CONTENT                                        VARCHAR2(3000)
 	QNA_WRITER                                         VARCHAR2(20)
 	QNA_DATE                                           DATE
 	QNA_HIT                                            NUMBER(10)
 	QNA_AVAILABLE                                      NUMBER(2)
 	QNA_GROUP                                          NUMBER(10)
 	QNA_STEP                                           NUMBER(10)
 	QNA_INDENT                                         NUMBER(10)
	*/
	
	//������ ��ϵ� �� ��ȣ�� ����
	public int getNextIdx(){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "SELECT qna_idx FROM qna_board ORDER BY qna_idx DESC";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			if(rs.next()){
				return rs.getInt("qna_idx") + 1;
			}else{
				return 1; //ù ��° �Խù��� ���
			}
			
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return -1; //�����ͺ��̽� ����
		
	}//getNextIdx method end
	
	
	//�Խ��� ��� ����
	public ArrayList<QnaBoardVo> getList(int pageNumber){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;

		String SQL= "SELECT rnum, qna_idx, qna_title, qna_content, qna_writer, qna_date, qna_hit, qna_available, qna_group, qna_step, qna_indent"
				+ " FROM (SELECT rownum as rnum, qna_idx, qna_title, qna_content, qna_writer, qna_date, qna_hit, qna_available, qna_group, qna_step, qna_indent" 
				+ " FROM (SELECT qna_idx, qna_title, qna_content, qna_writer, qna_date, qna_hit, qna_available, qna_group, qna_step, qna_indent"
				+ " FROM qna_board ORDER BY qna_group DESC, qna_step ASC))"
				+ " WHERE rnum between ? and ?";
		
		ArrayList<QnaBoardVo> voList= new ArrayList<>();
		
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1, (pageNumber - 1) * 10 + 1);
			pstmt.setInt(2, pageNumber * 10);

			rs= pstmt.executeQuery();
			
			while (rs.next()){
				QnaBoardVo vo= new QnaBoardVo();
				vo.setQnaIdx(rs.getInt("qna_idx"));
				vo.setQnaTitle(rs.getString("qna_title"));
				vo.setQnaContent(rs.getString("qna_content"));
				vo.setQnaWriter(rs.getString("qna_writer"));
				vo.setQnaDate(rs.getString("qna_date"));
				vo.setQnaHit(rs.getInt("qna_hit"));
				vo.setQnaAvailable(rs.getInt("qna_available"));
				vo.setQnaGroup(rs.getInt("qna_group"));
				vo.setQnaStep(rs.getInt("qna_step"));
				vo.setQnaIndent(rs.getInt("qna_indent"));
				
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
	
	
	//�Ű������� �־��� ��������ȣ�� ���� �������� �ִ��� Ȯ�� �� true/false ����
	public boolean nextPage(int pageNumber){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "SELECT * FROM qna_board WHERE qna_idx<? AND qna_available=1";
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
	
	
	//�Խñ� �� �ۼ�
	public int write(String qnaTitle, String qnaContent, String qnaWriter){
		System.out.println("������Ʈ �޼ҵ� ����");
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		
		//QNA_IDX, QNA_TITLE, QNA_CONTENT, QNA_WRITER, QNA_DATE, QNA_HIT, QNA_AVAILABLE, QNA_GROUP, QNA_STEP, QNA_INDENT
		String SQL= "INSERT INTO qna_board VALUES (?, ?, ?, ?, sysdate, 0, 1, ?, 0, 0)";
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextIdx());
			pstmt.setString(2, qnaTitle);
			pstmt.setString(3, qnaContent);
			pstmt.setString(4, qnaWriter);
			pstmt.setInt(5, getNextIdx());
			
			return pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return -1; //�����ͺ��̽� ����
		
	}//write method end
	
	
	//�Խñ� �� ������ ����
	public QnaBoardVo getBoard(int qnaIdx){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "SELECT * FROM qna_board WHERE qna_idx= ?";
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1, qnaIdx);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				QnaBoardVo vo= new QnaBoardVo();
				vo.setQnaIdx(rs.getInt("qna_idx"));
				vo.setQnaTitle(rs.getString("qna_title"));
				vo.setQnaContent(rs.getString("qna_content"));
				vo.setQnaWriter(rs.getString("qna_writer"));
				vo.setQnaDate(rs.getString("qna_date"));
				vo.setQnaHit(rs.getInt("qna_hit"));
				vo.setQnaAvailable(rs.getInt("qna_available"));
				vo.setQnaGroup(rs.getInt("qna_group"));
				vo.setQnaStep(rs.getInt("qna_step"));
				vo.setQnaIndent(rs.getInt("qna_indent"));
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
	
	
	//�Խñ� ����
	public int delete(int qnaIdx, int qnaGroup, int qnaStep){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "UPDATE qna_board SET qna_available=0 WHERE qna_idx=?";
		//String SQL= "UPDATE qna_board SET qna_available=0 WHERE qna_group=? and qna_step>=?";
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1, qnaIdx);
			//pstmt.setInt(1, qnaGroup);
			//pstmt.setInt(2, qnaStep);
	
			return pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return -1; //�����ͺ��̽� ����
	}//delete method end
	
	
	//�Խñ� ����
	public int update(int qnaIdx, String qnaTitle, String qnaContent){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "UPDATE qna_board SET qna_title=?, qna_content=? WHERE qna_idx=?";
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setString(1, qnaTitle);
			pstmt.setString(2, qnaContent);
			pstmt.setInt(3, qnaIdx);
	
			return pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return -1; //�����ͺ��̽� ����
	}
	
	
	//��ȸ�� �߰�
	public void addHit(int qnaIdx){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "UPDATE qna_board SET qna_hit=qna_hit+1 where qna_idx=?";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1,  qnaIdx);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}//addHit method end
	
	
	//�亯�� ����Ʈ ��� ���� �����
	public void replyShape(int qnaGroup, int qnaStep){
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL= "UPDATE qna_board SET qna_step= qna_step+1 where qna_group=? and qna_step>?";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1, qnaGroup);
			pstmt.setInt(2, qnaStep);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}//replyShape method end
	
	
	//�亯�� �ޱ�
	public int reply(int qnaIdx, String qnaTitle, String qnaContent, String qnaWriter, int qnaGroup, int qnaStep, int qnaIndent){
		replyShape(qnaGroup, qnaStep);
		
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		//QNA_IDX, QNA_TITLE, QNA_CONTENT, QNA_WRITER, QNA_DATE, QNA_HIT, QNA_AVAILABLE, QNA_GROUP, QNA_STEP, QNA_INDENT
		String SQL= "INSERT INTO qna_board VALUES (?, ?, ?, ?, sysdate, 0, 1, ?, ?, ?)";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextIdx());
			pstmt.setString(2, qnaTitle);
			pstmt.setString(3, qnaContent);
			pstmt.setString(4, qnaWriter);
			pstmt.setInt(5, qnaGroup);
			pstmt.setInt(6, qnaStep + 1);
			pstmt.setInt(7, qnaIndent + 1);
			
			return pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return -1; //�����ͺ��̽� ����
		
	}//reply method end
	
	
	//Q&A �Խñ� ���� ����
	public String getDeleteTotalQna(String delCount) {
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL = "select count(*) as cnt from qna_board where qna_available = 0";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			rs.next();
			delCount = rs.getString("cnt"); //1 , count(*)
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return delCount;
	}
	
	
	//Q&A �Խñ� �� ���� ��ȸ
	public String getTotalQna(String QnaCount) {
		Connection conn= JdbcUtil.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		
		String SQL = "select count(*) as cnt from qna_board where qna_available = 1";
		
		try{
			pstmt= conn.prepareStatement(SQL);
			rs= pstmt.executeQuery();
			rs.next();
			QnaCount = rs.getString("cnt"); //1 , count(*)
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return QnaCount;
	}
	
	
}//qnaBoardDao end
