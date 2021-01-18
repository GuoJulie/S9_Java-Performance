import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;

// test1:
// executor: Interface:ExecutorService --> submit(Runnable)
// return: Future object
// thread pool: ThreadPoolExecutor() --> corePoolSize:2; maximumPoolSize:4; keepAliveTime:60s
// thread real: 1


// test2:
// executor: Interface:ExecutorService --> submit(Runnable)
// return: Future object --> not set
// thread pool: ThreadPoolExecutor() --> corePoolSize:2; maximumPoolSize:4; keepAliveTime:60s
// thread real: 5


//test3:
//executor: Interface: ExecutorService --> execute(Runnable)
//return: none
//thread pool: Executors.newSingleThreadExecutor() --> PoolSize:1
//thread real: 1


//test4:
//executor: Interface: ExecutorService --> submit(Callable)
//return: Future object
//thread pool: Executors.newFixedThreadPool() --> PoolSize:3
//thread real: tache_1:10 + tache_2:10


public class TestThreadPoolExecutor {

	public static void main(String[] args) throws InterruptedException {

		// test1 + test2
		ExecutorService executorService = new ThreadPoolExecutor(2, 4, 60,
		    TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		
		
		// test3
//	    ExecutorService executorService = Executors.newSingleThreadExecutor();
	    	
		
	    // test4
//	    ExecutorService executorService = Executors.newFixedThreadPool(3);

	    
		// test1 + test3
		Future future = executorService.submit(new Runnable() {	// test1
//	    executorService.execute(new Runnable() {	// test3
			@Override
			public void run() {
				System.out.println("debut tache " + Thread.currentThread().getName());
		        try {
		          Thread.sleep(5000);
		        } catch (InterruptedException e) {
		          e.printStackTrace();
		        }
		        System.out.println("fin tache");
			}
		});
		
	    
		// test2
//		for (int i = 0; i < 5; i++) {
//			executorService.submit(new Runnable() {
//				@Override
//		        public void run() {
//		        	System.out.println("debut tache " + Thread.currentThread().getName());
//		        	try {
//		        		Thread.sleep(1000);
//		        	} catch (InterruptedException e) {
//		        		e.printStackTrace();
//		        	}
//		        	System.out.println("fin tache");
//				}
//			});
//		}
	    
	    
	    // test4
//	    Future<String> future1 = executorService.submit(new Callable<String>() {
//	        public String call() throws Exception {
//	        	int i = 0;
//	        	System.out.println("debut tache 1");
//	        	while (i < 10 && !Thread.currentThread().isInterrupted()) {
//	        		Thread.sleep(1000);
//	        		i++;
//	        	}
//	        	System.out.println("fin tache 1");
//	        	return "Tache 1";
//	        }
//	    });
//	    Future<String> future2 = executorService.submit(new Callable<String>() {
//	        public String call() throws Exception {
//	        	int i = 0;
//	        	System.out.println("debut tache 2 ");
//	        	while (i < 10 && !Thread.currentThread().isInterrupted()) {
//	        		Thread.sleep(500);
//	        		i++;
//	        	}
//	        	System.out.println("fin tache 2");
//	        	return "Tache 2";
//	        }
//	    });

	    
	    executorService.shutdown();
		executorService.awaitTermination(300, TimeUnit.SECONDS); // permet d'attendre de manière bloquante la fin de l'exécution de toutes les tâches soumises.
		
	    
		// test1
	    try {
	      System.out.println("resultat=" + future.get());
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    } catch (ExecutionException e) {
	      e.printStackTrace();
	    }
		
		
		// test4
//	    try {
//	    	executorService.awaitTermination(1, TimeUnit.HOURS);
//	        System.out.println("result1 = " + future1.get());
//	        System.out.println("result2 = " + future2.get());
//	    } catch (InterruptedException ie) {
//	    	ie.printStackTrace();
//	    } catch (ExecutionException ee) {
//	        ee.printStackTrace();
//	    }

	    
	    System.out.println("Fin thread principal");
	}
}