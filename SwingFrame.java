import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;

public class SwingFrame {

	private JFrame frmInA;
	public int recX=0;
	public int recY=0;
	public boolean state=true;	//megmutatja, hogy éppen az X, vagy az O játékos jön. (x-true, o-false)
	public Table table = new Table();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingFrame window = new SwingFrame();
					window.frmInA.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void drawGreen(Graphics g){
		for (int i=0;i<10;i++){
			g.setColor(Color.GREEN);
			g.drawRect(i, i, 150-2*i, 150-2*i);
		}
	}
	public void drawBlack(Graphics g){
			for (int i=0;i<10;i++){
				g.setColor(Color.BLACK);
				g.drawRect(i, i, 150-2*i, 150-2*i);
			}
	}
	private void initialize() {
		frmInA = new JFrame();
		frmInA.setTitle("5 in a Row");
		frmInA.setBounds(0, 0, 1920, 1080);
		frmInA.setSize(1200, 1080);
		frmInA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frmInA.getContentPane().setBackground(Color.DARK_GRAY);
		JMenuBar menuBar = new JMenuBar();
		frmInA.setJMenuBar(menuBar);
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		frmInA.getContentPane().setLayout(null);
		
		JPanel table_panel = new Gamespace();
		table_panel.setBounds(0, 0, 968, 968);
		frmInA.getContentPane().add(table_panel);
		
		JPanel x_panel = new X_Panel();
		x_panel.setBounds(1009, 250, 150, 150);
		frmInA.getContentPane().add(x_panel);
		
		JPanel o_panel = new O_Panel();
		o_panel.setBounds(1009, 568, 150, 150);
		frmInA.getContentPane().add(o_panel);
		
		table_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mouse) {
				int x = mouse.getX();
				int y = mouse.getY();
				//figyelembe kell venni azt az esetet is, amikor az egérrel nem a mezõk fölött kattintunk
				if (x<960 && y<960 && table.getField((x-x%30)/30,(y-y%30)/30)==0){
					Graphics gx = x_panel.getGraphics();
					Graphics go = o_panel.getGraphics();
					Graphics g = table_panel.getGraphics();
					if(state == true){
						state = false;
						drawBlack(gx);
						drawGreen(go);
						table.takeX((x-x%30)/30, (y-y%30)/30);
						g.setColor(new Color(5,5,5));
						g.fillRect(x-x%30+1, y-y%30+1, 29, 29);
						g.setColor(Color.RED);
						for (int a=0;a<4;a++){
							g.drawLine(x-x%30, y-y%30, x-x%30+30, y-y%30+30);
						}
					} 
					else{
						drawBlack(go);
						drawGreen(gx);
						table.takeO((x-x%30)/30, (y-y%30)/30);
						g.setColor(new Color(5,5,5));
						g.fillRect(x-x%30+1, y-y%30+1, 29, 29);
						g.setColor(Color.RED);
						for (int a=0;a<4;a++){
							g.drawLine(x-x%30+30, y-y%30, x-x%30, y-y%30+30);
						}
						state = true;
					}
					
				}
			}
		});

		table_panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent mouse) {
				//First we have to analyse in which rectangle is the cursor.
				Graphics g=table_panel.getGraphics();

				int x=mouse.getX();
				int y=mouse.getY();
				/*Amennyiben kilépünk a kurzorunkkal egy mezõbõl, akkor az elõzõ (tárolt) mezõt feketére színezzük, az újat pedig narancsra.
				 * A széleken szükség van korrekcióra
				 */
				if ((x>recX+30 || x<recX || y>recY+30 || y<recY) && table.getField((x-x%30)/30, (y-y%30)/30)==0){
					if (recX<960 && recY<960 && table.getField((recX-recX%30)/30, (recY-recY%30)/30)==0){
						g.setColor(Color.BLACK);
						g.fillRect(recX+1, recY+1, 29, 29);
					}
					recX = x-x%30;
					recY = y-y%30;
					if(x<960 && y<960){
						g.setColor(Color.ORANGE);
						g.fillRect(recX+1, recY+1, 29, 29);
					}
				}
			}
		});
	}
}
