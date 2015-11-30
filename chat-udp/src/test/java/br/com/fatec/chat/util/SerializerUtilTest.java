/**
 * 
 */
package br.com.fatec.chat.util;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import br.com.fatec.chat.model.KeepAlive;
import br.com.fatec.chat.model.Leave;
import br.com.fatec.chat.model.Report;
import br.com.fatec.chat.model.Say;
import br.com.fatec.chat.model.Search;
import br.com.fatec.chat.model.User;
import br.com.fatec.chat.model.Whisper;
import br.com.fatec.chat.serializer.KeepAliveJSONSerializer;
import br.com.fatec.chat.serializer.LeaveJSONSerializer;
import br.com.fatec.chat.serializer.ReportJSONSerializer;
import br.com.fatec.chat.serializer.SayJSONSerializer;
import br.com.fatec.chat.serializer.SearchJSONSerializer;
import br.com.fatec.chat.serializer.WhisperJSONSerializer;

/**
 * @author Diego
 *
 */
public class SerializerUtilTest {

	@Test
	public void testKeepAlive() {
		KeepAlive keepAliveExpected = new KeepAlive("leave",
				Arrays.asList(new User("Teste", "127.0.0.1"), new User("Teste 2", "192.168.0.1")));

		String keepAliveSerialized = new KeepAliveJSONSerializer().serialize(keepAliveExpected);
		KeepAlive keepAliveActual = (KeepAlive) SerializerUtil.descobreMensagem(keepAliveSerialized);

		assertEquals(keepAliveExpected, keepAliveActual);
	}

	@Test
	public void testLeave() {
		Leave leaveExpected = new Leave();

		String leaveSerialized = new LeaveJSONSerializer().serialize(leaveExpected);
		Leave leaveActual = (Leave) SerializerUtil.descobreMensagem(leaveSerialized);

		assertEquals(leaveExpected, leaveActual);
	}

	@Test
	public void testReport() {
		Report reportExpected = new Report("leave");

		String reportSerialized = new ReportJSONSerializer().serialize(reportExpected);
		Report reportActual = (Report) SerializerUtil.descobreMensagem(reportSerialized);

		assertEquals(reportExpected, reportActual);
	}

	@Test
	public void testSay() {
		Say sayExpected = new Say("127.0.0.1", "report");

		String saySerialized = new SayJSONSerializer().serialize(sayExpected);
		Say sayActual = (Say) SerializerUtil.descobreMensagem(saySerialized);

		assertEquals(sayExpected, sayActual);
	}

	@Test
	public void testSearch() {
		Search searchExpected = new Search("keepAlive");

		String searchSerialized = new SearchJSONSerializer().serialize(searchExpected);
		Search searchActual = (Search) SerializerUtil.descobreMensagem(searchSerialized);

		assertEquals(searchExpected, searchActual);
	}

	@Test
	public void testWhisper() {
		Whisper whisperExpected = new Whisper("search");

		String whisperSerialized = new WhisperJSONSerializer().serialize(whisperExpected);
		Whisper whisperActual = (Whisper) SerializerUtil.descobreMensagem(whisperSerialized);

		assertEquals(whisperExpected, whisperActual);
	}

}
