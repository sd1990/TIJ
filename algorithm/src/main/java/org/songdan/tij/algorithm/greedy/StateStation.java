package org.songdan.tij.algorithm.greedy;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * 州和电台，贪婪算法
 *
 * @author song dan
 * @since 21 五月 2018
 */
public class StateStation {

	private Set<String> needConvered = new HashSet<String>(){
		{
			add("1");
			add("2");
			add("3");
			add("4");
			add("5");
			add("6");
			add("7");
			add("8");
			add("9");
			add("10");
		}
	};

	private Set<Station> stations;

	public StateStation(Set<Station> stations) {
		this.stations = stations;
	}

	public static void main(String[] args) {
		HashSet<Station> stations = Sets.newLinkedHashSet();
		Station a = new Station("a",Sets.newHashSet("1","3","4","5"));
		Station b = new Station("b",Sets.newHashSet("1","2","3","5","9"));
		Station c = new Station("c",Sets.newHashSet("6","7","3"));
		Station d = new Station("d",Sets.newHashSet("8","10"));
		Station e = new Station("e",Sets.newHashSet("2","9","10"));
		stations.add(a);
		stations.add(b);
		stations.add(c);
		stations.add(d);
		stations.add(e);
		StateStation stateStation = new StateStation(stations);
		Set<Station> greedy = stateStation.greedy();
		System.out.println(greedy);
	}


	public Set<Station> greedy() {
		/*
		1. 找出可以覆盖最多的未被其他电台覆盖的州的电台，将其加入电台集合，将覆盖到的州
		2. 只要needConvered不为空，重复第1步
		 */
		// 已经被覆盖的州
		Set<Station> finalStations = Sets.newLinkedHashSet();
		while (!needConvered.isEmpty()) {
			Station bestStation = null;
			Set<String> bestConvered = Sets.newHashSet();
			//找到可以覆盖最多的未被其他电台覆盖的州
			for (Station station : stations) {
				Set<String> convered = inner(station.convered, needConvered);
				if (bestStation == null) {
					bestStation = station;
					bestConvered = convered;
				}
				else {
					if (convered.size() > bestConvered.size()) {
						bestStation = station;
						bestConvered = convered;
					}

				}
			}
			System.out.println("best station is :"+bestStation);
			System.out.println("convered is :"+bestConvered);
			// 将最优的station到最终结果中
			finalStations.add(bestStation);
			// 从需要覆盖到州里面去除 bestConvered
			needConvered.removeAll(bestConvered);
		}
		return finalStations;


	}

	public static Set<String> inner(Set<String> first, Set<String> second) {
		return Sets.intersection(first, second);
	}

	public static Set<String> different(Set<String> first, Set<String> second) {
		HashSet<String> copy = Sets.newHashSet(first);
		copy.removeAll(second);
		return copy;
	}


	private static class Station {
		private String station;

		private Set<String> convered;

		public Station(String station, Set<String> convered) {
			this.station = station;
			this.convered = convered;
		}

		@Override
		public String toString() {
			return "Station{" +
					"station='" + station + '\'' +
					'}';
		}
	}


}
