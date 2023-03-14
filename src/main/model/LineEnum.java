package main.model;

public enum LineEnum {
	CRONOS("Cronos"),
	ARES(  "Ares");

	private String name;

	LineEnum(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}
