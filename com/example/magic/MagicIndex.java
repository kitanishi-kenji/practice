package com.example.magic;

import java.util.ArrayList;
import java.util.List;

/**
 * マジックインデックス
 * @author kenji
 *
 */
public class MagicIndex {

	/**
	 * マジックインデックスの検索
	 * @param array 異なる昇順のint配列
	 * @return マジックインデックスの一覧
	 */
	private static Integer[] serachAsc(int[] array){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0 ; i<array.length ; i++){
			// 異なる昇順の整数の場合、A[0] = 0, A[1] = 1, ... , A[n] = n しかありえない
			if(array[i] == i){
				list.add(i);
			} else {
				// 値が異なれば終了
				break;
			}
		}
		return list.toArray(new Integer[0]);
	}
	
	/**
	 * マジックインデックスの検索
	 * @param array 昇順のint配列
	 * @return マジックインデックスの一覧
	 */
	private static Integer[] serach(int[] array){
		List<Integer> list = new ArrayList<Integer>();
		// 全ループパターン
		int count = 0;
		for(int i=0 ; i<array.length ; i++){
			count++;
			if(array[i] == i){
				list.add(i);
			}
		}
		System.out.println("[" + count + "]回ループしました");
		list.clear();
		// ちょっと凝ったパターン
		int index = 0;
		count = 0;
		while(index < array.length){
			count++;
			if(array[index] > index){
				index = array[index]; // 早送り
			} else {
				if(array[index] == index){
					list.add(index);
				}
				index++;
			}
		}
		System.out.println("[" + count + "]回ループしました");
		return list.toArray(new Integer[0]);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 昇順の異なる整数の配列
		int[] arrayAsc = {0,1,2,3,4,6,7,8};
		System.out.print("配列{");
		for(int i = 0;i<arrayAsc.length ; i++){
			System.out.print("[" + i + ":" + arrayAsc[i] + "]");
		}
		System.out.println("}の");
		Integer[] ascIndexes = serachAsc(arrayAsc);
		System.out.print("マジックインデックスは{ ");
		for(Integer index : ascIndexes){
			System.out.print(index + " ");
		}
		System.out.println("}です");
		
		// 昇順の同じ整数を持つ可能性のある配列
		int[] array = {0,1,2,3,6,6,6,10,11,20};
		System.out.print("配列{");
		for(int i = 0;i<array.length ; i++){
			System.out.print("[" + i + ":" + array[i] + "]");
		}
		System.out.println("}の");
		Integer[] indexes = serach(array);
		System.out.print("マジックインデックスは{ ");
		for(Integer index : indexes){
			System.out.print(index + " ");
		}
		System.out.println("}です");
	}

}
