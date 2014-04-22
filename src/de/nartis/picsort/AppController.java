package de.nartis.picsort;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.nartis.lang.Lang;
import de.nartis.picsort.gui.FullscreenWindow;
import de.nartis.picsort.gui.Window;

public class AppController implements ActionListener, KeyListener {

	private AppModel model;
	
	private Window mainWindow;
	private FullscreenWindow fsWindow;

	public AppController() {

		model = new AppModel();
		
		Lang.addLanguage( "en", "english" );
		Lang.addLanguage( "de", "german" );
		Lang.setLanguageByOs();
		
		mainWindow = new Window( this, model );
		fsWindow = new FullscreenWindow( this, model );
	}
	
	public AppController(String sourcePath, String destPath) {
		this();

		try {
			if(sourcePath != null) {
				model.setSourcePath( new File(sourcePath) );
			}

			if(destPath != null) {
				model.setDestPath( new File(destPath) );
			}
			
			if(sourcePath != null && destPath != null) {
				
				try {
					model.prepare();
					fsWindow.showNow();
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(null, 
							Lang.get( "error.dirNoImages" ), 
							Lang.get( "error.headline" ), JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased( KeyEvent e ) {
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			fsWindow.hide();
			fsWindow.dispose();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DELETE) {
			// TODO: After delete, imagemanager must updated (queue is wrong for next image)
			// model.deleteImage();
		}

		if(e.getKeyCode() == KeyEvent.VK_D) {
			model.turnImage( AppModel.TURN_RIGHT );
		}
		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			model.turnImage( AppModel.TURN_LEFT );
		}
		
		if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
			model.openEventTypeInput();
			
			String platform = System.getProperty("os.name").toLowerCase();

			if(platform.startsWith("windows")) {
				fsWindow.setExtendedState(JFrame.NORMAL);
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			model.moveImage();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			model.nextImg();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			model.prevImg();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F1) {
			model.switchInfo();
		}
	}

	@Override
	public void actionPerformed( ActionEvent event ) {
		
		if(event.getActionCommand().equals("button.source")) {
			
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
			int returnVal = fc.showOpenDialog( mainWindow );

			if( returnVal == JFileChooser.APPROVE_OPTION ) {
				File file = fc.getSelectedFile();
				model.setSourcePath( file );
				
				try {
					model.prepare();

				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(null, 
							Lang.get( "error.dirNoImages" ), 
							Lang.get( "error.headline" ), JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		if(event.getActionCommand().equals("button.dest")) {
			
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
			int returnVal = fc.showOpenDialog( mainWindow );

			if( returnVal == JFileChooser.APPROVE_OPTION ) {
				File file = fc.getSelectedFile();
				model.setDestPath( file );
			}
			
			fsWindow.showNow();
		}
		
	}
	
	@Override
	public void keyPressed( KeyEvent e ) {}

	@Override
	public void keyTyped( KeyEvent e ) {}
}
