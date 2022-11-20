package library.library.books.resource;

import library.library.books.mapper.BookMapper;
import library.library.books.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "rest/book")
public class BookResource {

    private final BookMapper bookMapper;

    @Autowired
    public BookResource(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @GetMapping("/all")
    public List<Book> getBooks() {
        return bookMapper.findAll();
    }

    @GetMapping("/add")
    public List<Book> addBook(Book book) {
        Optional<Book> bookOptional = bookMapper.findBookByTitle(book.getTitle());
        if (bookOptional.isPresent()) {
            throw new IllegalStateException("Book already present");
        }
        bookMapper.insert(book);
        return bookMapper.findAll();
    }

    @GetMapping("/update")
    public List<Book> updateBook(Long bookId, String title, String author, String publisher) {
        Book book = (Book) bookMapper.findById(bookId).orElseThrow(() -> new IllegalStateException("Book with ID " + bookId + " does not exist"));

        if ((title != null && title.length() > 0 && !Objects.equals(book.getTitle(), title)) &&
                (author != null && author.length() > 0 && !Objects.equals(book.getAuthor(), author)) &&
                (publisher != null && publisher.length() > 0 && !Objects.equals(book.getPublisher(), publisher))) {
            bookMapper.update(bookId, title, author, publisher);
        }
        return bookMapper.findAll();
    }

    @GetMapping("/delete")
    public List<Book> deleteBook(Long bookId) {
        bookMapper.deleteBook(bookId);
        return bookMapper.findAll();
    }
}
