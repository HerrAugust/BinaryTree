package org.altervista.geheimerschatz.BinaryTree;

import java.util.*;

import org.altervista.geheimerschatz.Queue_Stack.Queue;

/**
 * Generic BinaryTree built on BinaryNode instances
 */

/* BinaryTree<E> class
*
******************PUBLIC OPERATIONS*********************
*
*  BinaryTree()		--> Build an empty binary tree
*  BinaryTree(E data)	--> build a binary tree that contains the only root with data
*  BinaryTree(E data, BinaryTree<E> ltree, BinaryTree<E> rtree)
*   --> build a binary tree whose root contains data. A left and a right trees are assigned to the root
*  boolean isEmpty( )	--> Verify if tree is empty
*  clear( )		--> Get the tree empty
*  E getValue()		--> return the value in the root
*  BinaryTree<E> getLeft( )  --> return left subtree
*  BinaryTree<E> getRight( ) --> return right subtree
*  BinaryNode<E> getRoot( )  --> return root of this binary tree
*  int size()    --> return the number of nodes (internal or leaf) of the binary tree
*  int height()  --> return the height of the binary tree
*  boolean equals(Object other)  --> verify if the two trees have both the same structure and values
*  ArrayList<E> fringe()  --> return the value of leaf, from left to right
*  preorder(List<E> dataList)  --> create a lista that contains the values of the tree in pre-order
*  inorder(List<E> dataList)   --> create a lista that contains the values of the tree in symmetric-order
*  postorder(List<E> dataList) --> create a lista that contains the values of the tree in post-order
*  String toString() --> return a string that describes the binary tree
*/


public class BinaryTree<E> {
	private BinaryNode<E> root;
	// aggiunta di altri eventuali campi...

	public BinaryTree<E> mirror() {
		BinaryTree<E> speculare = new BinaryTree<E>(this.root.getValue());
		speculare.getRoot().setIsRoot(true);
		if(this.root.hasRight())
			speculare.root.setLeft(this.root.getRight().mirror());
		if(this.root.hasLeft())
			speculare.root.setRight(this.root.getLeft().mirror());
		return speculare;
	}
	
        /**
         * @return un albero binario (vuoto) 
         */
        public BinaryTree( )
        {
            root =  null;
        }

        public BinaryTree(E data)
        {
            root =  new BinaryNode<E>(data);
        }


        public BinaryTree(E data, BinaryTree<E> ltree, BinaryTree<E> rtree)
        {
            root =  new BinaryNode<E>(data, ltree.root, rtree.root);
        }

        public static BinaryTree<Integer> generaCasuale(int h) {
        	final int maxvalue = 20;
        	
        	BinaryTree<Integer> casuale = new BinaryTree<Integer>(new Integer(0));
        	casuale.getRoot().setIsRoot(true); //necessario a Visualizzatore
        	
        	MyRandom rand = new MyRandom(maxvalue);
        	Queue<BinaryNode<Integer>> queue = new Queue<BinaryNode<Integer>>();
        	queue.enqueue(casuale.getRoot());
        	
        	for(int i = 0; i < h && !queue.isEmpty(); i++) {
        		@SuppressWarnings("unchecked")
				BinaryNode<Integer> corrente = (BinaryNode<Integer>) queue.dequeue();
        		if(corrente == null)
        			continue;
        		corrente.setValue(rand.nextInt());
        		if(new Random().nextBoolean()) {
        			corrente.setLeft(new BinaryNode<Integer>(rand.nextInt()));
        			queue.enqueue(corrente.getLeft());
        		}
        		if(new Random().nextBoolean()) {
        			corrente.setRight(new BinaryNode<Integer>(rand.nextInt()));
        			queue.enqueue(corrente.getLeft());
        		}
        		
        	}
        	
        	return casuale;
        }

        /**
         * Test se l'albero binario è vuoto.
         * @return true se vuoto, false altrimenti.
         */
        public boolean isEmpty( )
        {
            return root == null;
        }


        /**
         * Svuota l'albero binario.
         */

        public void clear( )
        {
            root = null;
        }


	/**
	 * @return il valore contenuto in radice
	 */
	public E getValue() {
		return root.data;
	}


        /**
         * @return l'albero sinistro.
         */
        public BinaryTree<E> getLeft( )
        {
	   BinaryTree<E> left = new BinaryTree<E>();
	   left.root=root.getLeft();
	   return left;
        }


        /**
         * @return l'albero destro.
         */
        public BinaryTree<E> getRight( )
        {
	   BinaryTree<E> right = new BinaryTree<E>();
	   right.root=root.getRight();
	   return right;
        }


        /**
         * @return la radice dell'albero binario.
         */
        public BinaryNode<E> getRoot( )
        {
            return root;
        }


	/**
	 * @return il numero dei nodi (interni e foglie) dell'albero binario.
	 */
	public int size() {
		if (isEmpty()) return 0;
	        BinaryTree<E> left=getLeft();
		BinaryTree<E> right=getRight();
		return 1 + left.size() + right.size();
	}


	/**
	 * @return l'altezza dell'albero binario.
	 */
	public int height() {
		if (isEmpty()) return -1;
	        BinaryTree<E> left=getLeft();
		BinaryTree<E> right=getRight();
		return 1 + Math.max(left.height(),right.height()); 
	}
	

	/**
	 * @return true se gli alberi hanno la stessa struttura e gli stessi valori, false altrimenti
	 */

	/* public boolean equals(Object other) {
		if(!(other instanceof BinaryTree<?>))
			return false;
		BinaryTree<E> t2 = (BinaryTree<E>) other;
		if (this.isEmpty() && t2.isEmpty()) return true;
		if (this.isEmpty() != t2.isEmpty()) return false;
		return ((this.getValue()).equals(t2.getValue()) && 
			(this.getLeft()).equals(t2.getLeft()) &&
			(this.getRight()).equals(t2.getRight()));
	} */

	public boolean equals(Object other) {
		if(!(other instanceof BinaryTree<?>))
			return false;
		BinaryTree<?> t2 = (BinaryTree<?>) other;
		if (isEmpty() && t2.isEmpty()) return true;
		if (this.isEmpty() != t2.isEmpty()) return false;
		return (this.getRoot()).equals(t2.getRoot());
	}


/* NB
*  tipo WILDCARD: BinaryTree<?>
*  "binary tree of unknown", cioè fattorizza tutti i BinaryTree<E> senza distinzione 
*/

	/**
	 * @return le foglie, in ordine da sinistra verso destra
	 */
	public ArrayList<E> fringe() {    
		ArrayList<E> f = new ArrayList<E>();
		addToFringe(f);
		return f;
	}

	/**
	 * Helper for fringe, adding fringe data to the list
	 */
	private void addToFringe(ArrayList<E> fringe) {
		if (isEmpty()) return;
	        BinaryTree<E> left=getLeft();
		BinaryTree<E> right=getRight();
		if (left.isEmpty() && right.isEmpty()) {  //foglia
			fringe.add(getValue());
		}
		else {
			left.addToFringe(fringe);
			right.addToFringe(fringe);
		}
	}


	public LinkedList<String> levelOrder() {
		LinkedList<String> dataList = new LinkedList<String>();
		
		Queue <BinaryNode<E>> queue = new Queue<BinaryNode<E>>();
		queue.enqueue(this.root);
		while(queue.isEmpty() == false) {
			BinaryNode<E> node = (BinaryNode<E>) queue.dequeue();
			if(node == null) {
				dataList.add("nil");
				continue;
			}
			dataList.add(node.getValue().toString());
			queue.enqueue(node.getLeft());
			queue.enqueue(node.getRight());
		}
		
		return dataList;
	}

	public void preorder(List<E> dataList) {
		if (isEmpty()) return;
	        BinaryTree<E> left=getLeft();
		BinaryTree<E> right=getRight();
		dataList.add(root.data);
		left.preorder(dataList);	// recurse on left tree
		right.preorder(dataList);	// recurse on right tree
	}



	public void inorder(List<E> dataList) {
		if (isEmpty()) return;
	        BinaryTree<E> left=getLeft();
		BinaryTree<E> right=getRight();
		left.inorder(dataList);	// recurse on left tree
		dataList.add(root.data);
		right.inorder(dataList);	// recurse on right tree
	}


	public void postorder(List<E> dataList) {
		if (isEmpty()) return;
	        BinaryTree<E> left=getLeft();
		BinaryTree<E> right=getRight();
		left.postorder(dataList);	// recurse on left tree
		right.postorder(dataList);	// recurse on right tree
		dataList.add(root.data);
	}

	
	public String toString() {
		String r = root.toString() + "\n";
		
		if(root.hasLeft()) r += getLeft().toString();
		else r+= " nil ";
		if(root.hasRight()) r += getRight().toString();
		else r+= " nil ";
		return r;
	}


//	/**	
//	 * Testing program
//	 */
//	public static void main(String [] args) {	
//
//		// Create a tree by building it up
//
//		//albero 1
//		BinaryTree<String> leftChild = new BinaryTree<String>("orso",
//				new BinaryTree<String>("formica"), new BinaryTree<String>("gatto"));
//		BinaryTree<String> tree= new BinaryTree<String>("cavallo", leftChild,
//				new BinaryTree<String>("cane"));
//		//albero 2
//		leftChild = new BinaryTree<String>("orso",
//				new BinaryTree<String>("formica"), new BinaryTree<String>("gatto"));
//		BinaryTree<String> tree2= new BinaryTree<String>("cavallo", leftChild,
//				new BinaryTree<String>("cane"));
//
//
//		// Some tests of methods
//		System.out.println("tree: \n" + tree); 
//		System.out.println("Size of tree = " + tree.size());
//		System.out.println("Height of tree = " + tree.height());
//		System.out.println("Fringe of tree =" + tree.fringe());
//
//		// Build a tree from traversals
//		List<String> preList = new LinkedList<String>();
//		tree.preorder(preList);
//		System.out.println("Preorder traversal of the tree =" + preList);
//
//		List<String> inList = new LinkedList<String>();
//		tree.inorder(inList);
//		System.out.println("Inorder traversal of the tree =" + inList);
//
//		List<String> postList = new LinkedList<String>();
//		tree.postorder(postList);
//		System.out.println("Postorder traversal of the tree =" + postList);
//
//		System.out.println("\n\n\ntree and tree are equal? ");
//		System.out.println(tree.equals(tree2));
//
//	}		
}
