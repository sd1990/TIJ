package org.songdan.tij.query.expression;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 14 五月 2018
 */
public enum Comparisons {

    lt("<"), gt(">"), le("<="), ge(">=");

    private String signature;

    Comparisons(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

}
