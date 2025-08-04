package api.log.formater;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * 默认的参数格式化器
 * @author: chenenwei
 * @date: 2025/7/29
 */
public class DefaultParamFormatter implements ParamFormatter{

    @Override
    public Object format(Parameter[] parameters, Object[] parameterValues) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            String formatValues = formatValue(parameterValues[i]);
            sb.append("\"").append(parameters[i].getName()).append("\"").append(":").append(formatValues).append(" ");
        }
        return sb.toString();
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "null";
        }

        Class<?> clazz = value.getClass();

        if (isPrimitiveOrWrapper(clazz)) {
            if (value instanceof String && "".equals(value.toString().trim())) {
                return "\"\"";
            }
            return value.toString();
        }

        if (clazz.isArray()) {
            return formatArray(value);
        }

        if (value instanceof Collection) {
            return formatCollection((Collection<?>) value);
        }

        if (value instanceof Map) {
            return formatMap((Map<?, ?>) value);
        }

        return formatObject(value);
    }

    // 判断是否为基本类型或包装类型
    private boolean isPrimitiveOrWrapper(Class<?> clazz) {
        return clazz.isPrimitive() ||
                clazz == Integer.class ||
                clazz == Long.class ||
                clazz == Double.class ||
                clazz == Float.class ||
                clazz == Boolean.class ||
                clazz == Character.class ||
                clazz == Byte.class ||
                clazz == Short.class ||
                clazz == String.class;
    }

    // 格式化数组
    private String formatArray(Object array) {
        StringBuilder sb = new StringBuilder("[");
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            sb.append(formatValue(Array.get(array, i)));
            if (i < length - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }

    // 格式化集合
    private String formatCollection(Collection<?> collection) {
        StringBuilder sb = new StringBuilder("[");
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(formatValue(it.next()));
            if (it.hasNext()) sb.append(", ");
        }
        return sb.append("]").toString();
    }

    // 格式化Map
    private String formatMap(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder("{");
        Iterator<? extends Map.Entry<?, ?>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<?, ?> entry = it.next();
            sb.append(formatValue(entry.getKey()))
                    .append("=")
                    .append(formatValue(entry.getValue()));
            if (it.hasNext()) sb.append(", ");
        }
        return sb.append("}").toString();
    }

    // 格式化普通对象（递归遍历字段）
    private String formatObject(Object obj) {
        try {
            StringBuilder sb = new StringBuilder("{");
            List<Field> fields = getAllFields(obj.getClass());
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                field.setAccessible(true);
                sb.append("\"")
                    .append(field.getName())
                    .append("\"")
                    .append(":")
                    .append(formatValue(field.get(obj)));
                if (i < fields.size() - 1) sb.append(", ");
            }
            return sb.append("}").toString();
        } catch (IllegalAccessException e) {
            return "[Error accessing fields]";
        }
    }

    // 递归获取类及其父类的所有字段
    private List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}
