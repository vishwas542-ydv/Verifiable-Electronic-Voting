import uk.ac.ic.doc.jpair.pairing.Point;

import java.math.BigInteger;
import java.security.SecureRandom;

public class zero_knowledge_proof {

    private static final SecureRandom random = new SecureRandom();
    private static final BigInteger p = new BigInteger("23"); // A prime number
    private static final BigInteger g = new BigInteger("5"); // A primitive root modulo p
    private DataPoint x;
    // Prover's secret
//    private static BigInteger x;
    private static BigInteger y;

    // Prover generates a commitment
    private static BigInteger generateCommitment(BigInteger r) {

        BigInteger commitment = g.modPow(r, p);
        System.out.println("Prover's commitment: " + commitment);
        return commitment;
    }

    // Verifier sends a challenge

    private static BigInteger generateChallenge() {
        BigInteger challenge = new BigInteger(p.bitLength() - 1, random).mod(p);
        System.out.println("Verifier's challenge: " + challenge);
        return challenge;
    }

    // Prover generates a response
    private static BigInteger generateResponse(BigInteger r, BigInteger challenge, BigInteger Keys) {
        BigInteger S = r.add(challenge.multiply(Keys)).mod(p.subtract(BigInteger.ONE));
        return S;
    }

    // Verifier checks the proof
    private static boolean verifyProof(BigInteger commitment, BigInteger challenge, BigInteger S) {
        BigInteger leftHandSide = g.modPow(S, p);
        BigInteger rightHandSide = commitment.multiply(y.modPow(challenge, p)).mod(p);
        System.out.println("Verifier's check: LHS = " + leftHandSide + ", RHS = " + rightHandSide);
        return leftHandSide.equals(rightHandSide);
    }

    public void initiate(){
        // Prover sets her secret
        x = new DataPoint();

        Point key = x.ReadKeys();
        String cleankey = key.toString();
        cleankey= cleankey.substring(1, cleankey.length() - 1);;
        BigInteger Keys = new BigInteger(cleankey.replaceAll(",", ""));
        y = g.modPow(Keys, p);
        BigInteger r = new BigInteger(p.bitLength() - 1, random).mod(p.subtract(BigInteger.ONE));
        // Prover generates a commitment
        BigInteger commitment = generateCommitment(r);

        // Verifier generates a challenge
        BigInteger challenge = generateChallenge();

        // Prover generates a response

        BigInteger response = generateResponse(r, challenge, Keys);

        // Verifier checks the proof
        boolean isValid = verifyProof(commitment, challenge, response);

        System.out.println("Proof is " + (isValid ? "valid" : "invalid"));
    }



}
