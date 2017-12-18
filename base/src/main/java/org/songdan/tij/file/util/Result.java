package org.songdan.tij.file.util;


/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 29 十一月 2017
 */
public class Result {

	private boolean ok;

	private String cause;

	private Result(boolean ok, String cause) {
		this.ok = ok;
		this.cause = cause;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public static Result success() {
		return new Result(true, null);
	}


	public static Result fail(String msg) {
		return new Result(false, msg);
	}
}
