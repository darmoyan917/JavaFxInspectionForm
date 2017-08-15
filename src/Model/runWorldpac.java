package Model;


import java.io.*;
import java.util.Map;

public class runWorldpac implements Runnable {


        public runWorldpac() {
            On();


        }
        public void On() {
        try {
            ProcessBuilder pBuilder = new ProcessBuilder("C:\\speedDIAL\\speedDIAL.exe");

            // don't forget to handle the error stream, and so
            // either combine error stream with input stream, as shown here
            // or gobble it separately
            pBuilder.redirectErrorStream(true);
            final Process process = pBuilder.start();
            final InputStream is = process.getInputStream();

            // in case you need to send information back to the process
            // get its output stream. Don't forget to close when through with it
            final OutputStream os = process.getOutputStream();

            // thread to handle or gobble text sent from input stream
            new Thread(() -> {
                // try with resources
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }).start();

            // thread to get exit value from process without blocking
            Thread waitForThread = new Thread(() -> {
                try {
                    int exitValue = process.waitFor();
                    // TODO: handle exit value here
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            waitForThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

