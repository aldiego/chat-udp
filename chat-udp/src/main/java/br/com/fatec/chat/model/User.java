package br.com.fatec.chat.model;

import br.com.fatec.chat.util.ValidationUtil;

/**
 * @author Diego
 *
 */
public class User {

	private String nickname;
	private String address;
	private Long timestamp;

	/**
	 * 
	 */
	public User() {
	}

	/**
	 * @param nickname
	 * @param address
	 */
	public User(String nickname, String address) {
		super();

		if (ValidationUtil.isValidInetAddress(address))
			this.address = address;
		else
			throw new IllegalArgumentException("IP inválido");

		this.nickname = nickname;
	}

	public User(String nickname, String address, Long timestamp) {
		super();
		this.nickname = nickname;
		if (ValidationUtil.isValidInetAddress(address))
			this.address = address;
		else
			throw new IllegalArgumentException("IP inválido");
		this.timestamp = timestamp;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (ValidationUtil.isValidInetAddress(address))
			this.address = address;
		else
			throw new IllegalArgumentException("IP inválido");

	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timesptamp) {
		this.timestamp = timesptamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (nickname == null) {
			if (other.nickname != null) {
				return false;
			}
		} else if (!nickname.equals(other.nickname)) {
			return false;
		}
		if (timestamp == null) {
			if (other.timestamp != null) {
				return false;
			}
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [nickname=" + nickname + ", address=" + address + ", timesptamp=" + timestamp + "]";
	}

}
