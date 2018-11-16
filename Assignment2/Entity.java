import java.util.List;
import processing.core.PImage;
import java.util.Optional;
import java.util.Random;


public interface Entity {

    public void nextImage();

    public void setPosition(Point newPos);
    public Point getPosition();
    public void setImages(List<PImage> newImages);
    public List<PImage> getImages();
    public void setResourceLimit(int newResLim);
    public int getResourceLimit();
    public void setResourceCount(int newResCt);
    public int getResourceCount();
    public void setActionPeriod(int newActPer);
    public int getActionPeriod();
    public void setAnimationPeriod(int newAnimPer);
    public int getAnimationPeriod();

    public PImage getCurrentImage();
}
