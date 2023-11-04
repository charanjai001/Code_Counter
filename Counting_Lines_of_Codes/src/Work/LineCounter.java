package Work;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 
public class LineCounter {
 
    public static void main(String[] args) {
    	// Replace with the actual path to your Java project
        String projectDirectory = "path/to/your/java/project"; 
        countLinesInDirectory(projectDirectory);
    }
 
    public static void countLinesInDirectory(String directory) {
        int totalLines = 0;
        int totalJavaFiles = 0;
        int totalSubfolders = 0;
        File folder = new File(directory);
 
        if (folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".java")) {
                    try {
                        Path path = Paths.get(file.getPath());
                        long lines = Files.lines(path).count();
                        totalLines += lines;
                        totalJavaFiles++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (file.isDirectory()) {
                    totalSubfolders++;
                    int[] subfolderStats = countLinesInSubfolder(file.getAbsolutePath());
                    totalLines += subfolderStats[0];
                    totalJavaFiles += subfolderStats[1];
                    totalSubfolders += subfolderStats[2];
                }
            }
        }
 
        System.out.println("Total lines of code in the Java project: " + totalLines);
        System.out.println("Total Java files: " + totalJavaFiles);
        System.out.println("Total subfolders: " + totalSubfolders);
    }
 
    public static int[] countLinesInSubfolder(String subfolderPath) {
        int totalLines = 0;
        int totalJavaFiles = 0;
        int totalSubfolders = 0;
        File subfolder = new File(subfolderPath);
 
        if (subfolder.exists() && subfolder.isDirectory()) {
            for (File file : subfolder.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".java")) {
                    try {
                        Path path = Paths.get(file.getPath());
                        long lines = Files.lines(path).count();
                        totalLines += lines;
                        totalJavaFiles++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (file.isDirectory()) {
                    totalSubfolders++;
                    int[] subfolderStats = countLinesInSubfolder(file.getAbsolutePath());
                    totalLines += subfolderStats[0];
                    totalJavaFiles += subfolderStats[1];
                    totalSubfolders += subfolderStats[2];
                }
            }
        }
 
        return new int[]{totalLines, totalJavaFiles, totalSubfolders};
    }
}
