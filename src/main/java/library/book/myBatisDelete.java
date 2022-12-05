package library.book;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class myBatisDelete {

    Reader reader = Resources.getResourceAsReader("myBatisConfig.xml");
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    SqlSession session = sessionFactory.openSession();

    public void delete(int id) {
        session.delete("Book.delete", id);
        session.commit();
        session.close();
    }
}
