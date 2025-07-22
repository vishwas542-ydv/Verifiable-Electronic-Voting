
import javax.crypto.SecretKey;
import java.io.File;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    public static zero_knowledge_proof zkp = new zero_knowledge_proof();

    //Constructor for AES key initialization at the beginning.
    public static void init_Key() throws Exception {
        String Filename = "V://Project_GIT//project//Keys//AESkey.txt", status= "nil";
        SecretKey AESkey;
        int keySize = 128;
        Vote_Encryption ve = new Vote_Encryption();
        DataPoint dp = new DataPoint();
        File keyFile = new File(Filename);
        if (keyFile.exists() && keyFile.length() > 0) {
            status = "OK";
        }else {
            ve.generateVoterKey(keySize);
        }
    }
    public static void Voter() throws Exception {
        Voting vp = new Voting();
        System.out.println("!!Vote For Your Leader!!");
        //change this section after candidate registration part completed....
        System.out.println("WE Have Nominated Parties to Vote list as below \n 1. candidate1 \n 2.candidate2 \n 3.candidate3 \n 4.candidate4: ");
        int ch = sc.nextInt();
        System.out.println("1 " +ch);
        vp.Selection(ch);
    }

    public static void Admin () throws Exception {
        System.out.println("!! WELCOME ADMIN LOGIN PORTAL !! \n\n--  Input Admin Pin(eg., XXXXXXXX) --");
        String ln = sc.next();

        if (ln.equals("Your Passkey")) {
            System.out.println("!!Welcome Admin Area!! \n ");
            Admin ad = new Admin();
            ad.CandidateFileDecryption();
        }else {
            System.out.println("!!!NOT AUTHORIZED!!!");
        }
    }

    public static void main(String[] arg) throws Exception {

        String option;
        init_Key();
        Voter_Registration Voter_Reg = new Voter_Registration();

        do {
            System.out.println("------login as------");
        System.out.println("   1. Admin");
        System.out.println("   2. Voter");
        System.out.println("------Other Services------");
        System.out.println("   3. Complain Box / Voter Verification ");
        int Choice = sc.nextInt();

        if (Choice == 1) {
            Admin();
        } else if (Choice == 2) {
            Voter_Reg.Voter_Reg();
        } else if (Choice == 3) {
            zkp.initiate();
        } else {
            System.out.println("fail");
        }
        System.out.println("------Do You Want to Stay with us------");
        option = sc.next();

        }while(option.equalsIgnoreCase("y") );





    }
}
