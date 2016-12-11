package org.songdan.tij.thread;

/**
 * @author Songdan
 * @date 2016/9/12 12:56
 */
public class DateFormatMultiThreadEnv {

    public static void main(String[] args) throws Exception {

       /* final DateFormat format = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> task = () -> format.parse("20101022");

        //pool with 5 threads
        ExecutorService exec = Executors.newFixedThreadPool(5);
        List<Future<Date>> results = new ArrayList<>();

        //perform 10 date conversions
        for(int i = 0 ; i < 10 ; i++){
            results.add(exec.submit(task));
        }
        exec.shutdown();

        //look at the results
        for(Future<Date> result : results){
            System.out.println(result.get());
        }*/
    }
}
