package br.com.fatec.chat.serializer;

import br.com.fatec.chat.model.Mensagem;

/**
 * @author Diego
 *
 * @param <T extends Mensagem>
 */
public interface Serializer<T extends Mensagem> {

	/**
	 * @param byteArray
	 * @return
	 */
	public T deserialize(String byteArray);

	/**
	 * @param object
	 * @return
	 */
	public String serialize(T object);

}
