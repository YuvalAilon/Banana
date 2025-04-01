package cybersecurityDemonstration.Level0;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import cybersecurityDemonstration.ImageManager;

/**
 *
 */
public class Banana {
  public static void main(String[] args) {
    String address = "https://m.media-amazon.com/images/I/31dke4F+cTL._AC_UF894,1000_QL80_.jpg";
    ImageManager.getImageFrom(address);

  }

}