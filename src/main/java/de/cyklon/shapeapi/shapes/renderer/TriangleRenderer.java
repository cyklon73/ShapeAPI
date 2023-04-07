package de.cyklon.shapeapi.shapes.renderer;

import de.cyklon.shapeapi.shapes.Shape;
import de.cyklon.shapeapi.shapes.Triangle;
import org.bukkit.Location;
import org.bukkit.World;

public class TriangleRenderer extends ShapeRenderer {
    @Override
    protected void initPoints(Shape shape, World world, Location location) {
        if (shape instanceof Triangle triangle) {
            double sideLength = triangle.getSideLenght();
            double height = Math.sqrt(3) / 2 * sideLength;
            Location loc1 = location.clone();
            Location loc2 = location.clone().add(sideLength, 0, 0);
            Location loc3 = location.clone().add(sideLength/2, height, 0);
            points.addAll(loc(LineRenderer.getLinePoints(loc1.toVector(), loc2.toVector()), world));
            points.addAll(loc(LineRenderer.getLinePoints(loc2.toVector(), loc3.toVector()), world));
            points.addAll(loc(LineRenderer.getLinePoints(loc3.toVector(), loc1.toVector()), world));
        }
    }
}
