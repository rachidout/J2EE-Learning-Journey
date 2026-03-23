package web;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entities.Panier;
@WebServlet(name="cs" ,urlPatterns={"/login"})
public class LoginServlet extends HttpServlet{
	  @Override
  protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
		  HttpSession session = request.getSession(false);
    if(session != null && session.getAttribute("user")!=null) {
    	response.sendRedirect("produits");
    }else {
    	request.getRequestDispatcher("/login.html").forward(request, response);
    }
  }
	  @Override
  protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException ,IOException {
	  String username = request.getParameter("user");
	  String password = request.getParameter("password");
	  if(username == null || username.trim().isEmpty() || password.trim().isEmpty()|| password == null) {
		 response.sendRedirect("login.html?error=2");
		 return ;
	  }
	  if("admin".equals(username) && "123".equals(password)) {
		  HttpSession session = request.getSession(true);
		  session.setAttribute("user",username);
		  session.setAttribute("panier", new Panier());
		  
		  response.sendRedirect("produits");
	  }else {
		  response.sendRedirect("login.html?error=1");
	  }
  }
}
