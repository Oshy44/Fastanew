import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MyGenBank {

    public MyGenBank() {
    }

    public static void datenbank(String pathname, String id, String entryname, String dataclass, String molecule, int sequenzlenght, String insgesamt) {
        File datei = new File(pathname);

        try {
            FileWriter writer = new FileWriter(datei, false);
            writer.write("\n");
            writer.write("ID: " + id + " Entryname: " + entryname + " Dataclass: " + dataclass + " Molecule: " + molecule + " Sequenzlenght: " + sequenzlenght + "\n");
            for (int i = 0; i < insgesamt.length(); ++i) {
                if (i % 80 == 0) {
                    writer.write("\n");
                }

                writer.write(insgesamt.charAt(i));
            }

            writer.write("\n");
            writer.flush();
            writer.close();
        } catch (IOException var10) {
            System.out.println("Problem");
        }

    }



    public static void EMBLTToMyGenBank(String pathfind, String meineBank) {
        File datei = new File(pathfind);

        try {
            Scanner scan = new Scanner(datei);

            label40:
            while (scan.hasNext()) {
                scan.next();
                String id = scan.next();
                id=id.replace(";","");
                String x = scan.next();
                String entryname = x + "" + scan.next();
                entryname=entryname.replace(";","");
                String dataclass = scan.next();
                dataclass=dataclass.replace(";","");
                x = scan.next();
                String molecule = x + scan.next();
                molecule=molecule.replace(";","");
                scan.next();
                scan.next();
                int sequenzlenght = scan.nextInt();
                String insgesamt = "";
                scan.next();

                while (true) {
                    while (true) {
                        if (!scan.hasNext()) {
                            continue label40;
                        }

                        if (scan.hasNext("SQ")) {
                            scan.nextLine();

                            while (scan.hasNext()) {
                                insgesamt = insgesamt + scan.next();
                                insgesamt = insgesamt.replaceAll("[0-9]", "");
                                insgesamt = insgesamt.replaceAll("/", "");
                            }

                            datenbank(meineBank, id, entryname, dataclass, molecule, sequenzlenght, insgesamt);
                        } else {
                            scan.nextLine();
                        }
                    }
                }
            }
        } catch (FileNotFoundException var10) {
            System.out.println(var10);
        }

    }

    public static void GenBankToMyGenBank(String herkunft, String meineBank) {
        File datei = new File(herkunft);
        Scanner scan = null;
        String id = "",
                entryname = "",
                dataclass = "",
                molecule = "",
                insgesamt = "";
        int sequenzlenght = 0;

        try {
            label39:
            for (scan = new Scanner(datei); scan.hasNext(); datenbank(meineBank, id, entryname, dataclass, molecule, sequenzlenght, insgesamt)) {
                scan.next();
                id = scan.next();
                sequenzlenght = scan.nextInt();
                scan.next();
                molecule = scan.next();
                dataclass = scan.next();
                entryname = scan.next();
                insgesamt = "";

                while (true) {
                    while (true) {
                        if (!scan.hasNext()) {
                            continue label39;
                        }

                        if (scan.hasNext("ORIGIN")) {
                            scan.nextLine();
                            scan.nextLine();

                            while (scan.hasNext()) {
                                insgesamt += scan.next();
                                insgesamt = insgesamt.replaceAll("[0-9]", "");
                                insgesamt = insgesamt.replaceAll("/", "");
                            }
                        } else {
                            scan.nextLine();
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

    }

}