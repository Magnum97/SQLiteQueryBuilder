package com.alexfu.sqlitequerybuilder.builder;

import com.alexfu.sqlitequerybuilder.api.Builder;
import com.alexfu.sqlitequerybuilder.utils.StringUtils;

public class SelectAndBuilder extends SegmentBuilder {

  private Builder prefix;
  private String condition;

  public SelectAndBuilder(Builder prefix, String condition) {
    this.prefix = prefix;
    this.condition = condition;
  }

  public SelectAndBuilder and(String condition) {
    return new SelectAndBuilder(this, condition);
  }

  public SelectLimitBuilder limit(int limit) {
    return new SelectLimitBuilder(this, limit);
  }

  public SelectOrderByBuilder orderBy(String column) {
    return new SelectOrderByBuilder(this, column);
  }

  @Override
  public String build() {
    return StringUtils.join(" ", prefix.build(), "AND", condition);
  }
}
