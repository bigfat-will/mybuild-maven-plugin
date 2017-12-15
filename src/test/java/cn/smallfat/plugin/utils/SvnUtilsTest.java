package cn.smallfat.plugin.utils;

import org.junit.Test;

public class SvnUtilsTest {

	@Test
	public void test() {
		String path = "svn://gitee.com/willluan/easyloan//easyloan_r.1.0.0/easyloan-web";
		SvnUtils.getStartRevision(path);
	}

}
