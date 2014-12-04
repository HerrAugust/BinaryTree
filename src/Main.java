import java.util.LinkedList;
import java.util.List;

import org.altervista.geheimerschatz.BinaryTree.*;

public class Main {

	public static void main(String[] argv) {
		run();
	}
	
	public static void run() {
		BinaryTree<Integer> bt;
		bt = BinaryTree.generaCasuale(2);
		LinkedList<String> lvorder = bt.levelOrder();
		System.out.println(lvorder.toString());
		
		new Visualizzatore("prova", new java.awt.Dimension(400,400), bt);
	}
	
}
