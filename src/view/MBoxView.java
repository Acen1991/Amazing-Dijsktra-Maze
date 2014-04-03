package view;

import java.awt.Color;
	import java.awt.Font;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;

	import javax.swing.JPanel;

	import maze.EBox;
	import maze.MBox;
	
public class MBoxView extends JPanel implements MouseListener
{
		private Labyrinthe labyrinthe;
		private MBox mBox;
		private Color color;
		private char charbox;
		private int line;
		private int column;
		
		public MBoxView(int line, int column, Labyrinthe labyrinthe, Color color, char charbox) {
			this.charbox = charbox;
			this.line = line;
			this.column = column;
			this.labyrinthe = labyrinthe;
			this.color = color;
			this.mBox = labyrinthe.getBuilder().createBox(labyrinthe.getMaze(),line, column);
			this.addMouseListener(this);
			this.setBackground(Color.BLACK);
			this.setVisible(true);
			
		}
		
		public MBox getBox() {
			return mBox;
		}
		
		public void setColor(Color color) {
			this.color = color;
		}
		
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(color);
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());
			g2.setColor(Color.white);
			Font font = new Font("Times New Roman", Font.ITALIC, 25);
			g2.setFont(font);
			String str=""+charbox;
			g2.drawString(str, 3*getWidth()/8, 3*getHeight()/4);
			
		}

		public void mouseClicked(MouseEvent e) {
			mBox = labyrinthe.getBuilder().createBox(labyrinthe.getMaze(), line, column);
			color = labyrinthe.getColor();
			charbox = labyrinthe.getChar();
			this.repaint();
			labyrinthe.updateBox(mBox.getLine(), mBox.getColumn());
		}

		
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
}
