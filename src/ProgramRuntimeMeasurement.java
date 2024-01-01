import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramRuntimeMeasurement {

    public static void main(String[] args) {
        //Name of process to track
        String targetProcessName = "Cyberpunk2077.exe";
        try {
            //take all the list of processes running currently
            Process processListProcess = new ProcessBuilder("tasklist").start();
            //ProcessBuilder? : RUN Window command in JAVA.
            //If there are more than one input command seperate with spaces.
            //Ex) new ProcessBuilder("/bin/sh", "-c", cmd).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(processListProcess.getInputStream()));
            String line;
            int targetProcessPID = -1;  // 초기값으로 임의의 음수 설정
            while ((line = reader.readLine()) != null) {
                if (line.contains(targetProcessName)) {
                    String[] tokens = line.split("\\s+");
                    targetProcessPID = Integer.parseInt(tokens[1]);
                    break;
                }
            }

            // 프로세스가 종료될 때까지 대기
            processListProcess.waitFor();

            if (targetProcessPID != -1) {
                System.out.println("프로세스 '" + targetProcessName + "'의 PID: " + targetProcessPID);

                // taskkill 명령어로 프로세스 종료 (예시, 주의: 이 부분을 실제로 사용할 때에는 주의가 필요합니다.)
                Process killProcess = new ProcessBuilder("taskkill", "/F", "/PID", String.valueOf(targetProcessPID)).start();
                killProcess.waitFor();
            } else {
                System.out.println("프로세스 '" + targetProcessName + "'가 실행 중이 아닙니다.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
