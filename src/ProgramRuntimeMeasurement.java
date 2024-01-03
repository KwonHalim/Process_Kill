import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ProgramRuntimeMeasurement {

    static LocalTime running_time_measure(String str_creation_time) {
        DateTimeFormatter format_type = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        //현재 시간을 yyyyMMddHHmmss형태로 치환하기 위해 씀
        //원래 LocalDateTime은 yyyy-MM-ddTHH:mm:ss.SSS 구조로 되어있음
        //뒤의 sss는 밀리세컨드를 말하는 듯?
        LocalDateTime now = LocalDateTime.now();
        String str_now = now.format(format_type);
        LocalDateTime current_time = LocalDateTime.parse(str_now, format_type);

        //String 형태로 넘어온 생성시간을 LocalDateTime 객체로 반환
        LocalDateTime creation_time = LocalDateTime.parse(str_creation_time, format_type);

        Duration elapsed_time = Duration.between(creation_time, current_time);
        System.out.println("Time elapsed: " + elapsed_time);

        //이건 LocalTime으로 시간 경과를 나타낸건데 of형태는 다시 봐야할 필요 있음
        LocalTime time_passed = LocalTime.of(0, 0, 0).plusSeconds(elapsed_time.getSeconds());
//        System.out.println(time_passed.getHour());
//        System.out.println(time_passed.getMinute());
//        System.out.println(time_passed.getSecond());
        return time_passed;
    }

    public static void main(String[] args) {
        boolean found = false;
        //Name of process to track
        String targetProcessName = "";
        try {
            //take all the list of processes running currently
            Process processListProcess = new ProcessBuilder("wmic", "process", "get", "Name", ",", "CreationDate", ",", "processid").start();
            //ProcessBuilder? : RUN Window command in JAVA.
            //If there are more than one input command seperate with spaces.
            //Ex) new ProcessBuilder("/bin/sh", "-c", cmd).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(processListProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if ((line.contains("CreationDate")) || (line.contains("Name"))) {
                    continue;
                } else if (line.equals("")) {
                    continue;
                } else {
                    String name = line.substring(27);
                    if (name.toLowerCase().contains(targetProcessName.toLowerCase())) {
                        System.out.println("Found the program.");
//                        System.out.println(line);//생성날짜, 이름, PID순서
//                        System.out.println("This Program started at : " + line.substring(0, 14));//이건 생성날짜 추출
//                        System.out.println(line.substring(27, 57)); //이건 게임이름 추출
                        System.out.println("ID:" + line.substring(57));//이건 PID 추출
                        LocalTime time = running_time_measure(line.substring(0, 14));
                        if (time.getSecond() >= 1) {//##################현재는 1초만 지나도 끄게 되어있음. 추후 변경해야함################
                            Process kill = new ProcessBuilder("taskkill", "/im", targetProcessName).start();
                            System.out.println("Sent the message to turn off the program");
                        }
                        found = true;
                        break;
                    }
                }
            }
            if (found == false) {
                System.out.println("There is no such program running");
            } else {
                //if found문과 별개로...
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}