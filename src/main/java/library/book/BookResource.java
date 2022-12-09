package library.book;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping(path = "rest/book")
public class BookResource {
    Reader reader = Resources.getResourceAsReader("config.xml");
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession session = sessionFactory.openSession();

    @Autowired
    public BookResource() throws IOException {
    }

    @PostMapping("/add")
    public void addBook(Book book) {
        session.insert("Book.insert", book);
        session.commit();
        session.close();
    }

    @GetMapping("/find")
    public void findAll() {
        List<Book> bookList = session.selectList("Book.findAll");
        session.commit();
        session.close();
    }

    @GetMapping("find/{id}")
    public void findById(int id) {
        Book book = session.selectOne("Book.findById", id);
        session.commit();
        session.close();
    }

    @GetMapping("find/{title}")
    public void findByTitle(String title) {
        Book book = session.selectOne("Book.findByTitle", title);
        session.commit();
        session.close();
    }

    @PutMapping("update")
    public void update(int id, Book book) {

        book.setPublisher(book.getPublisher());
        book.setAuthor(book.getAuthor());
        book.setTitle(book.getTitle());

        session.update("Book.update", book);
        session.commit();
        session.close();
    }

    @DeleteMapping("/delete")
    public void delete(int id) {
        session.delete("Book.delete", id);
        session.commit();
        session.close();
    }
}
