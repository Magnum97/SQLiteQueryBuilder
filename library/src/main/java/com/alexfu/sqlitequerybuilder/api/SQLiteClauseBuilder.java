package com.alexfu.sqlitequerybuilder.api;

import com.alexfu.sqlitequerybuilder.builder.where.ClauseBuilder;

public class SQLiteClauseBuilder {
	public static ClauseBuilder clause(String clause) {
		return new ClauseBuilder(clause);
	}

	public static ClauseBuilder clause(ClauseBuilder clause) {
		return new ClauseBuilder(clause);
	}
}