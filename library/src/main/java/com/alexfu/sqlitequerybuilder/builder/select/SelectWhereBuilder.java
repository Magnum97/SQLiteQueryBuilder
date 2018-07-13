package com.alexfu.sqlitequerybuilder.builder.select;

import com.alexfu.sqlitequerybuilder.api.Builder;
import com.alexfu.sqlitequerybuilder.builder.SegmentBuilder;
import com.alexfu.sqlitequerybuilder.builder.where.ClauseBuilder;

import static com.alexfu.sqlitequerybuilder.utils.StringUtils.join;

public class SelectWhereBuilder extends SegmentBuilder {

  private Builder prefix;
  private ClauseBuilder condition;

  public SelectWhereBuilder(Builder prefix, ClauseBuilder condition) {
    this.condition = condition;
    this.prefix = prefix;
  }

  public SelectLimitBuilder limit(int limit) {
    return new SelectLimitBuilder(this, limit);
  }

  public SelectOrderByBuilder orderBy(String column) {
    return new SelectOrderByBuilder(this, column);
  }

  @Override
  public String build() {
    return join(" ", prefix.build(), "WHERE", condition);
  }
}
