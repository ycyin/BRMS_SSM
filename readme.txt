关于mybatis从数据库反向生成实体、DAO、mapper:
	参考文章：http://www.cnblogs.com/wangkeai/p/6934683.html
第一种方式：main方法运行（推荐）
	1.在pom.xml中加入依赖：
		<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.4</version>
</dependency>
<!-- mybatis-generator-core 反向生成java代码-->
<dependency>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-core</artifactId>
    <version>1.3.5</version>
</dependency>
	2.写mbgConfiguration.xml文件，jdbc.properties文件
	3.写/SSM/src/main/java/main/GenMain.java  main方法
	4.运行main函数，刷新
第二种方式：maven插件运行
	1.pom.xml配置插件build节点下加入插件：
			<!-- 第二种方式数据库反向生成java -->
			 <plugin>  
                <groupId>org.mybatis.generator</groupId>  
                <artifactId>mybatis-generator-maven-plugin</artifactId>  
                <version>1.3.2</version>  
                <configuration>  
                    <verbose>true</verbose>  
                    <overwrite>true</overwrite>  
                </configuration>  
            </plugin>
     2.	写generatorConfig.xml文件，与mbgConfiguration.xml文件不同的是：
     	文件名固定只能是generatorConfig，文件中要配置数据库连接驱动绝对路径，并且，数据库连接信息url,username,password等都要写固定
     	<classPathEntry  location="E:\MavenRepository\mysql\mysql-connector-java\5.1.38\mysql-connector-java-5.1.38.jar" />
	 3.运行方法：在eclipse 中，选择pom.xml文件，击右键先择Run AS——>Maven Build… ——>在Goals框中输入：mybatis-generator:generate