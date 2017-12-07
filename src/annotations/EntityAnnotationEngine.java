package annotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityAnnotationEngine {

	public static String getTableName(Class<? extends Object> clazz) {

		String entityName = clazz.getSimpleName().toLowerCase();

		if (clazz.isAnnotationPresent(Entity.class) && !("".equals(clazz.getAnnotation(Entity.class).name()))) {
			entityName = clazz.getAnnotation(Entity.class).name();
		}

		return entityName;
	}

	public static List<String> getColumnsNames(Class<? extends Object> clazz) {
		List<String> columnsNames = new ArrayList<>();
		Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
			if (field.isAnnotationPresent(Column.class)) {
				String fieldName = field.getName();
				String annName = field.getAnnotation(Column.class).name();
				if (!"".equals(annName))
					fieldName = annName;
				columnsNames.add(fieldName);
			}
		});

		return columnsNames;
	}
	
	public static Object getColumnValue(String fieldName, Object obj) {

		Class<? extends Object> clazz = obj.getClass();

		Field foundField = Arrays.stream(clazz.getDeclaredFields()).filter(field -> {
			if (field.isAnnotationPresent(Column.class)) {
				String foundFieldName = field.getName();
				String annName = field.getAnnotation(Column.class).name();
				if (!"".equals(annName))
					foundFieldName = annName;
				return foundFieldName.equals(fieldName);
			}
			return false;
		}).findFirst().get();
		
		foundField.setAccessible(true);
		
		try {
			return foundField.get(obj);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
