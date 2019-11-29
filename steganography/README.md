Steganography is the practice of concealing a file, message, image, or video within another file, message, image, or video.
This is a sample implementation of steganography using images.

The project includes two images, the source steg_s.png and the target(hidden image), steg_t.png.
When you run the code in scilab(MATLAB as well), it will perform the following

1. Read source image
2. Split RGB channels
3. Mask the las two bits
4. Read target image
5. Split RGB channels
6. Devide channels by 100
7. OR with the source image to hide the image in the last two bits
8. write to output file steg_out.jpg

Read more about Steganography : https://en.wikipedia.org/wiki/Steganography