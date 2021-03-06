package br.com.fatec.chat.serializer;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.fatec.chat.model.Search;

public class SearchJSONSerializer implements Serializer<Search> {

	ObjectMapper objectMapper = new ObjectMapper();

	public Search deserialize(String byteArray) {
		try {
			Search search = objectMapper.readValue(byteArray, Search.class);

			return search;

		} catch (IOException e) {
			System.err.println("N�o foi poss�vel ler o JSON !!!" + e);
			return null;
		}
	}

	public String serialize(Search search) {
		try {
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

			StringWriter stringWriter = new StringWriter();

			objectMapper.writeValue(stringWriter, search);

			return stringWriter.toString();

		} catch (IOException e) {
			System.err.println("N�o foi poss�vel ler o JSON !!!" + e);
			return null;
		}
	}

}
