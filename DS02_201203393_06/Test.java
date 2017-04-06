package avltree;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVLTree tree = new AVLTree(3);
		tree.add(5);
		tree.add(1);
		tree.add(8);
		tree.add(6);
		tree.add(2);
		tree.add(11);
		tree.add(4);
		tree.add(10);
		tree.add(9);
		tree.add(7);

		System.out.println(tree);
		
		
		tree.decrease(3);
		System.out.println("Delete 3 : " + tree);

		tree.decrease(5);
		System.out.println("Delete 5 : " + tree);

		tree.decrease(1);
		System.out.println("Delete 1 : " + tree);

		tree.decrease(8);
		System.out.println("Delete 8 : " + tree);

		tree.decrease(6);
		System.out.println("Delete 6 : " + tree);
		

	}

}
