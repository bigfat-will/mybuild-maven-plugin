package cn.smallfat.plugin.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.tigris.subversion.javahl.ClientException;
import org.tigris.subversion.javahl.Info;
import org.tigris.subversion.javahl.LogMessage;

import cn.smallfat.plugin.mybuild.Build;
/**
 * 
* @author will
* @email wuxiao@smallfat.cn
* @version 1.0
* 2017年12月18日 下午11:07:59
 */
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
	
	private static Log log = new SystemStreamLog();
	
	public static void building(Build build) throws ClientException {

		try {
			List<String> list = getDiffFileList(build);
			
			copyFileWithDiff(build, list);
			
			String archive = CompressUtils.archive(build.getRealTargetPath());// 生成tar包
			String path = CompressUtils.compressArchive(archive);// 生成gz包
			File file = new File(path);
			path = path.replace(file.getName(), build.getRealTragzName());
			file.renameTo(new File(path));
			log.info("------------------------------------------------------------------------");
			log.info("MyBuild build ：   "+ path);
			log.info("------------------------------------------------------------------------");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void copyFileWithDiff(Build build , List<String> diffList) throws IOException{
		/*
		 * 遍历 diffList
		 */
		for(String diff : diffList){
			diff = build.getRealProjectPath() + File.separator + diff;
			int index = diff.lastIndexOf(File.separator);
			File file = new File(diff.substring(0, index));
			for(File dFile : file.listFiles()){
				if(dFile.isDirectory()){
					continue;
				}
				String fileName = dFile.getName();
				if(fileName.endsWith(_JAVA_CLASS)){
					fileName = fileName.replace(_JAVA_CLASS, "");
					if(fileName.lastIndexOf("$")>0){
						fileName = fileName.substring(0, fileName.lastIndexOf("$"));
					}
				}
				/*
				 *如果能 匹配 則 把 文件 copy 
				 */
				String name = diff.substring(index + 1);
				if(name.startsWith(fileName)){
					String tmpPath = dFile.getAbsolutePath().replace(build.getRealProjectPath(), build.getRealTargetPath());
					log.info("dFile   : "+dFile);
					log.info("tmpPath : "+tmpPath);
					FileUtils.copyFile(dFile, new File(tmpPath));
				}
			}
		}
	}
	
	public static List<String> getDiffFileList(Build build) throws ClientException {
		List<String> diffs = diff(build);
		List<String> newDiffs = new ArrayList<String>();
		for (String line : diffs) {
			/*
			 * 过滤 忽略的文件
			 */
			if (Pattern.matches(build.getRealIgnored(), line)) {
				continue;
			}
			line = line.replace("\\", "/").replace("/", File.separator);
			StringBuffer sb = new StringBuffer();
			sb.append(SRC).append(File.separator)
					.append(MAIN).append(File.separator);
			int index = line.indexOf(sb.toString());
			if(index < 0){
				log.info("line : "+line);
				continue;
			}
			line = line.substring(index);
			line = line.replace(sb.toString() + WEBAPP + File.separator, "");
			line = line.replace(sb.toString() + JAVA, WEB_INF + File.separator
					+ JAVA_CLASS);
			line = line.replace(sb.toString() + RESOURCES, WEB_INF + File.separator
					+ JAVA_CLASS);
			line = line.replace(_JAVA, "");

			newDiffs.add(line);
		}

		for (String jar : build.getJars()) {
			newDiffs.add(WEB_INF + File.separator + JAVA_LIB + File.separator + jar);
		}
		log.info("------------------------------------------------------------------------");
		for (String line : newDiffs) {
			log.info("MyBuild build ：   "+ line);
		}
		log.info("MyBuild build ：   处理文件数量   "+ newDiffs.size());
		log.info("------------------------------------------------------------------------");
		return newDiffs;
	}
	
	public static List<String> diff(Build build) throws ClientException{
		SVNClientUtils.buildSvnClient(build.getAccountName(), build.getPassword());
		String svnUrl = build.getSvnPath();
		if(StringUtils.isEmpty(svnUrl)){
			Info info = SVNClientUtils.info(build.getPath());
			svnUrl = info.getUrl();
		}
		Long startRevision = build.getStartRevision();
		if(startRevision == null){
			LogMessage logMessage = SVNClientUtils.log(svnUrl);
			startRevision = logMessage.getRevisionNumber();
		}
		 return SVNClientUtils.diff(svnUrl,startRevision);
	}
}
