package de.cyklon.shapeapi.shapes.renderer;

import de.cyklon.shapeapi.shapes.Line;
import de.cyklon.shapeapi.shapes.Shape;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class LineRenderer extends ShapeRenderer {
    @Override
    protected void initPoints(Shape shape, World world, Location location) {
        if (shape instanceof Line line) {
            double distance = 0.8;
            Vector direction = line.getSecondPos().clone().subtract(location.toVector()).normalize().multiply(distance);
            double length = location.toVector().distance(line.getSecondPos());
            for (double d = 0; d <= length; d += distance) points.add(loc(location.toVector().clone().add(direction.clone().multiply(d)), world));
        }
    }
}
