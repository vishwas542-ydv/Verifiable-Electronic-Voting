import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Voter_Registration {
    public void Voter_Reg() throws Exception {
        System.out.println("!!Welcome to Voter Verification Phase!!");

        try {

            BufferedReader in = new BufferedReader(new FileReader("V://Project_GIT//project//Store//Voter_Info//Voter_verify.txt"));
            //
            String line;

            Scanner sc = new Scanner(System.in);
            System.out.println("Input Your National Identifier (eg. ddmmyyyy+(3 digit code provided by government)): ");
            String ln = sc.next();

            boolean found = false;

            while ((line = in.readLine()) != null) {
                if (line.startsWith("NI" + ln)) {
                    String[] arr = line.split("-");
                    if (arr.length == 2) {
                        String pointStr = arr[1];

                        System.out.println("Found Voter :" + pointStr);

                        found = true;
                        break;
                    } else {
                        System.out.println("Invalid NI");
                    }
                }
            }
            if (found) {
                System.out.println("NI Found.");
                Main Auth = new Main();
                Auth.Voter();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
