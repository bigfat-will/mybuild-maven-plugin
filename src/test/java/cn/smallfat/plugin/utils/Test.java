package cn.smallfat.plugin.utils;

import java.util.ArrayList;
import java.util.List;

import org.tigris.subversion.javahl.ClientException;
import org.tigris.subversion.javahl.Depth;
import org.tigris.subversion.javahl.DiffSummary;
import org.tigris.subversion.javahl.DiffSummaryReceiver;
import org.tigris.subversion.javahl.Info;
import org.tigris.subversion.javahl.LogMessage;
import org.tigris.subversion.javahl.Revision;
import org.tigris.subversion.javahl.Revision.Number;
import org.tmatesoft.svn.core.javahl.SVNClientImpl;




public class Test {
	public static void main(String[] args) throws ClientException{
				
//		String path = "https://172.16.49.100:8443/svn/jk/weidai/branches/bxloan-r1.5.259.2";
		SVNClientImpl svn = SVNClientImpl.newInstance();
		/*svn.username("wuxiao");
		svn.password("wuxiao");*/
		Info info = svn.info("G:\\eclipse\\workspace_plugs\\easyloan_r.1.0.0");
		System.out.println(info.getUrl());
//		LogMessage[] log = svn.logMessages(path, Revision.START, Revision.HEAD,true);
//		
//		System.out.println(log[0].getRevision());
//		final List<String> list = new ArrayList<String>();
//		DiffSummaryReceiver receiver = new DiffSummaryReceiver() {
//			@Override
//			public void onSummary(DiffSummary descriptor) {
//				list.add(descriptor.toString());
//			}
//		};
//		svn.diffSummarize(path, new Number(54680), path, Revision.HEAD,  Depth.infinity, null, true, receiver);
	}
}
