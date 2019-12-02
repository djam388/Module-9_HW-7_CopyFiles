
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Please enter source directory:");
        String pathToWork = new Scanner(System.in).nextLine();
        File sourceFolder = new File(pathToWork);

        System.out.println("Please enter destination directory:");
        pathToWork = new Scanner(System.in).nextLine();
        File destinationFolder = new File(pathToWork);

        if (!destinationFolder.exists())
        {
            destinationFolder.mkdir();
        }



        copyDir(sourceFolder.toString(), destinationFolder.toString(), true);
    }

    private static void copyDir(String src, String dest, boolean overwrite) {
        try {
            Files.walk(Paths.get(src), Integer.MAX_VALUE, FileVisitOption.FOLLOW_LINKS).forEach(a -> {
                Path b = Paths.get(dest, a.toString().substring(src.length()));
                try
                {
                    if (!a.toString().equals(src))
                    {
                        Files.copy(a, b, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            });
        }
        catch (IOException e)
        {
            //permission issue
            e.printStackTrace();
        }
    }
}
