package com.alexfu.sqlitequerybuilder.builder.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum FunctionType {
	AVERAGE("AVG(", Collections.singletonList(1)),
	COUNT("COUNT(", Arrays.asList(0, 1)),
	GROUP_CONCATENATION("GROUP_CONCAT(", Arrays.asList(1, 2)),
	MAX("MAX(", Collections.singletonList(1)),
	MIN("MIN(", Collections.singletonList(1)),
	SUM("SUM(", Collections.singletonList(1)),
	TOTAL("TOTAL(", Collections.singletonList(1));

	public final String name;
	public final List<Integer> argumentNumber;

	FunctionType(String name, List<Integer> argumentNumber) {
		this.name = name;
		this.argumentNumber = argumentNumber;
	}
}
