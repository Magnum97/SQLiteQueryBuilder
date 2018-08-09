package com.alexfu.sqlitequerybuilder.builder.expressions;

import com.alexfu.sqlitequerybuilder.builder.SegmentBuilder;
import com.alexfu.sqlitequerybuilder.utils.Preconditions;
import com.alexfu.sqlitequerybuilder.utils.StringUtils;

public class CaseThenBuilder extends SegmentBuilder {

  private CaseWhenBuilder prefix;
  private String expression;

  CaseThenBuilder(CaseWhenBuilder prefix, String expression) {
    this.prefix = prefix;
    this.expression = expression;
  }

  public CaseWhenBuilder when(String expression) {
    Preconditions.checkArgument(expression != null, "Expression cannot be null");
    return new CaseWhenBuilder(this, expression);
  }

  public CaseElseBuilder elseExp(String expression) {
    Preconditions.checkArgument(expression != null, "Expression cannot be null");
    return new CaseElseBuilder(this, expression);
  }

  public CaseEndBuilder end(String columnAlias) {
    return new CaseEndBuilder(this, columnAlias);
  }

  @Override
  public String build() {
    return StringUtils.join(" ", prefix.build(),
        "THEN", expression);
  }
}
