package application.output.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SQLInsertQuery {
	private String tableName;
	private List<Column> columns = new ArrayList<>();

	public SQLInsertQuery setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	public SQLInsertQuery addColumn(Column column) {
		columns.add(column);
		return this;
	}

	public SQLInsertQuery addColumns(Collection<Column> columns) {
		this.columns.addAll(columns);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		strBld.append("INSERT INTO ");
		strBld.append(tableName);
		strBld.append("(");
		appendColumnNames(strBld);
		strBld.append(") VALUES (");
		appendColumnValues(strBld);
		strBld.append(");");
		return strBld.toString();

	}

	private void appendColumnNames(StringBuilder strBld) {
		int i = 0;
		for (Column column : columns) {
			strBld.append(column.getFieldName());
			if (++i != columns.size())
				strBld.append(", ");
		}
	}

	private void appendColumnValues(StringBuilder strBld) {
		int i = 0;
		for (Column column : columns) {
			if (null != column.getFieldValue()) {
				boolean isStr = column.getFieldValue().getClass().equals(String.class);
				if (isStr)
					strBld.append('\'');
				strBld.append(column.getFieldValue());
				if (isStr)
					strBld.append('\'');
			} else {
				strBld.append("NULL");
			}
			if (++i != columns.size())
				strBld.append(", ");
		}

	}
}
