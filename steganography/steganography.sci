close;
clear ;
clc;
//% Read in original RGB image.
srcRgbImage = imread('steg_s.png');
//% Extract color channels.
srcRedChannel = srcRgbImage(:,:,1); //% Red channel
srcGreenChannel = srcRgbImage(:,:,2); //% Green channel
srcBlueChannel = srcRgbImage(:,:,3); //% Blue channel
//% Create an all black channel.
allBlack = uint8(zeros(200,200));

srcRedChannel = uint8(srcRedChannel & uint8(hex2dec('FC')));
srcGreenChannel = uint8(srcGreenChannel & uint8(hex2dec('FC')));
srcBlueChannel =  uint8(srcBlueChannel & uint8(hex2dec('FC')));


tarRgbImage = imread('e:\Projects\image processing\steg_t.png');
tarRedChannel = uint8(tarRgbImage(:,:,1))/100; //% Red channel 
tarGreenChannel = uint8(tarRgbImage(:,:,2))/100; //% Green channel
tarBlueChannel = uint8(tarRgbImage(:,:,3))/100; //% Blue channel

srcRedChannel = uint8(srcRedChannel | tarRedChannel);
srcGreenChannel = uint8(srcGreenChannel | tarGreenChannel);
srcBlueChannel = uint8(srcBlueChannel | tarBlueChannel);
//% Recombine the individual color channels to create the original RGB image again.
recombinedRGBImage = cat(3, srcRedChannel, srcGreenChannel, srcBlueChannel); 
figure; imshow(recombinedRGBImage);
imwrite(recombinedRGBImage,'steg_out.png');


disp("Recovering hidden image");
sleep(4000);
close;
clear ;
clc;
//% Read in original RGB image.
rgbImage = imread('steg_out.png');
//% Extract color channels.
redChannel = rgbImage(:,:,1); //% Red channel
greenChannel = rgbImage(:,:,2); //% Green channel
blueChannel = rgbImage(:,:,3); //% Blue channel
//% Create an all black channel.
allBlack = uint8(zeros(200,200));
//% Create color versions of the individual color channels.
just_red = cat(3, redChannel, allBlack, allBlack);
just_green = cat(3, allBlack, greenChannel, allBlack);
just_blue = cat(3, allBlack, allBlack, blueChannel);

redChannel = uint8(redChannel & uint8(hex2dec('03')))*100;
greenChannel = uint8(greenChannel & uint8(hex2dec('03'))) *100;
blueChannel =  uint8(blueChannel & uint8(hex2dec('03')))*100;

//% Recombine the individual color channels to create the original RGB image again.
recombinedRGBImage = cat(3, redChannel, greenChannel, blueChannel); 

figure; imshow(recombinedRGBImage);
