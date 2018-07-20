package com.alexfu.sqlitequerybuilder.testreal;

import static com.alexfu.sqlitequerybuilder.testreal.utils.ConnectionUtils.columns;
import static com.alexfu.sqlitequerybuilder.testreal.utils.ConnectionUtils.tables;
import static org.assertj.core.api.Assertions.assertThat;

import com.alexfu.sqlitequerybuilder.api.ForeignKeyConstraint;

import org.junit.Test;

import com.alexfu.sqlitequerybuilder.api.Column;
import com.alexfu.sqlitequerybuilder.api.ColumnConstraint;
import com.alexfu.sqlitequerybuilder.api.ColumnType;
import com.alexfu.sqlitequerybuilder.api.SQLiteQueryBuilder;

import java.sql.SQLException;

public final class CreateTableTest extends SQLiteTest {

  @Test
  public final void testCreateTableWithOneColumn() throws SQLException {
    // Arrange
    Column column = new Column("column1", ColumnType.INTEGER, ColumnConstraint.PRIMARY_KEY);

    // Act
    String sql = SQLiteQueryBuilder
      .create()
      .table("myTable")
      .column(column)
      .toString();

    statement.execute(sql);

    // Assert
    assertThat(tables(connection)).containsOnly("myTable");
    assertThat(columns(connection, "myTable")).containsOnly("column1");
  }

  @Test
  public final void testCreateTableWithMultipleColumns() throws SQLException {
    // Arrange
    Column column1 = new Column("column1", ColumnType.INTEGER, ColumnConstraint.PRIMARY_KEY);
    Column column2 = new Column("column2", ColumnType.TEXT);
    Column column3 = new Column("column3", ColumnType.TEXT, ColumnConstraint.NOT_NULL);

    // Act
    String sql = SQLiteQueryBuilder
      .create()
      .table("myTable")
      .column(column1)
      .column(column2)
      .column(column3)
      .toString();

    statement.execute(sql);

    // Assert
    assertThat(tables(connection)).containsOnly("myTable");
    assertThat(columns(connection, "myTable")).containsOnly( "column1", "column2", "column3");
  }

  @Test
  public final void testCreateTableWithDefaultColumnValue() {
    // Arrange
    Column column = new Column("column1", ColumnType.INTEGER, ColumnConstraint.PRIMARY_KEY, "0");

    // Act
    String query = SQLiteQueryBuilder
      .create()
      .table("myTable")
      .column(column)
      .toString();

    // Assert
    assertThat(query).isEqualTo("CREATE TABLE myTable(column1 INTEGER PRIMARY KEY DEFAULT 0)");
  }

  @Test(expected = IllegalArgumentException.class)
  public final void passNullColumnShouldThrowException() {
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

  @Test
  public final void createTempTable() {
    Column column = new Column("column1", ColumnType.INTEGER, ColumnConstraint.PRIMARY_KEY);

    String query = SQLiteQueryBuilder
      .create()
      .temporary()
      .table("myTable")
      .column(column)
      .build();

    assertThat(query).isEqualTo("CREATE TEMPORARY TABLE myTable(column1 INTEGER PRIMARY KEY)");
  }

  @Test
  public final void createTempTableIfNotExists() {
    Column column = new Column("column1", ColumnType.INTEGER, ColumnConstraint.PRIMARY_KEY);

    String query = SQLiteQueryBuilder
      .create()
      .temporary()
      .table("myTable")
      .ifNotExists()
      .column(column)
      .build();

    assertThat(query).isEqualTo("CREATE TEMPORARY TABLE IF NOT EXISTS myTable(column1 INTEGER "
      + "PRIMARY KEY)");
  }

  @Test(expected = IllegalArgumentException.class)
  public final void emptyTableName() {
    Column column = new Column("column1", ColumnType.INTEGER, ColumnConstraint.PRIMARY_KEY);

    SQLiteQueryBuilder
      .create()
      .table("")
      .column(column)
      .build();
  }

  @Test
  public final void createTablePrimaryKeyAutoIncrement() {
    Column column = new Column("column1", ColumnType.INTEGER,
      ColumnConstraint.PRIMARY_KEY_AUTO_INCREMENT);

    String query = SQLiteQueryBuilder
      .create()
      .table("myTable")
      .column(column)
      .build();

    assertThat(query).isEqualTo("CREATE TABLE myTable(column1 INTEGER PRIMARY KEY AUTOINCREMENT)");
  }

  @Test
  public final void createTableWithSingleForeignKey() {
    // Arrange
    Column column1 = new Column("column1", ColumnType.INTEGER, ColumnConstraint.PRIMARY_KEY);
    Column column2 = new Column("column2", ColumnType.INTEGER);

    ForeignKeyConstraint constraint =
      new ForeignKeyConstraint("column2", "parentTable", "parentKey");

    // Act
    String query = SQLiteQueryBuilder
      .create()
      .table("myTable")
      .column(column1)
      .column(column2)
      .foreignKey(constraint)
      .toString();

    // Assert
    assertThat(query).isEqualTo("CREATE TABLE myTable(column1 INTEGER PRIMARY KEY," +
      "column2 INTEGER)" + " FOREIGN KEY(column2) REFERENCES parentTable(parentKey)");
  }

  @Test
  public final void createTableWithMultipleForeignKeys() {
    // Arrange
    Column column1 = new Column("column1", ColumnType.INTEGER, ColumnConstraint.PRIMARY_KEY);
    Column column2 = new Column("column2", ColumnType.INTEGER);
    Column column3 = new Column("column3", ColumnType.TEXT);

    ForeignKeyConstraint fk1 = new ForeignKeyConstraint("column2", "parentTable1", "parentKey1");
    ForeignKeyConstraint fk2 = new ForeignKeyConstraint("column3", "parentTable2", "parentKey2");

    // Act
    String query = SQLiteQueryBuilder
      .create()
      .table("myTable")
      .column(column1)
      .column(column2)
      .column(column3)
      .foreignKey(fk1)
      .foreignKey(fk2)
      .build();

    // Assert
    assertThat(query).isEqualTo("CREATE TABLE myTable(column1 INTEGER PRIMARY KEY," +
      "column2 INTEGER,column3 TEXT) " +
      "FOREIGN KEY(column2) REFERENCES parentTable1(parentKey1)," +
      "FOREIGN KEY(column3) REFERENCES parentTable2(parentKey2)");
  }
}
