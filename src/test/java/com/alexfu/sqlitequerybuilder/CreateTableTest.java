package com.alexfu.sqlitequerybuilder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.alexfu.sqlitequerybuilder.api.Column;
import com.alexfu.sqlitequerybuilder.api.ColumnConstraint;
import com.alexfu.sqlitequerybuilder.api.ColumnType;
import com.alexfu.sqlitequerybuilder.api.SQLiteQueryBuilder;

public class CreateTableTest {

  @Test
  public final void testCreateTableWithOneColumn() {
    // Arrange
    Column column = new Column("column1", ColumnType.INTEGER, ColumnConstraint.PRIMARY_KEY);

    // Act
    String query = SQLiteQueryBuilder
        .create()
        .table("myTable")
        .column(column)
        .toString();

    // Assert
    assertThat(query).isEqualTo("CREATE TABLE myTable(column1 INTEGER PRIMARY KEY)");
  }

  @Test
  public final void testCreateTableWithMultipleColumns() {
    // Arrange
    Column column1 = new Column("column1", ColumnType.INTEGER, ColumnConstraint.PRIMARY_KEY);
    Column column2 = new Column("column2", ColumnType.TEXT);
    Column column3 = new Column("column3", ColumnType.TEXT, ColumnConstraint.NOT_NULL);

    // Act
    String query = SQLiteQueryBuilder
        .create()
        .table("myTable")
        .column(column1)
        .column(column2)
        .column(column3)
        .toString();

    // Assert
    assertThat(query).isEqualTo("CREATE TABLE myTable(column1 INTEGER PRIMARY KEY,column2 TEXT,column3 TEXT NOT NULL)");
  }

  @Test(expected = IllegalArgumentException.class)
  public final void passNullColumnShouldThrowExeption() {
    // Act
    SQLiteQueryBuilder
        .create()
        .table("myTable")
        .column(null)
        .toString();
  }

  @Test
  public final void createTableIfNotExists() {
  	// Arrange
  	Column column = new Column("column1", ColumnType.INTEGER, ColumnConstraint.PRIMARY_KEY);
  	
  	// Act
  	String query = SQLiteQueryBuilder
        .create()
        .table("myTable")
        .ifNotExists()
        .column(column)
        .toString();
  	
  	// Assert
  	assertThat(query).isEqualTo("CREATE TABLE IF NOT EXISTS myTable(column1 INTEGER PRIMARY KEY)");
  }
}