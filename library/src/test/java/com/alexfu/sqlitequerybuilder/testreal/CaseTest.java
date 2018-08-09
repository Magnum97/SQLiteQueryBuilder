package com.alexfu.sqlitequerybuilder.testreal;

import com.alexfu.sqlitequerybuilder.api.SQLiteExpressionBuilder;
import com.alexfu.sqlitequerybuilder.api.SQLiteQueryBuilder;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class CaseTest extends SQLiteTest {

  @Override
  public void before() throws SQLException {
    super.before();

    String createTable = "CREATE TABLE my_table (id INTEGER)";
    statement.execute(createTable);

    for (int i = 1; i <= 10; i++) {
      String sql = "INSERT INTO my_table (id) VALUES (" + i + ")";
      statement.execute(sql);
    }
  }

  @Test
  public void testSimpleCase() throws SQLException {
    // Arrange
    String sql = SQLiteQueryBuilder
        .select(
            SQLiteExpressionBuilder
                .caseExp("SUM(id)")
                .when("55").then("'correct'")
                .elseExp("'error'").end("first").build(),
            SQLiteExpressionBuilder
                .caseExp("SUM(id)")
                .when("56").then("'correct'")
                .elseExp("'error'").end("second").build())
        .from("my_table")
        .build();

    // Assert
    ResultSet set = statement.executeQuery(sql);
    assertThat(set.getString("first")).isEqualTo("correct");
    assertThat(set.getString("second")).isEqualTo("error");
  }

  @Test
  public void testSimpleCase2() throws SQLException {
    // Arrange
    String sql = SQLiteQueryBuilder
        .select(
            SQLiteExpressionBuilder
                .caseExp()
                .when("COUNT(id) > 5").then("COUNT(id)")
                .elseExp("NULL").end("first").build(),
            SQLiteExpressionBuilder
                .caseExp()
                .when("COUNT(id) > 11").then("COUNT(id)")
                .elseExp("NULL").end("second").build())
        .from("my_table")
        .build();

    // Assert
    ResultSet set = statement.executeQuery(sql);
    assertThat(set.getInt("first")).isEqualTo(10);
    assertThat(set.getString("second")).isEqualTo(null);
  }
}