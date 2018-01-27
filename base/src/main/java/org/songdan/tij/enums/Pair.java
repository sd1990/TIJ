package org.songdan.tij.enums;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 10 一月 2018
 */
public class Pair<K, V> {

	private K k;

	private V v;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pair<?, ?> pair = (Pair<?, ?>) o;

		if (k != null ? !k.equals(pair.k) : pair.k != null) return false;
		return v != null ? v.equals(pair.v) : pair.v == null;
	}

	@Override
	public int hashCode() {
		int result = k != null ? k.hashCode() : 0;
		result = 31 * result + (v != null ? v.hashCode() : 0);
		return result;
	}

	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}

	public static <K,V> Pair<K, V> of(K k,V v) {
		return new Pair<>();
	}

}
