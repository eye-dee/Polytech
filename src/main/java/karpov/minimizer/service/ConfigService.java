package karpov.minimizer.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Polytech
 * Created by Игорь on 21.02.2017.
 */
public class ConfigService {
    private static FileReader fileReader;
    private static BufferedReader bufferedReader;

    public static boolean load() {
        try {
            fileReader = new FileReader("E:\\IdeaProject\\Polytech\\src\\main\\resources\\karpov.minimizer\\automat.txt");
            bufferedReader = new BufferedReader(fileReader);
            return true;
        } catch (final IOException ioe){
            System.out.println(ioe.getMessage());
        }
        fileReader = null;
        return false;
    }

    public static FileReader getReader(){
        if (fileReader != null){
            return fileReader;
        } else {
            throw new IllegalArgumentException("Не создан fileReader");
        }
    }

    public static BufferedReader getBufferedReader(){
        return bufferedReader;
    }
}
