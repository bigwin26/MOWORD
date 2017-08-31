package com.newlec.javaweb.controller.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlec.javaweb.entity.Notice;

@WebServlet("/customer/notice-reg")
public class NoticeRegController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
	
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String sql = "INSERT INTO Notice(id,title,content,writerid) "
				+ "VALUES((select ifnull(max(cast(id as unsigned)),0)+1 id from Notice n),?,?,?)";


		try {
			Class.forName("com.mysql.jdbc.Driver");

			// 연결 / 인증
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// 실행
			//Statement st = con.createStatement();
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, "newlec");
		

			// 결과 가져오기
			int result = st.executeUpdate();

		
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("notice-list");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//response.sendRedirect("notice.jsp");
		request.getRequestDispatcher("/WEB-INF/views/customer/notice/reg.jsp").forward(request, response);
	}

}
