package cn.smallfat.plugin.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import cn.smallfat.plugin.mybuild.Build;

public class BuildUtils {
	private static final String SRC = "src";
	private static final String MAIN = "main";
	private static final String JAVA = "java";
	private static final String _JAVA = ".java";
	private static final String WEBAPP = "webapp";
	private static final String WEB_INF = "WEB-INF";
	private static final String JAVA_CLASS = "classes";
	private static final String _JAVA_CLASS = ".class";
	private static final String JAVA_LIB = "lib";
	private static final String RESOURCES = "resources";
	

	public static void building(Build build) {

		try {
			List<String> list = getDiffFileList(build);
			File classPathFile = new File(build.getTargetPath() + File.separator
					+ build.getProjectName());
			File classPathFileTmp = new File(build.getRealTargetPath());
			FileUtils.copyDirectory(classPathFile, classPathFileTmp);
			removeFileExDiff(classPathFileTmp, list);
			String archive = CompressUtils.archive(build.getRealTargetPath());// 生成tar包
			String path = CompressUtils.compressArchive(archive);// 生成gz包
			File file = new File(path);
			path = path.replace(file.getName(), build.getRealTragzName());
			file.renameTo(new File(path));
			System.out.println("------------------------------------------------------------------------");
			System.out.println("MyBuild build ：   "+ path);
			System.out.println("------------------------------------------------------------------------");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static List<String> getDiffFileList(Build build) {
		List<String> diffs = SvnUtils.getDiff(build.getRealSvnPath(),
				build.getStartRevision());
		List<String> newDiffs = new ArrayList<String>();
		for (String line : diffs) {
			/*
			 * 过滤 忽略的文件
			 */
			if (Pattern.matches(build.getRealIgnored(), line)) {
				continue;
			}
			line = line.replace("\\", "/").replace("/", File.separator);
			StringBuffer sb = new StringBuffer(build.getBuildPath());
			sb.append(File.separator).append(SRC).append(File.separator)
					.append(MAIN).append(File.separator);
			int index = line.indexOf(sb.toString());
			line = line.substring(index);
			line = line.replace(sb.toString() + WEBAPP + File.separator, "");
			line = line.replace(sb.toString() + JAVA, WEB_INF + File.separator
					+ JAVA_CLASS);
			line = line.replace(sb.toString() + RESOURCES, WEB_INF + File.separator
					+ JAVA_CLASS);
			line = line.replace(_JAVA, "");

			newDiffs.add(build.getRealTargetPath() + File.separator + line);
		}

		for (String jar : build.getJars()) {
			newDiffs.add(build.getRealTargetPath() + File.separator + WEB_INF
					+ File.separator + JAVA_LIB + File.separator + jar);
		}
		System.out.println("------------------------------------------------------------------------");
		for (String line : newDiffs) {
			System.out.println("MyBuild build ：   "+ line);
		}
		System.out.println("MyBuild build ：   处理文件数量   "+ newDiffs.size());
		System.out.println("------------------------------------------------------------------------");
		return newDiffs;
	}

	public static void removeFileExDiff(File classPathFile,
			List<String> diffList) {
		File[] files = classPathFile.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				removeFileExDiff(file, diffList);
			} else {
				String path = file.getAbsolutePath();
				if (path.endsWith(_JAVA_CLASS)) {
					path = path.replace(_JAVA_CLASS, "");
					if (file.getName().contains("$")) {
						path = path.substring(0, path.lastIndexOf("$"));
					}
				}
				if (!diffList.contains(path)) {
					file.delete();
				}
			}
		}
		/*
		 * 如果为空 则删除
		 */
		if (classPathFile.list().length == 0) {
			classPathFile.delete();
		}
	}
}
