package SchedulerTest;


import client.scheduler.Scheduler;
import java.time.LocalTime;

class Schedule{
    public static void main(String args[]){
        Scheduler s=new Scheduler();
        s.schedule(LocalTime.of(11,20));
    }
}