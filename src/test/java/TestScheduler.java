import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.simpl.RAMJobStore;
import org.quartz.simpl.SimpleThreadPool;

/**
 * Created by Kutekidila on 25.05.2016.
 */
public class TestScheduler {

    static Logger logger = LogManager.getLogger(TestScheduler.class);

    public static void main(String[] args) {
        TestScheduler example = new TestScheduler();
        example.startSchedulerDirect();
    }

    public void startSchedulerDirect() {
        DirectSchedulerFactory factory = DirectSchedulerFactory.getInstance();
        try {
                // Initialize the Scheduler Factory with 10 threads
                factory.createVolatileScheduler(500);
                // Get a scheduler from the factory
                 factory.createScheduler("job1","1",new SimpleThreadPool(1,Thread.NORM_PRIORITY),new RAMJobStore());
                 factory.createScheduler("job2","2",new SimpleThreadPool(2,Thread.NORM_PRIORITY),new RAMJobStore());

                Scheduler scheduler1 = factory.getScheduler("job1");
                Scheduler scheduler2 = factory.getScheduler("job2");

                //Start the scheduler running
                scheduler1.start();
                scheduler2.start();
                System.out.println("Scheduler starting up..." + scheduler1.getSchedulerName());
                System.out.println("Scheduler starting up..." + scheduler2.getSchedulerName());


//                factory.createVolatileScheduler(4);
//
//                System.out.println("Scheduler starting up..." + scheduler1.getSchedulerName());
//                System.out.println("Scheduler starting up..." + scheduler2.getSchedulerName());

            } catch (SchedulerException ex) {
                logger.error(ex);
        }
    }
}