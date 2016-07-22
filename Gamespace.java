import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Gamespace extends JPanel{

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		super.setBackground(Color.BLACK);
		
		g.setColor(Color.YELLOW);
		for(int i=0; i< 32; i++){
			g.drawLine(i*30, 0, i*30, 960);
			g.drawLine(0,i*30, 960,i*30);
		}
		g.fillRect(960, 0, 8, 968);
		g.fillRect(0, 960, 968, 8);
	}
}
