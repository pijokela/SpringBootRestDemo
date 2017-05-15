package io.pirkka.RestDemo;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
	Book findById(int id);
}