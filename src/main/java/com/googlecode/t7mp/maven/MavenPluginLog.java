package com.googlecode.t7mp.maven;

import org.apache.maven.plugin.logging.Log;

import com.googlecode.t7mp.PluginLog;

public class MavenPluginLog implements PluginLog {

    private final Log realLog;

    public MavenPluginLog(Log realLog) {
        this.realLog = realLog;
    }

    @Override
    public String formatMessage(CharSequence message) {
        return message.toString();
    }

    @Override
    public void debug(CharSequence message) {
        realLog.debug(message);
    }

    @Override
    public void debug(Throwable cause) {
        realLog.debug(cause);
    }

    @Override
    public void debug(CharSequence message, Throwable cause) {
        realLog.debug(message, cause);

    }

    @Override
    public void error(CharSequence message) {
        realLog.error(message);
    }

    @Override
    public void error(Throwable cause) {
        realLog.error(cause);
    }

    @Override
    public void error(CharSequence message, Throwable cause) {
        realLog.error(message, cause);
    }

    @Override
    public void info(CharSequence message) {
        realLog.info(message);
    }

    @Override
    public void info(Throwable cause) {
        realLog.info(cause);
    }

    @Override
    public void info(CharSequence message, Throwable cause) {
        realLog.info(message, cause);
    }

    @Override
    public boolean isDebugEnabled() {
        return realLog.isDebugEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return realLog.isErrorEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return realLog.isInfoEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return realLog.isWarnEnabled();
    }

    @Override
    public void warn(CharSequence message) {
        realLog.warn(message);
    }

    @Override
    public void warn(Throwable cause) {
        realLog.warn(cause);
    }

    @Override
    public void warn(CharSequence message, Throwable cause) {
        realLog.warn(message, cause);
    }

}
