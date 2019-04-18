package org.songdan.tij.query.adapter.mysql;

import com.google.common.base.Joiner;

public interface MysqlConstants {

    String IS_NULL = " is null ";

    String EQUAL = " = ";

    String NOT_EQUAL = " != ";

    String IN = " in ";

    String LIKE = " like ";
    String LT = " < ";
    String GT = " > ";
    String LE = " <= ";
    String GE = " >= ";

    Joiner COMMA_JOINER = Joiner.on(",");

    Joiner AND_JOINER = Joiner.on(" and ");

    Joiner OR_JOINER = Joiner.on(" or ");

    String AND = " AND ";
    String OR = " OR ";
    String PERCENT = "%";
    String QUOTE = "'";
}
