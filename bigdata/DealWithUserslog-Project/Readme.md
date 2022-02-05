# 用户日志处理、查询、分析课程设计

分工：

罗[@blackswords](https://github.com/blackswords)

连[@]

# 一、内容

1、**用户查询日志的存储与处理**

2、**基于大数据计算技术的条件查询**

3、**时段流量统计**

4、**用户使用频率统计**

5、**访问行为统计**



# 二、分析

1. 数据清洗：载入文本中的日志数据，每行作为一条记录，分为访问时间，用户ID，查询词，返回结果排名，顺序号，URL这六个字段（列），存入HBASE。

2. HBase条件查询、联合查询：

- a)  时间段：输入信息为开始时间和结束时间，用‘|’字符隔开
- b)  用户ID：输入信息为一个或多个用户ID，用‘|’字符隔开
- c)  搜索词关键字：输入信息为一个或多个关键字，用‘|’字符隔开
- d)  url访问记录：输入信息为一个或多个关键字，用‘|’字符隔开
- e)  以上四个条件的联合查询，使用“+”号分割条件

3. 使用Hadoop的MapReduce或Spark的RDD，实现以下功能：

- a)  基于大数据计算技术的条件查询：使用MapReduce框架或RDD算子，实现类似于HBase的六个字段条件搜索。
- b)  时段流量统计：以hh:mm:ss格式输入起始时间和结束时间，统计这段时间之内的总搜索次数、各个查询词搜索次数，各个网站的访问量。
- c)  用户使用频率统计：统计每个用户一天内的搜索次数
- d)  访问行为统计：根据该页面在搜索结果中的排名(第4字段)，统计不同排名的结果被访问的情况。





# 三、环境介绍

**开发环境**

系统：Windows 10系统

IDE：IntelliJ IDEA Ultimate 2021.1.1

环境：JDK 1.8，Hadoop 3.2.2，HBase 2.3.5，Spark 3.1.2



**运行环境**

系统：Debian 10（腾讯云）2核4G

环境：JDK 1.8，Hadoop 3.2.2，HBase 2.4.3，Spark 3.1.2





# 四、设计过程

## 1、数据清洗

### 设计思路

数据清洗的设计思路为：读取输入的路径的文件（来自于HDFS），读取文件中的记录，将每条记录分割为六个字段，分别对应访问时间，用户ID，查询词，返回结果排名，顺序号，URL，将读取到的每条记录存入HBase数据库。

考虑到数据的庞大，存入使用批量存入的方式，每次缓存4000条数据再存入，读取结束不足4000条的数据最后插入。



### **实机演示**

![image-20220205141621406](https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/202202051416764.png)

![image-20220205141626791](https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/202202051416788.png)



![image-20220205141642234](https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/202202051416548.png)

**每次插入数据数对用时的影响：**

|            | 4000  | 5000  | 6000  | 12000 |
| ---------- | ----- | ----- | ----- | ----- |
| 用时（ms） | 88863 | 78300 | 82125 | 96368 |



## 2、数据搜索

### 设计思路

需要同时满足多个条件的搜索，用到了过滤器，通过“过滤器链”来实现同时对多个条件进行筛选。涉及到条件的交集或是并集的计算。设置过滤器链的属性为MUST_PASS_ALL或MUST_PASS_ONE来控制是同时满足所有的条件还是只需满足其中一个条件。通过添加比较器SubstringComparator来进行子串的匹配。

### 实机演示

![image-20220205141749603](https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/202202051417910.png)

![image-20220205141756436](https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/202202051417408.png)



## 3、RDD功能实现

### 设计思路

为了实现数据搜索，使用filter函数进行rdd转换，为了实现流量统计、用户行为统计，则使用maptopair函数进行转换。整体转换思路围绕着“读入文件——转换为rdd——执行行动——写为文件”

因为操作较多，使用命令行传参的方式进行行为选择。并对于结果写为文件。

传入四种参数进行使用：

1. search（eg. 00:00:00 | 00:01:00 + 2982199073774412 + 360 + it.com）

   ​	用于联合条件查询，查询结果会写在/output/search

2. flow（eg. 00:00:00  00:01:00）

    	用于时段流量统计，结果会写在/output/flow

3. user 

   ​	用于用户频率统计，结果会写在/output/user

4. action

   ​	用于访问行为统计，结果会写在/output/action



### 实机演示

**输入：**

/usr/local/spark-3.1.2/bin/spark-submit --class edu.jmu.rdd.SparkDB ~/SparkDB-2.0.jar search 00:00:00 | 00:01:00 + 2982199073774412 + 360 + it.com

 

**输出（部分）：**

![image-20220205141844880](https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/202202051418969.png)



**输入：**

/usr/local/spark-3.1.2/bin/spark-submit target/LogProccessing-1.0-jar-with-dependencies.jar flow 00:00:00 00:00:01

 

**输出：**

*对链接统计：*

![image-20220205141904213](https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/202202051419775.png)

![image-20220205141909100](https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/202202051419400.png)



*对关键字统计：*

![image-20220205141926986](https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/202202051419136.png)

![image-20220205141932194](https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/202202051419239.png)

**输入：**

/usr/local/spark-3.1.2/bin/spark-submit target/LogProccessing-1.0-jar-with-dependencies.jar user

 

**输出（部分）：**

![image-20220205141951796](https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/202202051419884.png)

**输入：**

/usr/local/spark-3.1.2/bin/spark-submit target/LogProccessing-1.0-jar-with-dependencies.jar action

 

**输出（部分）：**

![image-20220205142011126](https://hong-not-pic-1258424340.cos.ap-nanjing.myqcloud.com/notepic/202202051420073.png)















