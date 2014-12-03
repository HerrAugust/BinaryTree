import BinaryTree.*;

public class Main {

	public static void main(String[] argv) {
		run();
	}
	
	public static void run() {
		BinaryTree<Integer> bt;
		bt = BinaryTree.generaCasuale(2);
		
		new Visualizzatore("prova", new java.awt.Dimension(400,400), bt, 2);
	}
	
}
