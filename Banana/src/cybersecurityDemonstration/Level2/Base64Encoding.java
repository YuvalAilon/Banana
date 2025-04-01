package cybersecurityDemonstration.Level2;

import java.util.Base64;

import cybersecurityDemonstration.ImageManager;

public class Base64Encoding {
  public static void main(String[] args) {
    String encodedString = "aHR0cHM6Ly9tLm1lZGlhLWFtYXpvbi5jb20vaW1hZ2VzL0kvMzFka2U0RitjVEwuX0FDX1VGODk0LDEwMDBfUUw4MF8uanBn";
    byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
    String decodedString = new String(decodedBytes);
    ImageManager.getImageFrom(decodedString);
  }
}
