package de.nartis.utils;

import java.io.IOException;
import java.util.Properties;

public class Conf {
	
	private static Properties props;
	
	public static String get( String key ) {
		if( props == null ) {
			props = new Properties();
			try {
				props.load( ClassLoader.getSystemResourceAsStream( "app.properties" ) );
			} catch( IOException e ) {
				e.printStackTrace();
			}
		}
		
		try {
			return props.get( key ).toString();
		} catch( Exception e ) {
			return key;
		}
	}
}
