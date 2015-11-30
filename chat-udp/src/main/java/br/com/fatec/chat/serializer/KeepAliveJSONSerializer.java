/**
 * 
 */
package br.com.fatec.chat.serializer;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.fatec.chat.model.KeepAlive;

/**
 * @author Diego
 *
 */
public class KeepAliveJSONSerializer implements Serializer<KeepAlive> {

	ObjectMapper objectMapper = new ObjectMapper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fatec.chat.serializer.Serializer#deserialize(byte[])
	 */
	public KeepAlive deserialize(String byteArray) {
		try {
			KeepAlive keepAlive = objectMapper.readValue(byteArray, KeepAlive.class);

			return keepAlive;

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
	public String serialize(KeepAlive keepAlive) {
		try {
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

			StringWriter stringWriter = new StringWriter();

			objectMapper.writeValue(stringWriter, keepAlive);

			return stringWriter.toString();

		} catch (IOException e) {
			System.err.println("Não foi possível ler o JSON !!!" + e);
			return null;
		}
	}

}
