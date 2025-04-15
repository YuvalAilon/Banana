package cybersecurityDemonstration.Level3;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import cybersecurityDemonstration.ImageManager;

public class BasicSteganography {
  public static void main(String[] args) throws IOException {
    ImageManager.getImageFrom(extractMessageFromImage("src/cybersecurityDemonstration/Level3/Image/message.png"));
  }

  /**
   * Encodes the given message in the given image.
   * @param message the message
   * @param filepath the filepath to the original image
   * @param messageName the filepath of the image to be created
   * @throws IOException if image cannot be read/witten
   */
  private static void encodeMessageInImage(
          String message,
          String filepath,
          String messageName
  ) throws IOException {
    List<Integer> binary = new ArrayList<>();
    for(char character : message.toCharArray()){
      addBinaryTo(character, binary);
    }
    File originalFile = new File(filepath);
    BufferedImage image = ImageIO.read(originalFile);
    BufferedImage imageWithMessage = new BufferedImage(
            image.getWidth(),
            image.getHeight(),
            BufferedImage.TYPE_INT_RGB
    );
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        int index = coordToIndex(x,y,image.getWidth());
        int rgb = image.getRGB(x,y);
        Color secretColor = AddSecretToColorValue(
                binaryOrZero(index, binary),
                binaryOrZero(index + 1, binary),
                binaryOrZero(index + 2, binary)
                ,rgb);
        imageWithMessage.setRGB(x,y, secretColor.getRGB());
      }
    }
    ImageIO.write(imageWithMessage, "png", new File(messageName));
  }


  /**
   * Adds the binary encoding of a given character to the given list as a series of 1s and 0s.
   * @param character the given character
   * @param binaryList the given list
   */
  private static void addBinaryTo(char character, List<Integer> binaryList){
    String binaryString = String.format(
            "%8s",
            Integer.toBinaryString(character)).replace(' ', '0'
    );

    for (Character c : binaryString.toCharArray()) {
      binaryList.add(Integer.parseInt(String.valueOf(c)));
    }
  }

  /**
   * Given an (x,y) coordinate in an image, transforms it to the index of the bit to be hidden.
   * at that coordinate
   * @param x the x-coordinate
   * @param y the y-coordinate
   * @param width the width of the image
   * @return the index of the bit to be hidden
   */
  private static int coordToIndex(int x, int y, int width){
    return (y * width + x) * 3;
  }

  /**
   * Returns either the bit at the specified location in the binary list, or a 0-bit
   * @param index the index of the specified bit
   * @param binary the binary array
   * @return the bit at the given index, or 0 if it is out of bounds
   */
  private static int binaryOrZero(int index, List<Integer> binary){
    try {
      return binary.get(index);
    }catch (Exception ignored){
      return 0;
    }
  }

  /**
   * Adds three secret bits to a given color value.
   * @param r the bit to be hidden in the red value
   * @param g the bit to be hidden in the green value
   * @param b the bit to be hidden in the blue value
   * @param colorValue the color value
   * @return the color value with the hidden 3 bits
   */
  private static Color AddSecretToColorValue(int r, int g, int b, int colorValue){
    Color color = new Color(colorValue);
    return new Color(
            alterLSB(r, color.getRed()),
            alterLSB(g, color.getGreen()),
            alterLSB(b, color.getBlue())
    );
  }

  /**
   * Given a red, green, or blue value, alters its least significant bit to contain a secret.
   * @param secret the secret
   * @param colorValue the red, green, or blue color value
   * @return the altered color value
   */
  private static int alterLSB(int secret, int colorValue){
    String colorBinary = String.format("%8s", Integer.toBinaryString(colorValue)).replace(' ', '0');
    colorBinary = colorBinary.substring(0, colorBinary.length() - 1) + secret;
    return Integer.parseInt(colorBinary, 2);
  }


  /**
   * Extracts a message from an image.
   * @param filepath the filepath of the image with the secret message
   * @return the string hidden in the image.
   * @throws IOException if the image cannot be read
   */
  private static String extractMessageFromImage(String filepath) throws IOException {
    File originalFile = new File(filepath);
    BufferedImage image = ImageIO.read(originalFile);
    List<Integer> binary = new ArrayList<>();
    StringBuilder message = new StringBuilder();
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        extractLSBTo(new Color(image.getRGB(x,y)), binary);
      }
    }
    for (int charIndex = 0; charIndex < binary.size(); charIndex += 8) {
      StringBuilder asciiCodeString = new StringBuilder();
      for (int bit = charIndex; bit < charIndex + 8 ; bit++) {
        try {
          asciiCodeString.append(binary.get(bit));
        }catch (Exception ignored){
          asciiCodeString.append(0);
        }

      }
      String asciiCode = String.join("",asciiCodeString.toString() );
      if (asciiCode.contains("1")){
        message.append(Character.toString(Integer.parseInt(asciiCode, 2)));
      }
    }
    return message.toString();
  }

  /**
   * Adds the least significant bits of an RGB color value to a given binary list.
   * @param color the RGB color value
   * @param binary the given binary list
   */
  private static void extractLSBTo(Color color, List<Integer> binary){
    binary.add(extractLSB(color.getRed()));
    binary.add(extractLSB(color.getGreen()));
    binary.add(extractLSB(color.getBlue()));
  }

  /**
   * Extrats the least significant bit from a red, green, or blue color value.
   * @param colorValue the color value
   * @return the LSB
   */
  private static int extractLSB(int colorValue){
    String colorBinary = Integer.toBinaryString(colorValue);
    return Integer.parseInt(
            String.valueOf(Integer.toBinaryString(colorValue).charAt(colorBinary.length() - 1))
    );
  }

}
