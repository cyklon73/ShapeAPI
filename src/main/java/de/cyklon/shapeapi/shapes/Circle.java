package de.cyklon.shapeapi.shapes;

import de.cyklon.shapeapi.shapes.renderer.CircleRenderer;
import de.cyklon.shapeapi.shapes.renderer.ShapeRenderer;

public interface Circle extends Shape {

    double getRadius();


    @Override
    default ShapeRenderer getRenderer() {
        //return getRenderManager().getRenderer(CircleRenderer.class);
        return new CircleRenderer();
    }
}
