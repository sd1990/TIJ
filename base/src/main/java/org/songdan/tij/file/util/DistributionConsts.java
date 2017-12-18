package org.songdan.tij.file.util;

import java.io.File;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 29 十一月 2017
 */
public final class DistributionConsts {

	public static final String HOT = "hot";
	public static final String COLD = "cold";
	public static final String GENERAL = "general";
	public static final String DESK = "desk";
	public static final String PLUG = "plug";
	public static final String SUFFIX = ".csv";

	private static final String BJ = "bj/";
	private static final String NO_BJ = "no_bj/";

	public static final String BASE = "/Users/songdan/Desktop"+ File.separator;

	/**
	 * 陈列模版
	 */
	public static final String DISPLAY_TEMPALE = BASE + "distribution/display_template/";

	public static final String DISPLAY_TEMPALE_BJ = DISPLAY_TEMPALE + BJ;

	public static final String DISPLAY_TEMPALE_NO_BJ = DISPLAY_TEMPALE + NO_BJ;

	/**
	 * 铺货模版
	 */
	public static final String DISTRIBUTION_TEMPALE = BASE+ "distribution/distribution_template/";

	public static final String DISTRIBUTION_TEMPALE_BJ = DISTRIBUTION_TEMPALE + BJ;

	public static final String DISTRIBUTION__TEMPALE_NO_BJ = DISTRIBUTION_TEMPALE + NO_BJ;

	/**
	 * 结果
	 */
	public static final String RESULT_TARGET = BASE + "distribution/result/";

	public static final String RESULT_TARGET_BJ = RESULT_TARGET + BJ;

	public static final String RESULT_TARGET_NO_BJ = RESULT_TARGET + NO_BJ;

	public static final String UPLOAD_DIR = BASE + "distribution/upload/";

	public static final String UPLOAD_TARGET_BJ = UPLOAD_DIR + BJ;

	public static final String UPLOAD_TARGET_NO_BJ = UPLOAD_DIR + NO_BJ;

	

}
