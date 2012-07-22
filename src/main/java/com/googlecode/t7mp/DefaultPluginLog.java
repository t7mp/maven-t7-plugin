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

/**
 * TODO Comment.
 * @author Joerg Bellmann
 *
 */
public class DefaultPluginLog implements PluginLog {

    @Override
    public void debug(CharSequence message) {
        System.err.println(formatMessage(message));
    }

    @Override
    public void debug(Throwable cause) {
        cause.printStackTrace();
    }

    @Override
    public void debug(CharSequence message, Throwable cause) {
        System.err.println(formatMessage(message));
        cause.printStackTrace();
    }

    @Override
    public String formatMessage(CharSequence message) {
        return message.toString();
    }

    @Override
    public void error(CharSequence message) {
        System.err.println(formatMessage(message));
    }

    @Override
    public void error(Throwable cause) {
        cause.printStackTrace();
    }

    @Override
    public void error(CharSequence message, Throwable cause) {
        System.err.println(formatMessage(message));
        cause.printStackTrace();
    }

    @Override
    public void info(CharSequence message) {
        System.err.println(formatMessage(message));
    }

    @Override
    public void info(Throwable cause) {
        cause.printStackTrace();
    }

    @Override
    public void info(CharSequence message, Throwable cause) {
        System.err.println(formatMessage(message));
        cause.printStackTrace();
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isErrorEnabled() {
        return false;
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    @Override
    public void warn(CharSequence message) {
        System.err.println(formatMessage(message));
    }

    @Override
    public void warn(Throwable cause) {
        cause.printStackTrace();
    }

    @Override
    public void warn(CharSequence message, Throwable cause) {
        System.err.println(formatMessage(message));
        cause.printStackTrace();
    }

}
