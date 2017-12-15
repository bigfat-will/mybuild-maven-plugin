# mybuild-maven-plugin
my  mybuild-maven-plugin
先只支持 svn 
使用方法：
在 pom.xml 中 添加一下内容

---
	<build>
		<plugins>
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

					<!-- 用户配置配置 -->
					<!--打包的项目 -->
					<buildPath>**-web</buildPath>
					<svnPath>svn地址</svnPath>
					<!-- 不指定时，系统自动获取当前分支最早版本 -->
					<!-- <startRevision>3</startRevision> -->
					<number>01</number>
					<ignoreds>
						<ignored>.*/resources/application.*</ignored>
						<ignored>.*/resources/.*properties</ignored>
					</ignoreds>
					<jars>
						<jar>*.jar</jar>
					</jars>
				</configuration>
			</plugin>
		</plugins>
	</build>
---