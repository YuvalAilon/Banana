package cybersecurityDemonstration;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

public class ImageManager {
  public static void getImageFrom(String address) {
    try{
      URI uri = new URI(address);
      Image image = ImageIO.read(uri.toURL());
      ImageIO.write((BufferedImage) image, "jpg", new File("banana.jpg"));
    } catch (IOException e) {
      System.out.println("Could not get image from: " + address);
    } catch (URISyntaxException e) {
      System.out.println("Invalid Format");
    }

  }
}
