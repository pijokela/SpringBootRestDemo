package io.pirkka.RestDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class DemoController {
	
	@Autowired
	private BookRepository bookDao;

    @RequestMapping(value="/{id}",produces={"application/json"}, method=RequestMethod.GET)
    JsonNode getBook(@PathVariable("id") long id) {
    	Book book = bookDao.findById(id);
    	if (book == null) {
    		throw new IllegalArgumentException("No book: " + id);
    	}
    	return jsonRoot(book);
    }
    
    @RequestMapping(produces={"application/json"}, method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    JsonNode createBook(@RequestBody ObjectNode json) {
    	Book book = new Book();
    	book.setJson(json);
    	book = bookDao.save(book);
    	return jsonRoot(book);
    }
    
    @RequestMapping(value="/{id}",produces={"application/json"}, method=RequestMethod.PUT)
    JsonNode editBook(@PathVariable("id") long id, @RequestBody ObjectNode json) {
    	Book book = bookDao.findById(id);
    	book.setJson(json);
    	book = bookDao.save(book);
    	return jsonRoot(book);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBook(@PathVariable("id") long id) {
    	bookDao.delete(id);
    }
    
    private JsonNode jsonRoot(Book book) {
    	JsonNodeFactory json = JsonNodeFactory.instance;
    	ObjectNode root = json.objectNode();
    	root.set("meta", json.objectNode().set("version", json.numberNode(1)));
    	root.set("data", json.pojoNode(book));
    	return root;
    }
}
