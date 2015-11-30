package br.com.fatec.chat.model;

/**
 * @author Diego
 *
 */
public class Leave extends Mensagem {

	public Leave() {
		super(Action.LEAVE);
	}

	@Override
	public String toString() {
		return "Leave [toString()=" + super.toString() + "]";
	}

}
