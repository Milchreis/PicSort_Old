package de.nartis.picsort.gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;

import de.nartis.picsort.AppController;
import de.nartis.picsort.AppModel;

public class FullscreenWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private ImagePanel imagepanel;

	private GraphicsDevice device;

	public FullscreenWindow( AppController controller, AppModel model ) {
		super();
		super.setLayout( new GridLayout( 1, 1 ) );
		super.addKeyListener( controller );

		imagepanel = new ImagePanel( model );
		model.addObserver( imagepanel );

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = ge.getDefaultScreenDevice();

		super.add( imagepanel );
	}

	public void showNow() {

		if( isDisplayable() ) {
			setVisible( false );
			dispose();
		}

		setUndecorated( true );
		if( !isVisible() ) {
			setVisible( true );
		}

		device.setFullScreenWindow( this );
	}

	public void hide() {
		device.setFullScreenWindow( null );
	}
}
