package org.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/addbooks")
public class AddBookDetials extends HttpServlet {
	Connection con=null;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root", "Hrithickroshan-6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("bookname");
		int price=(int) Double.parseDouble(req.getParameter("bookprice"));
		String author=req.getParameter("author");
		int bookid = Integer.parseInt(req.getParameter("bookid"));
		PreparedStatement pstmt=null;
		
		String query="insert into books values(?,?,?,?)";

			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setInt(2, price);
				pstmt.setString(3, author);
				pstmt.setInt(4, bookid);
				int count = pstmt.executeUpdate();
				PrintWriter pw =resp.getWriter();
				pw.print("<h1>"+count+" Record Inserted");
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}
	

}
