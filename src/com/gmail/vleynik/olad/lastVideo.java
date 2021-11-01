package com.gmail.vleynik.olad;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class lastVideo {
    public static void save(String path) {
        try(FileWriter writer = new FileWriter("resources/config.txt"))
        {
            writer.write(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String load() {
        try (Scanner scanner = new Scanner(new File("resources/config.txt"))) {
            if (scanner.hasNext()) {
                File file =  new File(scanner.nextLine());
                if (file.exists()) {
                    return file.getAbsolutePath();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "resources/bunny.mp4";
    }
}
