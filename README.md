# Banana | CY2550 TA Project
The purpose of this project is to showcase how programs can be unexpectedly
hidden within other programs. Each of the programs in this repository hides the URL to an image of a banana. This demonstrates how any text, including malicious instructions
can be hidden in a program.

Below is a list of how each file hides the banana program. This list will be updated as more
programs are developed:

## Level 0: Banana
Showcases the "malicious" url of the image that will be hidden in the rest of the projects, 
creating the following jpg file of a banana on the user's machine with the name banana.jpg.

![alt text](https://m.media-amazon.com/images/I/31dke4F+cTL._AC_UF894,1000_QL80_.jpg)

## Level 1: Security by Obscurity
Hides the URL split into parts in a dictionary, and the "validation" function just swaps out the given url with the malicious URL. 

This level is a reminder to always verify the function of code you download from an unknown source, instead of blindly trusting it.

## Level 2: Encryption
The URL is Base64 encoded, and the orginal text is not easy to find unless you know that such an encoding occured. Many real-world malicious programs encrypt their instructions to go by undetected by anti-virus programs

## Level 3: Steganography
The image of Van Gogh's *Starry Night* contains the URL of the banana image in its least significant bits. 

For every pixel in the image the last digit of the binary format of the R, G and B values is altered to hide a message (this is the least significant bit).

For Example to hide the message [010110] in the two pixels:

    
    R: 11010100 G: 11010100 B: 11010101
    
    R: 00101100 G: 01110101 B: 10101011

The following bits would be affected


    R: 1101010[0] G: 1101010[1] B: 1101010[0]
    
    R: 0010110[1] G: 0111010[1] B: 1010101[0]

Since the change in RGB values is so small, the message is imperceptible to the human eye, and is very well hidden. 

This image hides part of the first chapter of Pride & Prejudice!

![PrideAndPrejudice](https://github.com/user-attachments/assets/006cd8fb-f3d7-4bfa-856e-99cca2a918e4)

