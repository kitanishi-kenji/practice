package com.example.friends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 人クラス
 * 
 * @author kenji
 * 
 */
public class Person {

	/**
	 * 名前
	 */
	private String name;

	/**
	 * 友達一覧
	 */
	private Map<String, Person> friends = new LinkedHashMap<>();

	/**
	 * コンストラクタ
	 * 
	 * @param name
	 *            名前
	 */
	public Person(String name) {
		this.name = name;
	}

	/**
	 * 名前の取得
	 * 
	 * @return 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * 友達追加
	 * 
	 * @param person
	 *            友達
	 */
	public void addPerson(Person person) {
		friends.put(person.getName(), person);
	}

	/**
	 * 友達一覧の取得
	 * 
	 * @return 友達一覧
	 */
	public Person[] getFriends() {
		return friends.values().toArray(new Person[0]);
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person me = new Person("Me");
		Person a = new Person("A");
		Person b = new Person("B");
		Person c = new Person("C");
		Person d = new Person("D");
		Person e = new Person("E");
		Person you = new Person("You");
		// 私の友達はA,B,C
		me.addPerson(a);
		me.addPerson(b);
		me.addPerson(c);
		// Aの友達はD
		a.addPerson(d);
		// Bの友達はいない
		// Cの友達はYou
		c.addPerson(you);
		// Dの友達はE
		d.addPerson(e);

		System.out.println(link(me, you));
	}

	/**
	 * つながりを求める、人は一人では生きていけない。。。
	 * 
	 * @param src
	 *            開始点
	 * @param dest
	 *            　終了点
	 * @return
	 */
	public static int link(Person src, Person dest) {
		// レベル、まあ、距離か。
		Map<String,Integer> levelArray = new HashMap<>();
		// 訪問した人の名前
		List<String> visited = new ArrayList<>();
		// 初期設定
		int level = 0;
		levelArray.put(src.getName(),level);
		Queue<Person> queue = new LinkedList<>();
		queue.add(src);
		// srcに訪問
		visited.add(src.getName());
		// さーQueueが空になるまで探索だ！
		while (!queue.isEmpty()) {
			level += 1;
			Person person = queue.poll();
			if (person.equals(dest))
				return levelArray.get(dest.getName());
			for (Person friend : person.getFriends()) {
				if (!visited.contains(friend.getName())) {
					visited.add(friend.getName());
					levelArray.put(friend.getName(), level);
					queue.add(friend);
				}
			}
		}
		return -1;
	}

}
