package com.googlecode.t7mp;

/**
 * Let Maven sleep a long time.
 * 
 * 
 * @author Joerg Bellmann
 *
 */
public final class ExecutionLock {
    
    public synchronized void lock() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
