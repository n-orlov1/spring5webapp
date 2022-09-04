package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {


    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("ddd", "12345");

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book someBook = new Book("J2EE", "1232145");

        rod.getBooks().add(someBook);
        someBook.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(someBook);

        System.out.println("Lets get the party started!!!");
        System.out.println("The amount of authors in our table is " + authorRepository.count() +
                " The amount of books in our table is " + bookRepository.count());
    }
}
