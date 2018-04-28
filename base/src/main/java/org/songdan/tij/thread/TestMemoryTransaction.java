package org.songdan.tij.thread;

import java.util.concurrent.TimeUnit;

import org.songdan.tij.model.Person;

/**
 * 是不是很傻
 *
 * @author song dan
 * @since 20 四月 2018
 */
public class TestMemoryTransaction {

	public static void main(String[] args) {
		int j = 1 / 0;
		Person person = new Person();
		person.setAge(1);
		new Thread(()->{

			for (int i = 0; i < 10; i++) {
				System.out.println(person.getAge());
				try {
					TimeUnit.MILLISECONDS.sleep(10);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}).start();
		new Thread(()->{

			person.setAge(1);
			throw new RuntimeException("故意的");

		}).start();
	}
}
