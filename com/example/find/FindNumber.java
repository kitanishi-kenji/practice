package com.example.find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 配列から要素を検索し、そのインデックスを取得する
 * 
 * @author kenji
 * 
 */
public class FindNumber {
	
	public static int find(int[] array, int number, int start, int end) {
		if (start > end) {
			return -1;
		}
		int index = (start + end) / 2;
		System.out.println(number + ":" + array[start] + "[" + start + "]-"
				+ array[end] + "[" + end + "]" + ":" + array[index] + "["
				+ index + "]");
		if (array[index] == number) {
			// 一致、君が望むのはこれだね！
			return index;
		} else {
			// 境界の位置が左か右か判別する、なぜなら境界の反対側は単なる昇順だから
			// start > indexなら、左側に境界が存在,
			if (array[start] > array[index]) {
				// 境界は左、なので右側は単なる昇順
				// index < number かつ number <= endなら右側
				if (array[index] < number && number <= array[end]) {
					return find(array, number, index + 1, end);
				} else {
					return find(array, number, start, index - 1);
				}
			} else {
				// 境界は右、なので左側は単なる昇順
				// number < index かつ start <= numberなら左側
				if (array[start] <= number && number < array[index]) {
					return find(array, number, start, index - 1);
				} else {
					return find(array, number, index + 1, end);
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 乱数
		int seed = 1;
		int arrayMax = 30;
		int valueMax = 100;
		Random rnd = new Random(seed);

		// 最初に昇順の配列を作成
		List<Integer> numbers = new ArrayList<>();
		int[] array = new int[rnd.nextInt(arrayMax) + 1];
		for (int i = 0; i < array.length; i++) {
			Integer v = null;
			do {
				v = Integer.valueOf(rnd.nextInt(valueMax));
				if (!numbers.contains(v)) {
					numbers.add(v);
					break;
				}
			} while (true);
			array[i] = v;
		}
		output(" Create:", array);
		Arrays.sort(array);
		output("   Sort:", array);

		// 回転
		int move = Math.abs(rnd.nextInt(array.length));
		int[] results = new int[array.length];
		// 移動
		for (int j = 0; j < array.length; j++) {
			int index = j + (move % array.length);
			// System.out.println(j + ":" +index);
			if (index >= array.length) {
				index -= array.length;
			}
			// System.out.println(j + ":" +index);
			results[index] = array[j];
		}
		array = results;
		output("Rolling:", array);

		// 探索
		for (int i = 0; i < array.length; i++) {
			int index = find(array, array[i], 0, array.length - 1);
			System.out.println(array[i] + "[" + index + "]");
		}
		// そして失敗の例
		int index = find(array, valueMax, 0, array.length - 1);
		System.out.println(valueMax + "[" + index + "]");
	}

	/**
	 * 配列を出力
	 * 
	 * @param array
	 */
	public static void output(String prefix, int[] array) {
		StringBuilder sb = new StringBuilder(prefix);
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(String.format("%02d", array[i]) + "[" + i + "]");
		}
		System.out.println(sb);
	}
}
