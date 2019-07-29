package org.songdan.tij.check.result;

import lombok.Data;

import java.util.Iterator;

/**
 * 兼容failfast,failover两种模式
 * @author: Songdan
 * @create: 2019-05-14 19:46
 **/
@Data
public class CheckResultItem implements CheckResult{

    private boolean pass;

    private String message;

    private String key;

    private int code;

    @Override
    public Iterator<CheckResultItem> iterator() {
        return new NullIterator();
    }

    public static CheckResultItem success(String key) {
        return create(true,key,null);
    }

    public static CheckResultItem fail(String message) {
        return fail(null,message);
    }

    public static CheckResultItem fail(String key,String message) {
        return create(false,key,message);
    }

    public static CheckResultItem fail(int code,String key,String message) {
        return create(false,key,message,code);
    }

    public static CheckResultItem create(boolean pass,String key,String message) {
        CheckResultItem checkResultItem = new CheckResultItem();
        checkResultItem.setPass(pass);
        checkResultItem.setKey(key);
        checkResultItem.setMessage(message);
        return checkResultItem;
    }

    public static CheckResultItem create(boolean pass,String key,String message,int code) {
        CheckResultItem checkResultItem = new CheckResultItem();
        checkResultItem.setPass(pass);
        checkResultItem.setKey(key);
        checkResultItem.setMessage(message);
        checkResultItem.setCode(code);
        return checkResultItem;
    }

    @Override
    public boolean addResult(CheckResult checkResult) {
        throw new UnsupportedOperationException();
    }

    class NullIterator implements Iterator<CheckResultItem> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public CheckResultItem next() {
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
