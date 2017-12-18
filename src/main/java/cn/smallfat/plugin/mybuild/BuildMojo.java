package cn.smallfat.plugin.mybuild;

import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import cn.smallfat.plugin.utils.BuildUtils;
/**
 * 
* @author will
* @email wuxiao@smallfat.cn
* @version 1.0
* 2017年12月18日 下午11:08:04
 */
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
	private String accountName;
	@Parameter
	private String password;
	@Parameter
	private String svnPath;
	@Parameter(defaultValue="01")
	private String number;
	@Parameter
	private Long startRevision;
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
    	
    	getLog().info("------------------------------------------------------------------------");
    	getLog().info("MyBuild build start");
    	getLog().info("------------------------------------------------------------------------");

    	Build build = new Build.BuildBuilder().path(path).buildPath(buildPath).targetPath(targetPath).projectName(projectName).number(number)
    					.accountName(accountName).password(password).svnPath(svnPath).startRevision(startRevision).packageName(packageName)
    					.ignoreds(ignoreds).jars(jars).createBuild();
    	getLog().info("------------------------------------------------------------------------");
    	getLog().info(build.toString());
    	getLog().info("------------------------------------------------------------------------");
    	try {
			BuildUtils.building(build);
		} catch (Exception e) {
			getLog().error(e);
			new MojoExecutionException("MyBuild build start: 出错！", e);
		}
    }

}