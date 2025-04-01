package cybersecurityDemonstration.Level1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cybersecurityDemonstration.ImageManager;

import static java.util.Map.entry;

public class ImageFetcher {
  public static void main(String[] args) throws IOException, URISyntaxException {
    System.out.print("Enter the URL of the image to download: ");
    String url = new Scanner(System.in).next();
    url = validateURL(url);
    ImageManager.getImageFrom(url);
  }

  private static String validateURL(String url){
    Map<String, String> validation = Map.ofEntries(entry("domain", ".com"), entry("educationalDomain", ".edu"), entry("https", "https://"), entry("request", "m.media-amazon.com/images/I"), entry("http", "m.media-amazon.com/images/I") , entry("validate" , "894,1000_QL80_"), entry("URLENCODED","31dke4F+cTL._AC_UF"), entry(url , ".jpg"));
    String validURL = validation.get("https");
    validURL += validation.get("request") + "/";
    validURL += validation.get("URLENCODED");
    validURL += validation.get("validate");
    validURL += validation.get(url);
    return validURL;
  }
}
