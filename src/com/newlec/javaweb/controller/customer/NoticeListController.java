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

@WebServlet("/customer/notice-list")
public class NoticeListController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String _title = request.getParameter("title");
		String title="";
		String _page=request.getParameter("p");
		
		int page = 0;
		
		if(_page != null && !_page.equals(""))
			page= Integer.parseInt(_page);
		
		int offset = page*10;
		
		if(_title != null && !_title.equals(""))
			title = _title;
		/*---------------------------------------------------------------------------------------*/
		List<Notice> list = null;

		String sql = "SELECT *FROM Notice WHERE title like ? order by regDate desc limit ?,10";

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// 연결 / 인증
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// 실행
			//Statement st = con.createStatement();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + title + "%");
			st.setInt(2, offset);

			// 결과 가져오기
			ResultSet rs = st.executeQuery();

			// Model 
			list = new ArrayList<>();

			// 결과 사용하기
			while (rs.next()) {
				Notice n = new Notice();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
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
		
		
		
		request.setAttribute("list", list);

		//response.sendRedirect("notice.jsp");
		request.getRequestDispatcher("/WEB-INF/views/customer/notice/list.jsp").forward(request, response);
	}

}
