import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class test {
    public static void main(String[] args) {
        //주석 부분은 다 필요 없음..헷갈려서 계속 이것저것 치다가 안 지우고 내부려 둔 것
//        // 현재 날짜와 시간을 가져오기
//        LocalDateTime now = LocalDateTime.now();
//
//        // 형식 지정을 위한 DateTimeFormatter 사용
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//
//        // 현재 날짜와 시간을 지정된 형식으로 포맷팅
//        String formattedDateTime = now.format(formatter);
//        LocalDateTime current_time = LocalDateTime.parse(now.format(formatter));
//
//        System.out.println("Current Date and Time: " + formattedDateTime);
//        String creation_time = "20240102183640";
//        LocalDateTime createdTime = LocalDateTime.parse(creation_time, formatter);
//        LocalDateTime currentTime = LocalDateTime.parse(formattedDateTime, formatter);
//
//        System.out.println(createdTime);
//        System.out.println(currentTime);
//
//
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//
//        // 현재 날짜를 "yyyyMMdd" 형식으로 포맷팅
//        String formattedDate = now.format(formatter);
//        System.out.println("Current Date: " + formattedDate);
//
//        // 형식 지정된 문자열을 다시 LocalDateTime으로 파싱
//        LocalDateTime parsedDate = LocalDateTime.parse(formattedDate, formatter);
//        System.out.println("Parsed Date: " + parsedDate);
//
//
//
//
//
//
//        String creation_time = "20240102183640";
//        LocalDateTime createdTime = LocalDateTime.parse(creation_time, formatter);
//        String formattedCreatedTime = createdTime.format(formatter);
//        System.out.println("Parsed Created Time: " + formattedCreatedTime);
//        System.out.println(createdTime.getHour());
//        System.out.println("Time elpased");
//
//        Duration elaplsed = Duration.between(createdTime, parsedDate);

        //포맷팅 타입
        DateTimeFormatter format_type= DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        //now를 뽑고 그걸 parsing하고 daytime으로 바꾸어야함
        LocalDateTime now = LocalDateTime.now();
        String now_format_Str = now.format(format_type);
        LocalDateTime current_time = LocalDateTime.parse(now_format_Str, format_type);
        System.out.println(now);


        String creation_time = "20240102215030";
        LocalDateTime LDT_creation_time = LocalDateTime.parse(creation_time, format_type);
        System.out.println(LDT_creation_time);

        Duration elapsed_time = Duration.between(LDT_creation_time, current_time);
        System.out.println(elapsed_time);
        System.out.println(elapsed_time.getSeconds());

        LocalTime time_passed = LocalTime.of(0,0,0).plusSeconds(elapsed_time.getSeconds());
        System.out.println(time_passed.getHour());
        System.out.println(time_passed.getMinute());
        System.out.println(time_passed.getSecond());
    }
}
