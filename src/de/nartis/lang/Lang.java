package de.nartis.lang;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Lang {
	
	private static Properties props;
	private static Map<String, String> map;
	
	
	public static void addLanguage( String locale, String name) {
		
		if(map == null) 
			map = new HashMap<String, String>();
		
		map.put( locale, name );
	}
	
	
	public static void setLanguageByOs() {

		if( map == null )
			throw new IllegalStateException( "Please add possible languages before using: Lang.addLanguage(\"english\")" );
		
		String locale = System.getProperty("user.language");
		
		String file = map.get( locale );
			
		try {

			if( file != null )
				Lang.setLanguage( file );

		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public static void setLanguage( String language ) throws FileNotFoundException, IOException {
		props = new Properties();

		// Set default language
		String lang = "english.properties";

		// Try to find the wanted language
		if( language != null && !language.isEmpty() ) {
			lang = language + ".properties";
		}
		
		props.load( ClassLoader.getSystemResourceAsStream( lang ) );
	}
	
	public static String get( String key ) {
		if( props == null )
			throw new IllegalStateException( "Configure a language: Lang.setLanguage(...)" );
		
		try {
			return props.get( key ).toString();
		} catch( Exception e ) {
			return key;
		}
	}
	
	public static void openChooser( JFrame window, String[] languages ) {
		
		String s = (String) JOptionPane.showInputDialog( 
				window, 
				"Please, choose your favorit language.", 
				"Change Language", 
				JOptionPane.PLAIN_MESSAGE, 
				null, 
				languages, "->" );
		
		if( ( s != null ) && ( s.length() > 0 ) ) {
			
			try {
				Lang.setLanguage( s );
			
			} catch( Exception e ) {
				e.printStackTrace();
			}

			return;
		}
	}
}
