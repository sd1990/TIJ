package org.songdan.tij.thread;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author: Songdan
 * @create: 2019-11-14 20:27
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class StringJMH {

    @Benchmark
    public void test() {
        String str = "set-xr-rc-zeus-zeus-test02-201911011732-8994308";
        str.replaceAll("-", "");
    }

    @Benchmark
    public void testApache() {
        String str = "set-xr-rc-zeus-zeus-test02-201911011732-8994308";
        StringUtils.replaceChars(str,"-", "");

    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder().include(StringJMH.class.getSimpleName()).build();
        new Runner(opts).run();
    }


}
