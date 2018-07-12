package com.alexfu.sqlitequerybuilder.builder.where;

import static com.alexfu.sqlitequerybuilder.utils.StringUtils.join;

public class ClauseOrBuilder extends ClauseBuilder {

	private ClauseBuilder prefix;
	private String clause;

	public ClauseOrBuilder(ClauseBuilder prefix, String clause) {
		super(clause);
		this.prefix = prefix;
		this.clause = clause;
	}

	@Override
	public String build() {
		return "(" + join(") OR (", prefix.build(), clause) + ")";
	}
}