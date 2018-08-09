package com.alexfu.sqlitequerybuilder.api;

import com.alexfu.sqlitequerybuilder.builder.expressions.ExpressionCaseBuilder;
import com.alexfu.sqlitequerybuilder.utils.Preconditions;

public class SQLiteExpressionBuilder {
  public static ExpressionCaseBuilder caseExp() {
    return new ExpressionCaseBuilder();
  }

  public static ExpressionCaseBuilder caseExp(String expresion) {
    Preconditions.checkArgument(expresion != null, "Expression cannot be null");
    return new ExpressionCaseBuilder(expresion);
  }

}
