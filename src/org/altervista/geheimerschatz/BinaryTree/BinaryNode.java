package org.altervista.geheimerschatz.BinaryTree;

/* BinaryNode<E> class
*
******************PUBLIC OPERATIONS*********************
*
*  BinaryNode(E data)              --> Build a root/leaf node
*  BinaryNode(E data, BinaryNode<E> left, BinaryNode<E> right) --> Build an internal node
*  public E getValue()             --> returns the number in the node
*  public BinaryNode<E> getLeft()  -->  returns left child
*  public BinaryNode<E> getRight() -->  return right child
*  public void setValue(E newValue)-->  assign a value to the node
*  public void setLeft(BinaryNode<E> newLeft)   --> assign left child
*  public void setRight(BinaryNode<E> newRight) --> assign right child
*  public boolean hasLeft()  --> verify if node has a left child
*  public boolean hasRight() --> >verify if node has a right child
*  public boolean isInner()  --> verify if node is internal
*  public boolean isLeaf()   --> verify if node is a leaf
*  public boolean equals(Object other)   --> verify if two nodes are equal
*/


public class BinaryNode<E> {
	protected BinaryNode<E> left, right;	// children; can be null
	protected E data;
	protected boolean isRoot = false; //necessaria a class Visualizzatore

	public BinaryNode<E> mirror() {
		BinaryNode<E> speculare = new BinaryNode<E>(this.getValue());
		if(this.left != null) //se ho un sottoalbero sinistro faccio lo speculare di quello e lo restituisco con padre me
			speculare.setRight(this.left.mirror());
		if(this.right != null)
			speculare.setLeft(this.right.mirror());
		return speculare;
	}
	
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
	
	/**
	 * Necessario a class Visualizzatore: dice che la root del presente albero è una root
	 * di un albero.
	 */
	public void setIsRoot(boolean isroot) {
		this.isRoot = isroot;
	}
		
}
