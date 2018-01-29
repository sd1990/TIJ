package org.songdan.tij.recursive;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 29 一月 2018
 */
public class RecursiveTest {

	/**
	 * 尾递归调用
	 * @param factorial
	 * @param current
	 * @return
	 */
    public static int factorialTailRecursive(int factorial, int current) {

        if (current == 1) {
            return factorial;
        } else {
            return factorialTailRecursive(factorial + current, current - 1);
        }

    }

	/**
	 * 普通递归调用
	 * @param factorial
	 * @return
	 */
	public static int factorialRecursive(int factorial) {
        if (factorial == 1) {
            return 1;
        } else {
            return factorial + factorialRecursive(factorial - 1);
        }

    }

	/**
	 * lamb尾递归调用
	 * @param factorial
	 * @param current
	 * @return
	 */
    public static TailRecursion<Integer> factorialTailRecursiveLamb(int factorial,int current) {
		if (current == 1) {
			return TailInvoke.done(factorial);
		}else{
			return TailInvoke.call(() -> (() -> factorialTailRecursiveLamb(factorial + current, current - 1)));
		}
	}

	public static void main(String[] args) {
//		System.out.println(factorialRecursive(100000));
//		System.out.println(factorialTailRecursive(0, 100000));
		System.out.println(factorialTailRecursiveLamb(0,100000).invoke());
	}

}
