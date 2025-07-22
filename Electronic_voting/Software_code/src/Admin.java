import java.util.Scanner;

public class Admin {
    Scanner sc = new Scanner(System.in);
    public void CandidateFileDecryption() throws Exception {
        DataPoint dp = new DataPoint();
        Vote_Encryption ve = new Vote_Encryption();

        System.out.println("Choose Candidate to Generate File of Decrypted votes: \n1. Candidate1 \n2. Candidate2 \n3. Candidate3 \n4. Candidate4");
        int ch = sc.nextInt();
        if (ch == 1){
            String efile="V://Project_GIT//project//Store//EncryptedFiles//Candidate1.txt";
            String dfile="V://Project_GIT//project//Store//DecryptedFiles//Candidate1.txt";
            String Decryption = ve.Decrypt(efile);
            dp.writeDecryptedFile(Decryption, dfile);

        } else if (ch == 2){
            String efile="V://Project_GIT//project//Store//EncryptedFiles//Candidate2.txt";
            String dfile="V://Project_GIT//project//Store//DecryptedFiles//Candidate2.txt";
            String Decryption = ve.Decrypt(efile);
            dp.writeDecryptedFile(Decryption, dfile);


        } else if (ch == 3) {
            String efile="V://Project_GIT//project//Store//EncryptedFiles//Candidate3.txt";
            String dfile="V://Project_GIT//project//Store//DecryptedFiles//Candidate3.txt";
            String Decryption = ve.Decrypt(efile);
            dp.writeDecryptedFile(Decryption, dfile);


        } else if (ch == 4) {
            String efile="V://Project_GIT//project//Store//EncryptedFiles//Candidate4.txt";
            String dfile="V://Project_GIT//project//Store//DecryptedFiles//Candidate4.txt";
            String Decryption = ve.Decrypt(efile);
            dp.writeDecryptedFile(Decryption, dfile);

        }else{
            System.out.println("fail");
        }
    }
}
