package org.songdan.tij.stream;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author: Songdan
 * @create: 2020-09-07 21:43
 **/
public class StreamGenerate {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //这个stream不会结束
        Stream<Integer> stream = Stream.generate(() -> {
            return ThreadLocalRandom.current().nextInt(100);
        });
        BlockingQueue<Person> queue = new LinkedBlockingQueue<>();
        Iterator<Person> iterator = new Iterator<Person>() {

            private int count = 5000;

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < count;
            }

            @Override
            public Person next() {
                try {
                    TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                index++;
                Person person = new Person();
                person.setAge(index * (ThreadLocalRandom.current().nextInt(10) > 5 ? 1 : -1));
                person.setName("person_" + index);
                System.out.println(index);
                return person;
            }
        };
        Spliterator<Person> spliterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.NONNULL);
        Stream<Person> dynamicStream = StreamSupport.stream(spliterator, false);
        List<Person> result = dynamicStream.peek(person -> {
            System.out.println(Thread.currentThread() + ":" + person);
        }).collect(Collectors.toList());

        System.out.println(result.parallelStream().map(Person::getAge).reduce(Math::max));
        System.out.println(result);

    }


    static class Person {
        private String name;

        private Integer age;

        private Integer owner;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getOwner() {
            return owner;
        }

        public void setOwner(Integer owner) {
            this.owner = owner;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", owner=" + owner +
                    '}';
        }
    }

    static class PosionPerson extends Person {

    }

}
