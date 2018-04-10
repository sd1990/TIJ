package org.songdan.tij.codewars;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 10 四月 2018
 */
public class ClosestWeightTest {
	private static void testing(String actual, String expected) {
		assertEquals(expected, actual);
	}

	@Test
	public void test() {
		System.out.println("Fixed Tests closest");
		String s = "";
		String r = "[]";
		testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
		s = "456899 50 11992 176 272293 163 389128 96 290193 85 52";
		r = "[[13, 9, 85], [14, 3, 176]]";
		testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
		s = "239382 162 254765 182 485944 134 468751 62 49780 108 54";
		r = "[[8, 5, 134], [8, 7, 62]]";
		testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
		s = "241259 154 155206 194 180502 147 300751 200 406683 37 57";
		r = "[[10, 1, 154], [10, 9, 37]]";
		testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
		s = "89998 187 126159 175 338292 89 39962 145 394230 167 1";
		r = "[[13, 3, 175], [14, 9, 167]]";
		testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
		s = "462835 148 467467 128 183193 139 220167 116 263183 41 52";
		r = "[[13, 1, 148], [13, 5, 139]]";
		testing(Arrays.deepToString(ClosestWeight.closest(s)), r);

		s = "403749 18 278325 97 304194 119 58359 165 144403 128 38";
		r = "[[11, 5, 119], [11, 9, 128]]";
		testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
		s = "28706 196 419018 130 49183 124 421208 174 404307 60 24";
		r = "[[6, 9, 60], [6, 10, 24]]";
		testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
		s = "189437 110 263080 175 55764 13 257647 53 486111 27 66";
		r = "[[8, 7, 53], [9, 9, 27]]";
		testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
		s = "79257 160 44641 146 386224 147 313622 117 259947 155 58";
		r = "[[11, 3, 146], [11, 9, 155]]";
		testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
		s = "315411 165 53195 87 318638 107 416122 121 375312 193 59";
		r = "[[15, 0, 315411], [15, 3, 87]]";
		testing(Arrays.deepToString(ClosestWeight.closest(s)), r);
	}
}