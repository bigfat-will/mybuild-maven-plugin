package cn.smallfat.plugin.utils;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class SvnUtils {
	private static final String SVN_INFO = "svn info ";
	private static final String SVN_DIFF = "svn diff ";
	
	public static String getLastChangedRev(String path){
		String lastChangedRev = "";
		List<String> list = getInfo(path);
		for(String str : list){
			String line = str.replace(" ","");
			if(line.contains("LastChangedRev")){
				lastChangedRev = line.replace("LastChangedRev:", "");
				break;
			}
		}
		System.out.println(lastChangedRev);
		return lastChangedRev;
	}
	
	public static List<String> getInfo(String path) {
		try {
			//svn info D:\workspace_bx_test\bxloan-r1.5.259.2\bxloan-web
			StringBuffer sb = new StringBuffer(SVN_INFO);
			String exe = sb.append(path).toString();
			System.out.println(exe);
			Process pro = Runtime.getRuntime().exec(exe);
			List<String> list = IOUtils.readLines(pro.getInputStream(), "GBK");
			for (String str : list) {
				System.out.println(str);
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> getDiff(String path, String num) {
		try {
			//svn diff -r54657:head D:\workspace_bx_test\bxloan-r1.5.259.2\bxloan-web --summarize
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

	public static void main(String[] args) {
		String path = "G:\\eclipse\\workspace_plugs\\easyloan_r.1.0.0\\easyloan-web";
//		getInfo(path);
		getDiff(path, "3");
//		getLastChangedRev(path);
	}
}
