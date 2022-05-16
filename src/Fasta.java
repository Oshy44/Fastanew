import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Fasta {
    private String dna = "";
    private String header = ">";

    public Fasta() {
        this.dna = "ACTACT";
        this.header = ">g5835";
    }

    public Fasta(String dna, String header) {
        this.header = header;
        this.dna = dna;
        this.istdna(dna);
        this.istheader(header);
    }

    public Fasta(Fasta Fasta) {
        this.header = Fasta.header;
        this.dna = Fasta.dna;
    }

    public void reset() {
        this.dna = "ACTACT";
        this.header = ">g5835";
    }

    public String getHeader() {
        return this.header;
    }

    public String getdna() {
        return this.dna;
    }

    public Integer getdnalaenge() {
        return this.dna.length();
    }

    public String zusammenfassen(String dna, String dna2) {
        String neuedna = dna + dna2;
        return neuedna;
    }

    public void teilsequenz(int x) {
        int m = 0;

        for(int k = 0; k < this.getdnalaenge() - x; k += x) {
            System.out.println(this.dna.substring(k, k + x));
            m = k;
        }

        if (this.getdnalaenge() % x != 0) {
            System.out.println(this.dna.substring(m + x));
        }

    }

    public boolean istdna(String dna) {
        boolean checkdna = true;
        boolean checkchar = false;

        for(int i = 0; i < dna.length(); ++i) {
            if (dna.charAt(i) != 'C' && dna.charAt(i) != 'A' && dna.charAt(i) != 'T' && dna.charAt(i) != 'G') {
                checkchar = false;
            } else {
                checkchar = true;
            }

            if (!checkchar) {
                checkdna = false;
                throw new IllegalSequenceException();
            }
        }

        return checkdna;
    }

    public boolean istheader(String header) {
        boolean checkheader = false;
        if (header.charAt(0) == '>') {
            checkheader = true;
            return checkheader;
        } else {
            throw new IllegalHeaderException();
        }
    }

    public void plotteplot(String dna, String dna2) {
        System.out.print(" ");

        int i;
        for(i = 0; i < this.getdnalaenge(); ++i) {
            PrintStream var10000 = System.out;
            char var10001 = dna.charAt(i);
            var10000.print(" " + var10001 + " ");
        }

        System.out.println();

        for(i = 0; i < dna2.length(); ++i) {
            System.out.print(dna2.charAt(i));

            for(int k = 0; k < dna.length(); ++k) {
                if (dna2.charAt(i) != dna.charAt(k)) {
                    System.out.print("   ");
                } else {
                    System.out.print(" * ");
                }
            }

            System.out.println(" ");
        }

    }

    public void schreiben() {
        File datei = new File("Test.txt");

        try {
            FileWriter writer = new FileWriter(datei);
            writer.write(this.header + "\n");

            for(int i = 0; i < this.getdnalaenge(); ++i) {
                if (i % 80 == 0) {
                    writer.write("\n");
                }

                writer.write(this.dna.charAt(i));
            }

            writer.flush();
            writer.close();
        } catch (IOException var4) {
            System.out.println("ging nicht");
        }

    }

    public void lesen(String x) {
        File datei = new File(x);
        Scanner scan = null;

        try {
            scan = new Scanner(datei);
        } catch (FileNotFoundException var8) {
            System.out.println("File not found");
        }

        try {
            this.header = scan.nextLine();
            this.istheader(this.header);
        } catch (IllegalHeaderException var7) {
            System.out.println(var7);
        }

        System.out.println(this.header);

        String insgesamt;
        for(insgesamt = ""; scan.hasNext(); insgesamt = insgesamt + scan.nextLine()) {
        }

        try {
            this.istdna(insgesamt);
            this.dna = insgesamt;
        } catch (IllegalSequenceException var6) {
            System.out.println(var6);
        }

        System.out.println(insgesamt);
    }

    public static void main(String[] args) {

        Fasta fasta2 = new Fasta("AACAAGCAAGGAAAAAAGCAAGGAAAAAAGCAAGGAAAAAGG", ">Proteine sind toll");
        MyGenBank bank1 = new MyGenBank();
        Herausnehmen wert1=  new Herausnehmen();
        bank1.EMBLTToMyGenBank("emblexample.txt","test1.txt");
        bank1.GenBankToMyGenBank("genbankexample.txt","test2.txt");
        wert1.herausnehmen("test1.txt");
        System.out.println(wert1.molecule);
        wert1.herausnehmen("test2.txt");
        System.out.println(wert1.entryname);
    }
}

