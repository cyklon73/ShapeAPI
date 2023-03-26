package de.cyklon.shapeapi.shapes.renderer;

import de.cyklon.shapeapi.shapes.Circle;
import de.cyklon.shapeapi.shapes.Shape;
import org.bukkit.Location;
import org.bukkit.World;

public class CircleRenderer extends ShapeRenderer {

    @Override
    protected void initPoints(Shape shape, World world, Location location) {
        if (shape instanceof Circle circle) {
            int segments = circle.getSegments();
            double radius = circle.getRadius();
            if (segments==0) segments = calculateSegments(radius, 0.5);
            if (segments<0) segments = calculateSegments(radius, segments*-1);
            for (int i = 0; i < segments; i++) {
                double angle = 2 * Math.PI * i / segments;
                double x = location.getX() + radius * Math.cos(angle);
                double z = location.getZ() + radius * Math.sin(angle);
                points.add(new Location(world, x, location.getY(), z));
            }
        }
    }

    private int calculateSegments(double radius, double distance) {
        return (int) Math.ceil((Math.PI * (radius*2)) / distance);
    }
}
