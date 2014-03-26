package de.nartis.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageOperation {

	public static Image ResizeImage( Image img, int wNew, int hNew ) {
		// Größe verändern
		Image resizedImage = img.getScaledInstance( wNew, hNew, Image.SCALE_SMOOTH );
		return resizedImage;
	}

	public static void SaveFile( Image img, String path ) {
		String ending = path.substring( path.length() - 3, path.length() );
		BufferedImage saveImg = null;

		// Bild auf Buffer zeichnen
		if( ending.equals( "png" ) || ending.equals( "PNG" ) )
			saveImg = new BufferedImage( img.getWidth( null ), img.getHeight( null ),
					BufferedImage.TRANSLUCENT );
		else
			saveImg = new BufferedImage( img.getWidth( null ), img.getHeight( null ),
					BufferedImage.TYPE_INT_RGB );

		Graphics g = saveImg.getGraphics();
		g.drawImage( img, 0, 0, null );
		g.dispose();

		try {
			ImageIO.write( saveImg, ending, new File( path ) );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}

	public static Image AddText( Image img_src, String message, int message_x, int message_y ) {
		BufferedImage src = new BufferedImage( img_src.getWidth( null ), img_src.getHeight( null ),
				BufferedImage.TRANSLUCENT );

		Graphics g = src.getGraphics();
		g.drawImage( img_src, 0, 0, null );
		g.setFont( new Font( "Sans", Font.PLAIN, 10 ) );
		g.setColor( new Color( 0 ) );
		g.drawString( message, message_x, message_y );

		return src;
	}

	public static Image AddLogo( Image img_src, Image img_logo, int img_logo_x, int img_logo_y ) {
		BufferedImage src = new BufferedImage( img_src.getWidth( null ), img_src.getHeight( null ),
				BufferedImage.TRANSLUCENT );

		Graphics g = src.getGraphics();
		g.drawImage( img_src, 0, 0, null );
		g.drawImage( img_logo, img_logo_x, img_logo_y, null );

		return src;
	}

	public static Image InvertColors( Image img ) {
		BufferedImage dest = new BufferedImage( img.getWidth( null ), img.getHeight( null ),
				BufferedImage.TRANSLUCENT );
		BufferedImage src = new BufferedImage( img.getWidth( null ), img.getHeight( null ),
				BufferedImage.TRANSLUCENT );

		// Img to BI
		Graphics g = src.getGraphics();
		g.drawImage( img, 0, 0, null );

		for( int y = 0; y < src.getHeight(); y++ )
			for( int x = 0; x < src.getWidth(); x++ )
				dest.setRGB( x, y, ( src.getRGB( x, y ) & 0xff000000 )
						| ( ( ~src.getRGB( x, y ) ) & 0x00ffffff ) );

		return dest;
	}

	public static Image MirroringHorizontal( Image img ) {
		BufferedImage dest = new BufferedImage( img.getWidth( null ), img.getHeight( null ),
				BufferedImage.TRANSLUCENT );
		BufferedImage src = new BufferedImage( img.getWidth( null ), img.getHeight( null ),
				BufferedImage.TRANSLUCENT );

		// Img to BI
		Graphics g = src.getGraphics();
		g.drawImage( img, 0, 0, null );

		for( int y = 0; y < src.getHeight(); y++ )
			for( int x = 0; x < src.getWidth(); x++ )
				dest.setRGB( src.getWidth() - 1 - x, y, src.getRGB( x, y ) );

		return dest;
	}

	public static Image MirroringVertical( Image img ) {
		BufferedImage dest = new BufferedImage( img.getWidth( null ), img.getHeight( null ),
				BufferedImage.TRANSLUCENT );
		BufferedImage src = new BufferedImage( img.getWidth( null ), img.getHeight( null ),
				BufferedImage.TRANSLUCENT );

		// Img to BI
		Graphics g = src.getGraphics();
		g.drawImage( img, 0, 0, null );

		for( int y = 0; y < src.getHeight(); y++ )
			for( int x = 0; x < src.getWidth(); x++ )
				dest.setRGB( x, src.getHeight() - 1 - y, src.getRGB( x, y ) );

		return dest;
	}

	public static Image TurnImageRight( Image img ) {
		BufferedImage dest = new BufferedImage( img.getHeight( null ), img.getWidth( null ),
				BufferedImage.TRANSLUCENT );
		BufferedImage src = new BufferedImage( img.getWidth( null ), img.getHeight( null ),
				BufferedImage.TRANSLUCENT );

		// Img to BI
		Graphics g = src.getGraphics();
		g.drawImage( img, 0, 0, null );

		//
		for( int i = 0; i < src.getWidth(); i++ )
			for( int j = 0; j < src.getHeight(); j++ )
				dest.setRGB( src.getHeight() - 1 - j, i, src.getRGB( i, j ) );

		return dest;
	}

	public static Image TurnImageLeft( Image img ) {
		BufferedImage dest = new BufferedImage( img.getHeight( null ), img.getWidth( null ),
				BufferedImage.TRANSLUCENT );
		BufferedImage src = new BufferedImage( img.getWidth( null ), img.getHeight( null ),
				BufferedImage.TRANSLUCENT );

		// Img to BI
		Graphics g = src.getGraphics();
		g.drawImage( img, 0, 0, null );

		//
		for( int i = 0; i < src.getWidth(); i++ )
			for( int j = 0; j < src.getHeight(); j++ )
				dest.setRGB( j, src.getWidth() - 1 - i, src.getRGB( i, j ) );

		return dest;
	}
}
