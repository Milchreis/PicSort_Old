![alt text](https://31.media.tumblr.com/bb9e67b5816527cfbb1c13df221fd0c5/tumblr_inline_n2y0y7iVgi1r10owe.png "Logo")

PicSort is a simple program to view images in fullscreen and organize them in different categories. Set up the source and the destination directory of your images. Further you just navigate through your image pool or you define a category. Until now, the category is active and with the space-bar-key you copy the picture into the destination directory inside a subdirectory named by your category.

In that way, you are able to look for your new pictures, f.e. on your camera or smartphone and sort them in the same time. That is fast, clean and easy.

![alt text](http://abload.de/img/screen16srx.png "Sceenshot")

## Shortcuts ##

Press the F1 key in fullscreen to show the full shortcut information's. The following list shows the basic keys.

 - `Left` and `Right` to show the previous or next picture
 - `CTRL` to open up the dialog to input the category
 - `Space-bar` to copy the current picture to destination (like "destination/category/img.jpg")

## Download / Build ##

The latetest version of the picsort program is hosted on github. Feel free to [download](https://github.com/MilchReis/PicSort/raw/master/bin/picsort_0-2.jar "download-address") it. The program starts on windows systems normally with a double click. On linux set the execute-flag of the jar-file, f. e. `chmod 755 picsort.jar` and start it with `java -jar picsort.jar`.

Until now I create the executable jar-file with the build-in eclipse export system. Later, I will add an ant-script for the build-process.

## Future ##
The following list includes the changes in the current code to the latest version. The next release will contains this changes.

 - Bugfix: empty directories (no pictures inside) produce errors  
 - Bugfix: fullscreen starts black and backrotation produces an exception (invalid mod operation)

## Changelog ##

#### Version 0.2 ####

 - restore fullscreen for Windows 8 (after category dialog)
 - background for text elements in fullscreen, for better readability
 - ~~language switch (is missing)~~
 - commandline interface (see picsort.jar --help)

#### Version 0.1 ####

 - scaling pictures and view pictures in fullscreen
 - dialog to setting up the category
 - copy pictures
 - rotate pictures
 - preload the following pictures for a fast navigation

