/**
 * 
 */
package br.com.fatec.chat.util;

import java.nio.charset.Charset;

import br.com.fatec.chat.EntryPoint;
import br.com.fatec.chat.model.KeepAlive;
import br.com.fatec.chat.model.Leave;
import br.com.fatec.chat.model.Say;
import br.com.fatec.chat.model.Search;
import br.com.fatec.chat.model.User;
import br.com.fatec.chat.model.Whisper;
import br.com.fatec.chat.serializer.KeepAliveJSONSerializer;
import br.com.fatec.chat.serializer.LeaveJSONSerializer;
import br.com.fatec.chat.serializer.SayJSONSerializer;
import br.com.fatec.chat.serializer.SearchJSONSerializer;
import br.com.fatec.chat.serializer.WhisperJSONSerializer;

/**
 * @author Diego
 *
 */
public class ChatWriterUtil {

	/**
	 * @return
	 */
	public static byte[] montaSearch() {
		Search search = new Search(EntryPoint.NICKNAME);

		System.out.println(search);
		return new SearchJSONSerializer().serialize(search).getBytes(Charset.forName("UTF-8"));
	}

	/**
	 * @return
	 */
	public static byte[] montaLeave() {
		Leave leave = new Leave();

		System.out.println(leave);
		return new LeaveJSONSerializer().serialize(leave).getBytes(Charset.forName("UTF-8"));
	}

	/**
	 * @return
	 */
	public static byte[] montaKeepAlive() {
		KeepAlive keepAlive = new KeepAlive(EntryPoint.NICKNAME, ListaUsuarios.getListaUsuarios());

		System.out.println(keepAlive);
		return new KeepAliveJSONSerializer().serialize(keepAlive).getBytes(Charset.forName("UTF-8"));
	}

	/**
	 * @param user
	 * @param texto
	 * @return
	 */
	public static byte[] montaSay(User user, String texto) {
		Say say = new Say(user.getAddress(), texto);
		System.out.println(say);
		return new SayJSONSerializer().serialize(say).getBytes(Charset.forName("UTF-8"));
	}

	/**
	 * @param user
	 * @param texto
	 * @return
	 */
	public static byte[] montaSay(String texto) {
		Say say = new Say(texto);
		System.out.println(say);
		return new SayJSONSerializer().serialize(say).getBytes(Charset.forName("UTF-8"));
	}

	/**
	 * @param user
	 * @param texto
	 * @return
	 */
	public static byte[] montaWhisper(User user, String texto) {
		Whisper whisper = new Whisper(texto);

		System.out.println(whisper);
		return new WhisperJSONSerializer().serialize(whisper).getBytes(Charset.forName("UTF-8"));
	}

}
