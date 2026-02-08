package in.com;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
	static final String USER = "root";
	static final String PASS = "Priyadarshini!1";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    response.sendRedirect("Login.html");
	}

	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		System.out.println("LoginServlet called");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
			
			String sql = "SELECT * FROM users WHERE username=? AND password=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2,password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("username",username);
				response.sendRedirect(request.getContextPath()+"/dashboard.html");
			}else {
				response.sendRedirect(request.getContextPath()+"/Login.html");
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
			 response.sendRedirect(request.getContextPath()+"/Login.html");
		}
	}
	
	}


