package com.googlecode.t7mp.maven;

import com.googlecode.t7mp.steps.Context;

/**
 * {@link Context}implementation that will be used when running in
 * a Maven-Plugin.
 * 
 * @author Joerg Bellmann
 *
 */
public interface MavenPluginContext extends Context {
    
    AbstractT7BaseMojo getMojo();

}
