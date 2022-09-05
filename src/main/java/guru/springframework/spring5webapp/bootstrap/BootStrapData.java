package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Optional;

@Component
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

        Publisher manning = new Publisher("Manning", "20 Baldwin Road PO Box 761",
                "Shelter Island", "NY", "11964");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("ddd", "12345");

        ddd.setPublisher(manning);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(manning);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book someBook = new Book("J2EE", "1232145");

        rod.getBooks().add(someBook);
        someBook.getAuthors().add(rod);
        someBook.setPublisher(manning);

        authorRepository.save(rod);
        bookRepository.save(someBook);

        Publisher foundPublisher = publisherRepository.findAll().iterator().next();

        System.out.println("The amount of authors in our table is " + authorRepository.count() +
                " The amount of books in our table is " + bookRepository.count()
                + " The amount of publishers in our table is " + publisherRepository.count());

        foundPublisher.getBooks().forEach(book -> System.out.println("title " + book.getTitle() +
                " isbn " + book.getIsbn()));
    }
}
