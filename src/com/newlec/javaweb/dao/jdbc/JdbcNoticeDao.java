package com.newlec.javaweb.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlec.javaweb.dao.NoticeDao;
import com.newlec.javaweb.entity.Notice;
import com.newlec.javaweb.entity.NoticeView;

public class JdbcNoticeDao implements NoticeDao {

	public List<NoticeView> getList(int page, String query) {
		
		String sql = "SELECT *FROM NoticeView WHERE title like ? order by regDate desc limit ?,10";

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		
		List<NoticeView> list = null;
		int offset = page*10;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// 연결 / 인증
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// 실행
			//Statement st = con.createStatement();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + query + "%");
			st.setInt(2, offset);

			// 결과 가져오기
			ResultSet rs = st.executeQuery();

			// Model 
			list = new ArrayList<>();

			// 결과 사용하기
			while (rs.next()) {
				NoticeView n = new NoticeView();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				n.setWriterId(rs.getNString("writerId"));
				n.setWriterName(rs.getNString("writerName"));
				n.setContent(rs.getNString("content"));
				n.setHit(rs.getInt("hit"));
				n.setCountCmt(rs.getInt("CountCmt"));
				//..

				list.add(n);
			}
			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int getCount() {
		int count=0;
		
		String sqlCount = "select count(id) count from Notice;";

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// 연결 / 인증
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// 실행
			//Statement st = con.createStatement();

			Statement stCount = con.createStatement();
			ResultSet rsCount = stCount.executeQuery(sqlCount);
			rsCount.next();
			count = rsCount.getInt("count");

			rsCount.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public NoticeView get(String id) {
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
        String sql = "SELECT *FROM NoticeView WHERE id = ?";
        NoticeView n = null;
         
         try {
            Class.forName("com.mysql.jdbc.Driver");

            // 연결 / 인증
            Connection con = DriverManager.getConnection(url, "sist", "cclass");

            // 실행
            //Statement st = con.createStatement();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            
            // 결과 가져오기
            ResultSet rs = st.executeQuery();

            // Model 
            
            
            // 결과 사용하기
            if (rs.next()) {
               n = new NoticeView();
               n.setId(rs.getString("id"));
               n.setTitle(rs.getString("title"));
               n.setContent(rs.getString("content"));
               n.setWriterId(rs.getString("writerid"));
               n.setRegDate(rs.getDate("regDate"));
               n.setHit(rs.getInt("hit"));
               //..
               
            }
            rs.close();
            st.close();
            con.close();
            
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         } catch (SQLException e) {
            e.printStackTrace();
         }
         
		return n;
	}

	@Override
	public int update(String id, String title, String content) {
		
		
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String sql = "SELECT *FROM Notice WHERE id like ?";
		int result = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// 연결 / 인증
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// 실행
			//Statement st = con.createStatement();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);

			// 결과 가져오기
			result = st.executeUpdate();

			// Model 


		
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	

}
