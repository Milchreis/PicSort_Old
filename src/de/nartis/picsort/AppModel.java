package de.nartis.picsort;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Observable;

import javax.swing.JOptionPane;

import de.nartis.image.ImageOperation;
import de.nartis.image.ImageValidator;
import de.nartis.lang.Lang;
import de.nartis.utils.FileCopy;

public class AppModel extends Observable {

	public static final int TURN_RIGHT = 100;
	public static final int TURN_LEFT  = 200;
	
	private File sourcePath;
	private File destPath;

	private LinkedList<String> imgList;
	private ImageManager imgManager;
	
	private boolean isInfoEnabled = false;
	private String eventType = null;
	
	private int currentImg = 0;
	private int range = 0;
	private int rangePos = 0;

	public AppModel() {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		imgManager = new ImageManager( dim.width, dim.height, this );
	}
	
	public void prepare() throws Exception {
		
		loadImageList();
		
		if(imgList.size() == 0 ) {
			throw new Exception( "no pictures in directory found" );
		
		} else if(imgList.size() >= 3) {
			range = 2;
			
		} else {	
			range = imgList.size() - 1;
		}
			
		String[] initList = new String[range+1];
		initList[0] = imgList.get(0);
		
		for(int i=1; i<range+1; i++) {
			initList[i] = imgList.get(i);	
		}
		
		imgManager.init(initList);
	}

	public void nextImg() {

		// check 
		currentImg = (++currentImg % imgList.size());
		
		rangePos = currentImg + range;
		rangePos = (rangePos % imgList.size());
			
		imgManager.shiftLeft( imgList.get( rangePos ) );
	}

	public void prevImg() {
		
		currentImg = (--currentImg % imgList.size());	
		imgManager.shiftRight( imgList.get( currentImg ) );
	}

	public ImageModel getCurrent() {
		return imgManager.getCurrent();
	}

	@Override
	public void notifyObservers( Object arg0 ) {
		setChanged();
		super.notifyObservers( arg0 );
	}
	
	private void loadImageList() {

		if( sourcePath != null && sourcePath.isDirectory() ) {

			imgList = new LinkedList<String>();
			File[] files = sourcePath.listFiles();
			
			// Sort the array by file name
			Arrays.sort( files, new Comparator<File>() {
				@Override
			    public int compare(File a, File b) {
					return a.getName().compareTo( b.getName() );
			    }
			} );

			// filter valid image files
			for( File f : files ) {

				if( ImageValidator.isValid( f ) ) {
					imgList.add( f.getAbsolutePath() );
				}
			}

		} else {

			throw new IllegalArgumentException( "source path is no directory" );
		}

//		this.notifyObservers( null );
	}

	public File getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath( File sourcePath ) {
		this.sourcePath = sourcePath;
	}

	public File getDestPath() {
		return destPath;
	}

	public void setDestPath( File destPath ) {
		this.destPath = destPath;
	}
	
	public void switchInfo() {
		isInfoEnabled = !isInfoEnabled;
		this.notifyObservers( null );
	}

	public boolean isInfoVisible() {
		return isInfoEnabled;
	}
	
	public String getEventType() {
		return eventType;
	}

	public void openEventTypeInput() {
		eventType = JOptionPane.showInputDialog(
				null, Lang.get( "info.inputEvent" ), 
				"PicSort", 1);

		this.notifyObservers( null );
	}

	public void moveImage() {
		if( eventType == null ) {
			
			JOptionPane.showMessageDialog(null, 
					Lang.get( "info.eventBefore" ), 
					Lang.get( "info.headline" ), JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		File dir = new File(destPath, eventType);
		
		if(!dir.exists()) {
			if(!dir.mkdir()) {
				JOptionPane.showMessageDialog(null, 
						Lang.get( "error.dirNoCreate" ), 
						Lang.get( "error.headline" ), JOptionPane.ERROR_MESSAGE);
			}
		}
		
		try {
			ImageModel im = getCurrent();
			File imgFile = im.getFile();
			File destImgFile = new File(dir, im.getFilename());
			
			if(!destImgFile.exists()) {
				FileCopy.copyFileUsingStream( imgFile, destImgFile );
			}
		
			// MOVE OR COPY, maybe changable by settings
//			imgF.delete();
			
			nextImg();
			
		} catch( IOException e ) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(null, 
					Lang.get( "error.imgNoCopy" ), 
					Lang.get( "error.headline" ), JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void deleteImage() {
		ImageModel im = getCurrent();
		File img = im.getFile();
		
		int result = JOptionPane.showConfirmDialog( null, 
				Lang.get( "delete.question"), 
				Lang.get( "delete.headline" ), 
				JOptionPane.YES_NO_OPTION );
		
		if( result == JOptionPane.YES_OPTION ) {
			System.out.println(img.delete());
			
			loadImageList();
			
			nextImg();
		}
	}

	public void turnImage( int direction ) {
		ImageModel im = getCurrent();

		BufferedImage img = null;
		if(direction == TURN_RIGHT) {
			img = ( BufferedImage ) ImageOperation.TurnImageRight( im.getImg() );			

		} else {
			img = ( BufferedImage ) ImageOperation.TurnImageLeft( im.getImg() );
		}
		
		img = imgManager.resizeToScreen( img );
		im.setImg( img );

		this.notifyObservers( null );
	}
}
