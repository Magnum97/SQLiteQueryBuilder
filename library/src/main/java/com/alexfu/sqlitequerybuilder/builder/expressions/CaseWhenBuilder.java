package com.alexfu.sqlitequerybuilder.builder.expressions;

import com.alexfu.sqlitequerybuilder.builder.SegmentBuilder;
import com.alexfu.sqlitequerybuilder.utils.Preconditions;
import com.alexfu.sqlitequerybuilder.utils.StringUtils;

public class CaseWhenBuilder extends SegmentBuilder {

  private SegmentBuilder prefix;
  private String expression;

  CaseWhenBuilder(CaseThenBuilder prefix, String expression) {
    this.prefix = prefix;
    this.expression = expression;
  }

  CaseWhenBuilder(ExpressionCaseBuilder prefix, String expression) {
    this.prefix = prefix;
    this.expression = expression;
  }

  public CaseThenBuilder then(String expression) {
    Preconditions.checkArgument(expression != null, "Expression cannot be null");
    return new CaseThenBuilder(this, expression);
  }

  @Override
  public String build() {
    return StringUtils.join(" ", prefix.build(),
        "WHEN", expression);
  }
}
