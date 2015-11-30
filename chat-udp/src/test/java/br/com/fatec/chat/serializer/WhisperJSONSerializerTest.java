package br.com.fatec.chat.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.com.fatec.chat.model.Whisper;

public class WhisperJSONSerializerTest {

	@Test
	public void testSucess() {
		Whisper whisperExpected = new Whisper("teste");

		String whisperSerialized = new WhisperJSONSerializer().serialize(whisperExpected);
		Whisper whisperActual = new WhisperJSONSerializer().deserialize(whisperSerialized);

		assertEquals(whisperExpected, whisperActual);

	}

	@Test
	public void testDeserializeFail() {
		assertNull(new WhisperJSONSerializer().deserialize(new String()));

	}

	@Test
	public void testDeserializeSuccess() {
		String whisper = "{\"action\":\"whisper\",\"content\": \"Olá, josé, como vai?\"	}";
		Whisper whisperActual = new WhisperJSONSerializer().deserialize(whisper);

		assertEquals(new Whisper("Olá, josé, como vai?"), whisperActual);

	}

}
