import java.io.File;
import java.util.Scanner;

public class Herausnehmen {
    String id = "",
            entryname = "",
            dataclass = "",
            molecule = "";

    int sequenzlenght = 0;

    public void herausnehmen(String wo)

    {
        File datei = new File(wo);
        {

            try {
                Scanner scan = new Scanner(datei);

                if (scan.hasNext("ID:")) {
                    scan.next();
                    id = scan.next();
                    scan.next();
                    entryname = scan.next() ;
                    scan.next();
                    dataclass = scan.next();
                    scan.next();
                    molecule = scan.next();
                    scan.next();
                    sequenzlenght = scan.nextInt();
                }
            } catch (java.io.FileNotFoundException e) {
                System.out.println(e);
            }
        }

    }
}


