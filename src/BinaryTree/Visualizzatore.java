package BinaryTree;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Visualizzatore extends JPanel{
	
	private BinaryTree<Integer> bt;
	private int altezzaAlbero;
	private final int dimNodo = 40;
	private final float constDimezzAngolo = 2.0f;
	private final int lunghezzaArchi = 20;
	private final int angoloArchi = 45;
	
	public Visualizzatore(String n, Dimension dim, BinaryTree<Integer> alberobinario, int altezzaAlbero) 
	{
		bt = alberobinario;
		this.altezzaAlbero = altezzaAlbero;
		if(altezzaAlbero < 0 || bt.isEmpty()) {
			System.err.println("Altezza albero < 0 o albero vuoto => setto ai valori di default!");
			altezzaAlbero = 1;
			bt = new BinaryTree<Integer>(1);
		}
		
		JFrame frame = new JFrame(n);
		frame.setSize((int) dim.getWidth(), (int) dim.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setBackground(Color.LIGHT_GRAY);
		Container pane = frame.getContentPane();
		pane.add(this);
		
		frame.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		this.showAlbero(g, this.bt,0,false);
	}
	
	/**
	 * Visualizza l'albero binario.
	 * ALG: quando arrivo a una foglia, la disegno; se invece non sono in una
	 * foglia, procedo verso il basso
	 * @param bt
	 * @param altezza
	 */
	private void showAlbero(Graphics g, BinaryTree<Integer> sottoalberodadisegnare, int lvdisegnati, boolean isleftnode) {
		Point coordPadre = drawNodo(g, sottoalberodadisegnare.getRoot(), this.altezzaAlbero-lvdisegnati, isleftnode);
		
		//disegno gli archi di collegamento al figlio sinistro e destro
		double alpha = (90-this.angoloArchi)/this.constDimezzAngolo*lvdisegnati;
		g.drawLine(coordPadre.x, coordPadre.y + dimNodo, (int) (coordPadre.x + this.lunghezzaArchi*Math.cos(alpha) ), (int) (coordPadre.y + this.lunghezzaArchi*Math.sin(alpha) ));
		
		lvdisegnati--;
		showAlbero(g, sottoalberodadisegnare.getLeft(), altezzaAlbero-lvdisegnati, true);
		showAlbero(g, sottoalberodadisegnare.getRight(), altezzaAlbero-lvdisegnati, false);
	}
	
	/**
	 * Disegna un nodo inserendolo nel container di [frame].
	 * Attenzione: i nodi vengono disegnati in levelorder! 
	 * @param node nodo da disegnare
	 * @param altezza del nodo da disegnare
	 * @param isleftnode questo nodo è un figlio sinistro?
	 * @param g il Graphic dove disegnare (ottenuto da paintComponent())
	 * @return le coordinate in alto a sinistra del nodo disegnato
	 */
	private Point drawNodo(Graphics g, BinaryNode<Integer> node, int altezza, boolean isleftnode) {
		int x, y;
		
		if(node.isRoot()) //disegno al centro: x = dim.width/2
		{
			g.setColor(Color.CYAN);
			x = this.getWidth() / 2 - 20;
			y = 0;
			g.fillOval(x, y, dimNodo, dimNodo);
			//aggiungo il value del nodo al centro del cerchio
			g.setColor(Color.black);
			g.drawString(Integer.toString(node.getValue()), x+13, 23);
			//restituisco le coordinate del nodo (att: del punto in alto a sx!)
			return new Point(x,y);
		}
		/*else if(leftNodeDisegnato) //devo inserire il nodo a sinistra
		{
			//prendo le coordinate del nodo dx
			if(bt == null)
				//disegno due linee
			else
				//vedi sopra
		}
		else //devo inserire il nodo a destra
		{
			if(bt == null)
		}*/
		return null;
	}
	
}
