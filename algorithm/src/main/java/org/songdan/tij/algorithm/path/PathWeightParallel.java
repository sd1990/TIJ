package org.songdan.tij.algorithm.path;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * 【第三题】路径规划 假设现在有一个有向无环图,每个节点上都带有正数权重。我们希望找到一 条最优路径,使得这个路径上经过的节点的权重之和最大。 输入:n个节点,m个路径,起点 输出:最优路径的权重值之和 举例: 3个节点: A1 B2
 * C2 3条路径: A->B B->C A->C 起点: A 输出:5 (最优路径是 A->B->C , 权重之和是 1+2+2=5) • 附加问题:我们要求的输入是有向无环图,但是没人知道实际使用的时
 * 候会有什么数据输入进来,如何避免输入了带环路的图导致的死循环 呢?
 *
 * @author song dan
 * @since 31 一月 2018
 */
public class PathWeightParallel {

    public static void main(String[] args) {
        Set<Point> pointSet = Sets.newLinkedHashSet();
        Point a = new Point("A", 1);
        Point b = new Point("B", 2);
        Point c = new Point("C", 2);
        Point d = new Point("D", 1);
        a.getReachablePoints().add(b);
        a.getReachablePoints().add(c);
        b.getReachablePoints().add(c);
        b.getReachablePoints().add(d);
        // 回路
        // c.getReachablePoints().add(a);
        c.getReachablePoints().add(d);
        pointSet.add(a);
        pointSet.add(b);
        pointSet.add(c);
        Node nodeA = new Node(a);
        Node nodeB = new Node(nodeA, b);
//        System.out.println(nodeA.path().draw());
//        System.out.println(nodeB.path().draw());
        List<Path> paths = new PathWeightParallel().generatePath(pointSet);
        for (Path path : paths) {
            System.out.println(path.draw() + ":" + path.calculateWeight());
        }
        Optional<Path> first = paths.stream().peek(Path::draw).max(Comparator.comparingInt(Path::calculateWeight));
        System.out.println(first.get().draw());

    }

    public List<Path> generatePath(Set<Point> points) {
        List<Path> list = Lists.newArrayList();
        for (Point point : points) {
            list.addAll(new Walker(point).walkParallel());
        }
        return list;
    }

    interface Drawable {

        String draw();
    }

    /**
     * 点类
     */
    public static class Point {

        private String name;

        private int weight;

        private List<Point> reachablePoints = Lists.newArrayList();

        public Point(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        public List<Point> getReachablePoints() {
            return reachablePoints;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Point point = (Point) o;

            if (weight != point.weight)
                return false;
            return name != null ? name.equals(point.name) : point.name == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + weight;
            return result;
        }

        @Override
        public String toString() {
            return "Point{" + "name='" + name + '\'' + '}';
        }
    }

    /**
     * 路线类
     */
    private static class Path implements Drawable {

        private LinkedList<Point> points = new LinkedList<>();

        public Path(List<Point> points) {
            this.points.addAll(points);
        }

        public int calculateWeight() {
            return points.stream().map(Point::getWeight).reduce(0, (sum, item) -> sum + item);
        }

        public String draw() {
            return Joiner.on("->").join(points.stream().map(Point::getName).collect(Collectors.toList()));
        }

        public void add(Point point) {
            points.add(point);
        }

    }

    private static class Node {

        private Node prev;

        private Point current;

        public Node(Node prev, Point current) {
            this.prev = prev;
            this.current = current;

        }

        public Node(Point start) {
            this(null, start);
        }

        public Node getPrev() {
            return prev;
        }

        public Point getCurrent() {
            return current;
        }

        public Path path() {
            if (prev == null) {
                return new Path(Lists.newArrayList(current));
            }
            Path path = prev.path();
            path.add(current);
            return path;
        }
    }

    /**
     * 路线的探索者
     */
    private static class Walker implements Drawable {

        private LinkedList<Point> points = new LinkedList<>();

        private Point start;

        public Walker(Point point) {
            points.add(point);
            start = point;
        }

        public boolean forward(Point point) {
            if (points.contains(point)) {
                throw new RuntimeException(draw() + "包含了节点:" + point.getName());
            }
            return points.add(point);
        }

        public Point back() {
            return points.removeLast();
        }

        public String draw() {
            return Joiner.on("->").join(points.stream().map(Point::getName).collect(Collectors.toList()));
        }

        public List<Point> nextFowards() {
            return points.peekLast().getReachablePoints();
        }

        public List<Point> getPoints() {
            return this.points;
        }

        public List<Path> walk() {
            List<Path> list = Lists.newArrayList();
            if (nextFowards().isEmpty()) {
                // 到达终点，添加path
                list.add(new Path(getPoints()));
                return list;
            }
            for (Point reachablePoint : nextFowards()) {
                forward(reachablePoint);
                list.addAll(walk());
                back();
            }
            return list;
        }

        public void walk(ExecutorService executorService, List<Node> nodes, List<Path> result) {
            for (Node node : nodes) {
                executorService.execute(() -> {
                    if (node.getCurrent().getReachablePoints().isEmpty()) {
                        result.add(node.path());
                        return;
                    }
                });
                walk(executorService, node.getCurrent().getReachablePoints().stream()
                        .map(point -> new Node(node, point)).collect(Collectors.toList()), result);
            }
        }

        public List<Path> walkParallel() {
            List<Path> result = Lists.newArrayList();
            ExecutorService executorService = Executors.newCachedThreadPool();
            walk(executorService, start.getReachablePoints().stream()
                    .map(point -> new Node(new Node(start), point)).collect(Collectors.toList()), result);
            executorService.shutdown();
            try {
                executorService.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;

        }
    }

}
