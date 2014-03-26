package de.nartis.image;

import java.io.File;

public class ImageValidator {

	public static boolean isValid( File file ) {
		if( file == null || !file.isFile() )
			return false;
		
		String basename = file.getName().toLowerCase();
		if( !basename.endsWith( "jpg" ) && !basename.endsWith( "png" ) )
			return false;
			
		return true;
	}
}
