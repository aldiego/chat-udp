package br.com.fatec.chat.model;

/**
 * @author Diego
 *
 */
public class Whisper extends Mensagem {

	private String content;

	/**
	 * @param content
	 */
	public Whisper(String content) {
		super(Action.WHISPER);

		this.content = content;
	}

	/**
	 * 
	 */
	public Whisper() {
		super(Action.WHISPER);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((content == null) ? 0 : content.hashCode());
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
		Whisper other = (Whisper) obj;
		if (content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!content.equals(other.content)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Whisper [content=" + content + "]";
	}

}
