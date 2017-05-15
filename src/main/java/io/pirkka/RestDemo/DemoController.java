package io.pirkka.RestDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;


class HelloWorld {
	private String greeting;

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}

@RestController
public class DemoController {
	
	@Autowired
	private BookRepository bookDao;

    @RequestMapping(value="/{id}",produces={"application/json"})
    JsonNode home(@PathVariable("id") int id) {
    	
    	Book book = bookDao.findById(id);
    	
    	JsonNodeFactory json = JsonNodeFactory.instance;
    	ObjectNode root = json.objectNode();
    	root.set("meta", json.objectNode().set("version", json.numberNode(1)));
    	root.set("data", json.pojoNode(book));
    	return root;
    }
}
