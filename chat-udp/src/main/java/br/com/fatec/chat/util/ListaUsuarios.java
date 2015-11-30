/**
 * 
 */
package br.com.fatec.chat.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.fatec.chat.model.User;

/**
 * @author Diego
 *
 */
public class ListaUsuarios {

	/**
	 * 
	 */
	private static ConcurrentHashMap<String, String> usuarios = new ConcurrentHashMap<String, String>();

	/**
	 * @param newUser
	 * @return true caso seja um novo usuario
	 */
	public static boolean addUser(User newUser) {
		boolean retorno = usuarios.containsKey(newUser.getAddress());
		usuarios.put(newUser.getAddress(), newUser.getNickname());

		return retorno;
	}

	/**
	 * @return
	 */
	public static Map<String, String> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param hostName
	 * @return
	 */
	public static String getUsuario(String hostName) {
		return usuarios.get(hostName);
	}

	/**
	 * @param hostName
	 */
	public static void removeUsuario(String hostName) {
		usuarios.remove(hostName);
	}

	/**
	 * @return
	 */
	public static List<User> getListaUsuarios() {
		List<User> users = new ArrayList<User>();
		for (String address : usuarios.keySet()) {
			users.add(new User(usuarios.get(address), address));
		}
		return users;
	}

}
