package de.nartis.picsort;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import de.nartis.image.ImageLoader;
import de.nartis.image.ImageResizer;

public class ImageManager {
	
	private LinkedList<ImageModel> list;
	private int width, height;
	
	private AppModel model;
	
	public ImageManager(int width, int height, AppModel model) {
		list = new LinkedList<ImageModel>();
		this.width = width;
		this.height = height;
		this.model = model;
	}
	
	public void init(String[] images) {
		
		BufferedImage bi = null;
		
		for(String s : images) {
			
			bi = getResizedImage( s );
			list.add( new ImageModel( s, bi ) );
		}
	}
	
	public ImageModel getCurrent() {
		return list.getFirst();
	}
	
	public void shiftLeft(final String nextImage) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					
					list.removeFirst();
					list.add( new ImageModel( nextImage, getResizedImage( nextImage ) ) );
					
					model.notifyObservers( null );

				} catch( Exception e ) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void shiftRight( final String preImg ) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					list.addFirst( new ImageModel( preImg, getResizedImage( preImg ) ) );
					list.removeLast();
					model.notifyObservers( null );
					
				} catch( Exception e ) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public BufferedImage getResizedImage( String s ) {
		
		BufferedImage bi;
		bi = ImageLoader.load( s );
		
		return resizeToScreen( bi );
	}
	
	public BufferedImage resizeToScreen(BufferedImage bi) {
		if( bi.getWidth() > width ) {
			
			bi = ImageResizer.resizeToWidth( bi, width );
			
		} else if( bi.getHeight() > height ) {
			
			bi = ImageResizer.resizeToHeight( bi, height );
		}
		return bi;
	}

}
