#0、绪论
    2000 Ant出现，2004 maven出现，2012 gradle出现
    优点：
        抛弃了基于xml的繁琐配置。
        面向java应用为主，目前支持java，Scala，groovy
#2、Gradle的安装和配置
    下载解压版 
    配置环境变量
    gradle和idea集成
    
    使用maven仓库：
        如果本地没有相关jar包，gradle会下载到${USER_HOME}/.gradle文件夹下，
        若想让gradle下载到指定文件夹，配置GRADLE_USER_HOME环境变量
#3、groovy语言介绍
    gradle配置文件使用的语言
    弱类型
    
#4、gradle仓库配置
    默认仓库：
        mavenCentral()  中央仓库
    仓库可以切换成阿里云：
        maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
        maven{ url 'http://maven.aliyun.com/nexus/content/repositories/jcenter'}
        
    仓库可以指向本地maven仓库
#5、idea2019 集成gradle
    gradle user home
        gradle的仓库位置，
    use gradle home
        gradle工具的路径
    
