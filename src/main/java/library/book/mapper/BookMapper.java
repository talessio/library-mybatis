package library.book.mapper;

import library.book.model.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookMapper {

    @Select("select * from book")
    List<Book> findAll();

    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    @Insert("insert into book(author,publisher,title) values(#{author},#{publisher},#{title})")
    void insert(Book book);

    @Select("select b from book b where b.title = #{title}")
    Optional<Book> findByTitle(String title);

    @Select("select b form book b where b.id = #{id}")
    Optional<Object> findById(Long bookId);

    @Update("update book set title=#{title}, author=#{author}, publisher=#{publisher} where id=#{id}")
    void update(Long id, String title, String author, String publisher);

    @Delete("delete book where id=#{id}")
    void deleteBook(Long id);
}
