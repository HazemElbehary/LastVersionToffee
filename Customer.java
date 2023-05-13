import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;
public class Customer {
    String address,email,password, name;
    int phoneNumber;
    ArrayList<ArrayList<Object>> cart = new ArrayList<>();
     ArrayList<ArrayList<Object>> data = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    public Customer(){
    }
    public Customer(String add, String em, String pass, int phNum){
        address = add;
        email=em;
        password=pass;
        phoneNumber = phNum;
    }
    public static void OTP(String PhoneNumber){
        String recipientPhoneNumber = PhoneNumber; // Replace with recipient's phone number
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
    public static void sendOTPSMS(String recipientPhoneNumber, String otp) {
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
    
    void regester(){
        ArrayList<Object> dataOneUser = new ArrayList<>();
        System.out.println("Please Enter Your Name :)");
        name = sc.next();
        dataOneUser.add(name);
        System.out.println("Please Enter The Email:)");
        email = sc.next();
        String RegexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern patternEmail = Pattern.compile(RegexEmail);
        Matcher matcherEmail = patternEmail.matcher(email);
        if (!matcherEmail.matches()) {
            System.out.println("This is not vaild email");
            return;
        }
        dataOneUser.add(email);
        System.out.println("Please Enter The Address:)");
        address = sc.next();
        dataOneUser.add(address);
        System.out.println("Please Enter The Password:)");
        password = sc.next(); 
        String RegexPass = ".{9,}";
        Pattern pattern = Pattern.compile(RegexPass);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            System.out.println("password length is not greater than 8 please try again");
            return;
        }
        dataOneUser.add(password);
        System.out.println("Please Enter The Phone Number:)");
        phoneNumber = sc.nextInt();
        String RegexNum = ".{9,}";
        String num = String.valueOf(phoneNumber);
        Pattern patternNum = Pattern.compile(RegexNum);
        Matcher matcherNum = patternNum.matcher(num);
        if (!matcherNum.matches()) {
            System.out.println("This is not phone number please enter try phone number");
            return;
        }
        dataOneUser.add(phoneNumber);
        OTP(phoneNumber);
        data.add(dataOneUser);
    }
    void login(){
        System.out.println("######################");
        System.out.println(data);
        System.out.println("######################");
        System.out.println("Please Enter Your Email :)");
        String e = sc.next();
        for(int i=0;i<data.size();i++){
            String em = (String) data.get(i).get(1);
            // System.out.println("######################Email");
            // System.out.println(em.getClass().getSimpleName() + "\n" + e.getClass().getSimpleName());
            // System.out.println("######################Email");
            if(em.equals(e)) {
                System.out.println("Please Enter Your Password :)");
                String pass = sc.next();
                String p = (String) data.get(i).get(3);
                if(p.equals(pass)) {
                    System.out.println("Data IS True :)");
                    break;
                }
            }
        }
    }
    
    
    void addPorduct(Item ite){
        ArrayList<Object> product = new ArrayList<>();
        Item it =(Item) ite;
        product.add(ite.name);
        product.add(ite.price);
        cart.add(product);
    }
     ArrayList<ArrayList<Object>> getCart(){
         return cart;
    }
   
    
}
