/**
 * 
 */
package br.com.fatec.chat.serializer;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.fatec.chat.model.Leave;

/**
 * @author Diego
 *
 */
public class LeaveJSONSerializer implements Serializer<Leave> {

	ObjectMapper objectMapper = new ObjectMapper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.fatec.chat.serializer.Serializer#deserialize(byte[])
	 */
	public Leave deserialize(String byteArray) {
		try {
			Leave leave = objectMapper.readValue(byteArray, Leave.class);

			return leave;

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
	public String serialize(Leave leave) {
		try {
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

			StringWriter stringWriter = new StringWriter();

			objectMapper.writeValue(stringWriter, leave);

			return stringWriter.toString();

		} catch (IOException e) {
			System.err.println("Não foi possível ler o JSON !!!" + e);
			return null;
		}
	}

}
