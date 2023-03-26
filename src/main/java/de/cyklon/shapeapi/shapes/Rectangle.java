package de.cyklon.shapeapi.shapes;

import de.cyklon.shapeapi.shapes.renderer.RectangleRenderer;
import de.cyklon.shapeapi.shapes.renderer.ShapeRenderer;

public interface Rectangle extends Shape {

    int getWidth();

    int getHeight();

    @Override
    default ShapeRenderer getRenderer() {
        //return getRenderManager().getRenderer(RectangleRenderer.class);
        return new RectangleRenderer();
    }
}
