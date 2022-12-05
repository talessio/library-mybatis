package library.book;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class myBatisInsert {

    Reader reader = Resources.getResourceAsReader("myBatisConfig.xml");
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession session = sessionFactory.openSession();

    public void addBook(Book book) {
        session.insert("Book.insert", book);
        session.commit();
        session.close();
    }
}
