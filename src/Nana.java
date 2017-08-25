import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.newlec.javaweb.entity.Notice;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/notice")  //Annotation

public class Nana extends HttpServlet
{

	public void service(HttpServletRequest request,HttpServletResponse response)
			throws IOException, ServletException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; chatset=UTF-8");
		PrintWriter out = response.getWriter();
		/*OutputStream os = response.getOutputStream();
		PrintStream out = new PrintStream(os);*/

		String title = request.getParameter("title");

		/*---------------------------------------------------------------------------------------*/
		List<Notice>list = null;

		String sql = "SELECT *FROM Notice WHERE title like ?";

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// 연결 / 인증
			Connection con = DriverManager.getConnection(url, "sist", "cclass");

			// 실행
			//Statement st = con.createStatement();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%"+title+"%");

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
    /*----------------------------------------------------------------------view------------------------------------------------------------------*/
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<title>Insert title here</title>");
			out.println("</head>");
			out.println("<body> ");
			out.println("<form action=\"notice\" method=\"get\">");
			out.println("<label>검색어</label>");
			out.println("<input type=\"text\"  name=\"title\"/>"); 
			out.println("<input type=\"submit\" />");

			out.println("</form>");
			

			for(Notice n : list)
				out.println(n.getTitle()+ "<br />");
			
			out.println("</body>");
			out.println("</html>");


			/*out.println(list.get(0).getTitle());*/
		} catch (Exception e) {
			// TODO: handle exception
		}
		// JDBC 드라이버 로드

	}
}
