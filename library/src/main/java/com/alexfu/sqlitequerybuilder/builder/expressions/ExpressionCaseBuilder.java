package com.alexfu.sqlitequerybuilder.builder.expressions;

import com.alexfu.sqlitequerybuilder.builder.SegmentBuilder;
import com.alexfu.sqlitequerybuilder.builder.select.SelectBuilder;
import com.alexfu.sqlitequerybuilder.utils.Preconditions;
import com.alexfu.sqlitequerybuilder.utils.StringUtils;

public class ExpressionCaseBuilder extends SegmentBuilder {

  private String expression;

  public ExpressionCaseBuilder() {
    this(null);
  }

  public ExpressionCaseBuilder(String expression) {
    this.expression = expression;
  }

  public CaseWhenBuilder when(String expression) {
    Preconditions.checkArgument(expression != null, "Expression cannot be null");
    return new CaseWhenBuilder(this, expression);
  }

  @Override
  public String build() {
    return expression != null? StringUtils.join(" ","CASE", expression):"CASE";
  }
}
