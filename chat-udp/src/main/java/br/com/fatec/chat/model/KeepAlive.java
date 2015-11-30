package br.com.fatec.chat.model;

import java.util.List;

/**
 * @author Diego
 *
 */
public class KeepAlive extends Mensagem {

	private String nickname;
	private List<User> users;

	/**
	 * 
	 */
	public KeepAlive() {
		super(Action.KEEP_ALIVE);
	}

	/**
	 * @param nickname
	 * @param users
	 */
	public KeepAlive(String nickname, List<User> users) {
		super(Action.KEEP_ALIVE);
		this.nickname = nickname;
		this.users = users;
	}

	/**
	 * @param nickname
	 */
	public KeepAlive(String nickname) {
		super(Action.KEEP_ALIVE);
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		KeepAlive other = (KeepAlive) obj;
		if (nickname == null) {
			if (other.nickname != null) {
				return false;
			}
		} else if (!nickname.equals(other.nickname)) {
			return false;
		}
		if (users == null) {
			if (other.users != null) {
				return false;
			}
		} else if (!users.equals(other.users)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "KeepAlive [nickname=" + nickname + ", users=" + users + "]";
	}

}
