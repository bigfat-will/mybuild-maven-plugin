package cn.smallfat.plugin.mybuild;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.tools.ant.util.DateUtils;
/**
 * 
* @author will
* @email wuxiao@smallfat.cn
* @version 1.0
* 2017年12月18日 下午11:08:11
 */
public class Build {
	private String path;
	private String buildPath;
	private String targetPath;
	private String projectName;
	private String number;
	private String accountName;
	private String password;
	private String svnPath;
	private Long startRevision;
	private String packageName;
	private List<String> ignoreds;
	private List<String> jars;

	@Override
	public String toString() {
		return "Build [path=" + path + ", buildPath=" + buildPath + ", targetPath=" + targetPath + ", projectName="
				+ projectName + ", number=" + number + ", accountName=" + accountName + ", password=" + password
				+ ", svnPath=" + svnPath + ", startRevision=" + startRevision + ", packageName=" + packageName
				+ ", ignoreds=" + ignoreds + ", jars=" + jars + "]";
	}

	public Build(String path, String buildPath, String targetPath,
			String projectName, String number, String accountName,String password,String svnPath,
			Long startRevision, String packageName, List<String> ignoreds,
			List<String> jars) {
		super();
		this.path = path;
		this.buildPath = buildPath;
		this.targetPath = targetPath;
		this.projectName = projectName;
		this.number = number;
		this.accountName = accountName;
		this.password = password;
		this.svnPath = svnPath;
		this.startRevision = startRevision;
		this.packageName = packageName;
		this.ignoreds = ignoreds;
		this.jars = jars;
	}

	public String getPath() {
		return path;
	}

	public String getBuildPath() {
		return buildPath;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public List<String> getIgnoreds() {
		return ignoreds;
	}

	public List<String> getJars() {
		return jars;
	}

	public String getNumber() {
		return number;
	}

	public String getSvnPath() {
		return svnPath;
	}

	public Long getStartRevision() {
		return startRevision;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getPassword() {
		return password;
	}


	public static class BuildBuilder {
		private String path;
		private String buildPath;
		private String targetPath;
		private String projectName;
		private String number;
		private String accountName;
		private String password;
		private String svnPath;
		private Long startRevision;
		private String packageName;
		private List<String> ignoreds;
		private List<String> jars;

		public BuildBuilder path(String path) {
			this.path = path;
			return this;
		}

		public BuildBuilder buildPath(String buildPath) {
			this.buildPath = buildPath;
			return this;
		}

		public BuildBuilder targetPath(String targetPath) {
			this.targetPath = targetPath;
			return this;
		}

		public BuildBuilder projectName(String projectName) {
			this.projectName = projectName;
			return this;
		}

		public BuildBuilder number(String number) {
			this.number = number;
			return this;
		}
		
		public BuildBuilder accountName(String accountName) {
			this.accountName = accountName;
			return this;
		}
		
		public BuildBuilder password(String password) {
			this.password = password;
			return this;
		}
		
		public BuildBuilder svnPath(String svnPath) {
			this.svnPath = svnPath;
			return this;
		}

		public BuildBuilder startRevision(Long startRevision) {
			this.startRevision = startRevision;
			return this;
		}

		public BuildBuilder packageName(String packageName) {
			this.packageName = packageName;
			return this;
		}

		public BuildBuilder ignoreds(List<String> ignoreds) {
			this.ignoreds = ignoreds;
			return this;
		}

		public BuildBuilder jars(List<String> jars) {
			this.jars = jars;
			return this;
		}

		public Build createBuild() {
			return new Build(path, buildPath, targetPath, projectName, number,accountName,password,
					svnPath, startRevision, packageName, ignoreds, jars);
		}
	}

	public String getRealTragzName() {
		StringBuffer sb = new StringBuffer();
		sb.append(packageName).append("_")
				.append(DateUtils.format(new Date(), "yyyyMMdd")).append("_")
				.append(number).append(".tar.gz");
		return sb.toString();
	}

	public String getRealTargetPath() {
		StringBuffer sb = new StringBuffer();
		sb.append(targetPath).append(File.separator).append("tmp")
				.append(File.separator).append(packageName);
		return sb.toString();
	}

	public String getRealProjectPath() {
		StringBuffer sb = new StringBuffer();
		sb.append(targetPath).append(File.separator).append(projectName);
		return sb.toString();
	}
	
	public String getRealIgnored() {
		StringBuffer sb = new StringBuffer();
		for (String ignored : this.ignoreds) {
			sb.append(ignored).append("|");
		}
		return sb.toString();
	}

	public String getRealSvnPath() {
		StringBuffer sb = new StringBuffer();
		sb.append(svnPath).append("/")
				.append(path.replace(File.separator + buildPath, "").substring(path.lastIndexOf(File.separator) + 1))
				.append("/").append(buildPath);
		return sb.toString();
	}
}
