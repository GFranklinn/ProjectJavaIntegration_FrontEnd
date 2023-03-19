package main.model;

public enum ModelEnum {

    CRONOS_6001_A( CategoryEnum.CRONOS_OLD, "Cronos 6001-A"),
    CRONOS_6003(   CategoryEnum.CRONOS_OLD, "Cronos 6003"),
    CRONOS_7023(   CategoryEnum.CRONOS_OLD, "Cronos 7023"),
    CRONOS_6021L(  CategoryEnum.CRONOS_L,   "Cronos 6021L"),
    CRONOS_7023L(  CategoryEnum.CRONOS_L,   "Cronos 7023L"),
    CRONOS_6001_NG(CategoryEnum.CRONOS_NG,  "Cronos 6001-NG"),
    CRONOS_6003_NG(CategoryEnum.CRONOS_NG,  "Cronos 6003-NG"),
    CRONOS_6021_NG(CategoryEnum.CRONOS_NG,  "Cronos 6021-NG"),
    CRONOS_6031_NG(CategoryEnum.CRONOS_NG,  "Cronos 6031-NG"),
    CRONOS_7021_NG(CategoryEnum.CRONOS_NG,  "Cronos 7021-NG"),
    CRONOS_7023_NG(CategoryEnum.CRONOS_NG,  "Cronos 7023-NG"),
    ARES_7021(     CategoryEnum.ARES_TB,    "Ares 7021"),
    ARES_7031(     CategoryEnum.ARES_TB,    "Ares 7031"),
    ARES_7023(     CategoryEnum.ARES_TB,    "Ares 7023"),
    ARES_8023_15(  CategoryEnum.ARES_THS,   "Ares 8023 15"),
    ARES_8023_200( CategoryEnum.ARES_THS,   "Ares 8023 200"),
    ARES_8023_2_5( CategoryEnum.ARES_THS,   "Ares 8023 2,5");

	private final CategoryEnum categoryEnum;
	private final String name;

	ModelEnum(CategoryEnum categoryEnum, String name) {
		this.categoryEnum = categoryEnum;
		this.name = name;
	}

	public CategoryEnum getCategoryEnum() {
		return categoryEnum;
	}

	@Override
	public String toString() {
		return name;
	}
}