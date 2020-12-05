package learning.java.apps;

//import com.sun.java.util.jar.pack.Instruction;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;
//import java.io.Console;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardCopyOption.*;

public class FileManipulation {
    public static void main(String[] args) throws IOException {
       /* String path1 = null;
        Console cnsl = System.console();
        //System.out.println("Please give directory path:");
        path1 = cnsl.readLine("Directory Path:");
        System.out.println(path1);
        */


       Scanner in = new Scanner(System.in);

       System.out.println("Enter directory path:");

       Path strLine1 = Paths.get(in.nextLine());


       while (strLine1.toString().equals("")) {
           System.out.println("Please enter a valid directory path:");
          strLine1 = Paths.get(in.nextLine());
       }

        Pattern inPat = Pattern.compile("^[a-zA-Z]:\\\\w*\\w+.*[^\\Q.\\E][\\w]{3}$");


        Matcher inMat = inPat.matcher(strLine1.toString());

        if (!strLine1.toString().endsWith("\\")) {
            System.out.println("HI");

        }

        System.out.println("*****************************************");
        System.out.println(strLine1);

        System.out.println(inMat.find());
        System.out.println(inMat.group());
        System.out.println(inMat.start());
        System.out.println(inMat.end());
        System.out.println(inMat.end());


        System.out.println("*******************************************");

        System.out.println("Last 3 characters of the file extension type:");
        String strLine2 = in.nextLine();
        while (strLine2.equals("")) {
            System.out.println("Please enter a valid file extension");
            strLine2 = in.nextLine();
        }

        System.out.println("Press a if you would like the filenames to contain and be ordered by last access time\n" +
        "Press b if you would like the filenames to contain and be ordered by file size\n" +
        "Press c if you would like to keep the filenames as they are.");
        String strLine3 = in.nextLine();
        while (strLine3.equals("")) {
            System.out.println("Please choose a b or c");
            strLine3 = in.nextLine();
        }

        System.out.println("New subfolder will be named "+strLine2.toUpperCase()+"_Storage_"+LocalDate.now());

        switch (strLine3) {
            case "a" :

            try (DirectoryStream<Path> dir1 = Files.newDirectoryStream(strLine1, "*.{"+strLine2+"}")) {
            int fileCount = 0;
            for (Path p: dir1) {
                System.out.println();
                System.out.println("Moving "+p+" to folder ");
                //System.out.println();
                System.out.println(strLine1 + "\\" + strLine2.toUpperCase() + "_Storage_"+LocalDate.now());
                if (fileCount == 0) {
                    Files.createDirectory(Paths.get(strLine1 + "\\" + strLine2.toUpperCase() + "_Storage_" + LocalDate.now()));
                }
                System.out.println(p);

                long fooTime = Files.getLastModifiedTime(Paths.get(strLine1 + "\\" + p.getFileName())).toMillis();


                Files.copy(p, Paths.get(strLine1 + "\\" + strLine2.toUpperCase() + "_Storage_" + LocalDate.now() + "\\" + fooTime + "_" + p.getFileName()));//,REPLACE_EXISTING);
                System.out.println("File Count: "+ ++fileCount);

                System.out.println(strLine1 + "\\" + " -------------------------------");
            }
            System.out.println("Copying complete");

        }

                break;
            case "b" :

            try (DirectoryStream<Path> dir1 = Files.newDirectoryStream(strLine1, "*.{"+strLine2+"}")) {
            int fileCount = 0;
            for (Path p: dir1) {
                System.out.println();
                System.out.println("Moving " + p +" to folder ");
                System.out.println(strLine1 + "\\" + strLine2.toUpperCase() + "_Storage_"+LocalDate.now());
                if (fileCount == 0) {
                    Files.createDirectory(Paths.get(strLine1 + "\\" + strLine2.toUpperCase() + "_Storage_" + LocalDate.now()));
                }
                System.out.println(p);

                Path filePath2 = Paths.get(strLine1 + "\\" + p.getFileName());
                long fooSize = Files.size(filePath2);

                Files.copy(p, Paths.get(strLine1 + "\\" + strLine2.toUpperCase()+"_Storage_" + LocalDate.now() + "\\"+ fooSize + "_" + p.getFileName()));//,REPLACE_EXISTING);
                System.out.println("File Count: "+ ++fileCount);

                System.out.println(strLine1 + "\\" + " -------------------------------");
            }
            System.out.println("Copying complete");

        }

                break;
            default :

            try (DirectoryStream<Path> dir1 = Files.newDirectoryStream(strLine1, "*.{"+strLine2+"}")) {
            int fileCount = 0;
            for (Path p: dir1) {
                System.out.println();
                System.out.println("Moving "+p+" to folder ");
                System.out.println(strLine1 + "\\" + strLine2.toUpperCase()+"_Storage_"+LocalDate.now());
                if (fileCount == 0) {
                    Files.createDirectory(Paths.get(strLine1 + "\\" + strLine2.toUpperCase() + "_Storage_" + LocalDate.now()));
                }
                System.out.println(p);
                Files.copy(p, Paths.get(strLine1 + "\\" + strLine2.toUpperCase() + "_Storage_"+LocalDate.now()+"\\"+p.getFileName()));//,REPLACE_EXISTING);
                System.out.println("File Count: "+ ++fileCount);

                System.out.println(strLine1 + "\\" + " -------------------------------");
            }
            System.out.println("Copying complete");

        }

                break;
        }

    }
}






