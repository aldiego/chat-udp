package br.com.fatec.chat.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.com.fatec.chat.model.Search;

public class SearchJSONSerializerTest {

	@Test
	public void testSucess() {
		Search searchExpected = new Search("Diego");

		String searchSerialized = new SearchJSONSerializer().serialize(searchExpected);
		Search searchActual = new SearchJSONSerializer().deserialize(searchSerialized);

		assertEquals(searchExpected, searchActual);

	}

	@Test
	public void testDeserializeFail() {
		assertNull(new SearchJSONSerializer().deserialize(""));

	}

	@Test
	public void testDeserializeSuccess() {
		String search = "{\"action\":\"search\",\"nickname\": \"Luke\"	}";
		Search searchActual = new SearchJSONSerializer().deserialize(search);

		assertEquals(new Search("Luke"), searchActual);

	}

}
