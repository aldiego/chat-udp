package br.com.fatec.chat.model;

public enum Action {

	SEARCH("search"), KEEP_ALIVE("keepAlive"), SAY("say"), WHISPER("whisper"), LEAVE("leave"), REPORT("report");

	private String value;

	private Action(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
