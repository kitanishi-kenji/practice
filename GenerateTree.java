
/**
 * 昇順にソートされた配列から高さが最小になる二分探索木を作成
 * @author kitanishi
 *
 */
public class GenerateTree {

	/**
	 * ノード
	 * @author kitanishi
	 *
	 */
	private class Node{
		/**
		 * 値
		 */
		private final int value;
		
		/**
		 * コンストラクタ
		 * @param value
		 */
		public Node(int value) {
			this.value = value;
		}

		/**
		 * 左ノード、このノードより小さい
		 */
		private Node left = null;
		
		/**
		 * 右ノード、このノードより大きい
		 */
		private Node right = null;
	}
	
	/**
	 * Treeの生成
	 * @param values 値一覧
	 * @return 作成されたNode
	 */
	public Node genereteTree(int[] values){
		// 配列がnullまたは配列長さが0
		if(values == null || values.length == 0){
			return null;
		}
		// 配列の長さが1
		if(values.length == 1){
			return new Node(values[0]);
		}
		// 配列の長さが2以上
		int center = values.length / 2;
		int[] lefts = new int[center];
		for(int i=0 ; i<lefts.length ; i++){
			lefts[i] = values[i];
		}
		int[] rights = null;
		if(values.length > 2){
			rights = new int[values.length - center - 1];
			for(int i=0 ; i<rights.length ; i++){
				rights[i] = values[center + 1 + i];
			}
		}
		Node node = new Node(values[center]);
		node.left = genereteTree(lefts);
		node.right = genereteTree(rights);	
		
		return node;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] values;
		
		System.out.println("null------------------------------->");
		values = null;
		System.out.println("depth=>" + print(new GenerateTree().genereteTree(values),1));
		
		System.out.println("1---------------------------------->");
		values = new int[]{0};
		System.out.println("depth=>" + print(new GenerateTree().genereteTree(values),1));
		
		System.out.println("2---------------------------------->");
		values = new int[]{0,1};
		System.out.println("depth=>" + print(new GenerateTree().genereteTree(values),1));

		System.out.println("3---------------------------------->");
		values = new int[]{0,1,2};
		System.out.println("depth=>" + print(new GenerateTree().genereteTree(values),1));
		
		System.out.println("15---------------------------------->");
		values = new int[15];
		for(int i = 0 ; i < values.length ; i++){
			values[i] = i; 
		}
		System.out.println("depth=>" + print(new GenerateTree().genereteTree(values),1));
		
		System.out.println("31---------------------------------->");
		values = new int[31];
		for(int i = 0 ; i < values.length ; i++){
			values[i] = i; 
		}
		System.out.println("depth=>" + print(new GenerateTree().genereteTree(values),1));		
	}
	
	private static int print(Node node,int depth){
		int maxDepth = depth;
		if(node == null){
			System.out.println(node);	
		} else {
			if(node.left != null && node.right != null){
				System.out.println(node.left.value + "<-" + node.value + "->" + node.right.value + ",depth=" + depth);
			} else if(node.left == null && node.right != null){
				System.out.println(node.value + "->" + node.right.value + ",depth=" + depth);
			} else if(node.left != null && node.right == null){
				System.out.println(node.left.value + "<-" + node.value + ",depth=" + depth);
			} else {
				System.out.println(node.value + ",depth=" + depth);
			}
			if(node.left != null ){
				int newDepth = print(node.left,depth+1);
				if(maxDepth < newDepth){
					maxDepth = newDepth;
				}
			}
			if(node.right != null ){
				int newDepth = print(node.right,depth+1);
				if(maxDepth < newDepth){
					maxDepth = newDepth;
				}
			}
		}
		return maxDepth;
	}

}
