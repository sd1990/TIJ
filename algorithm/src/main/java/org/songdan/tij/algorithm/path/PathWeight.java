package org.songdan.tij.algorithm.path;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
public class PathWeight {

    private List<Path> paths = Lists.newArrayList();

    public static void main(String[] args) {
        Set<Point> pointSet = Sets.newLinkedHashSet();
        Point a = new Point("A", 1);
        Point b = new Point("B", 2);
        Point c = new Point("C", 2);
        a.getReachablePoints().add(b);
        a.getReachablePoints().add(c);
        b.getReachablePoints().add(c);
        //回路
//        c.getReachablePoints().add(a);
        pointSet.add(a);
        pointSet.add(b);
        pointSet.add(c);

        List<Path> paths = new PathWeight().generatePath(pointSet);
        for (Path path : paths) {
            System.out.println(path.draw() + ":" + path.calculateWeight());
        }
        Optional<Path> first = paths.stream().peek(Path::draw).max(Comparator.comparingInt(Path::calculateWeight));
        System.out.println(first.get().draw());

    }

    public void join(Path path, Point point) {
        if (point.getReachablePoints().isEmpty()) {
            // 到达终点，添加path
            paths.add(new Path(path.getPoints()));
            return;
        }
        for (Point reachablePoint : point.getReachablePoints()) {
            path.forward(reachablePoint);
            join(path, reachablePoint);
            path.back();
        }
    }

    public List<Path> generatePath(Set<Point> points) {
        for (Point point : points) {
            join(new Path(point), point);
        }
        return paths;
    }

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

        public void setName(String name) {
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public List<Point> getReachablePoints() {
            return reachablePoints;
        }

        public void setReachablePoints(List<Point> reachablePoints) {
            this.reachablePoints = reachablePoints;
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

    public static class Path {

        private LinkedList<Point> points = new LinkedList<>();

        public Path(Point start) {
            points.add(start);
        }

        public Path(LinkedList<Point> points) {
            this.points.addAll(points);
        }

        public int calculateWeight() {
            return points.stream().map(Point::getWeight).reduce(0, (sum, item) -> sum + item);
        }

        public boolean isLoop(Point point) {
            return points.contains(point);
        }

        public boolean forward(Point point) {
            if (this.isLoop(point)) {
                throw new RuntimeException(draw() + "存在回路:" + point.getName());
            }
            return points.add(point);
        }

        public Point back() {
            return points.removeLast();
        }

        public LinkedList<Point> getPoints() {
            return points;
        }

        public String draw() {
            return Joiner.on("->").join(points.stream().map(Point::getName).collect(Collectors.toList()));
        }

    }

}
