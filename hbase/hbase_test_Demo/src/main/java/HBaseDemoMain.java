/**
 * @Auther: 罗庆宏
 * @Date: 2021/04/12/22:29
 * @Description:
 */

import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HBaseDemoMain {
//    public static Configuration configuration = null;
    public static Connection connection = null;
    public static Admin admin = null;

    static  {
        try {
            //1. Get configuration info.
            Configuration configuration = HBaseConfiguration.create();
//            configuration.set("hbase.rootdir", "hdfs://192.168.243.128:9000/hbase");
            configuration.set("hbase.zookeeper.quorum","192.168.243.128");  //hbase 服务地址
            configuration.set("hbase.zookeeper.property.clientPort","2181"); //端口号
//            configuration.set("hbase.zookeeper.quorum","192.168.243.128");

            //2. Create link object.
            connection = ConnectionFactory.createConnection(configuration);

            //3. Create Admin Object
            admin = connection.getAdmin();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Init over");
        System.out.println("Start use it!");

        Scanner scanner = new Scanner(System.in);
//        int i = scanner.nextInt();
        while(true) {
            displayBaseInfo();

            int i = scanner.nextInt();
            if( i == 0)
                break;
            switch (i) {
                case 1:
                        // 创建表
                        createTableForMain();
                        break;
                case 2:
                        // 增加记录
                        addRecordForMain();
                        break;
                case 3:
                        // 按列查询
                        searchOfColumnForMain();
                        break;
                case 4:
                        //修改数据
                        alterDataForMain();
                        break;
                case 5:
                        // 删除表
                        deleteRowForMain();
                        break;
                default : System.out.println("请确认输入参数，请重新输入!"); break;
            }
        }

        close();
    }

    public static void deleteRowForMain() {

        System.out.println("开始删除表");
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入表名：");
        String s = scanner.nextLine();
        System.out.println("请输入行键");
        String ss = scanner.nextLine();

        try {
            deleteRow(s, ss);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("删除完毕");
    }

    public static void alterDataForMain() {

        System.out.println("开始修改数据");
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入表名：");
        String t = scanner.nextLine();
        System.out.println("请输入行键：");
        String r = scanner.nextLine();
        System.out.println("请输入列：");
        String c = scanner.nextLine();
        System.out.println("请输入值");
        String v = scanner.nextLine();

        try {
            modifyData(t, r, c, v);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("修改完毕");
    }

    public static void searchOfColumnForMain() {

        System.out.println("开始查找列数据");
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入表名：");
        String s = scanner.nextLine();
        System.out.println("请输入列：");
        String s1 = scanner.nextLine();

        try {
            scanColumn(s, s1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("查找完毕");
    }

    public static void addRecordForMain( ){
        System.out.println("开始插入数据");
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入表名：");
        String t = scanner.nextLine();
        System.out.println("请输入行键：");
        String r = scanner.nextLine();
        System.out.println("请输入列（及列限定符）：");
        String c = scanner.nextLine();
        System.out.println("请输入值：");
        String v = scanner.nextLine();

        String []cs = new String[] {c};
        String []vs = new String[] {v};
        try {
            addRecord(t, r, cs, vs);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("插入完毕");
    }

    public static void createTableForMain() {
        System.out.println("开始新建表");
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入表名：");
        String s = scanner.nextLine();
        System.out.println("请输入列：");
        String s1 = scanner.nextLine();

        try {
            createTable(s, s1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("创建完毕");
    }

    public static void displayBaseInfo() {
        System.out.println("=============== Choose selection==================");
        System.out.println("1. Create a new Table");
        System.out.println("2. Add one Record.");
        System.out.println("3. Select data with column.");
        System.out.println("4. Alter data.");
        System.out.println("5. Delete data of one line.");
        System.out.println("Enter:");
    }


    //2. 判断表是否存在
    public static boolean isTableExiat(String tableName) throws IOException {
        return admin != null && admin.tableExists(TableName.valueOf(tableName));
    }
    //3. 关闭
    public static void close(){
        try {

            if (admin!=null){
                admin.close();
            }
            if (connection!=null){
                connection.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 4. 建表
    public static void createTable(String tableName, String... cfs) throws IOException {
        //1. 判断是否存在参数
        if(cfs.length <= 0){
            System.out.println("Please set column family.");
            return ;
        }

        //2. 判断是否表存在
        if(isTableExiat(tableName)){
            System.out.println(tableName + " is exist.");
            return ;
        }

        //3. 创建表描述器
        TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));

        //4. 循环添加列族信息
        for (String cf : cfs) {
            //5. 创建列族描述器
            ColumnFamilyDescriptor of = ColumnFamilyDescriptorBuilder.of(cf);

            //6. 添加具体的列族信息
            builder.setColumnFamily(of);
        }

        //7. 创建表
        admin.createTable(builder.build());

    }

    //5. 向表插入数据

    /**
     * <p>Hello</p>
     * @param tableName 表名
     * @param rowKey 行键
     * @param cf 列族
     * @param cn 列名
     * @param value 插入的值
     * @throws IOException 抛出异常
     */
    public static void putData(String tableName, String rowKey,
                               String cf, String cn, String value) throws IOException
    {
        //1. 获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));

        //2. 创建put对象
        Put put = new Put(Bytes.toBytes(rowKey));

        //3. 给Put对象赋值
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));

        //4. 插入数据
        table.put(put);

        //5. 关闭table
        table.close();
    }
    //5. 插入数据
    public static void insertData(String tableName, String rowKey,
                                  String colFamily, String col,
                                  String val) throws IOException
    {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(rowKey.getBytes(StandardCharsets.UTF_8));

        put.addColumn(colFamily.getBytes(StandardCharsets.UTF_8), col.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8));
        table.put(put);
        table.close();
    }

    //6. 获取数据
    public static void getData(String tableName, String rowKey, String colFamily, String col) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(rowKey.getBytes(StandardCharsets.UTF_8));
        get.addColumn(colFamily.getBytes(StandardCharsets.UTF_8), col.getBytes(StandardCharsets.UTF_8));
        Result result = table.get(get);
        System.out.println(new String(result.getValue(colFamily.getBytes(StandardCharsets.UTF_8), col==null?null:col.getBytes(StandardCharsets.UTF_8) ) ));
        table.close();
    }

    /**
     * 修改某行某列的数据
     * @param tableName 表名
     * @param rowKey 行键
     * @param column 列，可以是 "f1:c1" 的形式，也可以不含列限定符
     * @param value 值
     * @throws IOException 排除异常
     */
    public static void modifyData(String tableName, String rowKey,
                                 String column, String value) throws IOException
    {
        Table table= connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(rowKey.getBytes(StandardCharsets.UTF_8));
        String []cols = column.split(":");
        if( cols.length <= 1) {
            // 不含列限定符
            put.addColumn(column.getBytes(StandardCharsets.UTF_8),
                    "".getBytes(StandardCharsets.UTF_8),
                    value.getBytes(StandardCharsets.UTF_8));
        }
        else {
            // 含列限定符
            put.addColumn(cols[0].getBytes(StandardCharsets.UTF_8),
                    cols[1].getBytes(StandardCharsets.UTF_8),
                    value.getBytes(StandardCharsets.UTF_8));

        }

        table.put(put);
        table.close();
    }
    //7. 删除表
    public static void dropTable(String tableName) throws IOException {
        if( !isTableExiat(tableName)) {
            try {
                //1. 使表下线
                admin.disableTable(TableName.valueOf(tableName));
                //2. 删除表
                admin.deleteTable(TableName.valueOf(tableName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //8. 创建命名空间
    public static void createNameSpace(String ns) {

        // 1. 创建命名空间描述器
        NamespaceDescriptor build = NamespaceDescriptor.create(ns).build();

        //2. 创建命名空间
        try {
            admin.createNamespace(build);
        } catch (NamespaceExistException e) {
            System.out.println(ns + " namespace is exist!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 增加记录
    public static void addRecord(String tableName, String row,
                                 String[] column, String[] values) throws IOException
    {
        int length = values.length;
        for ( int i = 0; i< length; i++){
            String[] ss = column[i].split(":");
            if( ss.length > 1)
                insertData(tableName, row, ss[0], ss[1], values[i]);
            else
                insertData(tableName, row, ss[0], "", values[i]);
        }
    }
    //查询一列

    /**
     * <p>查询表中一列的数据，使用列族+列名的形式的，按列族+列名的形式查.
     *  使用列族的形式的，按照列族的形式查</p>
     * @param tableName 表名
     * @param column 列名
     * @throws IOException 抛出IO异常
     */
    public static void scanColumn(String tableName, String column) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();

        String [] cols = column.split(":");
        if ( cols.length > 1)  {
            scan.addColumn(cols[0].getBytes(StandardCharsets.UTF_8), cols[1].getBytes(StandardCharsets.UTF_8));
        }
        else {
            scan.addFamily(column.getBytes(StandardCharsets.UTF_8));
        }

        System.out.println("RowKey\tValue");
        ResultScanner scanner = table.getScanner(scan);
        for ( Result r : scanner) {
            for ( Cell cell : r.rawCells()) {
                System.out.println(Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength()) + ": \t" +
                        Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));;
            }
        }
        table.close();
        scanner.close();

    }

    public static void deleteRow(String tableName, String row) throws IOException {
        Delete delete = new Delete(row.getBytes(StandardCharsets.UTF_8));
        Table table = connection.getTable(TableName.valueOf(tableName));

        table.delete(delete);
        table.close();
    }
}
