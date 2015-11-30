package br.com.fatec.chat;

import br.com.fatec.chat.comunication.Reader;
import br.com.fatec.chat.comunication.Writer;

public class EntryPoint {

	public static final int PORTA = 9000;
	public static final String NICKNAME = "Diego Lima";

	public static volatile boolean ALGUEM_NA_SALA = false;
	public static volatile boolean SAIR = false;

	private static Chat chat;
	private static Reader reader;
	private static Writer writer;

	public static void main(String[] args) {
		criaThreads();
		iniciaTela();

	}

	/**
	 * 
	 */
	private static void criaThreads() {
		System.out.println("Iniciando as Threads...");
		chat = Chat.getInsance();
		reader = new Reader();
		writer = new Writer();
	}

	/**
	 * 
	 */
	private static void iniciaTela() {

		new Thread(chat).start();
		new Thread(writer).start();
		new Thread(reader).start();
	}

	public static Chat getChat() {
		return chat;
	}

	public static Reader getReader() {
		return reader;
	}

	public static Writer getWriter() {
		return writer;
	}

}
