package com.googlecode.t7mp;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TomcatDirectorySetupTest {
	
	private File baseDir = null;
	
	private List<String> expectedDirectoryNames = Arrays.asList(new String[]{"conf","lib", "logs", "temp", "webapps", "work"});
	
	@Before
	public void setUp(){
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		baseDir = new File(tempDir, "tomcat_TEST_" +  UUID.randomUUID().toString());
		Assert.assertTrue(baseDir.mkdirs());
	}
	
	@After
	public void tearDown() throws IOException{
		FileUtils.deleteDirectory(baseDir);
	}
	
	@Test
	public void testCreateDirectory() throws MojoExecutionException{
		TomcatDirectorySetup creator = new TomcatDirectorySetup(baseDir);
		creator.createTomcatDirectories();
		for(String directory : expectedDirectoryNames){
			Assert.assertTrue(checkExist(directory));
		}
	}
	
	private boolean checkExist(String directoryName){
		File targetDirectory = new File(baseDir, "/"+ directoryName + "/");
		return targetDirectory.exists();
	}
	
	@Test(expected=TomcatSetupException.class)
	public void testBaseDirNotExist(){
		TomcatDirectorySetup creator = new TomcatDirectorySetup(new File("/peter"));
		creator.createTomcatDirectories();
	}
	
	@Test(expected=TomcatSetupException.class)
	public void testTomcatDirectoryCouldNotBeCreated(){
		TomcatDirectorySetup creator = new TomcatDirectorySetup(new File("/peter"));
		creator.createTomcatDirectory("test");
	}
	
//	@Test
//	public void testConfigurator() throws MojoExecutionException{
//		TomcatConfigurator configurator = new TomcatConfigurator(catalinaBaseDir, log, setupUtil);
//		configurator.createTomcatDirectories();
//		File[] createdDirectories = catalinaBaseDir.listFiles(new FileFilter(){
//			@Override
//			public boolean accept(File file) {
//				return file.isDirectory();
//			}
//		});
//		List<String> directoryNames = new ArrayList<String>();
//		for(File directory : createdDirectories) {
//			directoryNames.add(directory.getName());
//		}
//		Collections.sort(expectedDirectoryNames);
//		Collections.sort(directoryNames);
//		Assert.assertEquals(expectedDirectoryNames, directoryNames);
//	}

}