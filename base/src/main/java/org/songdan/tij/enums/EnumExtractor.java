package org.songdan.tij.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 枚举提取器
 *
 * @author song dan
 * @since 10 一月 2018
 */
public abstract class EnumExtractor {

    private Class<? extends Enum> enumClass;

    private ConcurrentHashMap<String, Future<Optional<Enum>>> enumCache = new ConcurrentHashMap<>();

    public EnumExtractor(Class<? extends Enum> enumClass) {
        this.enumClass = enumClass;
    }

	/**
	 * 根据给定的value从提取器中提取枚举
	 * @param value
	 * @return
	 */
	public Optional<Enum> extract(Object value) {
        while (true) {
            Future<Optional<Enum>> future = enumCache.get(getKey(value));
            if (future == null) {
                FutureTask<Optional<Enum>> task = new FutureTask<>(() -> doExtract(value));
                future = enumCache.putIfAbsent(getKey(value), task);
                if (Objects.isNull(future)) {
                    future = task;
                    task.run();
                }
            }
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                enumCache.remove(getKey(value));
            }
        }
    }

    private Optional<Enum> doExtract(Object value) {
	    if(Objects.isNull(value)){
            return Optional.empty();
        }
        Enum[] constants = enumClass.getEnumConstants();
        return Arrays.stream(constants).filter(item -> check(value, item)).findFirst();
    }

    private String getKey(Object value) {
        return String.valueOf(value);
    }

    protected abstract boolean check(Object expect, Enum item);

    public Class<? extends Enum> getEnumClass() {
        return enumClass;
    }
}
