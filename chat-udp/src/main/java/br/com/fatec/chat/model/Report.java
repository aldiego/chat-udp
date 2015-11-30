package br.com.fatec.chat.model;

/**
 * @author Diego
 *
 */
public class Report extends Mensagem {

	private String message;

	/**
	 * @param message
	 */
	public Report(String message) {
		super(Action.REPORT);
		this.message = message;
	}

	/**
	 * 
	 */
	public Report() {
		super(Action.REPORT);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		Report other = (Report) obj;
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Report [message=" + message + "]";
	}

}
