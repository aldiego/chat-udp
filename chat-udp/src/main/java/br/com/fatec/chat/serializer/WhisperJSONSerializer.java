package br.com.fatec.chat.serializer;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.fatec.chat.model.Whisper;

public class WhisperJSONSerializer implements Serializer<Whisper> {

	ObjectMapper objectMapper = new ObjectMapper();

	public Whisper deserialize(String byteArray) {
		try {
			Whisper whisper = objectMapper.readValue(byteArray, Whisper.class);

			return whisper;

		} catch (IOException e) {
			System.err.println("Não foi possível ler o JSON !!!" + e);
			return null;
		}

	}

	public String serialize(Whisper whisper) {
		try {
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

			StringWriter stringWriter = new StringWriter();

			objectMapper.writeValue(stringWriter, whisper);

			return stringWriter.toString();

		} catch (IOException e) {
			System.err.println("Não foi possível ler o JSON !!!" + e);
			return null;
		}

	}

}
