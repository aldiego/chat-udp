package br.com.fatec.chat.model;

/**
 * @author Diego
 *
 */
public class Mensagem {
	
	private String action;

	/**
	 * 
	 */
	public Mensagem() {
		super();
	}

	/**
	 * @param action
	 */
	public Mensagem(Action action) {
		super();
		this.action = action.getValue();
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
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
		Mensagem other = (Mensagem) obj;
		if (action == null) {
			if (other.action != null) {
				return false;
			}
		} else if (!action.equals(other.action)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Mensagem [action=" + action + "]";
	}

}
