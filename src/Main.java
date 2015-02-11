import java.awt.Dimension;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import org.altervista.geheimerschatz.BinaryTree.*;

public class Main {

	public static void main(String[] argv) {
		run();
	}
	
	public static void run() {
		BinaryTree<Integer> bt = new BinaryTree<Integer>(19);
		bt.getRoot().setIsRoot(true);
		bt.getRoot().setLeft(new BinaryNode<Integer>(10));
		BinaryNode<Integer> sottoalberodx = new BinaryNode<Integer>(1);
		sottoalberodx.setLeft(new BinaryNode<Integer>(5));
		bt.getRoot().setRight(sottoalberodx);
		new Visualizzatore("Normale", new Dimension(400,400), new Point(0,0), bt);
		
		BinaryTree<Integer> speculare;
		speculare = bt.mirror();
		new Visualizzatore("Mirror", new Dimension(400,400), new Point(410,0), speculare);
	}
	
}
