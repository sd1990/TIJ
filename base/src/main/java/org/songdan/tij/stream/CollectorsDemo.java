package org.songdan.tij.stream;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 14 十二月 2017
 */
public class CollectorsDemo {

	public static void main(String[] args) {
		List<Hosting> list = new ArrayList<>();
		list.add(new Hosting(1, "liquidweb.com", 80000));
		list.add(new Hosting(2, "linode.com", 90000));
		list.add(new Hosting(3, "digitalocean.com", 120000));
		list.add(new Hosting(4, "aws.amazon.com", 200000));
		list.add(new Hosting(5, "mkyong.com", 1));

		list.add(new Hosting(6, "linode.com", 100000)); // new line

		Map<String, Hosting> map = list.stream().collect(Collectors.toMap(Hosting::getName, hosting -> hosting, (oldvalue, newValue) -> {
			return oldvalue;
		}));

		for (Map.Entry<String, Hosting> entry : map.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue().getId());
		}

		System.out.println("=====================");

		// key = name, value - websites , but the key 'linode' is duplicated!?
		Map<String, IntSummaryStatistics> collect = list.stream().collect(
				Collectors.groupingBy(Hosting::getName, Collectors.summarizingInt(host -> (int) host.getWebsites())));

		for (Map.Entry<String, IntSummaryStatistics> sumEntry : collect.entrySet()) {
			System.out.println(sumEntry.getKey()+":"+sumEntry.getValue().getSum());
		}

		Integer num = null;
		num++;
		System.out.println(num);
	}

}

class Hosting {

	private int Id;
	private String name;
	private long websites;


	public Hosting(int id, String name, long websites) {
		Id = id;
		this.name = name;
		this.websites = websites;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getWebsites() {
		return websites;
	}

	public void setWebsites(long websites) {
		this.websites = websites;
	}
}
