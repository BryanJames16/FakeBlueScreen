/**
	* File: UserInterface.java
	* Description: This file is a part of Fake BSOD project
	* License: GPL-2.0
	* Copyright (C) 2016  Bryan James Ilaga
	* 
	* This program is free software; you can redistribute it and/or
	* modify it under the terms of the GNU General Public License
	* as published by the Free Software Foundation; either version 2
	* of the License, or (at your option) any later version.
	* 
	* This program is distributed in the hope that it will be useful,
	* but WITHOUT ANY WARRANTY; without even the implied warranty of
	* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	* GNU General Public License for more details.
	* 
	* You should have received a copy of the GNU General Public License
	* along with this program; if not, write to the Free Software
	* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

public class 
	UserInterface 
		implements MouseListener, KeyListener, Runnable
{
	
	private boolean 	isFullScreen 	= 	false;
	private boolean 	running 		= 	true;
	private int 		succession 		= 	0;
	private int 		unlockCode[] 	= 	new int[]{ KeyEvent.VK_F4, KeyEvent.VK_F8, KeyEvent.VK_F12 };
	
	JFrame 				frame 			= 	new JFrame();
	JPanel 				pnlData 		= 	new JPanel( new GridLayout( 30, 1 ) );
	
	JLabel 				lblTitle 		= 	new JLabel( "  Blue Screen Error" );
	JLabel 				lblSpace 		= 	new JLabel( "" );
	JLabel 				lblText[] 		= 	new JLabel[27];
	
	String []			driverslist		= 	new String[]{"KERNEL32.DLL", "USER32.DLL", "GDI32.DLL", "COMDLG32.DLL", "CLASSPNP.SYS"};
	String []			errorslist 		= 	new String[]{"  PAGE_FAULT_IN_NONPAGED_AREA", "  DRIVER_IRQL_NOT_FOUND", "  EVENT_DRIVEN_INITIATED_CRASH"};
	
	// Cursor
	BufferedImage 		cursorImg 		= 	new BufferedImage( 16, 16, BufferedImage.TYPE_INT_ARGB );
	Cursor 				blankCursor 	= 	Toolkit.getDefaultToolkit().createCustomCursor( cursorImg, new Point( 0, 0 ), "blank cursor" );
	
	public 
		UserInterface()
	{
		
		for( int count = 0; count < 27; count++ ) {
			lblText[ count ] = new JLabel();
		}
		
		lblText[0].setText("  A problem has been detected and windows has been shut down to prevent damage");
		lblText[1].setText("  to your computer.");
		lblText[2].setText("");
		lblText[3].setText("  The problem seems to be caused by the following file: " + driverslist[(int)(Math.random() * 4)]);
		lblText[4].setText("");
		lblText[5].setText(errorslist[(int)(Math.random() * 2)]);
		lblText[6].setText("");
		lblText[7].setText("  If this is the first time you've seen this stop error screen,");
		lblText[8].setText("  restart your computer. If this screen appears again, follow");
		lblText[9].setText("  these steps:");
		lblText[10].setText("");
		lblText[11].setText("  check to make sure any new hardware or software is properly installed.");
		lblText[12].setText("  If this is a new installation, ask your hardware or software manufacturer");
		lblText[13].setText("  for any windows updates you might need.");
		lblText[14].setText("");
		lblText[15].setText("  If problems continue, disable or remove any newly installed hardware");
		lblText[16].setText("  or software. Disable BIOS memory options such as caching or shadowing.");
		lblText[17].setText("  If you need to use safe mode to remove or disable components, restart");
		lblText[18].setText("  your computer, press F8 to select Advanced Startup Options, and then");
		lblText[19].setText("  select Safe Mode.");
		lblText[20].setText("");
		lblText[21].setText("  Technical Information:");
		lblText[22].setText("");
		lblText[23].setText("  *** STOP 0x00000050 (0xFD3004C2, 0x00000000, 0xFFFFF250, 0x00000000)");
		lblText[24].setText("");
		lblText[25].setText("  Windows is dumping file: ");
		lblText[26].setText("  *** DUMP_ERROR 0xC0000142");
		
		for( int count = 0; count < 27; count++ ) {
			/**
				* Font Sizes for different laptops
				* 1366 x 768 --> 24pt
				* 1280 x 300 --> 24pt
				* 1024 x 768 --> 20pt
				* 800 x 600  --> 16pt
			*/
			lblText[ count ].setForeground( Color.WHITE );
			lblText[ count ].setFont( new Font( "Courier New", Font.PLAIN, 24 ) );
		}
		
		lblTitle.setForeground( Color.WHITE );
		lblTitle.setFont( new Font( "Consolas", Font.PLAIN, 24 ) );
		
	}
	
	
	public void 
		launchFrame()
	{
		
		pnlData.setBackground( Color.BLUE );
		//pnlData.addMouseListener( this );
		
		frame.add( pnlData );
		frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent we) {
					
				}
			});
		frame.addKeyListener( this );
		
		frame.setBackground( Color.BLUE );
		frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		frame.setLocationRelativeTo( null );
		frame.setUndecorated( true );
		frame.setResizable( false );
		frame.setCursor( blankCursor );
		frame.setSize( 100, 100 );
		frame.setAlwaysOnTop( true );
		frame.setVisible( true );
		
		
		frame.setExtendedState( JFrame.MAXIMIZED_BOTH );
		pnlData.add( lblSpace );
		for( int count = 0; count < 27; count++ ) {
			pnlData.add( lblText[ count ] );
		}
		
		/*for( int count = 0; count < 15; count++ ) {
			Toolkit.getDefaultToolkit().beep();
		}*/
		
		new Thread(this, "Disable Alt+Tab").start();
		
	}
	
	/**
		* Key Listeners
	*/
	@Override
	public void 
		keyPressed( KeyEvent ke ) 
	{
		try {
			if( ke.getKeyCode() == unlockCode[ succession ] ) {
				succession++;
				
				if (succession == 3) {
					System.exit(0);
				}
			} else {
				succession = 0;
			}
		} catch( ArrayIndexOutOfBoundsException aiobe ) {
			succession = 0;
		}
	}
	
	@Override
	public void 
		keyReleased( KeyEvent ke ) 
	{
		
	}
	
	@Override
	public void 
		keyTyped( KeyEvent ke ) 
	{
		
	}
	
	/**
		* Mouse Listeners
	*/
	
	@Override
	public void
		mouseClicked( MouseEvent me )
	{
		if( me.getSource() == pnlData ) {
			if( isFullScreen ) {
				frame.setExtendedState( JFrame.MAXIMIZED_BOTH );
				pnlData.add( lblSpace );
				for( int count = 0; count < 27; count++ ) {
					pnlData.add( lblText[ count ] );
				}
				isFullScreen = false;
			} else {
				frame.setExtendedState( JFrame.NORMAL );
				pnlData.removeAll();
				pnlData.revalidate();
				pnlData.repaint();
				isFullScreen = true;
			}
		}
	}
	
	@Override
	public void 
		mouseEntered( MouseEvent me )
	{
		
	}
	
	@Override
	public void 
		mouseExited( MouseEvent me )
	{
		
	}
	
	@Override
	public void 
		mousePressed( MouseEvent me )
	{
		
	}
	
	@Override
	public void 
		mouseReleased( MouseEvent me )
	{
		
	}
	
	@Override
	public void 
		run()
	{
		try {
			Robot	robot	=	new Robot();
			while( running ) {
				robot.keyRelease( KeyEvent.VK_ALT );
				robot.keyRelease( KeyEvent.VK_TAB );
				frame.requestFocus();
				Thread.sleep(10);
			}
		} catch( Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	public void 
		stop()
	{
		running = false;
	}
	
}