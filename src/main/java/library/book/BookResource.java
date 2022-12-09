package library.book;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class BookResource {
    Reader reader = Resources.getResourceAsReader("config.xml");
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession session = sessionFactory.openSession();

    public BookResource() throws IOException {
    }

    public void addBook(Book book) {
        session.insert("Book.insert", book);
        session.commit();
        session.close();
    }

    public void findAll() {
        List<Book> bookList = session.selectList("Book.findAll");
        session.commit();
        session.close();
    }

    public void findById(int id) {
        Book book = session.selectOne("Book.findById", id);
        session.commit();
        session.close();
    }

    public void findByTitle(String title) {
        Book book = session.selectOne("Book.findByTitle", title);
        session.commit();
        session.close();
    }

    public void update(int id, Book book) {

        book.setPublisher(book.getPublisher());
        book.setAuthor(book.getAuthor());
        book.setTitle(book.getTitle());

        session.update("Book.update", book);
        session.commit();
        session.close();
    }

    public void delete(int id) {
        session.delete("Book.delete", id);
        session.commit();
        session.close();
    }
}
