package de.cyklon.shapeapi.shapes;

import de.cyklon.shapeapi.shapes.renderer.LineRenderer;
import de.cyklon.shapeapi.shapes.renderer.ShapeRenderer;
import org.bukkit.util.Vector;

public interface Line extends Shape {

    Vector getSecondPos();

    default double getLength() {
        return getLocation().toVector().distance(getSecondPos());
    }

    @Override
    default ShapeRenderer getRenderer() {
        //return getRenderManager().getRenderer(LineRenderer.class);
        return new LineRenderer();
    }
}
