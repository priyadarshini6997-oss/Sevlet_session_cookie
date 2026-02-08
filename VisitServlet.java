package in.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class VisitSevlet
 */
//@WebServlet("/VisitSevlet")
public class VisitServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		
		int visitCount = 0;
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visitCount")) {
                    visitCount = Integer.parseInt(cookie.getValue());
                }
            }
		}
		 visitCount++;

	        Cookie visitCookie = new Cookie("visitCount", String.valueOf(visitCount));
	        visitCookie.setMaxAge(24*60*60); // 1 day
	        response.addCookie(visitCookie);

	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<h2>Welcome! You visited this page " + visitCount + " times.</h2>");
	}
	}


