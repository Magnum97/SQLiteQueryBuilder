package com.alexfu.sqlitequerybuilder;

import com.alexfu.sqlitequerybuilder.api.SQLiteQueryBuilder;

import org.junit.Test;

import static com.alexfu.sqlitequerybuilder.api.SQLiteClauseBuilder.clause;
import static org.assertj.core.api.Assertions.assertThat;

public class WhereTest {

	@Test
	public void selectAndTest() {
		String query = SQLiteQueryBuilder
				.select("*")
				.from("mytable")
				.where(clause("id = 1").and("color = 'red'"))
				.build();

		assertThat(query).isEqualTo("SELECT * FROM mytable WHERE (id = 1) AND (color = 'red')");
	}

	@Test
	public void selectAndAndTest() {
		String query = SQLiteQueryBuilder
				.select("*")
				.from("mytable")
				.where(clause("id = 1").and("color = 'red'").and("color = 'blue'"))
				.build();

		assertThat(query).isEqualTo("SELECT * FROM mytable WHERE ((id = 1) AND (color = 'red')) AND (color = 'blue')");
	}

	@Test
	public void selectOrTest() {
		String query = SQLiteQueryBuilder
				.select("*")
				.from("mytable")
				.where(clause("id = 1").or("color = 'red'").or("color = 'blue'"))
				.build();

		assertThat(query).isEqualTo("SELECT * FROM mytable WHERE ((id = 1) OR (color = 'red')) OR (color = 'blue')");
	}

	@Test
	public void selectAndOrTest() {
		String query = SQLiteQueryBuilder
				.select("*")
				.from("mytable")
				.where(clause(clause("id = 1").and("color = 'red'")).or("color = 'blue'"))
				.build();

		assertThat(query).isEqualTo("SELECT * FROM mytable WHERE ((id = 1) AND (color = 'red')) OR (color = 'blue')");
	}
}