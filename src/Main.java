import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String recipientPhoneNumber = ""; // Replace with recipient's phone number
        String otp = generateOTP(6);
        sendOTPSMS(recipientPhoneNumber, otp);
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Your Verification Code:");
        String OTP = in.nextLine();
        ValidateOTP(otp,OTP);
    }
    public static String generateOTP(int length) {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }

        return new String(otp);
    }
    public static void ValidateOTP(String otp,String userIn) {
//        System.out.println(otp);
//        System.out.println(userIn);
        if(Objects.equals(otp, userIn)){
            System.out.printf("Perfect You are now Validated !");
        }
        else {
            System.out.printf("Invalid Code !");
        }
    }
    private static void sendOTPSMS(String recipientPhoneNumber, String otp) {
        String accountSid = "ACb219480261d6f99ad399ed06a457f9f6"; // Replace with your Twilio account SID
        String authToken = "162f976cf040df390951c44c3d220c16"; // Replace with your Twilio auth token
        String twilioPhoneNumber = "+12705182337"; // Replace with your Twilio phone number

        Twilio.init(accountSid, authToken);

        Message message = Message.creator(
                        new PhoneNumber(recipientPhoneNumber),
                        new PhoneNumber(twilioPhoneNumber),
                        "Your OTP: " + otp)
                .create();

        System.out.println("OTP sent successfully to " + recipientPhoneNumber);
    }
}