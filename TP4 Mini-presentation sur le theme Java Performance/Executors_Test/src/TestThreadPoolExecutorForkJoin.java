import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

//	test5:
//	executor: Interface: ExecutorService --> submit()
//	return: Future object
//	thread pool: ForkJoinPool() --> corePoolSize: available processors [choose 2] (ForkJoinPool --> public pool)
//	thread real: realsize / threshold (-->fork)


public class TestThreadPoolExecutorForkJoin extends RecursiveTask<Integer>{
    
    public static final int threshold = 2;
    
    private int start;
    private int end;
    
    public TestThreadPoolExecutorForkJoin(int start, int end) {
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Integer compute() {
        int sum = 0;
        // determine whether the work is performed or split by the array length
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int split = (start + end) / 2;
            TestThreadPoolExecutorForkJoin demo1 = new TestThreadPoolExecutorForkJoin(start, split);
            TestThreadPoolExecutorForkJoin demo2 = new TestThreadPoolExecutorForkJoin(split + 1, end);
            
            demo1.fork();
            demo2.fork();
            
            int result1 = demo1.join();
            int result2 = demo2.join();
            sum = result1 + result2;
            
//            List<Future<Integer>> futures = invokeAll(new TestThreadPoolExecutorForkJoin(start, split),
//            			new TestThreadPoolExecutorForkJoin(split + 1, end));
//            
//            for (Future<Integer> future : futures) {
//            	sum = +future.get();
//            }
        }
        
        return sum;
    }

    
    public static void main(String[] args) {
    	System.out.println("Objectif: 1 à 100 pour la somme");
        System.out.println("Threshold is " + threshold);
        
    	int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(Integer.toString(processors) + " processor"
                + (processors != 1 ? "s are " : " is ")
                + "available");
        
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        
        TestThreadPoolExecutorForkJoin forkjoindemo = new TestThreadPoolExecutorForkJoin(1, 100);
        
        long startTime = System.currentTimeMillis();
//        forkJoinPool.invoke(forkjoindemo);
        Future<Integer> future = forkJoinPool.submit(forkjoindemo);
        long endTime = System.currentTimeMillis();
        
        try {
            System.out.println("sum(1,100):" + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("Treatment took " + (endTime - startTime) + 
                " milliseconds.");
        
    }
}