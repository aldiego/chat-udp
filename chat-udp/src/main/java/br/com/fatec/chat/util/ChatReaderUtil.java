/**
 * 
 */
package br.com.fatec.chat.util;

import java.util.Calendar;

import br.com.fatec.chat.Chat;
import br.com.fatec.chat.EntryPoint;
import br.com.fatec.chat.model.KeepAlive;
import br.com.fatec.chat.model.Leave;
import br.com.fatec.chat.model.Mensagem;
import br.com.fatec.chat.model.Report;
import br.com.fatec.chat.model.Say;
import br.com.fatec.chat.model.Search;
import br.com.fatec.chat.model.User;
import br.com.fatec.chat.model.Whisper;

/**
 * @author Diego
 *
 */
public class ChatReaderUtil {

	/**
	 * @param chat
	 * @param msg
	 * @param hostName
	 */
	public static void atualizaTela(Chat chat, String msg, String hostName) {
		Mensagem mensagem = SerializerUtil.descobreMensagem(msg);
		System.out.println("Menasgem recebida... " + mensagem);
		switch (mensagem.getAction()) {
		case "search":
			atualizaTelaRecebeSearch(chat, (Search) mensagem, hostName);
			break;
		case "keepAlive":
			atualizaTelaRecebeKeepAlive(chat, (KeepAlive) mensagem, hostName);
			break;
		case "say":
			atualizaTelaRecebeSay(chat, (Say) mensagem, hostName);
			break;
		case "whisper":
			atualizaTelaRecebeWhisper(chat, (Whisper) mensagem, hostName);
			break;
		case "leave":
			atualizaTelaRecebeLeave(chat, (Leave) mensagem, hostName);
			break;
		case "report":
			atualizaTelaRecebeReport(chat, (Report) mensagem, hostName);
			break;
		default:
			System.err.println("Mensagem inválida" + msg);
			break;
		}
	}

	/**
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static String getTimestamp() {
		return Calendar.getInstance().getTime().getHours() + ":" + Calendar.getInstance().getTime().getMinutes() + ":"
				+ Calendar.getInstance().getTime().getSeconds();
	}

	/**
	 * @param chat
	 * @param mensagem
	 * @param hostName
	 */
	private static void atualizaTelaRecebeReport(Chat chat, Report mensagem, String hostName) {
		chat.getTextConversa().setText(getTimestamp() + " " + ListaUsuarios.getUsuario(hostName) + " reportou um erro: "
				+ mensagem.getMessage());

	}

	/**
	 * @param chat
	 * @param mensagem
	 * @param hostName
	 */
	private static void atualizaTelaRecebeLeave(Chat chat, Leave mensagem, String hostName) {
		chat.getTextConversa().setText(getTimestamp() + " " + ListaUsuarios.getUsuario(hostName) + " saiu da sala.");
		System.out.println(getTimestamp() + " " + ListaUsuarios.getUsuario(hostName) + " saiu da sala.");
		removeUsuario(chat, hostName);
	}

	/**
	 * @param chat
	 * @param mensagem
	 * @param hostName
	 */
	private static void atualizaTelaRecebeWhisper(Chat chat, Whisper mensagem, String hostName) {
		chat.getTextConversa().setText(getTimestamp() + " " + ListaUsuarios.getUsuario(hostName) + " sussurra à "
				+ EntryPoint.NICKNAME + ": " + mensagem.getContent());

		System.out.println(getTimestamp() + " " + ListaUsuarios.getUsuario(hostName) + " sussurra à "
				+ EntryPoint.NICKNAME + ": " + mensagem.getContent());
	}

	/**
	 * @param chat
	 * @param mensagem
	 * @param hostName
	 */
	private static void atualizaTelaRecebeSay(Chat chat, Say mensagem, String hostName) {
		if (mensagem.getTarget() == null || mensagem.getTarget().isEmpty()) {
			chat.getTextConversa().setText(getTimestamp() + " " + ListaUsuarios.getUsuario(hostName) + " diz à "
					+ ListaUsuarios.getUsuario(mensagem.getTarget() + ": " + mensagem.getContent()));

			System.out.println(getTimestamp() + " " + ListaUsuarios.getUsuario(hostName) + " diz à "
					+ ListaUsuarios.getUsuario(mensagem.getTarget() + ": " + mensagem.getContent()));
		} else {
			chat.getTextConversa().setText(
					getTimestamp() + ListaUsuarios.getUsuario(hostName) + " diz à todos: " + mensagem.getContent());
			System.out.println(
					getTimestamp() + ListaUsuarios.getUsuario(hostName) + " diz à todos: " + mensagem.getContent());

		}
		habilitaBtns(chat);
	}

	/**
	 * @param chat
	 * @param mensagem
	 * @param hostName
	 */
	private static void atualizaTelaRecebeKeepAlive(Chat chat, KeepAlive mensagem, String hostName) {
		if (adicionaUsuario(chat, mensagem.getNickname(), hostName)) {
			chat.getTextConversa().setText(getTimestamp() + " " + mensagem.getNickname() + " entrou na sala.");
			System.out.println(getTimestamp() + " " + mensagem.getNickname() + " entrou na sala.");
		}

		habilitaBtns(chat);
	}

	/**
	 * @param chat
	 */
	public static void atualizaTelaEnviaSearch(Chat chat) {
		chat.getTextConversa().setText(getTimestamp() + " Procurando usuarios na rede...");
		System.out.println(getTimestamp() + " Procurando usuarios na rede...");
	}

	/**
	 * @param chat
	 * @param mensagem
	 * @param hostName
	 */
	private static void atualizaTelaRecebeSearch(Chat chat, Search mensagem, String hostName) {
		if ("192.168.2.76".equals(hostName) || "ArkNote".equals(hostName)) {
			return;
		}
		if (adicionaUsuario(chat, mensagem.getNickname(), hostName)) {
			chat.getTextConversa().setText(getTimestamp() + " " + mensagem.getNickname() + " entrou na sala.");
		}
		habilitaBtns(chat);
	}

	private static boolean adicionaUsuario(Chat chat, String nickname, String hostName) {
		User user = new User(nickname, hostName);
		if (ListaUsuarios.addUser(user)) {
			chat.getComboBoxUsuarios().addItem(user);
			System.out.println(user);
			return true;
		}
		return false;
	}

	/**
	 * @param hostName
	 */
	private static void removeUsuario(Chat chat, String hostName) {
		User user = new User(ListaUsuarios.getUsuario(hostName), hostName);
		chat.getComboBoxUsuarios().removeItem(user);

		ListaUsuarios.removeUsuario(hostName);
	}

	/**
	 * @param chat
	 */
	private static void habilitaBtns(Chat chat) {
		EntryPoint.ALGUEM_NA_SALA = true;
		chat.getBtnSay().setEnabled(true);
		chat.getBtnWhisper().setEnabled(true);

	}

}
