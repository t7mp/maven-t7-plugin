/**
 * Copyright (C) 2010-2012 Joerg Bellmann <joerg.bellmann@googlemail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.googlecode.t7mp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.t7mp.scanner.ScannerConfiguration;

/**
 *
 * @author Joerg Bellmann
 *
 */
public class BaseConfiguration implements T7Configuration {

    public static final int DEFAULT_TOMCAT_HTTP_PORT = 8080;
    public static final int DEFAULT_TOMCAT_SHUTDOWN_PORT = 8005;
    public static final String DEFAULT_CONTEXT_PATH_ROOT = "ROOT";
    protected TomcatArtifact tomcatArtifact = new TomcatArtifact();
    /**
     *
     * @parameter expression="${t7.tomcatSetAwait}" default-value="true"
     *
     */
    protected boolean tomcatSetAwait = true;
    /**
     *
     * @parameter expression="${t7.lookInside}" default-value="false"
     *
     */
    protected boolean lookInside = false;
    /**
     *
     * @parameter expression="${t7.resolverUpdateSnapshotsAlways}" default-value="false"
     */
    protected boolean resolverUpdateSnapshotsAllways = false;
    /**
     *
     * @parameter expression="${t7.tomcatVersion}" default-value="7.0.37"
     */
    protected String tomcatVersion = TomcatArtifact.DEFAULT_TOMCAT_VERSION;
    /**
     *
     * @parameter expression="${t7.tomcatHttpPort}" default-value="8080"
     */
    protected int tomcatHttpPort = DEFAULT_TOMCAT_HTTP_PORT;
    /**
     *
     * @parameter expression="${t7.tomcatShutdownPort}" default-value="8005"
     */
    protected int tomcatShutdownPort = DEFAULT_TOMCAT_SHUTDOWN_PORT;
    /**
     *
     * @parameter expression="${t7.tomcatShutdownCommand}" default-value="SHUTDOWN"
     */
    protected String tomcatShutdownCommand = "SHUTDOWN";
    /**
     *
     * @parameter expression="${t7.tomcatShutdownHost}" default-value="localhost"
     *
     */
    protected String tomcatShutdownHost = "localhost";
    /**
     *
     * @parameter expression="${t7.tomcatHostName}" default-value="localhost"
     *
     */
    protected String tomcatHostName = "localhost";
    /**
     *
     * @parameter default-value="${project.build.directory}/tomcat"
     * @readonly // at the moment
     *
     */
    protected File catalinaBase;
    /**
     *
     * @parameter expression="${t7.tomcatConfigDirectory}" default-value="${basedir}/src/main/tomcat/conf"
     * @optional
     *
     */
    protected File tomcatConfigDirectory;
    /**
     *
     * @parameter expression="${t7.overwriteWebXML}"
     */
    protected File overwriteWebXML;
    /**
     *
     * @parameter default-value="${project.build.directory}/${project.build.finalName}"
     * @readonly
     *
     */
    protected File webappOutputDirectory;
    /**
     *
     * @parameter expression="${t7.contextPath}" default-value="${project.build.finalName}"
     * @optional
     *
     */
    protected String contextPath = DEFAULT_CONTEXT_PATH_ROOT;
    /**
     * @parameter default-value="${project.build.finalName}"
     * @readonly
     */
    protected String buildFinalName = DEFAULT_CONTEXT_PATH_ROOT;
    /**
     * @parameter default-value="${basedir}/src/main/webapp"
     * @readonly
     */
    protected File webappSourceDirectory = null;
    /**
     * @parameter default-value="${project.packaging}"
     *
     *
     */
    protected String packaging = "war";
    /**
     *
     * @parameter expression="${t7.scanClasses}" default-value="false"
     */
    protected boolean scanClasses = false;
    /**
     * @parameter default-value="${basedir}/target/classes"
     * @readonly
     */
    protected File webappClassDirectory = null;
    /**
     * @parameter @optional
     */
    protected File contextFile = null;
    /**
     *
     * @parameter
     */
    protected ArrayList<WebappArtifact> webapps = new ArrayList<WebappArtifact>();
    /**
     *
     * @parameter
     */
    protected Map<String, String> systemProperties = new HashMap<String, String>();
    /**
     *
     * @parameter
     */
    protected List<JarArtifact> libs = new ArrayList<JarArtifact>();
    /**
     *
     * @parameter
     */
    protected ArrayList<ScannerConfiguration> scanners = new ArrayList<ScannerConfiguration>();
    /**
     *
     * @parameter default-value="false"
     */
    protected boolean suspendConsoleOutput = false;
    /**
     * @parameter
     */
    protected ConfigurationArtifact configArtifact = null;

    /**
     *
     * @parameter default-value="true"
     */
    protected boolean deleteDefaultTomcatWebapps = true;

    /**
     * @parameter default-value="false"
     */
    protected boolean deleteTomcatDefaultRootWebapp = false;

    /**
     * @parameter default-value="false"
     */
    protected boolean deleteTomcatDefaultManagerWebapp = false;

    /**
     * @parameter default-value="false" 
     */
    protected boolean deleteTomcatDefaultHostManagerWebapp = false;

    /**
     * @parameter default-value="false"
     */
    protected boolean deleteTomcatDefaultExamplesWebapp = false;

    /**
     * @parameter default-value="false"
     */
    protected boolean deleteTomcatDefaultDocsWebapp = false;

    /**
     *
     *
     * @parameter default-value="1"
     */
    protected int instanceCount = 1;

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#isTomcatSetAwait()
     */
    @Override
    public boolean isTomcatSetAwait() {
        return tomcatSetAwait;
    }

    public void setTomcatSetAwait(boolean tomcatSetAwait) {
        this.tomcatSetAwait = tomcatSetAwait;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#isLookInside()
     */
    @Override
    public boolean isLookInside() {
        return lookInside;
    }

    public void setLookInside(boolean lookInside) {
        this.lookInside = lookInside;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#isResolverUpdateSnapshotsAllways()
     */
    @Override
    public boolean isResolverUpdateSnapshotsAllways() {
        return resolverUpdateSnapshotsAllways;
    }

    public void setResolverUpdateSnapshotsAllways(boolean resolverUpdateSnapshotsAllways) {
        this.resolverUpdateSnapshotsAllways = resolverUpdateSnapshotsAllways;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getTomcatVersion()
     */
    @Override
    public String getTomcatVersion() {
        return tomcatVersion;
    }

    public void setTomcatVersion(String tomcatVersion) {
        this.tomcatVersion = tomcatVersion;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getTomcatHttpPort()
     */
    @Override
    public int getTomcatHttpPort() {
        return tomcatHttpPort;
    }

    @Override
    public void setTomcatHttpPort(int tomcatHttpPort) {
        this.tomcatHttpPort = tomcatHttpPort;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getTomcatShutdownPort()
     */
    @Override
    public int getTomcatShutdownPort() {
        return tomcatShutdownPort;
    }

    @Override
    public void setTomcatShutdownPort(int tomcatShutdownPort) {
        this.tomcatShutdownPort = tomcatShutdownPort;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getTomcatShutdownCommand()
     */
    @Override
    public String getTomcatShutdownCommand() {
        return tomcatShutdownCommand;
    }

    public void setTomcatShutdownCommand(String tomcatShutdownCommand) {
        this.tomcatShutdownCommand = tomcatShutdownCommand;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getTomcatShutdownHost()
     */
    @Override
    public String getTomcatShutdownHost() {
        return tomcatShutdownHost;
    }

    public void setTomcatShutdownHost(String tomcatShutdownHost) {
        this.tomcatShutdownHost = tomcatShutdownHost;
    }

    @Override
    public String getTomcatHostName() {
        return tomcatHostName;
    }

    public void setTomcatHostName(String tomcatHostName) {
        this.tomcatHostName = tomcatHostName;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getCatalinaBase()
     */
    @Override
    public File getCatalinaBase() {
        return catalinaBase;
    }

    @Override
    public void setCatalinaBase(File catalinaBase) {
        this.catalinaBase = catalinaBase;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getTomcatConfigDirectory()
     */
    @Override
    public File getTomcatConfigDirectory() {
        return tomcatConfigDirectory;
    }

    public void setTomcatConfigDirectory(File tomcatConfigDirectory) {
        this.tomcatConfigDirectory = tomcatConfigDirectory;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getOverwriteWebXML()
     */
    @Override
    public File getOverwriteWebXML() {
        return overwriteWebXML;
    }

    public void setOverwriteWebXML(File overwriteWebXML) {
        this.overwriteWebXML = overwriteWebXML;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getWebappOutputDirectory()
     */
    @Override
    public File getWebappOutputDirectory() {
        return webappOutputDirectory;
    }

    public void setWebappOutputDirectory(File webappOutputDirectory) {
        this.webappOutputDirectory = webappOutputDirectory;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getContextPath()
     */
    @Override
    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getBuildFinalName()
     */
    @Override
    public String getBuildFinalName() {
        return buildFinalName;
    }

    public void setBuildFinalName(String buildFinalName) {
        this.buildFinalName = buildFinalName;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getWebappSourceDirectory()
     */
    @Override
    public File getWebappSourceDirectory() {
        return webappSourceDirectory;
    }

    public void setWebappSourceDirectory(File webappSourceDirectory) {
        this.webappSourceDirectory = webappSourceDirectory;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getPackaging()
     */
    @Override
    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#isScanClasses()
     */
    @Override
    public boolean isScanClasses() {
        return scanClasses;
    }

    public void setScanClasses(boolean scanClasses) {
        this.scanClasses = scanClasses;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getWebappClassDirectory()
     */
    @Override
    public File getWebappClassDirectory() {
        return webappClassDirectory;
    }

    public void setWebappClassDirectory(File webappClassDirectory) {
        this.webappClassDirectory = webappClassDirectory;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getContextFile()
     */
    @Override
    public File getContextFile() {
        return contextFile;
    }

    public void setContextFile(File contextFile) {
        this.contextFile = contextFile;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getWebapps()
     */
    @Override
    public ArrayList<WebappArtifact> getWebapps() {
        return webapps;
    }

    public void setWebapps(ArrayList<WebappArtifact> webapps) {
        this.webapps = webapps;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getSystemProperties()
     */
    @Override
    public Map<String, String> getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(Map<String, String> systemProperties) {
        this.systemProperties = systemProperties;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getLibs()
     */
    @Override
    public List<JarArtifact> getLibs() {
        return libs;
    }

    public void setLibs(List<JarArtifact> libs) {
        this.libs = libs;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getScanners()
     */
    @Override
    public ArrayList<ScannerConfiguration> getScanners() {
        return scanners;
    }

    public void setScanners(ArrayList<ScannerConfiguration> scanners) {
        this.scanners = scanners;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#isSuspendConsoleOutput()
     */
    @Override
    public boolean isSuspendConsoleOutput() {
        return suspendConsoleOutput;
    }

    public void setSuspendConsoleOutput(boolean suspendConsoleOutput) {
        this.suspendConsoleOutput = suspendConsoleOutput;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getConfigArtifact()
     */
    @Override
    public ConfigurationArtifact getConfigArtifact() {
        return configArtifact;
    }

    public void setConfigArtifact(ConfigurationArtifact configArtifact) {
        this.configArtifact = configArtifact;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#isWebProject()
     */
    @Override
    public boolean isWebProject() {
        return this.packaging.equals("war");
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#isDownloadDefaultTomcatWebapps()
     */
    @Override
    public boolean isDeleteDefaultTomcatWebapps() {
        return deleteDefaultTomcatWebapps;
    }

    public void setDownloadDefaultTomcatWebapps(boolean downloadDefaultTomcatWebapps) {
        this.deleteDefaultTomcatWebapps = downloadDefaultTomcatWebapps;
    }

    @Override
    public boolean isDeleteTomcatDefaultRootWebapp() {
        return deleteTomcatDefaultRootWebapp;
    }

    public void setDeleteTomcatDefaultRootWebapp(boolean deleteTomcatDefaultRootWebapp) {
        this.deleteTomcatDefaultRootWebapp = deleteTomcatDefaultRootWebapp;
    }

    @Override
    public boolean isDeleteTomcatDefaultManagerWebapp() {
        return deleteTomcatDefaultManagerWebapp;
    }

    public void setDeleteTomcateDefaultManagerWebapp(boolean deleteTomcatDefaultManagerWebapp) {
        this.deleteTomcatDefaultManagerWebapp = deleteTomcatDefaultManagerWebapp;
    }

    public boolean isDeleteTomcatDefaultHostManagerWebapp() {
        return deleteTomcatDefaultHostManagerWebapp;
    }

    public void setDeleteTomcatDefaultHostManagerWebapp(boolean deleteTomcatDefaultHostManagerWebapp) {
        this.deleteTomcatDefaultHostManagerWebapp = deleteTomcatDefaultHostManagerWebapp;
    }

    public boolean isDeleteTomcatDefaultExamplesWebapp() {
        return deleteTomcatDefaultExamplesWebapp;
    }

    public void setDeleteTomcatDefaultExamplesWebapp(boolean deleteTomcatDefaultExamplesWebapp) {
        this.deleteTomcatDefaultExamplesWebapp = deleteTomcatDefaultExamplesWebapp;
    }

    public boolean isDeleteTomcatDefaultDocsWebapp() {
        return deleteTomcatDefaultDocsWebapp;
    }

    public void setDeleteTomcatDefaultDocsWebapp(boolean deleteTomcatDefaultDocsWebapp) {
        this.deleteTomcatDefaultDocsWebapp = deleteTomcatDefaultDocsWebapp;
    }

    /* (non-Javadoc)
     * @see com.googlecode.t7mp.PluginConfiguration#getTomcatArtifact()
     */
    @Override
    public TomcatArtifact getTomcatArtifact() {
        return this.tomcatArtifact;
    }

    public void setTomcatArtifact(TomcatArtifact tomcatArtifact) {
        this.tomcatArtifact = tomcatArtifact;
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(int instanceCount) {
        this.instanceCount = instanceCount;
    }
}
