package org.altervista.geheimerschatz.BinaryTree;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Visualizzatore extends JPanel{
	private BinaryTree<Integer> bt;
	private final int dimNodo = 40;
	private final int distanzaLivelli = 25; //distanza tra un livello e un altro
	
	public Visualizzatore(String n, Dimension dim, BinaryTree<Integer> alberobinario) 
	{
		bt = alberobinario;
		if(bt.isEmpty()) {
			System.err.println("Albero vuoto => setto ai valori di default!");
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
		
		this.showAlbero(g, this.bt);
	}
	
	private void showAlbero(Graphics g, BinaryTree<Integer> albero) {
		this.showSottoalbero(g, albero, null, null);
	}
	
	/**
	 * Visualizza l'albero binario.
	 * Att: non richiamare questa funzione direttamente, ma farlo con showAlbero()
	 * @param bt
	 * @param altezza
	 */
	private void showSottoalbero(Graphics g, BinaryTree<Integer> sottoalberodadisegnare, Point coordPadre, Point coordNonno) {
		if(sottoalberodadisegnare.getRoot() == null) {
			//disegno il nodo (nero)
			drawNodo(g, sottoalberodadisegnare.getRoot(), coordPadre);
			return;
		}
		
		if(sottoalberodadisegnare.getRoot().isRoot()) {
			coordPadre = new Point(this.getWidth()/2, 0);
			coordNonno = new Point(0,0);
		}
		
		//disegno il nodo
		drawNodo(g, sottoalberodadisegnare.getRoot(), coordPadre);
		
		
		int xsx = (int) (coordPadre.getX() - Math.abs((coordNonno.getX() - coordPadre.getX()) / 2));
		int ysx = (int) coordPadre.getY() + this.dimNodo + this.distanzaLivelli;
		Point coordNodoSx = new Point(xsx, ysx);
		int xdx = (int) ((coordPadre.getX() - coordNodoSx.getX()) + coordPadre.getX());
		int ydx = (int) coordPadre.getY() + this.dimNodo + this.distanzaLivelli;
		Point coordNodoDx = new Point(xdx, ydx);
		//disegno gli archi di collegamento al figlio sinistro e destro
		g.drawLine((int) coordPadre.getX() + 10, (int) coordPadre.getY() + this.dimNodo, (int) coordNodoSx.getX() + this.dimNodo / 2, (int) coordNodoSx.getY()); //arco col figlio sx
		g.drawLine( (int) coordPadre.getX() + 30, (int) coordPadre.getY() + this.dimNodo, (int) coordNodoDx.getX() + this.dimNodo/2, (int) coordNodoDx.getY()); //arco col figlio dx
		
		//disegno i nodi figli
		this.showSottoalbero(g, sottoalberodadisegnare.getLeft(), coordNodoSx, coordPadre);
		this.showSottoalbero(g, sottoalberodadisegnare.getRight(), coordNodoDx, coordPadre);
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
	private void drawNodo(Graphics g, BinaryNode<Integer> node, Point coordWhereToDraw) {
		if(node == null)
			g.setColor(Color.BLACK);
		else if(node.isRoot())
			g.setColor(Color.CYAN);
		else
			g.setColor(Color.GREEN);
		g.fillOval((int) coordWhereToDraw.getX(), (int) coordWhereToDraw.getY(), dimNodo, dimNodo);
		//aggiungo il value del nodo al centro del cerchio
		g.setColor(Color.black);
		if(node != null)
			g.drawString(Integer.toString(node.getValue()), (int) coordWhereToDraw.getX()+this.dimNodo/4+3, (int) coordWhereToDraw.getY() + this.dimNodo/2+3);
	}
	
}
