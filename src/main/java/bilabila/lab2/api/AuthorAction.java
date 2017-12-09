package bilabila.lab2.api;
import bilabila.lab2.model.Author;
import bilabila.lab2.service.AuthorService;

import bilabila.lab2.model.Book;
import bilabila.lab2.service.BookService;
import com.opensymphony.xwork2.ActionSupport;

import java.sql.SQLException;
import java.util.List;

public class AuthorAction extends ActionSupport {
    private List<Book> books;
    private AuthorService as=new AuthorService();
    private Author author;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public AuthorService getAs() {
        return as;
    }

    public void setAs(AuthorService as) {
        this.as = as;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getById() {
        try {
            author=as.getById(author.getAuthorId());
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String add() {
        try {
            as.add(author);
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String listBooks() {
        try {
            books=as.listBooks(author);
            return SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR;
        }
    }
}
