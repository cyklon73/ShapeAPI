package de.cyklon.shapeapi.shapes;

import de.cyklon.shapeapi.shapes.renderer.ShapeRenderer;
import de.cyklon.shapeapi.shapes.renderer.TriangleRenderer;

public interface Triangle extends Shape {

    double getSideLenght();

    @Override
    default ShapeRenderer getRenderer() {
        return new TriangleRenderer();
    }
}
