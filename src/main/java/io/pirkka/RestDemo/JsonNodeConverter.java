package io.pirkka.RestDemo;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.AttributeConverter;

import org.postgresql.util.PGobject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonNodeConverter implements AttributeConverter<JsonNode, PGobject> {

  private final static ObjectMapper mapper = new ObjectMapper();

  @Override
  public PGobject convertToDatabaseColumn(JsonNode node) {
	  try {
		  String jsonString = mapper.writeValueAsString(node);
		  PGobject pgObject = new PGobject();
		  pgObject.setType("json");
		  pgObject.setValue(jsonString);
		  return pgObject;
	  } catch (JsonProcessingException e) {
		  throw new RuntimeException("Failed to write Json as String.", e);
	  } catch (SQLException e) {
		  throw new RuntimeException("Failed to write Json as String.", e);
	  }
  }

  @Override
  public JsonNode convertToEntityAttribute(PGobject jsonPgObject) {
      try {
    	  String jsonString = jsonPgObject.getValue();
    	  return mapper.readTree(jsonString);
      } catch (IOException e) {
    	  throw new RuntimeException("Failed to read Json from String.", e);
      }
  }

}