package bilabila.lab2.service;

import bilabila.lab2.model.Author;
import bilabila.lab2.model.Book;
import bilabila.lab2.util.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {
    public void add(Author author) throws SQLException {
        Connection con = DB.createConnection();
        PreparedStatement ps = DB.prepare(DB.createConnection(),
                "INSERT INTO Author(AuthorId, Name,Age,Country) VALUES (?,?,?,?)");
        ps.setString(1, author.getAuthorId());
        ps.setString(2, author.getName());
        ps.setString(3, author.getAge());
        ps.setString(4, author.getCountry());
        ps.executeUpdate();
        DB.close(con);
        DB.close(ps);
    }

    public Author getById(String authorId) throws SQLException {
        Connection con = DB.createConnection();
        PreparedStatement ps = DB.prepare(DB.createConnection(),
                "select * from Author where AuthorId = ?");
        ps.setString(1, authorId);
        ResultSet rs = ps.executeQuery();
        Author author = new Author();
        if (rs.next()) {
            author.setAge(rs.getString("Age"));
            author.setName(rs.getString("Name"));
            author.setAuthorId(rs.getString("AuthorID"));
            author.setCountry(rs.getString("Country"));
        }
        DB.close(con);
        DB.close(ps);
        return author;
    }

    public List<Book> listBooks(Author author) throws SQLException {
        if (author.getName().isEmpty()) return new BookService().list();
        Connection con = DB.createConnection();
        PreparedStatement ps = DB.prepare(DB.createConnection(),
                "select * from Book where AuthorId in (select AuthorID from Author where Name=?)");
        ps.setString(1, author.getName());
//        System.out.println(author.getName());
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