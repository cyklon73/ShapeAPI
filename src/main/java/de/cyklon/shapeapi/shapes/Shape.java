package de.cyklon.shapeapi.shapes;


import de.cyklon.shapeapi.particle.ParticleController;
import de.cyklon.shapeapi.shapes.renderer.RenderManager;
import de.cyklon.shapeapi.shapes.renderer.ShapeRenderer;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public interface Shape {

    int getID();

    Location getLocation();

    /**
     * rotation on x, y and z axis must between -180 and 180
     */
    Vector getRotation();

    default ShapeRenderer getRenderer() {
        //return getRenderManager().getRenderer(ShapeRenderer.EmptyRenderer.class);
        return new ShapeRenderer.EmptyRenderer();
    }

    default void render() {
        getRenderer().render(this);
    }

    ParticleController getParticleController();

    void destroy();

    /**
     * segments meaning:
     * segments>0 -> uses the specified number of segments.
     * segments==0 -> calculates segment amount with 0.5 blocks distance between particles.
     * segments<0 -> calculates the number of segments with the given number as distance (inverted).
     * example:
     *   @Override
     *   public int getSegments() {
     *       return -1.3;
     *   }
     *  the number of segments is calculated with 1.3 blocks spacing between each particle
     */
    default int getSegments() {
        return 0;
    }

    default RenderManager getRenderManager() {
        return RenderManager.get(getID());
    }
}
