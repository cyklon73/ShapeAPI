package de.cyklon.shapeapi.shapes.renderer;

import de.cyklon.shapeapi.shapes.Rectangle;
import de.cyklon.shapeapi.shapes.Shape;
import de.cyklon.shapeapi.utils.MathUtil;
import org.bukkit.Location;
import org.bukkit.World;

public class RectangleRenderer extends ShapeRenderer {

    @Override
    protected void initPoints(Shape shape, World world, Location location) {
        if (shape instanceof Rectangle rectangle) {
            double[][] rotationMatrix = MathUtil.createRotationMatrix(rectangle.getRotation());

            for (int i = 0; i < rectangle.getHeight(); i++) {
                double[] point = new double[]{location.getX(), location.getY() + i, location.getZ()};
                point = MathUtil.multiplyMatrixAndPoint(rotationMatrix, point);
                points.add(loc(vec(point), world));

                point = new double[]{location.getX(), location.getY() + i, location.getZ() + rectangle.getWidth()};
                point = MathUtil.multiplyMatrixAndPoint(rotationMatrix, point);
                points.add(loc(vec(point), world));
            }
            for (int i = 0; i < rectangle.getWidth(); i++) {
                double[] point = new double[]{location.getX(), location.getY(), location.getZ() + i};
                point = MathUtil.multiplyMatrixAndPoint(rotationMatrix, point);
                points.add(loc(vec(point), world));

                point = new double[]{location.getX(), location.getY() + rectangle.getHeight(), location.getZ() + i};
                point = MathUtil.multiplyMatrixAndPoint(rotationMatrix, point);
                points.add(loc(vec(point), world));
            }

            /*for (int i = 0; i < rectangle.getHeight(); i++) {
                points.add(new Location(world, location.getX(), location.getY()+i, location.getZ()));
                points.add(new Location(world, location.getX(), location.getY()+i, location.getZ() + rectangle.getWidth()));
            }
            for (int i = 0; i < rectangle.getWidth(); i++) {
                points.add(new Location(world, location.getX(), location.getY(), location.getZ()+i));
                points.add(new Location(world, location.getX(), location.getY()+rectangle.getHeight(), location.getZ() + i));
            }*/
        }
    }
}
