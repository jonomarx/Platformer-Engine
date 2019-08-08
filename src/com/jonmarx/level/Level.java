package com.jonmarx.level;

import com.jonmarx.level.entities.Entity;
import com.jonmarx.level.tiles.Tile;
import com.jonmarx.level.tiles.VoidTile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class Level {
    Tile[][] tiles;
    LinkedList<Entity> entities;
    HashMap<String, Double> prop;
    
    private Level(Tile[][] tiles, Entity[] entities, HashMap<String, Double> properties) {
        this.tiles = tiles;
        this.entities = new LinkedList<Entity>(Arrays.asList(entities));
        prop = properties;
    }
    
    public void addEntity(Entity e) {
        entities.add(e);
    }
    
    public void removeEntity(Entity e) {
        entities.remove(e);
    }
    
    public Tile[][] getTileSet() {
        return tiles;
    }
    
    public Entity[] getEntities() {
        return entities.toArray(new Entity[0]);
    }
    
    public void setProperty(String name, double value) {
        prop.put(name, value);
    }
    
    public double getProperty(String name) {
        return prop.get(name);
    }
    
    public static Level getLevel(URL url) throws IOException  {
        Scanner s = new Scanner(url.openStream());
        
        Tile[][] tileset;
        LinkedList<Entity> list = new LinkedList<>();
        tileset = new Tile[0][0];
        LinkedList<Entity> entities = new LinkedList<>();
        HashMap<String, Double> props = getDefaultProperties();
        
        while(s.hasNextLine()) {
            String line = s.nextLine().trim();
            String[] segments = line.split(",");
            switch(segments[0]) {
                case "SIZE":
                    tileset = new Tile[Integer.parseInt(segments[1])][Integer.parseInt(segments[2])];
                    break;
                case "ENTITY":
                    Entity en;
                    try {
                        URL ur = Level.class.getResource("/Assets/");
                        URL[] urls = new URL[]{ur};
                        
                        ClassLoader cl = new URLClassLoader(urls);
                        Class cls = cl.loadClass("Assets." + segments[4]);
                        Constructor<?> ctor = cls.getConstructor(double.class, double.class, BufferedImage.class);
                        en = (Entity) ctor.newInstance(Double.parseDouble(segments[1]), Double.parseDouble(segments[2]), ImageIO.read(Level.class.getResource("/Assets/" + segments[3])));
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        en = null;
                        e.printStackTrace();
                    }
                    entities.add(en);
                    break;
                case "TILE":
                    Tile tile;
                    try {
                        URL ur = Level.class.getResource("/Assets/");
                        URL[] urls = new URL[]{ur};
                        
                        ClassLoader cl = new URLClassLoader(urls);
                        Class cls = cl.loadClass("Assets." + segments[4]);
                        Constructor<?> ctor = cls.getConstructor(int.class, int.class, BufferedImage.class);
                        tile = (Tile) ctor.newInstance(Integer.parseInt(segments[1]), Integer.parseInt(segments[2]), ImageIO.read(Level.class.getResource("/Assets/" + segments[3])));
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        tile = null;
                        e.printStackTrace();
                    }
                    tileset[Integer.parseInt(segments[1])][Integer.parseInt(segments[2])] = tile;
                    break;
                case "SET":
                    props.put(segments[1], Double.parseDouble(segments[2]));
                    break;
            } 
        }
        for(int i = 0; i < tileset.length; i++) {
            for(int j = 0; j < tileset[0].length; j++) {
                if(tileset[i][j] == null) tileset[i][j] = new VoidTile(i, j, null);
            }
        }
        return new Level(tileset, entities.toArray(new Entity[0]), props);
    }
    private static HashMap<String, Double> getDefaultProperties() {
        HashMap<String, Double> map = new HashMap<>();
        map.put("GRAVITY", 1.0);
        
        return map;
    }
    
    public static Level createLevel(int x, int y) {
        Tile[][] tiles = new Tile[x][y];
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                tiles[i][j] = new VoidTile(i, j, null);
                System.out.println(tiles[i][j]);
            }
        }
        return new Level(tiles, new Entity[0], new HashMap<>());
    }
    
    public static Level getLevelFromJar(String location) throws IOException {
        JarFile jarFile = new JarFile(location);
        Enumeration<?> entries = jarFile.entries();
        while(entries.hasMoreElements()) {
            System.out.println(entries.nextElement());
        }
        
        JarEntry entry = jarFile.getJarEntry("level.csv");
        Scanner s = new Scanner(jarFile.getInputStream(entry));
        
        Tile[][] tileset;
        LinkedList<Entity> list = new LinkedList<>();
        tileset = new Tile[0][0];
        LinkedList<Entity> entities = new LinkedList<>();
        HashMap<String, Double> props = getDefaultProperties();
        
        while(s.hasNextLine()) {
            String line = s.nextLine().trim();
            System.out.println(line);
            String[] segments = line.split(",");
            switch(segments[0]) {
                case "SIZE":
                    tileset = new Tile[Integer.parseInt(segments[1])][Integer.parseInt(segments[2])];
                    break;
                case "ENTITY":
                    Entity en;
                    try {
                        URL ur = new URL("jar:file:" + jarFile.getName() + "!/");
                        URL[] urls = new URL[]{ur};
                        
                        ClassLoader cl = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
                        Class cls = cl.loadClass("Assets." + segments[4]);
                        Constructor<?> ctor = cls.getConstructor(double.class, double.class, BufferedImage.class);
                        en = (Entity) ctor.newInstance(Double.parseDouble(segments[1]), Double.parseDouble(segments[2]), ImageIO.read(cl.getResourceAsStream("/Assets/" + segments[3])));
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        en = null;
                        e.printStackTrace();
                    }
                    entities.add(en);
                    break;
                case "TILE":
                    Tile tile;
                    try {
                        URL ur = new URL("jar:file:" + jarFile.getName() + "!/");
                        URL[] urls = new URL[]{ur};
                        
                        ClassLoader cl = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
                        Class cls = cl.loadClass("Assets." + segments[4]);
                        Constructor<?> ctor = cls.getConstructor(int.class, int.class, BufferedImage.class);
                        tile = (Tile) ctor.newInstance(Integer.parseInt(segments[1]), Integer.parseInt(segments[2]), ImageIO.read(cl.getResourceAsStream("/Assets/" + segments[3])));
                    } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        tile = null;
                        e.printStackTrace();
                    }
                    tileset[Integer.parseInt(segments[1])][Integer.parseInt(segments[2])] = tile;
                    break;
                case "SET":
                    props.put(segments[1], Double.parseDouble(segments[2]));
                    break;
            } 
        }
        for(int i = 0; i < tileset.length; i++) {
            for(int j = 0; j < tileset[0].length; j++) {
                if(tileset[i][j] == null) tileset[i][j] = new VoidTile(i, j, null);
            }
        }
        return new Level(tileset, entities.toArray(new Entity[0]), props);
    }
}
