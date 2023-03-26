package de.cyklon.shapeapi.shapes.renderer;

import java.util.HashMap;

public class RenderManager {

    private static final HashMap<Integer, ShapeRenderer> renderMap = new HashMap<>();

    private final int id;

    RenderManager(int id) {
        this.id = id;
    }

    public static RenderManager get(int id) {
        return new RenderManager(id);
    }

    public ShapeRenderer getRenderer(Class<? extends ShapeRenderer> clazz) {
        return getRenderer(id, clazz);
    }

    public static ShapeRenderer getRenderer(int id, Class<? extends ShapeRenderer> clazz) {
        if (!renderMap.containsKey(id)) {
            try {
                renderMap.put(id, clazz.getDeclaredConstructor().newInstance());
            } catch (Exception ignored) {}
        }
        return renderMap.get(id);
    }

}
