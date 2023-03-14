package main.model;

public enum CategoryEnum {

    CRONOS_OLD(LineEnum.CRONOS, "Cronos Old"),
    CRONOS_L(  LineEnum.CRONOS, "Cronos L"),
    CRONOS_NG( LineEnum.CRONOS, "Cronos NG"),
    ARES_TB(   LineEnum.ARES,   "Ares TB"),
    ARES_THS(  LineEnum.ARES,   "Ares THS");

	private final LineEnum lineEnum;
	private final String name;

	CategoryEnum(LineEnum lineEnum, String name) {
		this.lineEnum = lineEnum;
		this.name = name;
	}

	public LineEnum getLineEnum() {
		return lineEnum;
	}

	@Override
	public String toString() {
		return name;
	}
}