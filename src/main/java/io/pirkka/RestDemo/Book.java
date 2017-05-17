package io.pirkka.RestDemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.fasterxml.jackson.databind.JsonNode;

@Entity 
@TypeDefs( {@TypeDef( name= "JsonNode", typeClass = JsonNodeUserType.class)})
@Table (name = "books")
public class Book {
    @Id @Column (name = "id", nullable = false)
    @SequenceGenerator(name="generator_book_id",sequenceName="seq_book_id", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator_book_id")
    private long id;

    @Column(name = "data", columnDefinition = "json")
    @Type(type = "JsonNode")
    private JsonNode json;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public JsonNode getJson() {
		return json;
	}

	public void setJson(JsonNode json) {
		this.json = json;
	}
}
