package com.newlec.javaweb.controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlec.javaweb.entity.Notice;

@WebServlet("/admin/notice-list")
public class NoticeListController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String _title = request.getParameter("title");
		String title="";

		if(_title != null && !_title.equals(""))
			title = _title;
		/*---------------------------------------------------------------------------------------*/
		List<Notice> list = null;

		String sql = "SELECT *FROM Notice WHERE title like ?";

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ���� / ����
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// ����
			//Statement st = con.createStatement();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + title + "%");

			// ��� ��������
			ResultSet rs = st.executeQuery();

			// Model 
			list = new ArrayList<>();

			// ��� ����ϱ�
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
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		request.setAttribute("list", list);
		
		//response.sendRedirect("notice.jsp");
		request.getRequestDispatcher("/WEB-INF/views/admin/notice/list.jsp").forward(request, response);
	}

}
