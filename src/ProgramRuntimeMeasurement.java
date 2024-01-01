import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProgramRuntimeMeasurement {


    static String getTodayDate() {
        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyyMMddHHmmss"; //hhmmss로 시간,분,초
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);
        return formatter.format(today);
    }


    public static void main(String[] args) {
        //Name of process to track
        String targetProcessName = "Cyberpunk2077.exe";
        try {
            //take all the list of processes running currently
            Process processListProcess = new ProcessBuilder("wmic", "process", "get", "Name", ",", "CreationDate",",","processid").start();
            //ProcessBuilder? : RUN Window command in JAVA.
            //If there are more than one input command seperate with spaces.
            //Ex) new ProcessBuilder("/bin/sh", "-c", cmd).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(processListProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if ((line.contains("CreationDate")) || (line.contains("Name"))) {
                    continue;
                }
                else if(line.equals("")) {
                    continue;
                }
                else {
                    String name = line.substring(27);
                    System.out.println(name);
                    if(name.contains(targetProcessName)) {
                        System.out.println("Gottcha");
                        System.out.println(line);//생성날짜, 이름, PID순서
                        System.out.println(line.substring(0, 14));//이건 생성날짜 추출
                        System.out.println(line.substring(27,59)); //이건 게임이름 추출
                        System.out.println("ID:" + line.substring(59));//이건 PID 추출
                    }
                }
            }
            System.out.println("Today Date:" + getTodayDate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}