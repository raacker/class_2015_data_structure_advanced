package hw06;

public class AVLTree {
	AVLTree root = this;
	private int key, height;
	private AVLTree left, right;

	public static final AVLTree NIL = new AVLTree();

	public AVLTree(int key) {
		this.key = key;
		left = right = NIL;
	}

	public boolean add(int key) {
		int oldSize = size();
		grow(key);
		return size() > oldSize;
	}

	public AVLTree grow(int key) {
		if (this == NIL)
			return new AVLTree(key);
		if (key == this.key)
			return this;
		if (key < this.key)
			left = left.grow(key);
		else
			right = right.grow(key);

		rebalance();
		height = 1 + Math.max(left.height, right.height);
		return this;
	}

	public AVLTree delete(int key) {
		if (root == NIL)
			return NIL;
		else if (root.key > key)
			root.left = left.delete(key);
		else if (root.key < key)
			root.right = right.delete(key);

		if (root.left == NIL && root.right == NIL)
			return NIL;

		if (key == root.key)
			return root.getCandidate(root);

		rebalance();
		root.height = 1 + Math.max(root.left.height, root.right.height);
		return this;

	}

	private AVLTree getCandidate(AVLTree deleteNode) {
		AVLTree candidateParent = deleteNode;
		AVLTree candidate = candidateParent.right;

		while (candidate.left != NIL) {
			candidateParent = candidate;
			candidate = candidate.left;
		}

		if (candidate != deleteNode.right) {
			candidateParent.left = candidate.right;
			candidate.right = deleteNode.right;
		}
		candidate.left = deleteNode.left;
		rebalance();
		candidate.height = 1 + Math.max(candidate.left.height,
				candidate.right.height);
		return root = candidate;
	}

	public int size() {
		if (this == NIL)
			return 0;
		return 1 + left.size() + right.size();
	}

	public String toString() {
		if (this == NIL)
			return "";
		return left + " " + key + "(" + height + ")" + " " + right;
	}

	public void inOrder() {
		if (root == NIL) {
			System.out.println("Tree is Empty!");
			return;
		} else {
			System.out.print("(");

			if (root.left != NIL) {
				left.inOrder();
				System.out.print(", ");
			}
			if ((root.left == NIL) && (root.right != NIL))
				System.out.print("NIL, ");

			System.out.print(root.key+"["+height+"]");

			if (root.right != NIL) {
				System.out.print(", ");
				right.inOrder();
			}
			if ((root.right == NIL) && (root.left != NIL))
				System.out.print(", NIL");

			System.out.print(")");
		}
	}

	private AVLTree() {
		left = right = this;
		height = -1;
	}

	private AVLTree(int key, AVLTree left, AVLTree right) {
		this.key = key;
		this.left = left;
		this.right = right;
		height = 1 + Math.max(left.height, right.height);
	}

	public void rebalance() {
		if (right.height > left.height + 1) {
			if (right.left.height > right.right.height)
				right.rotateRight();
			rotateLeft();
		} else if (left.height > right.height + 1) {
			if (left.right.height > left.left.height)
				left.rotateLeft();
			rotateRight();
		}
	}

	private void rotateLeft() {
		left = new AVLTree(key, left, right.left);
		key = right.key;
		right = right.right;
	}

	private void rotateRight() {
		right = new AVLTree(key, left.right, right);
		key = left.key;
		left = left.left;
	}
}
