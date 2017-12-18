package cn.smallfat.plugin.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.tigris.subversion.javahl.ClientException;
import org.tigris.subversion.javahl.Depth;
import org.tigris.subversion.javahl.DiffSummary;
import org.tigris.subversion.javahl.DiffSummaryReceiver;
import org.tigris.subversion.javahl.Info;
import org.tigris.subversion.javahl.LogMessage;
import org.tigris.subversion.javahl.Revision;
import org.tmatesoft.svn.core.javahl.SVNClientImpl;
import org.tigris.subversion.javahl.Revision.Number;

/**
 * 
* @author will
* @email wuxiao@smallfat.cn
* @version 1.0
* 2017年12月18日 下午10:33:46
 */
public class SVNClientUtils {
	public static SVNClientImpl svn = SVNClientImpl.newInstance();
	
	/**
	 * 
	 * @param accountName
	 * @param password
	 */
	public static void buildSvnClient(String accountName,String password){
		if(!StringUtils.isEmpty(accountName)){
			svn.username(accountName);
			svn.password(password);
		}
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 * @throws ClientException
	 */
	public static Info info(String path) throws ClientException{
		return svn.info(path);
	}
	/**
	 * 
	 * @param path
	 * @return
	 * @throws ClientException
	 */
	public static LogMessage log(String path) throws ClientException{
		LogMessage[] log = svn.logMessages(path, Revision.START, Revision.HEAD,true);
		return log[0];
	}
	/**
	 * 
	 * @param svnUrl
	 * @param startRevision
	 * @return
	 * @throws ClientException
	 */
	public static List<String> diff(String svnUrl,Long startRevision) throws ClientException{
		final List<String> diffList = new ArrayList<String>();
		DiffSummaryReceiver receiver = new DiffSummaryReceiver() {
			@Override
			public void onSummary(DiffSummary descriptor) {
				diffList.add(descriptor.toString());
			}
		};
		svn.diffSummarize(svnUrl, new Number(startRevision), svnUrl, Revision.HEAD,  Depth.infinity, null, true, receiver);
		return diffList;
	}
}
