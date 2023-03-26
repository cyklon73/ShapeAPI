package de.cyklon.shapeapi.shapes.renderer;

import de.cyklon.shapeapi.particle.ParticleController;
import de.cyklon.shapeapi.particle.ParticleData;
import de.cyklon.shapeapi.shapes.Shape;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ShapeRenderer {

    protected List<Location> points = null;
    protected ParticleController particleController = null;


    protected abstract void initPoints(Shape shape, World world, Location location);

    protected void doRender(Shape shape, World world) {
        int currPoint = 0;
        for (Location point : points) {
            ParticleData data = particleController.getData(currPoint);
            world.spawnParticle(data.getParticle(), point, data.getCount(), 0, 0, 0, data.getData());
            currPoint++;
        }
    }

    public void render(Shape shape) {
        Location loc = shape.getLocation();
        World world = loc.getWorld();
        if (world==null) return;
        if (points==null) {
            points = new ArrayList<>();
            initPoints(shape, world, loc);
        }
        if (particleController==null) particleController = shape.getParticleController();
        doRender(shape, world);
    }


    public static class EmptyRenderer extends ShapeRenderer {

        @Override
        protected void initPoints(Shape shape, World world, Location location) {

        }
    }

    protected Vector vec(double[] point) {
        return new Vector(point[0], point[1], point[2]);
    }

    protected Location loc(Vector vector, World world) {
        return new Location(world, vector.getX(), vector.getY(), vector.getZ());
    }

    protected Location[] loc(Vector[] vectors, World world) {
        Location[] locations = new Location[vectors.length];
        for (int i = 0; i < locations.length; i++) {
            locations[i] = loc(vectors[i], world);
        }
        return locations;
    }

    protected Collection<Location> loc(Collection<Vector> vectors, World world) {
        return List.of(loc(vectors.toArray(new Vector[vectors.size()]), world));
    }

}
