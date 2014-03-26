package de.nartis.utils;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class NativeLook {

	public static void setNativeUI() {
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch( ClassNotFoundException e ) {
			e.printStackTrace();
		} catch( InstantiationException e ) {
			e.printStackTrace();
		} catch( IllegalAccessException e ) {
			e.printStackTrace();
		} catch( UnsupportedLookAndFeelException e ) {
			e.printStackTrace();
		}
	}
}