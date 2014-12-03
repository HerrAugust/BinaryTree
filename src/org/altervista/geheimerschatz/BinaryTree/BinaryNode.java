package org.altervista.geheimerschatz.BinaryTree;

/* BinaryNode<E> class
*
******************PUBLIC OPERATIONS*********************
*
*  BinaryNode(E data)              --> Costruisce un nodo foglia/radice
*  BinaryNode(E data, BinaryNode<E> left, BinaryNode<E> right) --> Costruisce un nodo interno
*  public E getValue()             --> restituisce il valore contenuto nel nodo 
*  public BinaryNode<E> getLeft()  -->  restituisce il figlio sinistro
*  public BinaryNode<E> getRight() -->  restituisce il figlio destro
*  public void setValue(E newValue)-->  assegna un valore al nodo
*  public void setLeft(BinaryNode<E> newLeft)   --> assegna il figlio sinistro
*  public void setRight(BinaryNode<E> newRight) --> assegna il figlio destro
*  public boolean hasLeft()  --> verifica se il nodo ha il figlio sinistro
*  public boolean hasRight() --> verifica se il nodo ha il figlio destro
*  public boolean isInner()  --> verifica se il nodo e' interno
*  public boolean isLeaf()   --> verifica se il nodo e' una foglia
*  public boolean equals(Object other)   --> verifica se due nodi sono uguali
*/


public class BinaryNode<E> {
	protected BinaryNode<E> left, right;	// children; can be null
	protected E data;
	protected boolean isRoot = false; //necessaria a Visualizzatore

	/** Constructs a node where left and right are null **/
	public BinaryNode(E data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}


	/** Constructs inner node **/
	public BinaryNode(E data, BinaryNode<E> left, BinaryNode<E> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	
	public boolean isRoot() {
		return this.isRoot;
	}

	/**
	 * @return its data value 
	 */
	public E getValue() {
		return data;
	}


	/**
	 * @return its left child 
	 */
	public BinaryNode<E> getLeft() {
		return left;
	}


	/**
	 * @return its right child 
	 */
	public BinaryNode<E> getRight() {
		return right;
	}


	/**
	 * Sets its data value 
	 * @param newValue - the new data value
	 */
	public void setValue(E newValue) {
		data = newValue;
	}


	/**
	 * Sets its left child to be newLeft 
	 * @param newLeft - the new left child
	 */
	public void setLeft(BinaryNode<E> newLeft) {
		left = newLeft;
	}


	/**
	 * Sets its right child to be newRight 
	 * @param newRight - the new right child
	 */
	public void setRight(BinaryNode<E> newRight) {
		right = newRight;
	}



	/** Does it have a left child? **/
	public boolean hasLeft() {
		return left != null;
	}


	/** Does it have a right child? **/
	public boolean hasRight() {
		return right != null;
	}


	/** Is it an inner node? **/
	public boolean isInner() {
		return left != null || right != null;
	}


	/** Is it a leaf node? **/
	public boolean isLeaf() {
		return left == null && right == null;
	}


	/**
	 * Returns a string representation of the tree
	 */
	public String toString() {
		return this.getValue().toString();
	}



	public boolean equals(Object other) {
		if(!(other instanceof BinaryNode<?>))
			return false;
		BinaryNode<E> t2 = (BinaryNode<E>) other;
		if (hasLeft() != t2.hasLeft() || hasRight() != t2.hasRight()) return false;
		if (!data.equals(t2.data)) return false;
		if (hasLeft() && !left.equals(t2.left)) return false;
		if (hasRight() && !right.equals(t2.right)) return false;
		return true;
	}
		
}
