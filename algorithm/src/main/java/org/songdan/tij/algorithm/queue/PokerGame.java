package org.songdan.tij.algorithm.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


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

	public void play() {
		init();
		int index = 0;
		Gamer gamer = gamers.get(index);
		while (!isOver()) {
			List<Integer> cards = desktop.receiveCard(gamer.outCard());
			if (cards.size() == 0) {
				//下一个人出牌
				gamer = gamers.get(++index%gamers.size());
			}else{
				gamer.addCard(cards);
				//还是这个人出牌 不变
			}
			desktop.show();
		}
		System.out.println("winner is:"+gamer);
	}

	public static void main(String[] args) {
		Gamer one = new Gamer("张三");
		Gamer two = new Gamer("李四");
		ArrayList<Gamer> gamers = new ArrayList<>();
		gamers.add(one);
		gamers.add(two);
		new PokerGame(gamers,new Desktop()).play();
	}

	private boolean isOver() {
		return gamers.stream().filter(gamer -> {
			return !gamer.isOver();
		}).count() == 1;
	}

	private void init(){
		//初始化游戏
		gamers.forEach(gamer ->{
			ThreadLocalRandom localRandom = ThreadLocalRandom.current();
			List<Integer> cards = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				int card = localRandom.nextInt(1, 15);
				cards.add(card);
			}
			gamer.addCard(cards);
		});
	}

}

/**
 * 参与游戏的人
 */
class Gamer{

	private String name;

	private LinkedList<Integer> holder = new LinkedList<>();

	public Gamer(String name) {
		this.name = name;
	}

	public Integer outCard() {
		Integer card = holder.removeFirst();
		System.out.println("gamer :"+name+"->"+card);
		return card;
	}

	public void addCard(List<Integer> cards) {
		System.out.println("gamer :"+name+"<-"+cards);
		holder.addAll(cards);
	}

	public boolean isOver(){
		return holder.isEmpty();
	}

	@Override
	public String toString() {
		return name;
	}
}

/**
 * 牌桌
 */
class Desktop{


	private LinkedList<Integer> holder = new LinkedList<>();


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
