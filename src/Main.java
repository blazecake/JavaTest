import com.test.Person;
import com.test.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws ParseException {
//        System.out.println("Hello World!");

//        readFileChannel();

//        new Thread(() -> System.out.println("Hello world!")).start();

//        String[] atp = {"Rafael Nadal", "Novak Djokovic",
//                "Stanislas Wawrinka",
//                "David Ferrer", "Roger Federer",
//                "Andy Murray", "Tomas Berdych",
//                "Juan Martin Del Potro"};
//        List<String> players = Arrays.asList(atp);
//
//        players.forEach((player) -> System.out.print(player + ", "));
//
//        System.out.println("");
//        System.out.println("");
//        Collections.sort(players, (String s1, String s2) -> s1.compareTo(s2));
//        players.forEach(System.out::println);

        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        System.out.println("所有程序员的姓名:");
        javaProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        System.out.println("给程序员加薪 5% :");
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());
        javaProgrammers.forEach(giveRaise);

        System.out.println("下面是月薪超过 $1,400 的程序员:");
        List<Person> personList = javaProgrammers.stream().filter((p) -> (p.getSalary() > 1400))
                .limit(6)
                .sorted((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()))
                .collect(Collectors.toList());
//                .max((p1, p2) -> (p1.getSalary() - p2.getSalary())).get();
//                .forEach((p) -> System.out.printf("%s %s;", p.getFirstName(), p.getLastName()));

        personList.forEach((p) -> System.out.print(p.getFirstName() + ", "));

        String s = javaProgrammers.stream()
                .map(Person::getFirstName)
                .collect(Collectors.joining(";", "姓名：", " 姓名结束"));
        System.out.println();
        System.out.println(s);

        Set<String> s2 = javaProgrammers.stream()
                .map(Person::getFirstName)
                .collect(Collectors.toCollection(TreeSet::new));

        int totalSalary = javaProgrammers
                .parallelStream()
                .mapToInt(p -> p.getSalary())
                .sum();

        IntSummaryStatistics stats = javaProgrammers
                .stream()
                .mapToInt((p) -> p.getSalary())
                .summaryStatistics();
        System.out.println(stats.toString());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("/");
        stringBuilder.insert(0, "abc");
        System.out.println(stringBuilder.toString());

        Date date = new Date(System.currentTimeMillis());
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());
        String d = new SimpleDateFormat().format(date);

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        Date yourUtcDate = sdf.parse(d);
        sdf.format(yourUtcDate);


        Date date2 = Date.from(Instant.now());
        Calendar calendar = Calendar.getInstance(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        calendar.setTimeInMillis(System.currentTimeMillis());
        Date date1 = calendar.getTime();
        calendar.getTimeInMillis();


    }

    /***
     * 读取文件通道
     */
    @Test("member")
    public static void readFileChannel() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("doc/test.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        try {
            int byteRead = channel.read(buffer);
            while (byteRead != -1) {
                System.out.println("Read" + byteRead);
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
                byteRead = channel.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
