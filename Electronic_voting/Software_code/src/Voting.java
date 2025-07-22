import uk.ac.ic.doc.jpair.api.Pairing;
import uk.ac.ic.doc.jpair.pairing.BigInt;
import uk.ac.ic.doc.jpair.pairing.EllipticCurve;
import uk.ac.ic.doc.jpair.pairing.Point;
import uk.ac.ic.doc.jpair.pairing.Predefined;

import java.security.SecureRandom;
import java.util.Random;

public class Voting{
    Pairing e = Predefined.nssTate();
    Point P = e.RandomPointInG1(new Random());
    EllipticCurve g1 = e.getCurve();
    BigInt Key_voter = new BigInt(160, new SecureRandom());
    Random random = new Random();
    int ref_no;




    public void Selection(int ch) throws Exception {

        Vote_Encryption encryptVoting = new Vote_Encryption();
        DataPoint dp = new DataPoint();

        byte[] AESvoterKey= dp.AESreadKeys();
        byte[] AESvoterIV = dp.AESreadIV();

        if(ch==1){
            String Candidate1Path ="V://Project_GIT//project//Store//EncryptedFiles//Candidate1.txt"; // file Location: for Candidate 1
            Key_voter = Key_voter.multiply(BigInt.valueOf(2));
            Point gxi = g1.multiply(P, Key_voter);
            System.out.println("selected candidate is 1 \nGenerated Point G is: " + gxi);
            ref_no = 10000 + random.nextInt(90000);

            dp.writeVotes(gxi, ref_no);
            String data = "Vote for Candidate 1";

            try {
                String Encryption = encryptVoting.encrypt(AESvoterIV, data, AESvoterKey); //Storing Encrypted Votes
                dp.writeEncryptedVotes(Encryption, Candidate1Path); // Writing Encrypted votes
            } catch (Exception e) {throw new RuntimeException(e);}
        }

        else if(ch==2){
            String Candidate2Path ="V://Project_GIT//project//Store//EncryptedFiles//Candidate2.txt"; // file Location: for Candidate 2
            Key_voter = Key_voter.multiply(BigInt.valueOf(4));
            Point gxi = g1.multiply(P, Key_voter);
            System.out.println("selected candidate is 1 \nGenerated Point G is: " + gxi);
            ref_no = 10000 + random.nextInt(90000);


            System.out.println(gxi);
            dp.writeVotes(gxi, ref_no);
            String data = "Vote for Candidate 2";
            try {
                String Encryption = encryptVoting.encrypt(AESvoterIV, data, AESvoterKey); //Storing Encrypted Votes
                dp.writeEncryptedVotes(Encryption, Candidate2Path); // Writing Encrypted votes
            } catch (Exception e) {throw new RuntimeException(e);}
        }
        else if(ch==3){
            String Candidate3Path ="V://Project_GIT//project//Store//EncryptedFiles//Candidate3.txt"; // file Location: for Candidate 3
            Key_voter = Key_voter.multiply(BigInt.valueOf(8));
            Point gxi = g1.multiply(P, Key_voter);
            System.out.println("selected candidate is 1 \nGenerated Point G is: " + gxi);
            ref_no = 10000 + random.nextInt(90000);

            System.out.println(gxi);
            dp.writeVotes(gxi, ref_no);
            String data = "Vote for Candidate 3";
            try {
                String Encryption =encryptVoting.encrypt(AESvoterIV, data, AESvoterKey); //Storing Encrypted Votes
                dp.writeEncryptedVotes(Encryption, Candidate3Path); // Writing Encrypted votes
            } catch (Exception e) {throw new RuntimeException(e);}
        }
        else if(ch==4){
            String Candidate4Path ="V://Project_GIT//project//Store//EncryptedFiles//Candidate4.txt"; // file Location: for Candidate 4
            Key_voter = Key_voter.multiply(BigInt.valueOf(16));
            Point gxi = g1.multiply(P, Key_voter);
            System.out.println("selected candidate is 1 \nGenerated Point G is: " + gxi);
            ref_no = 10000 + random.nextInt(90000);

            System.out.println(gxi);
            dp.writeVotes(gxi, ref_no);
            String data = "Vote for Candidate 4";
            try {
                String Encryption = encryptVoting.encrypt(AESvoterIV, data, AESvoterKey); //Storing Encrypted Votes
                dp.writeEncryptedVotes(Encryption, Candidate4Path); // Writing Encrypted votes
            } catch (Exception e) {throw new RuntimeException(e);}
        }


    }

}