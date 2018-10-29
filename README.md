# bcce-java-api-sdk
A Java sdk for bcce exchange API

# open api sdk 使用说明
1. 在maven项目的pom.xml加入如下依赖
```xml

 <dependency>
    <groupId>com.bcce.dax</groupId>
    <artifactId>bcce-java-api-sdk</artifactId>
    <version>1.0.0-SNAPSHOT</version>
 </dependency>
 
```
由于当前发布的是snapshot版,因此需要您在maven settings.xml或pom.xml中设置如下repository

```xml
   <repository> 
       <id>oss-sonatype</id>
       <url>https://oss.sonatype.org/content/groups/staging/</url>
       <releases>
           <enabled>true</enabled>
           <updatePolicy>always</updatePolicy>
       </releases>
       <snapshots>
           <enabled>true</enabled>
           <updatePolicy>always</updatePolicy>
       </snapshots>
   </repository>
                
```

2. 创建 RestClient

```java

    /**
     * 用户 apiKey，需用户填写，在 https://www.bcce.com/user  api 中获取
     */
    String apiKey = "";
    /**
     * 用户 secretKey，需用户填写，在 https://www.bcce.com/user  api 中获取
     */
    String secretKey = "";
    /**
     * 口令，需用户填写，在 https://www.bcce.com/user  api 中获取（创建时由用户设定）
     */
    String passphrase = "";
    /**
     * ccex open api 根路径
     */
    String baseUrl = "https://www.bcce.com/api/v1/";

    ClientParameter parameter = ClientParameter.builder()
            .apiKey(apiKey)
            .secretKey(secretKey)
            .passphrase(passphrase)
            .baseUrl(baseUrl)
            .build();

    RestClient restClient = RestClient.builder()
                .configuration(parameter)
                .build();

```
3. 接口调用
- 创建 restClient 后便可以调用服务接口，以获取币对信息为例
```java
	List<CodeInfo> codeInfos = this.restClient.publics().products()
```
- 其他接口调用参照 test 包中的测试用例
