import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import java.awt.Toolkit;

public class SwingFrame {

	private JFrame frmInA;
	public int recX = 0;
	public int recY = 0;
	public boolean state = true; // megmutatja, hogy éppen az X, vagy az O
									// játékos jön. (x-true, o-false)
	public Table table = new Table();
	public boolean game = true;

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
	public void drawGreen(Graphics g) {
		for (int i = 0; i < 10; i++) {
			g.setColor(Color.GREEN);
			g.drawRect(i, i, 150 - 2 * i, 150 - 2 * i);
		}
	}

	public void drawBlack(Graphics g) {
		for (int i = 0; i < 10; i++) {
			g.setColor(Color.BLACK);
			g.drawRect(i, i, 150 - 2 * i, 150 - 2 * i);
		}
	}

	private void initialize() {
		frmInA = new JFrame();
		frmInA.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\onba\\Downloads\\am\u0151ba.png"));
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
				if (game) {
					int x = mouse.getX();
					int y = mouse.getY();
					// figyelembe kell venni azt az esetet is, amikor az egérrel
					// nem a mezõk fölött kattintunk
					if (x < 960 && y < 960 && table.getField((x - x % 30) / 30, (y - y % 30) / 30).getState() == 0) {
						Graphics gx = x_panel.getGraphics();
						Graphics go = o_panel.getGraphics();
						Graphics g = table_panel.getGraphics();
						if (state == true) {
							state = false;
							drawBlack(gx);
							drawGreen(go);
							table.takeX((x - x % 30) / 30, (y - y % 30) / 30);
							g.setColor(new Color(5, 5, 5));
							g.fillRect(x - x % 30 + 1, y - y % 30 + 1, 29, 29);
							g.setColor(Color.RED);
							for (int a = 0; a < 5; a++) {
								g.drawLine(x - x % 30 + 10 - a, y - y % 30 + 5, x - x % 30 + 25, y - y % 30 + 20 + a);
								g.drawLine(x - x % 30 + 5, y - y % 30 + 5 + a, x - x % 30 + 25 - a, y - y % 30 + 25);
								g.drawLine(x - x % 30 + 10 - a, y - y % 30 + 25, x - x % 30 + 25, y - y % 30 + 10 - a);
								g.drawLine(x - x % 30 + 25 - a, y - y % 30 + 5, x - x % 30 + 5, y - y % 30 + 25 - a);
							}
						} else {
							drawBlack(go);
							drawGreen(gx);
							table.takeO((x - x % 30) / 30, (y - y % 30) / 30);
							g.setColor(new Color(5, 5, 5));
							g.fillRect(x - x % 30 + 1, y - y % 30 + 1, 29, 29);
							for (int a = 0; a < 5; a++) {
								g.setColor(Color.BLUE);
								g.fillOval(x - x % 30 + 3, y - y % 30 + 3, 24, 24);
								g.setColor(new Color(5, 5, 5));
								g.fillOval(x - x % 30 + 9, y - y % 30 + 9, 12, 12);
							}
							state = true;
						}
						ArrayList<Field> WinnerFields = table.isThere5();
						if (WinnerFields != null) {
							g.setColor(Color.WHITE);
							g.drawLine(WinnerFields.get(0).getX() * 30 + 15, WinnerFields.get(0).getY() * 30 + 15,
									WinnerFields.get(4).getX() * 30 + 15, WinnerFields.get(4).getY() * 30 + 15);
								for (int i = 0; i < 32; i++) {
									for (int j = 0; j < 32; j++) {
										if (table.getField(i, j).getState() == 0) {
											int lx = table.getField(i, j).getX();
											int ly = table.getField(i, j).getY();
											if (WinnerFields.get(0).getState() == 1) {
											for (int a = 0; a < 5; a++) {
												g.drawLine(lx*30 + 10 - a, ly*30 + 5, lx*30 + 25, ly*30 + 20 + a);
												g.drawLine(lx*30 + 5, ly*30 + 5 + a, lx*30 + 25 - a, ly*30 + 25);
												g.drawLine(lx*30 + 10 - a, ly*30 + 25, lx*30 + 25, ly*30 + 10 - a);
												g.drawLine(lx*30 + 25 - a, ly*30 + 5, lx*30 + 5, ly*30 + 25 - a);
											}
										}
											else{
												for (int a = 0; a < 5; a++) {
													g.setColor(Color.WHITE);
													g.fillOval(lx*30 + 3, ly*30 + 3, 24, 24);
													g.setColor(Color.BLACK);
													g.fillOval(lx*30 + 9, ly*30 + 9, 12, 12);
												}
											}
									}
								}
							}
							game = false;
						}
					}
				}
			}
		});

		table_panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent mouse) {
				if (game) {
					// First we have to analyse in which rectangle is the
					// cursor.
					Graphics g = table_panel.getGraphics();

					int x = mouse.getX();
					int y = mouse.getY();
					/*
					 * Amennyiben kilépünk a kurzorunkkal egy mezõbõl, akkor az
					 * elõzõ (tárolt) mezõt feketére színezzük, az újat pedig
					 * narancsra. A széleken szükség van korrekcióra
					 */
					if ((x > recX + 30 || x < recX || y > recY + 30 || y < recY)
							&& table.getField((x - x % 30) / 30, (y - y % 30) / 30).getState() == 0) {
						if (recX < 960 && recY < 960
								&& table.getField((recX - recX % 30) / 30, (recY - recY % 30) / 30).getState() == 0) {
							g.setColor(Color.BLACK);
							g.fillRect(recX + 1, recY + 1, 29, 29);
						}
						recX = x - x % 30;
						recY = y - y % 30;
						if (x < 960 && y < 960) {
							g.setColor(Color.ORANGE);
							g.fillRect(recX + 1, recY + 1, 29, 29);
						}
					}
				}
			}
		});
	}
}
