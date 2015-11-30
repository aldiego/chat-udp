package br.com.fatec.chat.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.com.fatec.chat.model.Say;

public class SayJSONSerializerTest {

	@Test
	public void testSucess() {
		Say sayExpected = new Say("Ola");

		String saySerialized = new SayJSONSerializer().serialize(sayExpected);
		Say sayActual = new SayJSONSerializer().deserialize(saySerialized);

		assertEquals(sayExpected, sayActual);

	}

	@Test
	public void testSayCompleteSucess() {
		Say sayExpected = new Say("127.0.0.1", "Ola");

		String saySerialized = new SayJSONSerializer().serialize(sayExpected);
		Say sayActual = new SayJSONSerializer().deserialize(saySerialized);

		assertEquals(sayExpected, sayActual);

	}

	@Test
	public void testDeserializeFail() {
		assertNull(new SayJSONSerializer().deserialize(""));

	}

	@Test
	public void testDeserializeSuccess() {
		String say = "{\"target\":\"192.168.0.125\",\"action\":\"say\",\"content\": \"Olá!\"}";

		Say sayActual = new SayJSONSerializer().deserialize(say);

		assertEquals(new Say("192.168.0.125", "Olá!"), sayActual);

	}

}
