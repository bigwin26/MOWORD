package com.newlec.javaweb.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.newlec.javaweb.dao.MemberRoleDao;

public class JdbcMemberRoleDao implements MemberRoleDao{

	@Override
	public String getDefaultRoleId(String memberId) {
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
        String sql = "select roleid from MemberRole where memberId = ? and defaultRole = 1;";
        String r = null;
         
         try {
            Class.forName("com.mysql.jdbc.Driver");

            // 연결 / 인증
            Connection con = DriverManager.getConnection(url, "sist", "cclass");

            // 실행
            //Statement st = con.createStatement();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, memberId);
            
            // 결과 가져오기
            ResultSet rs = st.executeQuery();

            // Model 
            
            
            // 결과 사용하기
            if (rs.next()) {
            	r = new String(rs.getString("roleId"));
            }
               
            rs.close();
            st.close();
            con.close();
            
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         } catch (SQLException e) {
            e.printStackTrace();
         }
         
		return r;
	}
}
