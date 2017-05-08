#需求1:
    https://help.aliyun.com/document_detail/29745.html?spm=5176.doc29739.6.588.f5I868

#需求2:
    https://help.aliyun.com/document_detail/29752.html?spm=5176.doc29745.6.595.n2Bsjd

#公共参数：
    https://help.aliyun.com/document_detail/29745.html?spm=5176.doc29739.6.588.f5I868

#SDK：aliyun-java-sdk-alidns
    https://develop.aliyun.com/sdk/java?spm=5176.7926450.210367.1.xMo44e

#例子：
    https://help.aliyun.com/document_detail/29819.html?spm=5176.doc29747.2.2.xmEg08
    https://help.aliyun.com/document_detail/29747.html?spm=5176.doc29751.6.590.eCtOuZ
#签名算法
    https://help.aliyun.com/document_detail/29819.html?spm=5176.doc29747.2.2.SUHGfb
    
    
#文档说明
##config.json 存放了公共请求参数，使用前请修改这个文件
##代码已放到github 地址: https://github.com/jspdba/dns-manager/tree/4609089599265e97d550862a67862d3e020eea13

1. 参数：-c "配置文件路径"
    例如: java -jar dns-manager-1.0-SNAPSHOT.jar -c "D:/zhongliang/dns-manager/src/main/resources/config.json"
2. 参数: -p "请求字符串"
    例如: java -jar dns-manager-1.0-SNAPSHOT.jar -p "Action=DescribeDomainRecords&DomainName=example.com"
3. -help 帮助
    例如 java -jar dns-manager-1.0-SNAPSHOT.jar -help
例如
    java -jar dns-manager-1.0-SNAPSHOT.jar -p "Action=DescribeDomainInfo&DomainName=womaiapp.com"