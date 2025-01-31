
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

/**
 *
 */
public class Banana {
  public static void main(String[] args) {
    String address = "https://m.media-amazon.com/images/I/31dke4F+cTL._AC_UF894,1000_QL80_.jpg";
    String name = "banana.jpg";
    try {
      Image banana = getImageFrom(address);
      ImageIO.write((BufferedImage) banana, "jpg", new File(name));
    } catch (Exception e) {
      System.out.println("Could not get or store image at: " + address);
    }

  }

  private static Image getImageFrom(String address) throws IOException, URISyntaxException {
    try{
      URI uri = new URI(address);
      return ImageIO.read(uri.toURL());
    } catch (IOException e) {
      throw new IOException("Could not get image from: " + address);
    } catch (URISyntaxException e) {
      throw new URISyntaxException(address, "Invalid Format");
    }

  }
}