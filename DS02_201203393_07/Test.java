package performance;

public class Test {

	public static void main(String[] args) {
		PerformanceMeasurement p = new PerformanceMeasurement();

		ResultDataSet resultList = p.LinkedListMeasurement(args[0]);
		ResultDataSet AVLTree = p.AVLTreeMeasurement(args[0]);
		ResultDataSet BinarySearch = p.BinarySearchTreeMeasurement(args[0]);
		ResultDataSet DoubleHash = p.DoubleHashMeasurement(args[0]);
		
		System.out.println("--------------------Summary--------------------");
		resultList.result("LinkedList");
		AVLTree.result("AVLTree");
		BinarySearch.result("BinarySearchTree");
		DoubleHash.result("DoubleHash");
	}

}
