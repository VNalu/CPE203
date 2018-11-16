import java.util.List;
import processing.core.PImage;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import processing.core.PApplet;

public class Blacksmith implements Entity {

    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;
    private int resourceLimit;
    private int resourceCount;
    private int actionPeriod;
    private int animationPeriod;

    public Blacksmith(String id, Point position, List<PImage> images,
     int resourceLimit, int resourceCount, int actionPeriod, int animationPeriod) {
        // this.kind = kind;
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0; 
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;
    }

    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

    public void setPosition(Point newPos) {
        this.position = newPos;
    }

    public Point getPosition() {
        return position;
    }

    public void setImages(List<PImage> newImages) {
        this.images = newImages;
    }

    public List<PImage> getImages() {
        return images;
    }

    public void setResourceLimit(int newResLim) {
        this.resourceLimit = newResLim;
    }

    public int getResourceLimit() {
        return resourceLimit;
    }

    public void setResourceCount(int newResCt) {
        this.resourceCount = newResCt;
    }

    public int getResourceCount() {
        return resourceCount;
    }

    public void setActionPeriod(int newActPer) {
        this.actionPeriod = newActPer;
    }

    public int getActionPeriod() {
        return actionPeriod;
    }

    public void setAnimationPeriod(int newAnimPer) {
        this.animationPeriod = newAnimPer;
    }

    public int getAnimationPeriod() {
        return animationPeriod;
    }

    public PImage getCurrentImage()
    {
        return (this).images.get((this).imageIndex);
    }
}