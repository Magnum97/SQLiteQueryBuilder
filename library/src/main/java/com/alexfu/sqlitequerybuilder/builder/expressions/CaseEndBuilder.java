package com.alexfu.sqlitequerybuilder.builder.expressions;

import com.alexfu.sqlitequerybuilder.builder.SegmentBuilder;
import com.alexfu.sqlitequerybuilder.builder.select.FunctionType;
import com.alexfu.sqlitequerybuilder.builder.select.SelectFromBuilder;
import com.alexfu.sqlitequerybuilder.builder.select.SelectFunctionBuilder;
import com.alexfu.sqlitequerybuilder.utils.Preconditions;
import com.alexfu.sqlitequerybuilder.utils.StringUtils;

public class CaseEndBuilder extends SegmentBuilder {

  private SegmentBuilder prefix;
  private String columnAlias;

  CaseEndBuilder(CaseThenBuilder prefix, String columnAlias) {
    this.prefix = prefix;
    this.columnAlias = columnAlias;
  }

  CaseEndBuilder(CaseElseBuilder prefix, String columnAlias) {
    this.prefix = prefix;
    this.columnAlias = columnAlias;
  }

  @Override
  public String build() {
    return StringUtils.join(" ", prefix.build(), "END", columnAlias);
  }
}
