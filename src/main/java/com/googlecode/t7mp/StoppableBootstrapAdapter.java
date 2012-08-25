package com.googlecode.t7mp;

import org.apache.catalina.startup.Bootstrap;

/**
 * 
 * 
 * @author jbellmann
 *
 */
public class StoppableBootstrapAdapter implements Stoppable {

    private final Bootstrap bootstrap;

    public StoppableBootstrapAdapter(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    public void stop() {
        try {
            bootstrap.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
