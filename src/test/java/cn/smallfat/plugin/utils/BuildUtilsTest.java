package cn.smallfat.plugin.utils;

import java.util.Arrays;

import org.junit.Test;

import cn.smallfat.plugin.mybuild.Build;

public class BuildUtilsTest {

	@Test
	public void test() {
		Build build = new Build.BuildBuilder()
			.path("easyloan_r.1.0.0")
			.buildPath("easyloan-web")
			.targetPath("G:\\eclipse\\workspace_plugs\\easyloan_r.1.0.0\\easyloan-web\\target")
			.projectName("easyloan")
			.number("01")
			.svnPath("svn://gitee.com/willluan/easyloan/")
			.startRevision("3")
			.packageName("easyloan")
			.ignoreds(Arrays.asList(".*/resources/application.*",".*/resources/.*properties"))
			.jars(Arrays.asList("easyloan-service-app-0.0.1-SNAPSHOT.jar","easyloan-service-0.0.1-SNAPSHOT.jar","easyloan-dao-0.0.1-SNAPSHOT.jar","easyloan-commons-0.0.1-SNAPSHOT.jar"))
			.createBuild();

		BuildUtils.building(build);
		
	}

}
