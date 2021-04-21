import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.NamespaceExistException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.junit.Test;

import java.io.IOException;

/**
 * <p></p>
 * @author github/blackswords
 * @date 2021/04/13/22:06
 */
public class TestForHBase {


    @Test
    public void testCreateTable() {
        try {
            HBaseDemoMain.createTable("test123", "f1", "f2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HBaseDemoMain.close();

    }
    @Test
    public void testScan() {
        try {
            HBaseDemoMain.scanColumn("test1", "f1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        HBaseDemoMain.close();
    }

    @Test
    public void testPut() {
        try {
            HBaseDemoMain.putData("test1", "row1", "f2", "c1", "v2");
            HBaseDemoMain.putData("test1", "row1", "f1", "c1", "v1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HBaseDemoMain.close();
    }

    @Test
    public void testScanColumn () {
        try {
            HBaseDemoMain.scanColumn("test1", "f1");
            HBaseDemoMain.scanColumn("test1", "f1:c1");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testModify() {
        try {
            HBaseDemoMain.modifyData("test1", "row1", "f1", "newVal");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            HBaseDemoMain.deleteRow("test1", "row1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HBaseDemoMain.close();
    }

}
