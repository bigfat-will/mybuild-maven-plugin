package cn.smallfat.plugin.utils;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
/**
 * 
* @author will
* @email wuxiao@smallfat.cn
* @version 1.0
* 2017年12月18日 下午11:07:49
 */
public class SvnUtils {
	private static final String SVN_DIFF = "svn diff ";
	private static final String SVN_LOG  = "svn log ";

	public static List<String> getDiff(String path, String num) {
		try {
			StringBuffer sb = new StringBuffer(SVN_DIFF);
			String exe = sb.append("-r").append(num).append(":head ")
					.append(path).append(" --summarize").toString();
			System.out.println("------------------------------------------------------------------------");
			System.out.println("MyBuild build ：SVN 执行命令    "+exe);
			Process pro = Runtime.getRuntime().exec(exe);
			List<String> list = IOUtils.readLines(pro.getInputStream(), "GBK");
			System.out.println("MyBuild build ：共获取差异文件   "+list.size());
			System.out.println("------------------------------------------------------------------------");
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static List<String> getLog(String path) {
		try {
			StringBuffer sb = new StringBuffer(SVN_LOG);
			String exe = sb.append(" -q ").append(path).append(" --stop-on-copy").append(" --incremental").toString();
			System.out.println("------------------------------------------------------------------------");
			System.out.println("MyBuild build ：SVN 执行命令    "+exe);
			Process pro = Runtime.getRuntime().exec(exe);
			List<String> list = IOUtils.readLines(pro.getInputStream(), "GBK");
			System.out.println("------------------------------------------------------------------------");
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static String getStartRevision(String path){
		List<String> logs = getLog(path);
		String log = logs.get(logs.size() - 1).replace(" ", "");
		String[] arrayLog = log.split("\\|");
		String startRevision = arrayLog[0].substring(1);
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("MyBuild build ：startRevision  "+startRevision);
		System.out.println("------------------------------------------------------------------------");
		return startRevision;
	}
	
}
