package de.cyklon.shapeapi;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ShapeSetting {

    private final HashMap<String, Object> settings;

    public ShapeSetting() {
        this.settings = new HashMap<>();
    }

    public boolean contains(String key) {
        return settings.containsKey(key);
    }

    public String keyOf(Object obj) {
        AtomicReference<String> key = null;
        settings.forEach((k, v) -> {
            if (v.equals(obj)) key.set(k);
        });
        return key.get();
    }


    public void set(String key, Object obj) {
        settings.put(key, obj);
    }

    public Object get(String key) {
        return settings.get(key);
    }

    public String getString(String key) {
        String value = (String) get(key);
        return value==null ? "" : value;
    }

    public Integer getInt(String key) {
        Integer value = (Integer) get(key);
        return value==null ? 0 : value;
    }

    public Double getDouble(String key) {
        Double value = (Double) get(key);
        return value==null ? 0 : value;
    }

    public Float getFloat(String key) {
        Float value = (Float) get(key);
        return value==null ? 0 : value;
    }
    public Character getChar(String key) {
        Character value = (Character) get(key);
        return value==null ? ' ' : value;
    }
    public Short getShort(String key) {
        Short value = (Short) get(key);
        return value==null ? 0 : value;
    }

    public Long getLong(String key) {
        Long value = (Long) get(key);
        return value==null ? 0 : value;
    }

    public Byte getByte(String key) {
        Byte value = (Byte) get(key);
        return value==null ? 0 : value;
    }

    public Boolean getBoolean(String key) {
        Boolean value = (Boolean) get(key);
        return value != null && value;
    }

}
