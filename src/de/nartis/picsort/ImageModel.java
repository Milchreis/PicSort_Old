package de.nartis.picsort;

import java.awt.image.BufferedImage;
import java.io.File;

public class ImageModel {

	private File fileImg;
	private BufferedImage img;
	
	public ImageModel(File file) {
		fileImg = file;
	}
	
	public ImageModel( File file, BufferedImage bi ) {
		this(file);
		this.img = bi;
	}
	
	public ImageModel(String path, BufferedImage bi ) {
		this(new File(path), bi);
	}

	public void setImg( BufferedImage img ) {
		this.img = img;
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public String getFilename() {
		return fileImg.getName();
	}

	public File getFile() {
		return fileImg;
	}
}
