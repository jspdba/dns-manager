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
1. 参数：-c "配置文件路径"
    例如: -c "D:/zhongliang/dns-manager/src/main/resources/config.json"
2. 参数: -p "请求字符串"
    例如: -p "Action=DescribeDomainRecords&DomainName=example.com"
例如
    java -jar dns-manager-1.0-SNAPSHOT.jar -p "Action=DescribeDomainInfo&DomainName=womaiapp.com"
    
    195c0f18-1fe4-4b67-ac7a-85cef47963a7
    7a2aee88-af15-48f5-8e10-9e311d2ffe00