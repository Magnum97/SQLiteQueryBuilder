package com.alexfu.sqlitequerybuilder.builder.expressions;

import com.alexfu.sqlitequerybuilder.builder.SegmentBuilder;
import com.alexfu.sqlitequerybuilder.utils.StringUtils;

public class CaseElseBuilder extends SegmentBuilder {

  private CaseThenBuilder prefix;
  private String expression;

  CaseElseBuilder(CaseThenBuilder prefix, String expression) {
    this.prefix = prefix;
    this.expression = expression;
  }

  public CaseEndBuilder end(String columnAlias) {
    return new CaseEndBuilder(this, columnAlias);
  }

  @Override
  public String build() {
    return StringUtils.join(" ", prefix.build(),
        "ELSE", expression);
  }
}
