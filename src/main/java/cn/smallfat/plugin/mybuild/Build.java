package cn.smallfat.plugin.mybuild;

import java.util.List;

public class Build {
	private String path;
	private String buildPath;
	private String targetPath;
	private Integer numbers;
	private String svnPath;
	private String startRevision;
	private List<String> ignoreds;
	private List<String> jars;
	private List<String> diffFiles;
	
	
	public Build(String path, String buildPath, String targetPath, Integer numbers, String svnPath,String startRevision,
			List<String> ignoreds, List<String> jars, List<String> diffFiles) {
		super();
		this.path = path;
		this.buildPath = buildPath;
		this.targetPath = targetPath;
		this.numbers = numbers;
		this.svnPath = svnPath;
		this.startRevision = startRevision;
		this.ignoreds = ignoreds;
		this.jars = jars;
		this.diffFiles = diffFiles;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getBuildPath() {
		return buildPath;
	}
	public void setBuildPath(String buildPath) {
		this.buildPath = buildPath;
	}
	public String getTargetPath() {
		return targetPath;
	}
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}
	public List<String> getIgnoreds() {
		return ignoreds;
	}
	public void setIgnoreds(List<String> ignoreds) {
		this.ignoreds = ignoreds;
	}
	public List<String> getJars() {
		return jars;
	}
	public void setJars(List<String> jars) {
		this.jars = jars;
	}
	public List<String> getDiffFiles() {
		return diffFiles;
	}
	public void setDiffFiles(List<String> diffFiles) {
		this.diffFiles = diffFiles;
	}
	
	public Integer getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
	

	public String getSvnPath() {
		return svnPath;
	}

	public void setSvnPath(String svnPath) {
		this.svnPath = svnPath;
	}
	

	public String getStartRevision() {
		return startRevision;
	}
	public void setStartRevision(String startRevision) {
		this.startRevision = startRevision;
	}
	public String getIgnored(){
		StringBuffer sb = new StringBuffer();
		for(String ignored : this.ignoreds){
			sb.append(ignored).append("|");
		}
		return sb.toString();
	}
	
	public String getRealSvnPath(){
		StringBuffer sb = new StringBuffer();
			sb.append(svnPath).append("/").append(path).append("/").append(buildPath);
		return sb.toString();
	}
}
