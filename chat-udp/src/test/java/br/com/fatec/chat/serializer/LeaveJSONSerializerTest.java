/**
 * 
 */
package br.com.fatec.chat.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.com.fatec.chat.model.Leave;

/**
 * @author Diego
 *
 */
public class LeaveJSONSerializerTest {

	@Test
	public void testLeaveCompleteSucess() {
		Leave leaveExpected = new Leave();

		String leaveSerialized = new LeaveJSONSerializer().serialize(leaveExpected);
		Leave leaveActual = new LeaveJSONSerializer().deserialize(leaveSerialized);

		assertEquals(leaveExpected, leaveActual);

	}

	@Test
	public void testDeserializeFail() {
		assertNull(new LeaveJSONSerializer().deserialize(""));

	}

	@Test
	public void testDeserializeSuccess() {
		String leave = "{\"action\":\"leave\"}";

		Leave leaveActual = new LeaveJSONSerializer().deserialize(leave);

		assertEquals(new Leave(), leaveActual);

	}

}
