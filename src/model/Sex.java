package model;

public enum Sex {
	male("male"),
	female("female");
	
	private String value;
	
	Sex(String v) {
		value = v;
	}
	
	public String value() {
		return value;
	}
	
	public static Sex fromValue(String v) {
		for (Sex s: Sex.values()) {
			if (s.value.equals(v))
				return s;
		}
		throw new IllegalArgumentException(v);
	}
}