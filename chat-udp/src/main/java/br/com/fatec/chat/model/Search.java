package br.com.fatec.chat.model;

/**
 * @author Diego
 *
 */
public class Search extends Mensagem {

	private String nickname;

	/**
	 * @param nickname
	 */
	public Search(String nickname) {
		super(Action.SEARCH);
		this.nickname = nickname;
	}

	/**
	 * 
	 */
	public Search() {
		super(Action.SEARCH);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
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
		Search other = (Search) obj;
		if (nickname == null) {
			if (other.nickname != null) {
				return false;
			}
		} else if (!nickname.equals(other.nickname)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Search [nickname=" + nickname + "]";
	}

}
