package br.com.fatec.chat.model;

import br.com.fatec.chat.util.ValidationUtil;

/**
 * @author Diego
 *
 */
public class Say extends Mensagem {

	private String target;
	private String content;

	/**
	 * @param target
	 * @param content
	 */
	public Say(String target, String content) {
		super(Action.SAY);

		if (ValidationUtil.isValidInetAddress(target))
			this.target = target;
		else
			throw new IllegalArgumentException("IP inválido");

		this.content = content;
	}

	/**
	 * @param content
	 */
	public Say(String content) {
		super(Action.SAY);

		this.content = content;
	}

	/**
	 * 
	 */
	public Say() {
		super(Action.SAY);
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		Say other = (Say) obj;
		if (content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!content.equals(other.content)) {
			return false;
		}
		if (target == null) {
			if (other.target != null) {
				return false;
			}
		} else if (!target.equals(other.target)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Say [target=" + target + ", content=" + content + "]";
	}

}
