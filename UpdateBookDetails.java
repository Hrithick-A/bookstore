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

@WebServlet("/updatebook")
public class UpdateBookDetails extends HttpServlet{
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Connection con;

	    @Override
	    public void init() throws ServletException {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "Hrithickroshan-6");
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        PrintWriter pw = resp.getWriter();
	        int bookId = Integer.parseInt(req.getParameter("bookid"));
	        String bookName = req.getParameter("bookname");
	        int bookPrice = Integer.parseInt(req.getParameter("bookprice"));
	        String author = req.getParameter("author");

	        try {
	            String query = "UPDATE books SET bookname=?, bookprice=?, author=? WHERE bookid=?";
	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, bookName);
	            ps.setInt(2, bookPrice);
	            ps.setString(3, author);
	            ps.setInt(4, bookId);
	            int rowsUpdated = ps.executeUpdate();
	            if (rowsUpdated > 0) {
	                pw.println("<html><body><h2>Book details updated successfully</h2></body></html>");
	            } else {
	                pw.println("<html><body><h2>Unable to update book details</h2></body></html>");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void destroy() {
	        try {
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
