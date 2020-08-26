package shaik.springframework.spring5webapp.bootstrap;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shaik.springframework.spring5webapp.domain.Author;
import shaik.springframework.spring5webapp.domain.Book;
import shaik.springframework.spring5webapp.domain.Publisher;
import shaik.springframework.spring5webapp.repositories.AuthorRepository;
import shaik.springframework.spring5webapp.repositories.BookRepository;
import shaik.springframework.spring5webapp.repositories.PublisherRepository;

@Component
@Log4j2
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Started in bootstrap");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "12341234");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        log.info("Created an entry for author:{}", eric.getFirstName());


        Author rod = new Author("rod", "keller");
        Book lp = new Book("Linux Programing", "2229922");

        rod.getBooks().add(lp);
        lp.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(lp);

        log.info("Created an entry for author:{}", rod.getFirstName());

        log.info("Number of books: {}", bookRepository.count());

        Publisher publisher = new Publisher("Pearson", "6201 Crossbow rd, Dallas, TX, 50040");
        publisher.getBooks().add(lp);
        publisher.getBooks().add(ddd);

        publisherRepository.save(publisher);

        log.info("Publisher entry added: {}",publisher.getName());
        log.info("Number of publishers: {}", publisherRepository.count());
        log.info("Number of books added to publisher: {}", publisher.getBooks().size());
    }
}
