package library.book;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;

public class myBatisRead {

    Reader reader = Resources.getResourceAsReader("myBatisConfig.xml");
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession session = sessionFactory.openSession();

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
}
