package application.output.sql;

import java.util.ArrayList;
import java.util.List;

import annotations.EntityAnnotationEngine;
import application.output.Outputable;

public class SQLOutputGenerator implements Outputable {

	Object object;
	String tableName;
	List<Column> columns = new ArrayList<>();

	@Override
	public void init(Object object) {
		this.object = object;
		tableName = EntityAnnotationEngine.getTableName(object.getClass());

		List<String> columnNames = EntityAnnotationEngine.getColumnsNames(object.getClass());
		columnNames.forEach(fieldName -> {
			Object fieldValue = EntityAnnotationEngine.getColumnValue(fieldName, object);
			columns.add(new Column(fieldName, fieldValue));
		});
	}

	@Override
	public String generateOutput() {
		return (new SQLInsertQuery()).setTableName(tableName).addColumns(columns).toString();
	}

}
