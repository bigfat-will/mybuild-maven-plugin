package cn.smallfat.plugin.mybuild;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import cn.smallfat.plugin.utils.SvnUtils;

@Mojo(name = "build")
public class BuildMojo extends AbstractMojo {
	
	@Parameter
    private String path;
	@Parameter
    private String buildPath;
	@Parameter
	private String targetPath;
	@Parameter
	private String svnPath;
	@Parameter(defaultValue="1")
	private Integer number;
	@Parameter
	private String startRevision;
	@Parameter
	private List<String> ignoreds;
	@Parameter
	private List<String> jars;
	
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
//    	Map<String,Object> pluginContext = getPluginContext();
    	
//    	for(String key : pluginContext.keySet()){
//    		System.out.println(key);
//    		System.out.println(pluginContext.get(key));
//    	}
/*    	System.out.println("project");
    	MavenProject mavenProject =  (MavenProject) pluginContext.get("project");
    	System.out.println(mavenProject.getFile().getPath());*/
       /*
        * 过滤  不需要 build 的项目 
        */
    	if(path.lastIndexOf(buildPath)<0)
    		return;
    	
    	System.out.println(path);
    	path = path.replace(File.separatorChar+buildPath, "");
    	path = path.substring(path.lastIndexOf(File.separatorChar)+1);
    	System.out.println("MyBuild build...");
    	System.out.println(path);
    	System.out.println(buildPath);
    	System.out.println(svnPath);
        
        Build build = new Build(path, buildPath, targetPath,number,svnPath,startRevision,ignoreds,jars,null);
        System.out.println("build.getIgnored() :"+build.getIgnored());
//        String num = SvnUtils.getLastChangedRev(svnPath+path+"/"+buildPath);
        SvnUtils.getDiff(svnPath+path+"/"+buildPath, startRevision);
    }

}