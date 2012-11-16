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

import java.io.File;
import java.util.List;
import java.util.Map;

import com.googlecode.t7mp.scanner.ScannerConfiguration;

/**
 *
 * @author Joerg Bellmann
 *
 */
public interface T7Configuration {

    boolean isTomcatSetAwait();

    boolean isLookInside();

    boolean isResolverUpdateSnapshotsAllways();

    String getTomcatVersion();

    int getTomcatHttpPort();

    void setTomcatHttpPort(int port);

    int getTomcatShutdownPort();

    void setTomcatShutdownPort(int port);

    String getTomcatShutdownCommand();

    String getTomcatShutdownHost();

    String getTomcatHostName();

    File getCatalinaBase();

    void setCatalinaBase(File catalinaBase);

    File getTomcatConfigDirectory();

    File getOverwriteWebXML();

    File getWebappOutputDirectory();

    String getContextPath();

    String getBuildFinalName();

    File getWebappSourceDirectory();

    String getPackaging();

    boolean isScanClasses();

    File getWebappClassDirectory();

    File getContextFile();

    List<WebappArtifact> getWebapps();

    Map<String, String> getSystemProperties();

    List<JarArtifact> getLibs();

    List<ScannerConfiguration> getScanners();

    boolean isSuspendConsoleOutput();

    ConfigurationArtifact getConfigArtifact();

    boolean isWebProject();

    boolean isDownloadTomcatExamples();

    boolean isDownloadDefaultTomcatWebapps();

    TomcatArtifact getTomcatArtifact();

    int getInstanceCount();

}
