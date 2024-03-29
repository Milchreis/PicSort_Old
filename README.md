![alt text](https://31.media.tumblr.com/bb9e67b5816527cfbb1c13df221fd0c5/tumblr_inline_n2y0y7iVgi1r10owe.png "Logo")

PicSort is a simple program to view images in fullscreen and organize them in different categories. Set up the source and the destination directory of your images. Further you just navigate thru your image pool or you define a category. Until now, the category is active and with the spacebar-key you copy the picture into the destination directory with a subdirectory of your category.

In that way, you are able to look for your new pictures, f.e. on you camera or smartphone and sort them in the same time. Fast, clean and easy.

![alt text](http://abload.de/img/screen16srx.png "Sceenshot")

## Shortcuts ##

Press the F1 key in fullscreen to show the full shortcut information's. The following list shows the basic keys.

 - `Left` and `Right` to show the previous or next picture
 - `CTRL` to open up the dialog to input the category
 - `Spacebar` to copy the current picture to destination (like "destination/category/img.jpg")

## Download / Build ##

The latetest version of the picsort program is hosted on github. Feel free to [download](https://github.com/MilchReis/PicSort/raw/master/bin/picsort_0-3.jar "download-address") it. On windows the program starts normally with a double click on the file. On linux set the execute-flag, f. e. `chmod 755 picsort.jar` and start with `java -jar picsort.jar` in the terminal.

Until now I create the executable jar-file with the build-in eclipse export system. If you want to build your program for your self, download the source and import the project into eclipse. After that export the project as 'runnable jar'.

## Future builds ##
The following list includes the changes in the current code base to the lastest version. The next release will contains the following changes.

 - automatic language detection and changing by os defaults.

## Changelog ##

#### Version 0.3 ####

 - Bugfix: empty directories (no pictures inside) produce errors  
 - Bugfix: fullscreen starts black and backrotation produces exception (invalid mod operation)
 - show the number of images and the number for the current image

#### Version 0.2 ####

 - restore fullscreen for Windows 8 (after category dialog)
 - background for text elements in fullscreen, for better reading
 - *language switch (is missing)*
 - commandline interface

#### Version 0.1 ####

 - scaling pictures and view in fullscreen
 - dialog for categories
 - copy pictures
 - rotating pictures
 - preload following pictures for fast navigation

