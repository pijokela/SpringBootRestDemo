package io.pirkka.RestDemo;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

  private final static ObjectMapper mapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(JsonNode node) {
	  ObjectMapper mapper = new ObjectMapper();
	  try {
		  return mapper.writeValueAsString(node);
	  } catch (JsonProcessingException e) {
		  throw new RuntimeException("Failed to write Json as String.", e);
	  }
  }

  @Override
  public JsonNode convertToEntityAttribute(String jsonString) {
      try {
		return mapper.readTree(jsonString);
      } catch (IOException e) {
    	  throw new RuntimeException("Failed to read Json from String.", e);
      }
  }

}