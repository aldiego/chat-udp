/**
 * 
 */
package br.com.fatec.chat.util;

import br.com.fatec.chat.model.KeepAlive;
import br.com.fatec.chat.model.Leave;
import br.com.fatec.chat.model.Mensagem;
import br.com.fatec.chat.model.Report;
import br.com.fatec.chat.model.Say;
import br.com.fatec.chat.model.Search;
import br.com.fatec.chat.model.Whisper;
import br.com.fatec.chat.serializer.KeepAliveJSONSerializer;
import br.com.fatec.chat.serializer.LeaveJSONSerializer;
import br.com.fatec.chat.serializer.ReportJSONSerializer;
import br.com.fatec.chat.serializer.SayJSONSerializer;
import br.com.fatec.chat.serializer.SearchJSONSerializer;
import br.com.fatec.chat.serializer.Serializer;
import br.com.fatec.chat.serializer.WhisperJSONSerializer;

/**
 * @author Diego
 *
 */
public class SerializerUtil {

	private static Serializer<KeepAlive> keepAliveSerializer = new KeepAliveJSONSerializer();
	private static Serializer<Leave> leaveSerializer = new LeaveJSONSerializer();
	private static Serializer<Report> reportSerializer = new ReportJSONSerializer();
	private static Serializer<Say> saySerializer = new SayJSONSerializer();
	private static Serializer<Search> searchSerializer = new SearchJSONSerializer();
	private static Serializer<Whisper> whisperSerializer = new WhisperJSONSerializer();

	/**
	 * @param msg
	 * @return
	 */
	public static Mensagem descobreMensagem(String msg) {
		Mensagem mensagem = null;

		if (mensagem == null || msg.contains("keepAlive") && msg.contains("nickname")) {
			mensagem = keepAliveSerializer.deserialize(msg);
		}
		if (mensagem == null || msg.contains("leave") && !msg.contains("nickname") && !msg.contains("content")) {
			mensagem = leaveSerializer.deserialize(msg);
		}
		if (mensagem == null || msg.contains("report") && msg.contains("message")) {
			mensagem = reportSerializer.deserialize(msg);
		}
		if (mensagem == null || msg.contains("say") && msg.contains("content")) {
			mensagem = saySerializer.deserialize(msg);
		}
		if (mensagem == null || msg.contains("search") && msg.contains("nickname") && !msg.contains("users")) {
			mensagem = searchSerializer.deserialize(msg);
		}
		if (mensagem == null || msg.contains("whisper") && msg.contains("content")) {
			mensagem = whisperSerializer.deserialize(msg);
		}

		if (mensagem == null)
			throw new IllegalStateException("Não conseguiu fazer o parser do JSON");

		return mensagem;
	}

}
