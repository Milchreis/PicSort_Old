package de.nartis.image;

import java.awt.image.BufferedImage;

public class ImageResizer {

	public static BufferedImage resizeToWidth(BufferedImage img, int width) {
		float factor = width / (float) img.getWidth();
		int height = (int) ((float) img.getHeight() * factor); 
		
		return ImageLoader.toBufferedImage( img.getScaledInstance( width, height, 75 ) );					
	}

	public static BufferedImage resizeToHeight( BufferedImage img, int height ) {
		float factor = height / (float) img.getHeight();
		int width = (int) ((float) img.getWidth() * factor); 
		
		return ImageLoader.toBufferedImage( img.getScaledInstance( width, height, 75 ) );					
	}
	
	public static BufferedImage resize( int factor, BufferedImage image ) {
		int width = (int) ( image.getWidth() * ( (float) factor / 100.0f ) );
		int height = (int) ( image.getHeight() * ( (float) factor / 100.0f ) );

		return ImageLoader.toBufferedImage( image.getScaledInstance( width,
				height, BufferedImage.SCALE_AREA_AVERAGING ) );			
	}

	public static BufferedImage resize( int factor, BufferedImage image, int scaleOption ) {
		int width = (int) ( image.getWidth() * ( (float) factor / 100.0f ) );
		int height = (int) ( image.getHeight() * ( (float) factor / 100.0f ) );
		
		return ImageLoader.toBufferedImage( image.getScaledInstance( width,
				height, scaleOption ) );			
	}

	public static BufferedImage resizeShortSiteTo( int pixels, BufferedImage image ) {
		return resizeShortSiteTo( pixels, image, BufferedImage.SCALE_AREA_AVERAGING );
	}
	
	public static BufferedImage resizeShortSiteTo( int pixels, BufferedImage image, int scaleOption ) {
		int shortSite = 0;
		if( image.getWidth() < image.getHeight() ) {
			shortSite = image.getWidth();
		} else {
			shortSite = image.getHeight();
		}
		
		int factor = (int) ( pixels * 100 / (float) shortSite ); 
		return resize( factor, image, scaleOption );			
	}

	
}
