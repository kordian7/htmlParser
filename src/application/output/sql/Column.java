package application.output.sql;

public class Column {
	private String fieldName;
	private Object fieldValue;
	
	public Column(String fieldName, Object fieldValue) {
		super();
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Object getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
}
