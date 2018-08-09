package com.alexfu.sqlitequerybuilder.builder.select;

import com.alexfu.sqlitequerybuilder.builder.SegmentBuilder;
import com.alexfu.sqlitequerybuilder.builder.expressions.ExpressionCaseBuilder;
import com.alexfu.sqlitequerybuilder.utils.Preconditions;

public abstract class SelectBuilder extends SegmentBuilder {
  public SelectFromBuilder from(String table) {
    Preconditions.checkArgument(table != null, "Table cannot be null");
    return new SelectFromBuilder(this, table);
  }

  public SelectFunctionBuilder function(FunctionType function, String ...args) {
    Preconditions.checkArgument(args != null, "Function args cannot be null");
    return new SelectFunctionBuilder(this, function, args);
  }
}
