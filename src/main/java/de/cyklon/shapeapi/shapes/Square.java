package de.cyklon.shapeapi.shapes;

public interface Square extends Rectangle {

    int getSize();

    @Override
    default int getWidth() {
        return getSize();
    }

    @Override
    default int getHeight() {
        return getSize();
    }
}
