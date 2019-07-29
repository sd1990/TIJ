package org.songdan.tij.check.result;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * 兼容failfast,failover两种模式
 * @author: Songdan
 * @create: 2019-05-14 19:46
 **/
@Data
public class CheckResultGroup implements CheckResult {

    private List<CheckResult> checkResultList = Lists.newArrayList();

    @Override
    public boolean isPass() {
        return !Iterables.tryFind(checkResultList, new Predicate<CheckResult>() {
            @Override
            public boolean apply(CheckResult input) {
                return !input.isPass();
            }
        }).isPresent();
    }

    @Override
    public Iterator<CheckResultItem> iterator() {
        return new It(checkResultList.listIterator());
    }

    @Override
    public boolean addResult(CheckResult checkResult) {
        return checkResultList.add(checkResult);
    }

    public List<CheckResultItem> build(List<CheckResult> checkResultList) {
        List<CheckResultItem> list = Lists.newArrayList();
        for (CheckResult checkResult : checkResultList) {
            if (checkResult instanceof CheckResultItem) {
                list.add((CheckResultItem) checkResult);
            } else {
                list.addAll(build(((CheckResultGroup) checkResult).getCheckResultList()));
            }
        }
        return list;
    }

    class It implements Iterator<CheckResultItem> {

        private Stack<ListIterator<CheckResult>> stack = new Stack<>();

        public It(ListIterator<CheckResult> iterator) {
            stack.push(iterator);
        }

        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            } else {
                ListIterator<CheckResult> peek = stack.peek();
                if (!peek.hasNext()) {
                    stack.pop();
                    //继续递归
                    return hasNext();
                }
                CheckResult next = peek.next();
                if (!CheckResultGroup.class.isInstance(next)) {
                    //回退
                    peek.previous();
                    return true;
                } else {
                    CheckResultGroup nextMenu = (CheckResultGroup) next;
                    stack.push(nextMenu.checkResultList.listIterator());
                    return hasNext();
                }
            }
        }

        @Override
        public CheckResultItem next() {
            if (hasNext()) {
                Iterator<CheckResult> componentIterator = stack.peek();
                return (CheckResultItem) componentIterator.next();
            } else {
                return null;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
