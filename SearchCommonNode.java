import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 二分木から２つのノードの上位ノードで共通するものを探す
 * 
 * @author kitanishi
 * 
 */
public class SearchCommonNode {

	private static class Node {
		/**
		 * 値
		 */
		private final int value;

		/**
		 * 親ノード
		 */
		private final Node parent;

		/**
		 * 左側のノード
		 */
		private Node left = null;

		/**
		 * 右側のノード
		 */
		private Node right = null;

		/**
		 * コンストラクタ
		 * 
		 * @param value
		 *            値
		 */
		public Node(int value) {
			this(value, null);
		}

		/**
		 * コンストラクタ　
		 * 
		 * @param value
		 *            値
		 * @param parent
		 *            親ノード
		 */
		public Node(int value, Node parent) {
			this.parent = parent;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + ", left=" + left + ", right="
					+ right + "]";
		}
	}

	/**
	 * 二分木を作成
	 * 
	 * @param count
	 *            Nodeの数
	 * @param min
	 *            最小値
	 * @param max
	 *            最大値
	 * @return 作成された二分木、作成に失敗した場合はnull
	 */
	public static Node generateTree(int count) {// ,int min,int max){
		Random random = new Random(1);
		// 1からNode数までの値を持つリストを作成
		List<Integer> valueList = new LinkedList<Integer>();
		for (int i = 0; i < count; i++) {
			valueList.add(Integer.valueOf(i + 1));
		}
		// 子を作成するノードの一覧
		List<Node> parentNodes = new ArrayList<Node>();
		// 作成された子のノード一覧
		List<Node> childNodes = new ArrayList<Node>();
		// 二分木
		Node root = null;
		while (!valueList.isEmpty()) {
			if (parentNodes.isEmpty()) {
				// ルート
				int valueIndex = random.nextInt(valueList.size());
				root = new Node(valueList.get(valueIndex));
				valueList.remove(valueIndex);
				parentNodes.add(root);
			} else {
				// 作成された子ノードの一覧が空(=すべて子を持たない)の場合、子を作成するノード一覧で再トライ
				// それ以外の場合、子を作成するノード一覧に、作成された子ノードの一覧のノードを移動
				if (!childNodes.isEmpty()) {
					parentNodes.clear();
					for (Node node : childNodes) {
						parentNodes.add(node);
					}
					childNodes.clear();
				}
				// 子を作成するノードの一覧にあるノードに子を付加していく
				for (Node node : parentNodes) {
					int childCount = random.nextInt(4);
					if (childCount == 0) {
						// 次のノードはなし
					} else if (childCount == 1) {
						// 次のノードは左に一つ
						int valueIndex = random.nextInt(valueList.size());
						node.left = new Node(valueList.get(valueIndex), node);
						valueList.remove(valueIndex);
					} else if (childCount == 2) {
						// 次のノードは右に一つ
						int valueIndex = random.nextInt(valueList.size());
						node.right = new Node(valueList.get(valueIndex), node);
						valueList.remove(valueIndex);
					} else {
						// 次のノードは左右
						int valueIndex = random.nextInt(valueList.size());
						node.left = new Node(valueList.get(valueIndex), node);
						valueList.remove(valueIndex);
						// 作成数に達していれば、処理を抜ける
						if (valueList.isEmpty()) {
							break;
						}
						valueIndex = random.nextInt(valueList.size());
						node.right = new Node(valueList.get(valueIndex), node);
						valueList.remove(valueIndex);
					}
					// 作成された子ノードを追加
					if (node.left != null) {
						childNodes.add(node.left);
					}
					if (node.right != null) {
						childNodes.add(node.right);
					}
					// 作成数に達していれば、処理を抜ける
					if (valueList.isEmpty()) {
						break;
					}
				}
			}
		}
		return root;
	}

	/**
	 * Nodeを検索
	 * 
	 * @param root
	 *            二分木のルート
	 * @param value1
	 *            検索するノードの値一つ目
	 * @param value2
	 *            検索するノードの値二つ目
	 * @return 共通するNode
	 */
	private static Node search(Node root, int value1, int value2) {
		// 共通のノード
		Node commonNode = null;
		// ルートから一つ目のノードを検索
		Node node1 = search(root, value1);
		// 親に戻りつつ、二つ目のノードを検索
		Node parent = node1;
		while (parent != null) {
			if (search(parent, value2) != null) {
				commonNode = parent;
				break;
			}
			// さらに親に
			parent = parent.parent;
		}

		return commonNode;
	}

	/**
	 * 現在ノード以下を検索
	 * 
	 * @param currentNode
	 *            現在のノード
	 * @param value
	 *            検索する値
	 * @return 値に一致するノード
	 */
	private static Node search(Node currentNode, int value) {
		// Nodeが存在しなければ終了
		if (currentNode == null) {
			return null;
		}
		// 値が一致
		if (currentNode.value == value) {
			return currentNode;
		}
		// 子ノードを確認
		Node targetNode = null;
		// 左を検索
		targetNode = search(currentNode.left, value);
		// 左になければ右を検索
		if (targetNode == null) {
			targetNode = search(currentNode.right, value);
		}
		return targetNode;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = 6;
		Node root = SearchCommonNode.generateTree(count);
		System.out.println(root);
		for (int i = 1; i <= count; i++) {
			for (int j = 1; j <= count; j++) {
				System.out.println(i + "," + j
						+ "------------------------------------");
				System.out.println(search(root, i, j));
			}
		}
	}
}
