package de.nartis.picsort.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import de.nartis.picsort.AppModel;
import de.nartis.picsort.ImageModel;

public class ImagePanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;

	private AppModel model;
	private ImageModel imgModel;

	private Font font;
	
	public ImagePanel( AppModel model ) {
		this.model = model;
		font = new Font( "impact", Font.PLAIN, 12 );
	}

	@Override
	public void paint( Graphics g ) {
		super.paint( g );

		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont( font );
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		g.setColor( Color.BLACK );
		g.fillRect( 0, 0, getWidth(), getHeight() );

		if( imgModel != null ) {
			BufferedImage img = imgModel.getImg();
			
			g.drawImage( img, 
					this.getWidth()/2 - img.getWidth()/2,
					this.getHeight()/2 - img.getHeight()/2, null );
		
			if( model.isInfoVisible() ) {
				int y = 40;
				drawString( "Info:", 10, y, g2d );
				
				y += 20;
				drawString( "  F1     - show/hide this info", 10, y, g2d );
				
				y += 20;
				drawString( "  ESC    - close fullscreen window", 10, y, g2d );
				
				y += 20;
				drawString( "  DEL    - delete image", 10, y, g2d );

				y += 20;
				drawString( "  Left   - prevoius image", 10, y, g2d );
				
				y += 20;
				drawString( "  Right  - next image", 10, y, g2d );
				
				y += 20;
				drawString( "  A      - turn image to left", 10, y, g2d );
				
				y += 20;
				drawString( "  D      - turn image to right", 10, y, g2d );
				
				y += 20;
				drawString( "  CTRL   - new event type", 10, y, g2d );
				
				y += 20;
				drawString( "  SPACE  - COPIES the image in current event", 10, y, g2d );
				
				y += 40;
				drawString( imgModel.getFilename(), 10, y, g2d );
			}
			
			if( model.getEventType() != null ) {
				drawString( model.getEventType(), this.getWidth()/2 , 40, g2d );
			}
		}

	}
	
	private void drawString( String s, int x, int y, Graphics2D g2d ) {
		g2d.setColor( Color.BLACK );
		g2d.drawString( s, x+1, y+1);

		g2d.setColor( Color.WHITE );
		g2d.drawString( s, x, y);
	}

	@Override
	public void update( Observable arg0, Object arg ) {
		imgModel = model.getCurrent();
		repaint();
	}

}
