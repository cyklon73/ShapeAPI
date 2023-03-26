package de.cyklon.shapeapi;

import de.cyklon.shapeapi.particle.ParticleController;
import de.cyklon.shapeapi.shapes.Circle;
import de.cyklon.shapeapi.shapes.Line;
import de.cyklon.shapeapi.shapes.Rectangle;
import de.cyklon.shapeapi.shapes.Shape;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class ShapeAPI extends JavaPlugin {

    private static long renderC = 0;
    private static int ID = 0;
    private static final List<Shape> shapes = new ArrayList<>();

    private static BukkitRunnable renderTask;

    private static ShapeAPI INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        getCommand("version").setExecutor(this);

        renderTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (Shape shape : shapes) shape.render();
                renderC++;
            }
        };
        renderTask.runTaskTimerAsynchronously(this, 1, 1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, String label, String[] args) {
        switch (command.getName()) {
            case "version" -> sender.sendMessage(ChatColor.GREEN + "Current Version" + ChatColor.DARK_GRAY + ":" + ChatColor.GRAY + getDescription().getVersion() + "");
            /*case "test" -> {
                if (sender instanceof Player player) {
                    if (args.length==2) {
                        try {
                            createRectangle(player.getLocation(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), point -> () -> Particle.VILLAGER_ANGRY);
                            player.sendMessage(ChatColor.GREEN + "Created Rectangle!");
                        } catch (Exception e) {
                            player.sendMessage(ChatColor.RED + "Error:\n" + e);
                        }
                    } else if (args.length==1) {
                        try {
                            createCircle(player.getLocation(), Integer.parseInt(args[0]), -2, point -> () -> Particle.VILLAGER_ANGRY);
                            player.sendMessage(ChatColor.GREEN + "Created Circle!");
                        } catch (Exception e) {
                            player.sendMessage(ChatColor.RED + "Error:\n" + e);
                        }
                    } else if (args.length==0) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < 10; i++) {
                                    drawCircle(player.getLocation().add(0, i, 0), 2, point -> () -> Particle.VILLAGER_ANGRY);
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException ignored) {}
                                }
                            }
                        }.runTaskAsynchronously(this);
                    }
                }
            }*/
        }
        return false;
    }

    @Nullable
    public Shape getShape(int id) {
        for (Shape shape : shapes) {
            if (shape.getID()==id) return shape;
        }
        return null;
    }

    public boolean destroy(int id) {
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getID()==id) {
                shapes.remove(i);
                return true;
            }
        }
        return false;
    }

    private void destroyAfterRender(Shape shape) {
        long current = renderC;
        new BukkitRunnable() {
            @Override
            public void run() {
                while (current==renderC) {
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException ignored) {}
                }
                shape.destroy();
            }
        }.runTaskAsynchronously(this);
    }

    public void drawCircle(Location location, double radius, int segments, ParticleController controller) {
        destroyAfterRender(createCircle(location, radius, segments, controller));
    }

    public void drawCircle(Location location, double radius, ParticleController controller) {
        destroyAfterRender(createCircle(location, radius, controller));
    }

    public Circle createCircle(Location location, double radius, ParticleController controller) {
        return createCircle(location, radius, 0, controller);
    }

    public Circle createCircle(Location location, double radius, int segments, ParticleController controller) {
        Circle circle = new Circle() {
            @Override
            public double getRadius() {
                return radius;
            }

            @Override
            public int getSegments() {
                return segments;
            }

            @Override
            public int getID() {
                return ID;
            }

            @Override
            public Location getLocation() {
                return location;
            }

            @Override
            public Vector getRotation() {
                return new Vector(0, 0, 0);
            }

            @Override
            public ParticleController getParticleController() {
                return controller;
            }

            @Override
            public void destroy() {
                ShapeAPI.this.destroy(getID());
            }
        };
        ID++;
        shapes.add(circle);
        return circle;
    }

    public void drawRectangle(Location location, int width, int height, ParticleController controller) {
        destroyAfterRender(createRectangle(location, width, height, controller));
    }

    public Rectangle createRectangle(Location location, int width, int height, ParticleController controller) {
        Rectangle rectangle = new Rectangle() {
            @Override
            public int getWidth() {
                return width;
            }

            @Override
            public int getHeight() {
                return height;
            }

            @Override
            public int getID() {
                return ID;
            }

            @Override
            public Location getLocation() {
                return location;
            }

            @Override
            public Vector getRotation() {
                return new Vector(25, 20, -30);
            }

            @Override
            public ParticleController getParticleController() {
                return controller;
            }

            @Override
            public void destroy() {
                ShapeAPI.this.destroy(getID());
            }
        };
        ID++;
        shapes.add(rectangle);
        return rectangle;
    }

    public void drawLine(Location location, Vector secondPos, ParticleController controller) {
        destroyAfterRender(createLine(location, secondPos, controller));
    }

    public Line createLine(Location location, Vector secondPos, ParticleController controller) {
        Line line = new Line() {
            @Override
            public Vector getSecondPos() {
                return secondPos;
            }

            @Override
            public int getID() {
                return ID;
            }

            @Override
            public Location getLocation() {
                return location;
            }

            @Override
            public Vector getRotation() {
                return new Vector(0, 0, 0);
            }

            @Override
            public ParticleController getParticleController() {
                return controller;
            }

            @Override
            public void destroy() {
                ShapeAPI.this.destroy(getID());
            }
        };
        ID++;
        shapes.add(line);
        return line;
    }
    public static ShapeAPI get() {
        if (INSTANCE==null) throw new IllegalStateException("ShapeAPI isn`t loaded!");
        return INSTANCE;
    }
}
