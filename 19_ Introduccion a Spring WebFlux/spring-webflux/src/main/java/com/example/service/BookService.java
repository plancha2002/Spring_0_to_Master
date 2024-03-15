package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    /*
mono es cuando trabajamos con un restultado
como cuando usamos optional
 */
    public Mono<Book> findById(Long id){
        return this.repository.findById(id);
    }
    /*
    Flux nos servira para cuando devolvamos varios elementos
    flux en vez de devolver todos los elementos de golpe los va enviando de poco a poco
     */
    public Flux<Book> findAll(){
        return this.repository.findAll();
    }

    public Mono<Boolean> existById(Long id){
        //operdor ternario
        return this.repository.existById(id);
    }

    /**
     * Crea un nuevo libro en la base de datos.
     *
     * @param book El libro a crear.
     * @return Un `Mono<Book>` que emite el libro creado o un error si no se pudo crear.
     * @throws IllegalArgumentException Si el ID del libro no es nulo.
     */
    public Mono<Book> create(Book book){
        if(book.getId() != null){
            return Mono.error(new IllegalArgumentException("Id debe ser nulo"));
        }
        return this.repository.save(book);
    }

    /**
     * Actualiza un libro en la base de datos.
     *
     * @param book El libro a actualizar.
     * @return Un `Mono<Book>` que emite el libro actualizado o un error si no se pudo actualizar.
     * @throws IllegalArgumentException Si el ID del libro es nulo o si no se encuentra un libro con ese ID.
     */
    public Mono<Book> update(Book book){
        if(book.getId() == null){
            return Mono.error(new IllegalArgumentException("Id no debe se ser nulo"));
        }
        /**
         * Verifica la existencia de un libro y realiza la operación correspondiente:
         *  - Si el libro existe (e = true):
         *      - Delega la operación al repositorio para guardar el libro actualizado (`this.repository.save(book)`)
         *      - Retorna un `Mono<Book>` que emitirá el libro guardado.
         *  - Si el libro NO existe (e = false):
         *      - Señala un error con `Mono.error(new IllegalArgumentException("No se puede guardar"))`
         *      - No se ejecuta la operación de guardado.
         *
         * @param e Resultado del Mono<Boolean> emitido por `existById()`, indica si el libro existe.
         * @return Mono<Book> que emite el libro guardado o un error si no se pudo guardar.
         */
        return this.repository.existById(book.getId()).flatMap(e -> {
            if (e){
                 return this.repository.save(book);
            }else {
                return Mono.error(new IllegalArgumentException("No se puede guardar"));
            }
        }
        );
    }

    public Mono<Void> deleteById(Long id){
        return this.repository.deleteById(id);
    }

    public Mono<Void> deleteAll(){
        return this.repository.deleteAll();
    }

}
