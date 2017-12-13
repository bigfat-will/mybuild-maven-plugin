package cn.smallfat.plugin.mybuild;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.tools.ant.util.DateUtils;

public class Build {
	private String path;
	private String buildPath;
	private String targetPath;
	private String projectName;
	private String number;
	private String svnPath;
	private String startRevision;
	private String targzName;
	private List<String> ignoreds;
	private List<String> jars;

	@Override
	public String toString() { 
		return "Build [path=" + path + ", buildPath=" + buildPath
				+ ", targetPath=" + targetPath + ", projectName=" + projectName
				+ ", number=" + number + ", svnPath=" + svnPath
				+ ", startRevision=" + startRevision + ", targzName="
				+ targzName + ", ignoreds=" + ignoreds + ", jars=" + jars + "]";
	}

	public Build(String path, String buildPath, String targetPath,
			String projectName, String number, String svnPath,
			String startRevision, String targzName, List<String> ignoreds,
			List<String> jars) {
		super();
		this.path = path;
		this.buildPath = buildPath;
		this.targetPath = targetPath;
		this.projectName = projectName;
		this.number = number;
		this.svnPath = svnPath;
		this.startRevision = startRevision;
		this.targzName = targzName;
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

	public String getStartRevision() {
		return startRevision;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getTargzName() {
		return targzName;
	}

	public static class BuildBuilder {
		private String path;
		private String buildPath;
		private String targetPath;
		private String projectName;
		private String number;
		private String svnPath;
		private String startRevision;
		private String targzName;
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

		public BuildBuilder svnPath(String svnPath) {
			this.svnPath = svnPath;
			return this;
		}

		public BuildBuilder startRevision(String startRevision) {
			this.startRevision = startRevision;
			return this;
		}

		public BuildBuilder targzName(String targzName) {
			this.targzName = targzName;
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
			return new Build(path, buildPath, targetPath, projectName, number,
					svnPath, startRevision, targzName, ignoreds, jars);
		}
	}

	public String getRealTragzName() {
		StringBuffer sb = new StringBuffer();
		sb.append(targzName).append("_")
				.append(DateUtils.format(new Date(), "yyyyMMdd")).append("_")
				.append(number).append(".tar.gz");
		return sb.toString();
	}

	public String getRealTargetPath() {
		StringBuffer sb = new StringBuffer();
		sb.append(targetPath).append(File.separator).append("tmp")
				.append(File.separator).append(targzName);
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
		sb.append(svnPath).append("/").append(path).append("/")
				.append(buildPath);
		return sb.toString();
	}
}
