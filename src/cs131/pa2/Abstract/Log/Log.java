package cs131.pa2.Abstract.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import cs131.pa2.Abstract.Tunnel;
import cs131.pa2.Abstract.Vehicle;

public class Log {

    private final BlockingQueue<Event> log = new LinkedBlockingQueue<Event>();
    private final AtomicInteger eventCounter = new AtomicInteger();
    public Log() {
    }

    public void addToLog(Vehicle client, Tunnel server, EventType type, int sig) {
        try {
            log.put(new Event(client, server, type, sig));
        } catch (InterruptedException ex) {
            add_error_msg();
            ex.printStackTrace();
        }
    }
    
    public void addToLog(Vehicle client, Tunnel server, EventType type) {
        try {
            log.put(new Event(client, server, type));
        } catch (InterruptedException ex) {
            add_error_msg();
            ex.printStackTrace();
        }
    }

    public void addToLog(Vehicle client, EventType type) {
        try {
            log.put(new Event(client, type));
           // System.out.println("well i dont know what is going on" +  type) ;
        } catch (InterruptedException ex) {
            add_error_msg();
            ex.printStackTrace();
        }
    }

    public void addToLog(EventType type) {
        try {
            log.put(new Event(type));
        } catch (InterruptedException ex) {
            add_error_msg();
            ex.printStackTrace();
        }
    }

    public final boolean isEmpty() {
        return log.isEmpty();
    }

    private void add_error_msg() {
        try {
            log.put(new Event(EventType.ERROR));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public final void clearLog() {
        log.clear();
    }

    public final Event get() {
        Event next = new Event(EventType.ERROR);
        try {
            next = log.take();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return next;
    }
    
    public boolean contains(Event event){
        return log.contains(event);
    }
    
    public Event peek(){
        return log.peek();
    }
    
    public int nextLogEventNumber(){
        return eventCounter.getAndIncrement();
    }
    
    
}
