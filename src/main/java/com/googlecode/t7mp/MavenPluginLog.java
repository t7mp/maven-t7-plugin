/**
 * Copyright (C) 2010-2012 Joerg Bellmann <joerg.bellmann@googlemail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.t7mp;

import org.apache.maven.plugin.logging.Log;

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
