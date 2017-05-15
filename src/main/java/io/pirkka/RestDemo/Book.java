package io.pirkka.RestDemo;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.JsonNode;

@Entity 
@Table (name = "books")
public class Book {
    @Id @Column (name = "id", nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "data")
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode json;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JsonNode getJson() {
		return json;
	}

	public void setJson(JsonNode json) {
		this.json = json;
	}
}
