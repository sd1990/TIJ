package org.songdan.tij.algorithm.greedy;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * 美国相邻的州染上不同的颜色，最少使用几种颜色
 * @author: Songdan
 * @create: 2019-08-03 21:54
 **/
public class StateColor {

    private Map<State, Integer> stateColorMap = new HashMap<>();

    private Set<State> states;

    private Set<Integer> colorSet = Sets.newHashSet();

    private int colorCount = 0;

    public StateColor(Set<State> states) {
        this.states = states;
    }

    /**
     * 上色
     */
    public void color() {
        /*
            先找到邻居最多的州，找到后将其描色，然后排除这个州，继续迭代
        */
        Set<State> tempStateSet = Sets.newHashSet(states);
        while (tempStateSet.size() > 0) {
            State mostNeighborsState = getMostNeighborsState(tempStateSet);
            colorState(mostNeighborsState);
            tempStateSet.remove(mostNeighborsState);
        }
        for (Map.Entry<State, Integer> stateIntegerEntry : stateColorMap.entrySet()) {
            System.out.println("state:" + stateIntegerEntry.getKey().name + " color:" + stateIntegerEntry.getValue());
        }
    }

    private State getMostNeighborsState(Set<State> tempStateSet) {
        Iterator<State> iterator = tempStateSet.iterator();
        State most = iterator.next();
        while (iterator.hasNext()) {
            State next = iterator.next();
            if (next.neighbors.size() > most.neighbors.size()) {
                most = next;
            }
        }
        return most;
    }

    private void colorState(State state) {
        if (colorSet.isEmpty()) {
            colorCount++;
        }
        Integer stateColor = stateColorMap.get(state);
        if (stateColor == null) {
            stateColor = colorCount;
            stateColorMap.put(state, stateColor);
            colorSet.add(colorCount);
        }
        Set<Integer> neighborColorSet = Sets.newHashSet();
        for (State neighbor : state.neighbors) {
            Integer color = stateColorMap.get(neighbor);
            if (color != null) {
                neighborColorSet.add(color);
            }
        }
        if (neighborColorSet.contains(stateColor)) {
            Set<Integer> temp = Sets.newHashSet(colorSet);
            temp.removeAll(neighborColorSet);
            int color;
            if (temp.isEmpty()) {
                color = ++colorCount;
            } else {
                color = temp.iterator().next();
            }
            stateColorMap.put(state, color);
            colorSet.add(colorCount);
        }
    }

    public static void main(String[] args) {
        Set<State> states = Sets.newHashSet();
        State henan = State.build("河南", "湖北","陕西");
        State hubei = State.build("湖北", "河南", "湖南","陕西");
        State shanxi = State.build("陕西", "河南", "湖北");
        State hunan = State.build("湖南", "湖北");
        states.add(henan);
        states.add(hubei);
        states.add(shanxi);
        states.add(hunan);
        new StateColor(states).color();
    }

    /**
     * 州
     */
    public static class State{

        private String name;

        /**
         * 相邻的州
         */
        private Set<State> neighbors;

        public State(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Objects.equals(name, state.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public static State build(String name,String... stateNames) {
            State state = new State(name);
            if (stateNames != null && stateNames.length > 0) {
                Set<State> states = Sets.newHashSetWithExpectedSize(stateNames.length);
                for (String stateName : stateNames) {
                    states.add(new State(stateName));
                }
                state.neighbors = states;
            }
            return state;

        }
    }

}
