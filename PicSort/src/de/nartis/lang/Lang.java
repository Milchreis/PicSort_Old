package de.nartis.lang;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Lang {
	
	private static Properties props;
	
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
}
