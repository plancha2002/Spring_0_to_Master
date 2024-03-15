package com.example.repository;

import com.example.model.Book;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookRepository {

    //simulamos una bbdd ficticia
    private static Map<Long, Book> database;

    public BookRepository() {
        database = new ConcurrentHashMap<>();
        database.put(1L, new Book(1L, "book1", "aut1"));
        database.put(2L, new Book(2L, "book2", "aut2"));
        database.put(3L, new Book(3L, "book3", "aut3"));
        database.put(4L, new Book(4L, "book4", "aut4"));
        database.put(5L, new Book(5L, "book5", "aut5"));
        database.put(6L, new Book(6L, "book6", "aut6"));
        database.put(7L, new Book(7L, "book7", "aut7"));
        database.put(8L, new Book(8L, "book8", "aut8"));
    }

    /*
    mono es cuando trabajamos con un restultado
    como cuando usamos optional
     */
    public Mono<Book> findById(Long id){
        return Mono.just(database.get(id));
    }
    /*
    Flux nos servira para cuando devolvamos varios elementos
    flux en vez de devolver todos los elementos de golpe los va enviando de poco a poco
     */
    public Flux<Book> findAll(){
        return Flux.fromIterable(database.values());
    }

    public Mono<Boolean> existById(Long id){
                                    //operdor ternario
        return database.containsKey(id) ? Mono.just(true) : Mono.just(false);
    }

    public Mono<Book> save(Book book){
        if(book.getId() == null){
            /*
    - database.values().stream():
            Convierte la colección de valores del ConcurrentHashMap en un flujo de datos.
    - .mapToLong(Book::getId):
            Mapea cada elemento del flujo (que es un Book) a su ID (Long).
    - .max().orElse(0L):
            Obtiene el valor máximo del flujo de IDs. Si el flujo está vacío, se devuelve 0L como valor por defecto.
    - + 1: Se suma 1 al valor máximo obtenido para obtener el siguiente ID disponible.
             */
            Long id = database.values().stream().mapToLong(Book::getId).max().orElse(0L) + 1;
            book.setId(id);
        }
        database.put(book.getId(), book);
        return Mono.just(book);
    }

    public Mono<Void> deleteById(Long id){
        database.remove(id);
        return Mono.empty();
    }

    public Mono<Void> deleteAll(){
        database.clear();
        return Mono.empty();
    }


}
