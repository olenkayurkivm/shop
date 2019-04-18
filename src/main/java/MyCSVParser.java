import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OYurkiv on 12/14/2018.
 */
public class MyCSVParser {

    final static Logger LOG = Logger.getLogger(MyCSVParser.class);

    public static final String ORDERS_CSV = "src/test/resources/orders.csv";
    public static final String COMMENTS_CSV = "src/test/resources/comments.csv";
    public static final String PROXY_CSV = "src/test/resources/proxy.csv";
    public static final String USED_PROXY_CSV = "src/test/resources/usedproxy.csv";

    public static final String NEW_PROXY_CSV = "src/test/resources/newproxy.csv";

    public static User parseCSVToUsers() throws IOException {

        try (CSVParser csvParser = CSVFormat.DEFAULT.parse(new FileReader(new File(ORDERS_CSV)))) {

            List<User> users = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                User user = new User();
                user.setUserId(csvRecord.get(0));
                user.setEmail(csvRecord.get(1));
                user.setFirstName(csvRecord.get(2));
                user.setLastName(csvRecord.get(3));
                users.add(user);

            }
            User targetUser = users.get(0);
            users.remove(0);
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(ORDERS_CSV));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (User user : users) {
                csvPrinter.printRecord(user.getUserId(), user.getEmail(), user.getFirstName(), user.getLastName());
            }
            csvPrinter.flush();
            return targetUser;
        }
    }

    public static String parseCSVToComments() throws IOException {

        try (CSVParser csvParser = CSVFormat.DEFAULT.parse(new FileReader(new File(COMMENTS_CSV)))) {

            List<String> comments = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                comments.add(csvRecord.get(0));

            }
            String targetComment = comments.get(comments.size() - 1);
            comments.remove(comments.size() - 1);
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(COMMENTS_CSV));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (String comment : comments) {
                csvPrinter.printRecord(comment);
            }
            csvPrinter.flush();
            return targetComment;
        }
    }

    public static ProxyObject parseCSVToProxy() throws IOException {
        boolean proxyCheckedStatus = false;
        ProxyObject targetProxy = null;
        while (!proxyCheckedStatus) {

            try (CSVParser csvParser = CSVFormat.DEFAULT.parse(new FileReader(new File(PROXY_CSV)))) {

                List<ProxyObject> proxies = new ArrayList<>();
                Iterable<CSVRecord> csvRecords = csvParser.getRecords();

                for (CSVRecord csvRecord : csvRecords) {

                    ProxyObject proxyObject = new ProxyObject();
                    proxyObject.setProxyIp(csvRecord.get(0));
                    proxyObject.setProxyPort(csvRecord.get(1));
                    proxyObject.setProxyType(csvRecord.get(2));
                    proxies.add(proxyObject);

                }
                targetProxy = proxies.get(0);
                if (checkProxy(targetProxy)) {
                    proxyCheckedStatus = true;
                }
                proxies.remove(0);
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(PROXY_CSV));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
                for (ProxyObject proxy : proxies) {
                    csvPrinter.printRecord(proxy.getProxyIp(), proxy.getProxyPort(),proxy.getProxyType());
                }
                csvPrinter.flush();
            }
        }
        return targetProxy;
    }

    public static boolean checkProxy(ProxyObject proxyToCheck) throws IOException {

        boolean proxyIsNotUsed = true;
        try (CSVParser csvParser = CSVFormat.DEFAULT.parse(new FileReader(new File(USED_PROXY_CSV)))) {

            List<ProxyObject> usedProxies = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                ProxyObject proxyObject = new ProxyObject();
                proxyObject.setProxyIp(csvRecord.get(0));
                proxyObject.setProxyPort(csvRecord.get(1));
                proxyObject.setProxyType(csvRecord.get(2));
                usedProxies.add(proxyObject);

            }

            if (!usedProxies.contains(proxyToCheck)) {
                LOG.info(String.format("Good (not earlier used) proxy : %s", proxyToCheck.getProxyIp()));
                usedProxies.add(proxyToCheck);
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(USED_PROXY_CSV));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
                for (ProxyObject proxy : usedProxies) {
                    csvPrinter.printRecord(proxy.getProxyIp(), proxy.getProxyPort(),proxy.getProxyType());
                }
                csvPrinter.flush();
            } else {
                LOG.info(String.format("Bad (used) proxy! Taking another proxy : %s", proxyToCheck.getProxyIp()));
                proxyIsNotUsed = false;
            }
        }
        return proxyIsNotUsed;
    }

    //----------------------------------------------------------------------------------------------

    public static void writeProxy(List<ProxyObject> proxiesToRecord) throws IOException {
            LOG.info("Recording parsed proxies...");
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(PROXY_CSV));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (ProxyObject proxy : proxiesToRecord) {
                csvPrinter.printRecord(proxy.getProxyIp(), proxy.getProxyPort(),proxy.getProxyType());
            }
            csvPrinter.flush();
        }

}