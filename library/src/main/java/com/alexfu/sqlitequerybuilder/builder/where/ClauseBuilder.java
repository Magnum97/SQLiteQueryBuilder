package com.alexfu.sqlitequerybuilder.builder.where;

import com.alexfu.sqlitequerybuilder.builder.SegmentBuilder;

public class ClauseBuilder extends SegmentBuilder {

	private String clause;

	public ClauseBuilder(String clause) {
		this.clause = clause;
	}

	public ClauseBuilder(ClauseBuilder clause) {
		this.clause = clause.build();
	}

	public ClauseBuilder and(String clause) {
		return new ClauseAndBuilder(this, clause);
	}

	public ClauseBuilder or(String clause) {
		return new ClauseOrBuilder(this, clause);
	}

	@Override
	public String build() {
		return clause;
	}
}
