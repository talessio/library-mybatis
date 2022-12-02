package library.book.resource;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import library.book.mapper.BookMapper;
import library.book.model.Book;

@RestController
@RequestMapping(path = "rest/book")
public class BookResource {

	private final BookMapper bookMapper;
//	Reader reader = Resources.getResourceAsReader(conf);
//	SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//	SqlSessionFactory sessionFactory = builder.build();

	@Autowired
	public BookResource(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}

	@GetMapping("/all")
	public List<Book> getBooks() {
		return bookMapper.findAll();
	}

//    @GetMapping("/add")
	@PostMapping("/add")
	public void addBook(Book book) {
		Optional<Book> bookOptional = bookMapper.findByTitle(book.getTitle());
		if (bookOptional.isPresent()) {
			throw new IllegalStateException("Book already present");
		}
		bookMapper.insert(book);
//		return bookMapper.findAll();
	}

//	@GetMapping("/update")
	@PutMapping("/update")
	public void updateBook(Long bookId, String title, String author, String publisher) {
		Book book = (Book) bookMapper.findById(bookId)
				.orElseThrow(() -> new IllegalStateException("Book with ID " + bookId + " does not exist"));

		if ((title != null && title.length() > 0 && !Objects.equals(book.getTitle(), title))
				&& (author != null && author.length() > 0 && !Objects.equals(book.getAuthor(), author))
				&& (publisher != null && publisher.length() > 0 && !Objects.equals(book.getPublisher(), publisher))) {
			bookMapper.update(bookId, title, author, publisher);
		}
//		return bookMapper.findAll();
	}

//    @GetMapping("/delete")
	@DeleteMapping("/delete")
	public void deleteBook(Long bookId) {
		bookMapper.deleteBook(bookId);
//		return bookMapper.findAll();
	}
}
