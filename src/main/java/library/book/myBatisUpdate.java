package library.book;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class myBatisUpdate {

    Reader reader = Resources.getResourceAsReader("myBatisConfig.xml");
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession session = sessionFactory.openSession();

    public void update(int id) {

        Book book = session.selectOne("Book.findById", id);
        book.setPublisher(); //TODO: what to insert inside here?
        book.getAuthor(); //TODO: what to insert inside here?
        book.setTitle(); //TODO: what to insert inside here?

        session.update("Book.update", book);
        session.commit();
        session.close();
    }
}
