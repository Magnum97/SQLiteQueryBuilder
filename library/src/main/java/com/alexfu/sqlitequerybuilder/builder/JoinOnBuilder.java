package com.alexfu.sqlitequerybuilder.builder;

import com.alexfu.sqlitequerybuilder.builder.select.SelectJoinBuilder;
import com.alexfu.sqlitequerybuilder.builder.select.SelectWhereBuilder;
import com.alexfu.sqlitequerybuilder.builder.where.ClauseBuilder;
import com.alexfu.sqlitequerybuilder.utils.Preconditions;
import com.alexfu.sqlitequerybuilder.utils.StringUtils;

public class JoinOnBuilder extends SegmentBuilder {

  private com.alexfu.sqlitequerybuilder.builder.select.SelectJoinBuilder prefix;
  private String condition;

  public JoinOnBuilder(com.alexfu.sqlitequerybuilder.builder.select.SelectJoinBuilder prefix, String condition) {
    this.prefix = prefix;
    this.condition = condition;
  }

  public com.alexfu.sqlitequerybuilder.builder.select.SelectWhereBuilder where(ClauseBuilder condition) {
    Preconditions.checkArgument(condition != null, "Condition cannot be null");
    return new SelectWhereBuilder(this, condition);
  }

  public com.alexfu.sqlitequerybuilder.builder.select.SelectJoinBuilder join(String table) {
    Preconditions.checkArgument(table != null, "Table name cannot be null");
    return new SelectJoinBuilder(this, table, JoinType.JOIN);
  }

  @Override
  public String build() {
    return StringUtils.join(" ", prefix.build(), "ON", condition);
  }
}
