package org.songdan.tij.holding;

import java.util.Comparator;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 18 十二月 2017
 */
public class MultiComparator <T> implements Comparator<T> {

	private Comparator<T> comparator;

	public MultiComparator(Comparator<T>... comparators) {
		comparator = buildComparator(comparators);
	}

	public Comparator<T> buildComparator(Comparator<T>... comparators) {
		if (1 == comparators.length) {
			return comparators[0];
		}
		Comparator<T>[] dest = (Comparator<T>[])new Comparator[comparators.length-1];
		System.arraycopy(comparators, 1, dest, 0, comparators.length - 1);
		return new CompositeComparator<>(comparators[0], buildComparator(dest));
	}


	@Override
	public int compare(T o1, T o2) {
		return comparator.compare(o1, o2);
	}
}
