package analyzer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int BUFFER_SIZE = 4096;


    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        if (args.length < 2) {
            System.out.println("Wrong args");
            System.exit(0);
        }

        String fileDir = args[0];
        String pattern = args[1];
        File patternFile = new File(pattern);
        Scanner scanner = new Scanner(patternFile);
        List<Pattern> patternList = new ArrayList<>();
        while(scanner.hasNext()) {
            patternList.add(new Pattern(scanner.nextLine()));
        }

        Context context = new Context(new RBAlgorithm());
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (File file: Objects.requireNonNull(new File(fileDir).listFiles())) {
            executorService.execute(() -> {
                boolean flag = false;
                String answer = "Unknown file type";
                for (Pattern value : patternList) {
                    try (InputStream inputStream = new FileInputStream(file)) {
                        byte[] buffer = new byte[BUFFER_SIZE];
                        int maxPriority = Integer.MIN_VALUE;
                        flag = false;

                        String searchPattern = value.getIndication();
                        while (inputStream.read(buffer) != -1) {
                            if (context.execute(new String(buffer), searchPattern)) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag && value.getPriority() > maxPriority) {
                            maxPriority = value.getPriority();
                            answer = value.getAnswer();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println(file + ": " + answer);
            });
        }
        executorService.awaitTermination(10_000L, TimeUnit.MILLISECONDS);
    }
}
