# mybuild-maven-plugin
my  mybuild-maven-plugin
先只支持 svn 
使用方法：
在 pom.xml 中 添加一下内容


	<build>
		<plugins>
			<!-- My Build -->
			  <plugin>
                  <groupId>cn.smallfat.plugin</groupId>
				  <artifactId>mybuild-maven-plugin</artifactId>
				  <version>0.0.1-SNAPSHOT</version>
				  <configuration>
					<!-- 系统配置 -->
                    <goalPrefix>mybuild</goalPrefix>
                    <skipErrorNoDescriptorsFound>true</skipErrorNoDescriptorsFound>
                    <path>${basedir}</path>
                    <targetPath>${project.build.directory}</targetPath>
                    <projectName>${project.build.finalName}</projectName>
                    <targzName>${project.parent.artifactId}</targzName>
					
                    <!-- 用户配置配置 -->
                    <buildPath>bxloan-web</buildPath>
                    <svnPath>svn地址</svnPath>
                    <startRevision>54680</startRevision>
                    <number>02</number>
                    <ignoreds>
						<ignored>.*/resources/application.*</ignored>
						<ignored>.*/resources/.*properties</ignored>
					</ignoreds>
					<jars>
						<jar>*-0.0.1-SNAPSHOT.jar</jar>
					</jars>
                  </configuration>
            </plugin>
		</plugins>
	</build>