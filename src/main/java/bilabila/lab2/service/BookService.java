package bilabila.lab2.service;

import bilabila.lab2.model.Book;
import bilabila.lab2.util.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    public void delete(Book book) throws SQLException {
        deleteByIsbn(book.getIsbn());
    }

    public void deleteByIsbn(String isbn) throws SQLException {
        Connection con = DB.createConnection();
        PreparedStatement ps = DB.prepare(con, "delete from Book where ISBN=?");
        ps.setString(1, isbn);
        ps.executeUpdate();
        DB.close(con);
        DB.close(ps);
    }

    public void update(Book book) throws SQLException {
        //作者、出版社、出版日期、价格
        Connection con = DB.createConnection();
        PreparedStatement ps = DB.prepare(con,
                "UPDATE Book set Title=? , AuthorID = ?, Publisher = ?,PublishDate=?,Price=? where ISBN=?");
        ps.setString(1, book.getTitle());
        ps.setString(2, book.getAuthorId());
        ps.setString(3, book.getPublisher());
        ps.setString(4, book.getPublishDate());
        ps.setString(5, book.getPrice());
        ps.setString(6, book.getIsbn());
        ps.executeUpdate();
        DB.close(con);
        DB.close(ps);
    }

    public void add(Book book) throws SQLException {
        Connection con = DB.createConnection();
        PreparedStatement ps = DB.prepare(con, "INSERT INTO Book VALUES (?,?,?,?,?,?)");
        ps.setString(1, book.getIsbn());
        ps.setString(2, book.getTitle());
        ps.setString(3, book.getAuthorId());
        ps.setString(4, book.getPublisher());
        ps.setString(5, book.getPublishDate());
        ps.setString(6, book.getPrice());
        ps.executeUpdate();
        DB.close(con);
        DB.close(ps);
    }

    public List<Book> list() throws SQLException {
        Connection con = DB.createConnection();
        PreparedStatement ps = DB.prepare(DB.createConnection(), "select * from Book");
        List<Book> books = new ArrayList<Book>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Book book = new Book();
            book.setIsbn(rs.getString("ISBN"));
            book.setTitle(rs.getString("Title"));
            book.setAuthorId(rs.getString("AuthorID"));
            book.setPublisher(rs.getString("Publisher"));
            book.setPublishDate(rs.getString("PublishDate"));
            book.setPrice(rs.getString("Price"));
            books.add(book);
        }
        DB.close(con);
        DB.close(ps);
        DB.close(rs);
        return books;
    }
}


//            PreparedStatement stmt = con.prepareStatement("SELECT AuthorID FROM Author WHERE Name=?");
//            stmt.setString(1, authorName);
//            ResultSet rs = stmt.executeQuery();
//            if (!rs.next())


//    addAuthor(book.getAuthorID());
//        ps = con.prepareStatement("SELECT AuthorID FROM Author WHERE Name=?");
//        ps.setString(1, authorName);
//        ResultSet rs = ps.executeQuery();
//        if (!rs.next()) return ERROR;
//        authorID = rs.getString(1);