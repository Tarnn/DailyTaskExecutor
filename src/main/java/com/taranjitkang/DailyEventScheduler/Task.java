/*
Executes daily tasks for linux commands at a set period of time
 */

package main.java.com.taranjitkang.DailyEventScheduler;


import java.io.IOException;
import java.lang.Runnable;

public class Task {

    private String[] commands;

    public Task() {
        System.out.println("Please enter some commands - String Array");
    }

    public Task(String[] commands) {
        String listCommands = "Commands Entered: ";
        for (String command : commands) {
            listCommands += command + " ";
        }
        System.out.print(listCommands);
        this.commands = commands;
    }

    public void setCommands(String[] commandArray) {
        this.commands = commandArray;
    }

    public String[] getCommands() {
        return this.commands;
    }

    private Runnable createRunnable(String[] commands) {
        Runnable runnable = new Runnable() {
            public void run() {
                //TODO code to Run
                try {
                    Runtime runtime = Runtime.getRuntime();
                    Process pr = runtime.exec(commands);
                } catch (IOException ioe) {
                    System.out.println("IO exception Occured");
                }

            }
        };
        return runnable;
    }

    public static void main(String[] args) {
        String[] command = {"sh", "-c", "touch fileCreated1.txt"};
        Task myTask = new Task(command);
        Runnable myRunnable = myTask.createRunnable(myTask.getCommands());

        TaskExecutor execute = new TaskExecutor();
        execute.schedule(myRunnable);
    }
}