package cn.smallfat.plugin.utils;

import java.util.Arrays;

import org.junit.Test;
import org.tigris.subversion.javahl.ClientException;

import cn.smallfat.plugin.mybuild.Build;

public class BuildUtilsTest {

	@Test
	public void test() {
		Build build = new Build.BuildBuilder()
			.path("G:\\eclipse\\workspace_plugs\\easyloan_r.1.0.0\\easyloan-web")
			.buildPath("easyloan-web")
			.targetPath("G:\\eclipse\\workspace_plugs\\easyloan_r.1.0.0\\easyloan-web\\target")
			.projectName("easyloan")
			.number("01")
//			.svnPath("svn://gitee.com/willluan/easyloan/")
//			.startRevision(3L)
			.packageName("easyloan")
			.ignoreds(Arrays.asList(".*/resources/application.*",".*/resources/.*properties"))
			.jars(Arrays.asList("easyloan-service-app-0.0.1-SNAPSHOT.jar","easyloan-service-0.0.1-SNAPSHOT.jar","easyloan-dao-0.0.1-SNAPSHOT.jar","easyloan-commons-0.0.1-SNAPSHOT.jar"))
			.createBuild();

		try {
			BuildUtils.building(build);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
