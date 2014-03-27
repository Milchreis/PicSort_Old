package de.nartis.picsort;

import de.nartis.lang.Lang;

public class Main {

	
	/*
	 * TODO:
	 * 	- MainWindow	
	 * 	- Delete image by DEL
	 * 	- Scaling image totally to screen (now: greater than screen - image chropped)
	 */
	
	public static void main( String[] args ) {
		
		try {
			Lang.setLanguage( "german" );
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
		
		new AppController();
	}

}
