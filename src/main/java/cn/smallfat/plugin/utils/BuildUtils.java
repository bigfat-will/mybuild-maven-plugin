package cn.smallfat.plugin.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import cn.smallfat.plugin.mybuild.Build;

public class BuildUtils {
	private static final String SRC = "src";
	private static final String MAIN = "main";
	private static final String JAVA = "java";
	private static final String WEBAPP = "webapp";
	private static final String WEB_INF = "WEB-INF";
	private static final String JAVA_CLASS = "classes";
	private static final String JAVA_LIB = "lib";
	
	public static List<String> getDiffFileList(Build build){
        List<String> diffs = SvnUtils.getDiff(build.getRealSvnPath(), build.getStartRevision());
        List<String> newDiffs = new ArrayList<String>();
		for(String line : diffs){
			/*
			 * 过滤 忽略的文件
			 */
			if(Pattern.matches(build.getIgnored(), line)){
				continue;
			}
			line = line.replace("\\", "/").replace("/", File.separator);
			StringBuffer sb = new StringBuffer(build.getBuildPath());
			sb.append(File.separator).append(SRC).append(File.separator)
					.append(MAIN).append(File.separator);
			int index = line.indexOf(sb.toString());
			line = line.substring(index).replace(sb.toString(), "");
			line = line.replaceFirst(WEBAPP+"\\\\", "");
			line = line.replaceFirst(JAVA, WEB_INF+"\\\\"+JAVA_CLASS);
			
			newDiffs.add(line);
		}
		
		for(String jar : build.getJars()){
			newDiffs.add(WEB_INF+"\\"+JAVA_LIB+"\\"+jar);
		}
		
		for(String line : newDiffs){
			System.out.println(line);
		}
		
		return newDiffs;
	}
	
	public static void removeFileEXDiff(File classPathFile,List<String> diffList){
		File[] files = classPathFile.listFiles();
		for(File file : files){
			if(file.isDirectory()){
				removeFileEXDiff(file,diffList);
			}else{
				String path = file.getAbsolutePath();
				if(path.endsWith(".class")){
					path = path.substring(0,path.length()-6);
					if(file.getName().contains("$")){
						path = path.substring(0,path.lastIndexOf("$"));
					}
				}
				if(exist(path,diffList)){
					System.out.println(path);
				}else{
					file.delete();
				}
			}
		}
		/*
		 * 如果为空 则删除
		 */
		if(classPathFile.list().length==0){
			classPathFile.delete();
		}
	}
	public static boolean exist(String path,List<String> retainList ){
		
		for(String t:retainList){
			String classPath = "G:\\eclipse\\workspace_plugs\\easyloan_r.1.0.0\\easyloan-web\\target\\easyloan_tmp";
			t = classPath +"\\"+ t;
			if(t.startsWith(path)){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		String classPath = "G:\\eclipse\\workspace_plugs\\easyloan_r.1.0.0\\easyloan-web\\target\\easyloan_tmp";
		
		List<String> ignoreds = Arrays.asList(".*/resources/.*");
		List<String> jars = Arrays.asList("easyloan-service-app-0.0.1-SNAPSHOT.jar","easyloan-service-0.0.1-SNAPSHOT.jar","easyloan-dao-0.0.1-SNAPSHOT.jar","easyloan-commons-0.0.1-SNAPSHOT.jar");

		 Build build = new Build("easyloan_r.1.0.0", "easyloan-web", "G:\\eclipse\\workspace_plugs\\easyloan_r.1.0.0\\easyloan-web\\target"
				 ,1,"svn://gitee.com/willluan/easyloan/","3",ignoreds,jars,null);
		List<String> list = getDiffFileList(build);
		File classPathFile = new File(classPath);
		removeFileEXDiff(classPathFile, list);
	}
}
