package com.newlec.javaweb.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlec.javaweb.dao.MemberDao;
import com.newlec.javaweb.dao.jdbc.JdbcMemberDao;
import com.newlec.javaweb.entity.Member;

	@WebServlet("/member/join")
	public class JoinController extends HttpServlet{
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.getRequestDispatcher("/WEB-INF/views/member/join.jsp")
			.forward(request, response);
		}
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String id = request.getParameter("id"); 
	String[] pwds = request.getParameterValues("pwd"); 
	String moon = request.getParameter("moon"); 
	String name = request.getParameter("name"); 
	String gender = request.getParameter("gender"); 
	String birthday = request.getParameter("birthdaty"); 
	String phone = request.getParameter("phone"); 
	String email = request.getParameter("email"); 
	
	MemberDao memberDao = new JdbcMemberDao();
	Member member = new Member(id,pwds[0],moon,name,gender,birthday,phone,email);

	int result = memberDao.insert(member);
	
	if(result>0)
		response.sendRedirect("confirm");
	else
		response.sendRedirect("../error?code=1234");
}
}
