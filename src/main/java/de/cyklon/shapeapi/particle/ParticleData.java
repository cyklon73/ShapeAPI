package de.cyklon.shapeapi.particle;

import org.bukkit.Particle;

public interface ParticleData {

    Particle getParticle();

    default Object getData() {
        return null;
    };

    default int getCount() {
        return 1;
    };
}
