import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class O_Panel extends JPanel{
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		super.setBackground(Color.BLACK);
		g.setColor(new Color(0,0,51));
		g.fillRect(10, 10, 130, 130);
		//drawing O
		g.setColor(Color.RED);
		g.fillOval(26, 26, 98, 98);
		g.setColor(new Color(0,0,51));
		g.fillOval(36, 36, 78, 78);
	}
}
