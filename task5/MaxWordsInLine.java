import java.io.*;
import java.util.*;

public class MaxWordsInLine {
    public static void main(String[] args) {
        // Шлях до файлу (змініть шлях на ваш власний файл)
        String filePath = "input.txt";

        try {
            String maxLine = getMaxWordsLine(filePath);
            System.out.println("Рядок із максимальною кількістю слів:");
            System.out.println(maxLine);
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
    }

    public static String getMaxWordsLine(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        String maxWordsLine = "";
        int maxWordsCount = 0;

        while ((line = reader.readLine()) != null) {
            int wordsCount = countWords(line);
            if (wordsCount > maxWordsCount) {
                maxWordsCount = wordsCount;
                maxWordsLine = line;
            }
        }

        reader.close();
        return maxWordsLine;
    }

    public static int countWords(String line) {
        // Розділення рядка на слова за допомогою пробілів та інших пробільних символів
        String[] words = line.trim().split("\\s+");
        return words.length;
    }
}
