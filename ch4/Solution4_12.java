package ch4;

import java.util.HashMap;

/*Given a binary tree,Count the number of paths that sum to a given value*/
public class Solution4_12 {
	
	int countPathWithSum(TreeNode root, int targetSum){
		if(root == null) return 0;
		HashMap<Integer,Integer> pathCount = new HashMap<Integer,Integer>();
		incrementHashTable(pathCount,0,1);
		return countPathWithSum(root,targetSum,0,pathCount);
	}
	
	int countPathWithSum(TreeNode node, int targetSum, int runningSum, HashMap<Integer,Integer> pathCount){
		if(node == null) return 0;
		
		runningSum += node.value;
		incrementHashTable(pathCount,runningSum,1);
		
		/*Count paths with sum ending at the current node*/
		int sum = runningSum - targetSum;
		int totalPaths = pathCount.containsKey(sum) ? pathCount.get(sum) : 0;
		
		totalPaths += countPathWithSum(node.left, targetSum, runningSum, pathCount);
		totalPaths += countPathWithSum(node.right,targetSum, runningSum, pathCount);
		// remove current node's partial sum so as not to influence 
		// the nodes that are not current node's offspring.
		incrementHashTable(pathCount,runningSum,-1);
		return totalPaths;
	}
	
	void incrementHashTable(HashMap<Integer,Integer> hashTable, int key, int delta){
	
		if(!hashTable.containsKey(key)){
			hashTable.put(key, 0);
		}
		hashTable.put(key, hashTable.get(key) + delta);
	}
	
	public static void main(String[] args) {
		TreeNode root1 = new TreeNode(1);
		Solution4_12 t = new Solution4_12();
		System.out.println(t.countPathWithSum(root1, 1));

		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.left.left = new TreeNode(3);
		root.left.left.right = new TreeNode(-2);
		root.left.right = new TreeNode(1);
		root.left.right.right = new TreeNode(2);
		root.right = new TreeNode(-3);
		root.right.right = new TreeNode(11);
		System.out.println(t.countPathWithSum(root, 8));
	}
}
