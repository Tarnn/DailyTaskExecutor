package main.java.com.taranjitkang.DailyEventScheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.lang.Runnable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class TaskExecutor {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void schedule(Runnable command){

        //TODO set Local Date time for Execution
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd hh:mm:ss a");
        LocalDateTime currentTime = LocalDateTime.now();
        String date = currentTime.format(formatter);

        //Execution Date Every 11:59PM
        LocalDateTime executionDate = LocalDateTime.of(currentTime.getYear(),
                                                        currentTime.getMonth(),
                                                        currentTime.getDayOfMonth(),
                                                        13, 22);
        long initialDelay;

        //If Execution date > currentTime -> Execute 1 Day after
        if(currentTime.isAfter(executionDate)) initialDelay = currentTime.until(executionDate.plusDays(1), ChronoUnit.MILLIS);
        else initialDelay = currentTime.until(executionDate, ChronoUnit.MILLIS);

        //Delay of 86400000 milliseconds -> 24 Hours
        long delay = TimeUnit.HOURS.toMillis(24);

        System.out.println("Executing Task.....");
        ScheduledFuture<?> futureTask = scheduler.scheduleWithFixedDelay(command, initialDelay, delay, TimeUnit.MILLISECONDS);
        System.out.println("Task executed!");

    }
}
