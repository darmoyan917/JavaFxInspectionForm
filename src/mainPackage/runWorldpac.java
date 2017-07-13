package mainPackage;


import java.io.*;
import java.util.Map;

public class runWorldpac implements Runnable {


        public runWorldpac() {
            start();


        }

        public Thread start(){
            try {
                ProcessBuilder process = new ProcessBuilder("C:\\speedDIAL\\speedDIAL.exe");
                Map<String, String> environ = process.environment();
                Process p = process.start();
                InputStream is = p.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line;
                while ((line = br.readLine()) != null) {
                    //System.out.println(line);
                }
                System.out.println("Program terminated!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new Thread();
        }
        public void run() {

        }
    }

