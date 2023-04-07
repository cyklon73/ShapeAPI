package de.cyklon.shapeapi.shapes.renderer;

import de.cyklon.shapeapi.shapes.Line;
import de.cyklon.shapeapi.shapes.Shape;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class LineRenderer extends ShapeRenderer {
    @Override
    protected void initPoints(Shape shape, World world, Location location) {
        if (shape instanceof Line line) points.addAll(loc(getLinePoints(location.toVector(), line.getSecondPos()), world));
    }

    public static ArrayList<Vector> getLinePoints(Vector pos1, Vector pos2) {
        ArrayList<Vector> points = new ArrayList<>();
        double distance = 0.8;
        Vector direction = pos2.clone().subtract(pos1).normalize().multiply(distance);
        double length = pos1.distance(pos2);
        for (double d = 0; d <= length; d += distance) points.add(pos1.clone().add(direction.clone().multiply(d)));
        return points;
    }
}
