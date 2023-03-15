package main.model;

public enum LineEnum {
	CRONOS("Cronos"),
	ARES(  "Ares");

	private final String name;

	LineEnum(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
