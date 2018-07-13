package com.alexfu.sqlitequerybuilder.api;

import com.alexfu.sqlitequerybuilder.builder.*;
import com.alexfu.sqlitequerybuilder.builder.delete.DeleteBuilder;
import com.alexfu.sqlitequerybuilder.builder.insert.InsertBuilder;
import com.alexfu.sqlitequerybuilder.builder.select.SelectTypeBuilder;

public class SQLiteQueryBuilder {

  public static InsertBuilder insert() {
    return new InsertBuilder();
  }

  public static com.alexfu.sqlitequerybuilder.builder.select.SelectBuilder select(String... fields) {
    return new com.alexfu.sqlitequerybuilder.builder.select.SelectFieldBuilder(fields);
  }

  public static com.alexfu.sqlitequerybuilder.builder.select.SelectBuilder select(SelectType type) {
    return new SelectTypeBuilder(type);
  }

  public static CreateTableSegmentBuilder create() {
    return new CreateTableSegmentBuilder();
  }

  public static DropSegmentBuilder drop() {
    return new DropSegmentBuilder();
  }

  public static DeleteBuilder delete() {
    return new DeleteBuilder();
  }
}
