package com.alexfu.sqlitequerybuilder.builder.select;

import com.alexfu.sqlitequerybuilder.utils.StringUtils;

public class SelectFieldBuilder extends SelectBuilder {

  private String[] fields;

  public SelectFieldBuilder(String... fields) {
    this.fields = fields;
  }

  @Override
  public String build() {
    if(fields.length == 0) {
      return "SELECT";
    } else {
      return StringUtils.join(" ", "SELECT", StringUtils.join(",", fields));
    }
  }
}
