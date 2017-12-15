package cn.smallfat.plugin.mybuild;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import cn.smallfat.plugin.utils.BuildUtils;

@Mojo(name = "build")
public class BuildMojo extends AbstractMojo {
	
	@Parameter
    private String path;
	@Parameter
    private String buildPath;
	@Parameter
	private String targetPath;
	@Parameter
	private String projectName;
	@Parameter
	private String svnPath;
	@Parameter(defaultValue="01")
	private String number;
	@Parameter
	private String startRevision;
	@Parameter
	private String packageName;
	@Parameter
	private List<String> ignoreds;
	@Parameter
	private List<String> jars;
	
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
       /*
        * 过滤  不需要 build 的项目 
        */
    	if(path.lastIndexOf(buildPath)<0)
    		return;
    	System.out.println("------------------------------------------------------------------------");
    	System.out.println("MyBuild build start");
    	System.out.println("------------------------------------------------------------------------");
    	/*
    	 * 获取最外层文件夹名
    	 */
    	path = path.replace(File.separator+buildPath, "");
    	path = path.substring(path.lastIndexOf(File.separator)+1);
    	Build build = new Build.BuildBuilder().path(path).buildPath(buildPath).targetPath(targetPath).projectName(projectName).number(number)
    					.svnPath(svnPath).startRevision(startRevision).packageName(packageName).ignoreds(ignoreds).jars(jars).createBuild();
    	System.out.println("------------------------------------------------------------------------");
    	System.out.println(build);
    	System.out.println("------------------------------------------------------------------------");
    	BuildUtils.building(build);
    }

}