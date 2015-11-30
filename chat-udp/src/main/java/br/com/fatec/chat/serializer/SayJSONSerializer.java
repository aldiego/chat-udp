package br.com.fatec.chat.serializer;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.fatec.chat.model.Say;

/**
 * @author Diego
 *
 */
public class SayJSONSerializer implements Serializer<Say> {

	ObjectMapper objectMapper = new ObjectMapper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fatec.chat.serializer.Serializer#deserialize(byte[])
	 */
	public Say deserialize(String byteArray) {
		try {
			Say say = objectMapper.readValue(byteArray, Say.class);

			return say;

		} catch (IOException e) {
			System.err.println("Não foi possível ler o JSON !!!" + e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.fatec.chat.serializer.Serializer#serialize(br.com.fatec.chat.model
	 * .Mensagem)
	 */
	public String serialize(Say say) {
		try {
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

			StringWriter stringWriter = new StringWriter();

			objectMapper.writeValue(stringWriter, say);

			return stringWriter.toString();

		} catch (IOException e) {
			System.err.println("Não foi possível ler o JSON !!!" + e);
			return null;
		}
	}

}
