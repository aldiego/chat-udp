/**
 * 
 */
package br.com.fatec.chat.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Test;

import br.com.fatec.chat.model.KeepAlive;
import br.com.fatec.chat.model.User;

/**
 * @author Diego
 *
 */
public class KeepAliveJSONSerializerTest {

	@Test
	public void testKeepAliveCompleteSucess() {
		KeepAlive keepAliveExpected = new KeepAlive("Diego",
				Arrays.asList(new User("Teste", "127.0.0.1"), new User("Teste 2", "192.168.0.1")));

		String keepAliveSerialized = new KeepAliveJSONSerializer().serialize(keepAliveExpected);
		KeepAlive keepAliveActual = new KeepAliveJSONSerializer().deserialize(keepAliveSerialized);

		assertEquals(keepAliveExpected, keepAliveActual);

	}

	@Test
	public void testDeserializeFail() {
		assertNull(new KeepAliveJSONSerializer().deserialize(""));

	}

	@Test
	public void testDeserializeSuccess() {
		String keepAlive = "{\"action\":\"keepAlive\",\"nickname\": \"Vader\",\"users\" : [{\"nickname\":\"Luke\", \"address\":\"192.168.0.1\"},{\"nickname\":\"Yoda\", \"address\":\"192.168.0.4\", \"timestamp\":\"1234567\"},{\"nickname\":\"R2D2\", \"address\":\"192.168.0.6\"} ]}";

		KeepAlive keepAliveActual = new KeepAliveJSONSerializer().deserialize(keepAlive);

		assertEquals(new KeepAlive("Vader", Arrays.asList(new User("Luke", "192.168.0.1"),
				new User("Yoda", "192.168.0.4", 1234567L), new User("R2D2", "192.168.0.6"))), keepAliveActual);

	}

}
