import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TestDataTooLong {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestDataTooLong.class);
    private static final String SQL = "insert into test.test_tab (a,b,c,d,e,f,g,h,i,j,k,l,m,n,serial_no,p,q,r,s) select '50361593','87981261','1821053','42589298','792c69d1-14af-4d9','c769cbda-1155-409','79331634','9c','','7714722.00','92906792.00','','80923140','',ifnull(?,''),'134f5c3b-b19a-45f4-b4d8-32580eb76b9','d42444e5-2cbb-4cf6-9652-7fafc913166','41f2214c-e525-417f-827c-b52034d4189',''";

    private static String IP_PORT;
    private static String USER;
    private static String PASSWORD;
    private static String DB_NAME;
    private static String SERIAL_NO = "a83969eb-be2a-4165-acc5-39219a5ef49a83969eb-be2a-4165-acc5-39219a5ef49-a83969eb-be2a-4165-acc5-39219a5ef49-a83969eb-be2a-4165-ac";


    public static void main(String[] args) throws Exception {
        parseArgs(args);
        String driver = "com.mysql.jdbc.Driver";
        String url = String.format("jdbc:mysql://%s/%s?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&useServerPrepStmts=true&cachePrepStmts=true&allowMultiQueries=true&jdbcCompliantTruncation=false", IP_PORT, DB_NAME);

        try (Connection connection = DriverManager.getConnection(url, USER, PASSWORD);
             PreparedStatement ps =  connection.prepareStatement("SET NAMES utf8mb4 COLLATE utf8mb4_general_ci");
             PreparedStatement ps1 =  connection.prepareStatement(SQL);) {
            ps.execute();
            ps1.setString(1,SERIAL_NO);
            ps1.execute();
        }
    }

    private static void parseArgs(String [] args) {
        if (args.length == 4 || args.length == 5) {
            IP_PORT = args[0];
            USER = args[1];
            PASSWORD = args[2];
            DB_NAME = args[3];
            if (args.length == 5) {
                SERIAL_NO = args[4];
            }
        } else {
            LOGGER.error("please input 4 / 5 args:  IP_PORT USER PASSWORD  DB_NAME (SERIAL_NO) ");
            System.exit(1);
        }
    }
}
