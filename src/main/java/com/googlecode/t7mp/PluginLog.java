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
 * The Plugin-Log-Interface. Only for T7-Plugin-Logging.
 * 
 * @author Joerg Bellmann
 *
 */
public interface PluginLog {

    String formatMessage(CharSequence message);

    void debug(CharSequence message);

    void debug(Throwable cause);

    void debug(CharSequence message, Throwable cause);

    void error(CharSequence message);

    void error(Throwable cause);

    void error(CharSequence message, Throwable cause);

    void info(CharSequence message);

    void info(Throwable cause);

    void info(CharSequence message, Throwable cause);

    boolean isDebugEnabled();

    boolean isErrorEnabled();

    boolean isInfoEnabled();

    boolean isWarnEnabled();

    void warn(CharSequence message);

    void warn(Throwable cause);

    void warn(CharSequence message, Throwable cause);
}
