# Banana | CY2550 TA Project
The purpose of this project is to showcase how programs can be unexpectedly
hidden within other programs. Each of the programs in this repository
hide the base "banana" program, which downloads a photo of a banana from the web
onto the user's computer. This benign program is a stand-in for a malicious one.

The main goal of each level it to hide the URL to the image, demonstrating how potentially harmful instructions can be hidden in a program.

This project is intended for educational purposes and <u>cannot cause any harm to your machine</u>.
The "malicious" program was intentionally designed to be harmless, so code could be investigated
and run without any ill effects.

Below is a list of how each file hides the banana program. This list will be updated as more
programs are developed:

## Level 0: Banana
Showcases the "malicious" url of the image that will be hidden in the rest of the projects, 
creating the following jpg file of a banana on the user's machine with the name banana.jpg.

![alt text](https://m.media-amazon.com/images/I/31dke4F+cTL._AC_UF894,1000_QL80_.jpg)

## Level 1: Image Fetcher
Hides the url split into parts in a dictionary, and the "validation" function just swaps out the given url with the malicious URL. 
This level is a reminder to always verify the function of code you download from an unknown source, instead of blindly trusting it.
