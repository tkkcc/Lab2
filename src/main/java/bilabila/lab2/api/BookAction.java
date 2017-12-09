package bilabila.lab2.api;

import bilabila.lab2.model.Book;
import bilabila.lab2.service.BookService;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookAction extends ActionSupport {
    private List<Book> books;
    private BookService bs = new BookService();
    private Book book;

    public String delete() {
        try {
            bs.delete(book);
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String add() {
        try {
            bs.add(book);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String update() {
        try {
            bs.update(book);
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR;

        }
    }

    public String list() {
        try {
            books = bs.list();
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public BookService getBs() {
        return bs;
    }

    public void setBs(BookService bs) {
        this.bs = bs;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
