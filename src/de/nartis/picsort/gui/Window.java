package de.nartis.picsort.gui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.nartis.lang.Lang;
import de.nartis.picsort.AppController;
import de.nartis.picsort.AppModel;
import de.nartis.utils.Conf;
import de.nartis.utils.NativeLook;

public class Window extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JButton source, dest;

	private AppController controller;
	
	public Window( AppController controller, AppModel model ) {
		super();
		
		this.controller = controller;
		
		NativeLook.setNativeUI();
		
		super.setTitle( "PicSort - " + Conf.get( "app.version" ) );
		super.setSize( 280, 300 );

		initComponents();
		
		super.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		super.setVisible( true );
	}
	
	private void initComponents() {
		source = new JButton( "(1) " + Lang.get( "button.source" ) );
		source.setActionCommand( "button.source" );
		source.addActionListener( controller );
		
		dest = new JButton( "(2) " + Lang.get( "button.dest" ) );
		dest.setActionCommand( "button.dest" );
		dest.addActionListener( controller );
		
		JLabel logo = new JLabel(new ImageIcon( ClassLoader.getSystemResource( "Logo.png" ) ));
		logo.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent e ) {

				Lang.openChooser( Window.this, new String[]{"english", "german"} );
			}
		} );
		
		super.setLayout( new GridLayout( 3, 1 ) );
		super.add( logo );
		super.add( source );
		super.add( dest );
		
	}
}
