import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class X_Panel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			super.setBackground(Color.GREEN);
			g.setColor(new Color(0,0,51));
			g.fillRect(10, 10, 131, 131);
			g.setColor(Color.RED);
			//drawing X
			for (int i=0; i<10; i++){
				g.drawLine(40-i, 30, 120, 110+i);
				g.drawLine(30, 30+i, 120-i, 120);
				g.drawLine(40-i, 120, 120, 40-i);
				g.drawLine(120-i, 30, 30, 120-i);
			}
		}
}
