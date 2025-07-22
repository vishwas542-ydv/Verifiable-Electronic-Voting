import uk.ac.ic.doc.jpair.pairing.BigInt;
import uk.ac.ic.doc.jpair.pairing.Point;
import java.io.*;
import java.util.Base64;
import java.util.Scanner;

public class DataPoint {
    Point voterPreference;

    public void writeVotes(Point gxi, int ref_no){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("V://Project_GIT//project//Store//Voter_Info//VOTER.txt", //file location to Storing Reference number and ECDH generated G point
                true))) {
            writer.write("R" + ref_no + "-"+ gxi + "\n");
        } catch(IOException e)
        {e.printStackTrace();}
    }

    public void writeEncryptedVotes(String EncryptVp, String Filename){

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Filename, true)) ){
            writer.write(EncryptVp);
            writer.newLine();
        }catch (IOException e)
        {e.printStackTrace();}
    }

    public void writeDecryptedFile(String msg, String Filename){ // MEthod to Write Decrypted File

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Filename, true)) ){
            writer.write(msg);
            writer.newLine();
        }catch (IOException e)
        {e.printStackTrace();}
    }

    public byte[] readDecodedData(String Encrypted_File) throws IOException {  //Method for Decoding Stored Encrypted File Data
        String encodedData;

        try (BufferedReader reader = new BufferedReader(new FileReader(Encrypted_File))){
            encodedData = reader.readLine();
        }
        return Base64.getDecoder().decode((encodedData)); //Base64 decoding of Encrypted votes from String to byte
    }

    public void writeKeys(String AESkey, String Filename){ //Method to write AES Keys

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Filename)) ){
            writer.write(AESkey);
        }catch(IOException e){e.printStackTrace();}
    }

    public void writeIV(String IV, String Filename){ // Method to write Initialization Vector

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Filename)) ){
            writer.write(IV);
        }catch(IOException e){e.printStackTrace();}
    }

    public byte[] AESreadKeys() throws IOException { //Method for fetching AES Keys
        String encodedKey;
        String KeyFilepath = "V://Project_GIT//project//Keys//AESkey.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(KeyFilepath))){
            encodedKey = reader.readLine();
        }
        return Base64.getDecoder().decode((encodedKey));
    }

    public byte[] AESreadIV()throws Exception{ //Method for Fetching Initialization Vector
        String encodedIV;
        String IVFilepath = "V://Project_GIT//project//Keys//IV.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(IVFilepath))){
            encodedIV = reader.readLine();
        }
        byte[] decodeIV = Base64.getDecoder().decode((encodedIV));
        System.out.println("Decoded IV Length: " + decodeIV.length);

        return decodeIV;
    }


    public Point ReadKeys() {

        try {
            BufferedReader in = new BufferedReader(new FileReader("V://Project_GIT//project//Store//Voter_Info//VOTER.txt")); //Input File location for fetching Reference number and ECDH generated G point
            String line;

            Scanner sc = new Scanner(System.in);
            System.out.println("Input Your Reference Number:");
            int ln= sc.nextInt();

            boolean found = false;

            while ((line = in.readLine()) != null) {
                if (line.startsWith("R" + ln)) {
                    String[] arr = line.split("-");  // for content in our file like: R12345 - G point on Elliptic Curve
                    if (arr.length == 2) {
                        String pointStr = arr[1];

                        pointStr = pointStr.substring(1, pointStr.length() - 1);
                        String[] pointParts = pointStr.split(","); //splitting x and y as string
                        if (pointParts.length == 2) {
                            BigInt x = new BigInt(pointParts[0]);  // assigning first part to x as BigInt
                            BigInt y = new BigInt(pointParts[1]); // assigning second part to y as BigInt

                            voterPreference = new Point(x, y);  //combining both Coordinates and storing as Point G^(candidate_alloc*ECDH(voter_key))
                            System.out.println("Found Voter Preference :" + voterPreference); //prints G point located on Elliptic Curve

                            found = true;
                            break;
                        } else {
                            System.out.println("Invalid Ref");
                        }
                    }
                }
            }
            if (!found){
                System.out.println("Ref not Found.");
            }





            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return voterPreference;
    }

}