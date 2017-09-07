package com.newlec.javaweb.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlec.javaweb.dao.MemberRoleDao;
import com.newlec.javaweb.dao.jdbc.JdbcMemberDao;
import com.newlec.javaweb.dao.jdbc.JdbcMemberRoleDao;

@WebServlet("/member/home")
public class HomeController extends HttpServlet{

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Object _memberId = session.getAttribute("id");
		
		if(_memberId==null)
			//out.write("<script>alert('로그인이 필요합니다');location.href='../member/login';</script>");
			response.sendRedirect("login");
		else {
			String memberId = _memberId.toString();
			MemberRoleDao memberRoleDao = new JdbcMemberRoleDao();
			String defaultRoleId = memberRoleDao.getDefaultRoleId(memberId);
		//response.sendRedirect("notice.jsp");
		//request.getRequestDispatcher("/WEB-INF/views/member/home.jsp").forward(request, response);
			if(defaultRoleId.equals("ROLE_ADMIN"))
			response.sendRedirect("../admin/index");
			else if(defaultRoleId.equals("ROLE_STUDENT"))
			response.sendRedirect("../student/index");
			else
			response.sendRedirect("../index");
		}
	}
}
