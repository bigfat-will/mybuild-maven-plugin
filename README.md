# mybuild-maven-plugin
my  mybuild-maven-plugin
先只支持 svn 
使用方法：
在 pom.xml 中 添加一下内容

---
			<!-- My Build -->
			<plugin>
				<groupId>cn.smallfat.plugin</groupId>
				<artifactId>mybuild-maven-plugin</artifactId>
				<version>1.0</version>
				<configuration>
					<!-- 系统配置 -->
					<goalPrefix>mybuild</goalPrefix>
					<skipErrorNoDescriptorsFound>true</skipErrorNoDescriptorsFound>
					<path>${basedir}</path>
					<targetPath>${project.build.directory}</targetPath>
					<projectName>${project.build.finalName}</projectName>
					<packageName>${project.parent.artifactId}</packageName>

					<!-- 以下配置如果不指定，则默认下读取本机SVN配置--> 
					<!-- <accountName>*</accountName> -->
					<!-- <password>*</password> -->
					<!-- <svnPath>*</svnPath> -->
					<!-- <startRevision>3</startRevision>  -->
					
					<!-- 用户配置配置 -->
					<buildPath>*-web</buildPath>
					<number>01</number>
					<ignoreds>
						<ignored>.*/resources/application.*</ignored>
						<ignored>.*/resources/.*properties</ignored>
					</ignoreds>
					<jars>
						<jar>*.jar</jar>
						<jar>easyloan-service-0.0.1-SNAPSHOT.jar</jar>
					</jars>
				</configuration>
			</plugin>
		</plugins>
	</build>
---