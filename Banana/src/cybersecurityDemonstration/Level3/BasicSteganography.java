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

  private static void addBinaryTo(char character, List<Integer> binaryList){
    String binaryString = String.format("%8s", Integer.toBinaryString(character)).replace(' ', '0');;
    for (Character c : binaryString.toCharArray()) {
      binaryList.add(Integer.parseInt(String.valueOf(c)));
    }
  }

  private static int coordToIndex(int x, int y, int width){
    return (y * width + x) * 3;
  }

  private static int binaryOrZero(int index, List<Integer> binary){
    try {
      return binary.get(index);
    }catch (Exception ignored){
      return 0;
    }
  }

  private static Color AddSecretToColorValue(int r, int g, int b, int colorValue){
    Color color = new Color(colorValue);
    return new Color(
            alterLSB(r, color.getRed()),
            alterLSB(g, color.getGreen()),
            alterLSB(b, color.getBlue())
    );
  }

  private static int alterLSB(int secret, int colorValue){
    String colorBinary = String.format("%8s", Integer.toBinaryString(colorValue)).replace(' ', '0');
    colorBinary = colorBinary.substring(0, colorBinary.length() - 1) + secret;
    return Integer.parseInt(colorBinary, 2);
  }

  private static void encodeMessageInImage(String message, String filepath, String messageName) throws IOException {
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

  private static void extractLSBTo(Color color, List<Integer> binary){
    binary.add(extractLSB(color.getRed()));
    binary.add(extractLSB(color.getGreen()));
    binary.add(extractLSB(color.getBlue()));
  }

  private static int extractLSB(int colorValue){
    String colorBinary = Integer.toBinaryString(colorValue);
    return Integer.parseInt(String.valueOf(Integer.toBinaryString(colorValue).charAt(colorBinary.length() - 1)));
  }


}
