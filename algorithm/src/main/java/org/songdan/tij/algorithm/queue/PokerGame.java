package org.songdan.tij.algorithm.queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 使用队列和栈实现扑克牌接竹竿游戏
 *
 * @author song dan
 * @since 21 十一月 2017
 */
public class PokerGame {

	private List<Gamer> gamers;

	private Desktop desktop;

	public PokerGame(List<Gamer> gamers, Desktop desktop) {
		this.gamers = gamers;
		this.desktop = desktop;
	}

	public static void main(String[] args) {
		Gamer one = new Gamer("张三");
		Gamer two = new Gamer("李四");
		Gamer three = new Gamer("王五");
		ArrayList<Gamer> gamers = new ArrayList<>();
		gamers.add(one);
		gamers.add(two);
		gamers.add(three);
		new PokerGame(gamers, new Desktop()).play();
		System.out.println("<<<<<<<<<<<<<>>>>>>>>>>>>>");
		new PokerGame(gamers, new NewDesktop()).play();
	}

	public void play() {
		long start = System.currentTimeMillis();
//		init();
		init2();
		int sum = 0;
		int index = 0;
		Gamer gamer = gamers.get(0);
		while (!isOver()) {
			List<Integer> cards = desktop.receiveCard(gamer.outCard());
			if (cards.size() == 0) {
				//下一个人出牌
				gamer = gamers.get(++index % gamers.size());
				while (gamer.isOver()) {
					gamer = gamers.get(++index % gamers.size());
				}
			}
			else {
				gamer.addCard(cards);
				//还是这个人出牌 不变
			}
			sum++;
//			desktop.show();
		}
		System.out.println("winner is:" + gamer + ", sum is "+sum+" ,time cost is :" + (System.currentTimeMillis() - start));
	}

	private void init2() {
		for (Gamer gamer : gamers) {
			gamer.reset();
		}
		String cards = "12, 8, 10, 6, 8, 12, 12, 14, 5, 4, 12, 13, 8, 9, 10, 4, 2, 6, 1, 3";
		String cards2 = "13, 15, 6, 1, 11, 13, 15, 5, 3, 9, 7, 7, 14, 10, 11, 2, 2, 15, 7, 3";
		String cards3 = "6, 11, 9, 5, 1, 11, 4, 14, 7, 3, 15, 13, 8, 4, 10, 5, 2, 14, 1, 9";

		gamers.get(0).addCard(Arrays.stream(cards.split(",")).map(str -> Integer.parseInt(str.trim())).collect(Collectors.toList()));
		gamers.get(1).addCard(Arrays.stream(cards2.split(",")).map(str -> Integer.parseInt(str.trim())).collect(Collectors.toList()));
		gamers.get(2).addCard(Arrays.stream(cards3.split(",")).map(str -> Integer.parseInt(str.trim())).collect(Collectors.toList()));
	}

	private boolean isOver() {
		return gamers.stream().filter(gamer -> {
			return !gamer.isOver();
		}).count() == 1;
	}

	private void init() {
		for (Gamer gamer : gamers) {
			gamer.reset();
		}
		List<Integer> allCards = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 15; j++) {
				allCards.add(j + 1);
			}
		}
		Collections.shuffle(allCards);
		int index = 0;
		for (Integer card : allCards) {
			gamers.get(index++ % gamers.size()).add(card);
		}
		for (Gamer gamer : gamers) {
			gamer.show();
		}
	}

}

/**
 * 参与游戏的人
 */
class Gamer {

	private String name;

	private LinkedList<Integer> holder = new LinkedList<>();

	public Gamer(String name) {
		this.name = name;
	}

	public Integer outCard() {
		Integer card = holder.removeFirst();
//		System.out.println("gamer :" + name + "->" + card);
		return card;
	}

	public void addCard(List<Integer> cards) {
//		System.out.println("gamer :" + name + "<-" + cards);
		holder.addAll(cards);
	}

	public boolean isOver() {
		return holder.isEmpty();
	}

	public void add(Integer card) {

		holder.addLast(card);

	}

	public void reset() {
		holder.clear();
	}

	public void show() {
		System.out.println(name + " : " + holder);
	}

	@Override
	public String toString() {
		return name;
	}
}

/**
 * 牌桌
 */
class Desktop {


	protected LinkedList<Integer> holder = new LinkedList<>();


	/**
	 * 收到牌的时候
	 * @param card
	 * @return
	 */
	public List<Integer> receiveCard(Integer card) {
		List<Integer> container = new ArrayList<>();
		container.add(card);
		Iterator<Integer> reverseIterator = holder.descendingIterator();
		while (reverseIterator.hasNext()) {
			Integer next = reverseIterator.next();
			container.add(next);
			if (next.equals(card)) {
				//清除container
				holder.removeAll(container);
				return container;
			}
		}
		//没有发现匹配的将这张牌放到桌面上
		holder.addLast(card);
		return Collections.emptyList();

	}

	public void show() {
		System.out.println(holder);
	}

}

class NewDesktop extends Desktop {

	private int[] book = new int[16];

	@Override
	public List<Integer> receiveCard(Integer card) {
		if (book[card] == 0) {
			holder.addLast(card);
			book[card] = 1;
			return Collections.emptyList();
		}
		else {
			List<Integer> container = new ArrayList<>();
			container.add(card);
			Iterator<Integer> reverseIterator = holder.descendingIterator();
			while (reverseIterator.hasNext()) {
				Integer next = reverseIterator.next();
				container.add(next);
				//标记清空
				book[next] = 0;
				//移除元素
				reverseIterator.remove();
				if (next.equals(card)) {
					//清除container
					return container;
				}
			}
		}
		return Collections.emptyList();
	}
}
