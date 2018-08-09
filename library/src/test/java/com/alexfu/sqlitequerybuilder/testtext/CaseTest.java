package com.alexfu.sqlitequerybuilder.testtext;

import com.alexfu.sqlitequerybuilder.api.SQLiteExpressionBuilder;
import com.alexfu.sqlitequerybuilder.api.SQLiteQueryBuilder;

import org.junit.Test;

import static com.alexfu.sqlitequerybuilder.api.SQLiteClauseBuilder.clause;
import static org.assertj.core.api.Assertions.assertThat;

public class CaseTest {
  @Test
  public void caseSimpleTest() {
    String query = SQLiteQueryBuilder
        .select(
            SQLiteExpressionBuilder
                .caseExp("col")
                .when("'value'").then("'Normal'")
                .elseExp("'Extra'").end("column").build())
        .from("mytable")
        .where(clause("id = 1"))
        .build();

    assertThat(query).isEqualTo("SELECT CASE col WHEN 'value' THEN 'Normal' ELSE 'Extra' END column FROM mytable WHERE id = 1");
  }

  @Test
  public void caseSimple2Test() {
    String query = SQLiteQueryBuilder
        .select(
            SQLiteExpressionBuilder
                .caseExp()
                .when("COUNT(col) > 10").then("SUM(col)")
                .elseExp("NULL").end("column").build())
        .from("mytable")
        .where(clause("id = 1"))
        .build();

    assertThat(query).isEqualTo("SELECT CASE WHEN COUNT(col) > 10 THEN SUM(col) ELSE NULL END column FROM mytable WHERE id = 1");
  }
}
