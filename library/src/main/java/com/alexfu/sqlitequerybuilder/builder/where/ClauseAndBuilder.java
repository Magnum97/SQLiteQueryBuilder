package com.alexfu.sqlitequerybuilder.builder.where;

import static com.alexfu.sqlitequerybuilder.utils.StringUtils.join;

public class ClauseAndBuilder extends ClauseBuilder {

	private ClauseBuilder prefix;
	private String clause;

	ClauseAndBuilder(ClauseBuilder prefix, String clause) {
		super(clause);
		this.prefix = prefix;
		this.clause = clause;
	}

	@Override
	public String build() {
		return "(" + join(") AND (", prefix.build(), clause) + ")";
	}
}
