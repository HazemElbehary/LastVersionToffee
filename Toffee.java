import java.util.Scanner;
import java.util.ArrayList;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Objects;
import java.util.Random;

public class Toffee {
    public static void main(String[] args) {
//Start add items in arrayList of items   
ArrayList<Object> items = new ArrayList<>();
    Item item1 = new Item("item1","this is item1","fruit","solid",10,0);
    items.add(item1);
    Item item2 = new Item("item2","this is item2","fruit","solid",20,0);
    items.add(item2);
    Item item3 = new Item("item3","this is item3","fruit","solid",30,0);
    items.add(item3);
    Item item4 = new Item("item4","this is item4","fruit","solid",40,0);
    items.add(item4);
    Catalog catalog = new Catalog(items);
    ArrayList<Object> items2 = new ArrayList<>();
    Item item12 = new Item("item12","this is item1","fruit","solid",10,0);
    items2.add(item12);
    Item item22 = new Item("item22","this is item2","fruit","solid",20,0);
    items2.add(item22);
    Item item32 = new Item("item32","this is item3","fruit","solid",30,0);
    items2.add(item32);
    Item item42 = new Item("item42","this is item4","fruit","solid",40,0);
    items2.add(item42);
    Catalog catalog2 = new Catalog(items2);
        Orders orders = new Orders();
        Scanner sc = new Scanner(System.in);
        Customer obj = new Customer();
        String name,email,address,password;
        int phoneNumber;
        int proc=0;
        while(proc != -1){
            System.out.println("Hello Ya User Ya Happepy :)");
            System.out.println("if You Want Regester Write 1\nif You Want Log In Write 2\nif you want to display all items write 3\nif You Want Exit Write -1");
            proc = sc.nextInt();
            if(proc==1) {
               obj.regester();
            }
            else if (proc == 2) {
               Scanner scan = new Scanner(System.in);
               obj.login();
               //Start choose product
               System.out.println("If you want choose products write 1\nIf you want search for product write 2");
               int proc2 = scan.nextInt();
               if(proc2==1){
                    catalog2.display_catalog();
                    Customer cust1 = new Customer();
                    System.out.println(obj.name+" Please choose the products you want and when finish write -1");
                    int num=0;
                    while(num!=-1){
                        num=scan.nextInt();
                        if(num==-1)
                          break;
                        System.out.println("you choose product number : " + (num-1));
                        Item it = (Item) items.get(num-1);
                        cust1.addPorduct(it);
                    }
                    ArrayList<ArrayList<Object>> prod = new ArrayList<>();
                    prod = cust1.getCart();
                    int pri = 0;
                    for(int i=0;i<prod.size();i++){
                        pri+=(int) prod.get(i).get(1);
                    }
                    System.out.println("Your Total Price = " + pri);
                    ArrayList<Object> order = new ArrayList<>();
                    order.add(obj.name);
                    order.add(pri);
                    orders.AddOrder(order);
                    System.out.println("Your order in the way :)");
               }
               else{
                    Scanner scan2 = new Scanner(System.in);
                    System.out.println("Please enter the name of the product you want search about it");
                    String nameOfSearch = scan2.next(); 
                    Boolean b =false;
                    for(int i=0;i<items.size();i++){
                        Object obj2 = items.get(i);
                        Item item = (Item) obj2;
                        if(item.name.equals(nameOfSearch)){
                            b=true;
                            System.out.println("item name : "+item.name + " item description : " + item.description + " item brand : " + item.brand + " item price : " + item.price + " item discount Percentage : " + item.discountPercentage);
                            break;
                        }
                    }
                    if(!b) {
                    System.out.println("Sorry I don't fouund this item ");
                    }
               }
               //end choose product
            //    GmailSender gs = new GmailSender("hazemelbehary915@gmail.com");

            }
            else if (proc == 3) {
               catalog2.display_catalog();
            }
            // else if(proc == 4) {
            //     // Scanner scan2 = new Scanner(System.in);
            //     // System.out.println("Please enter the name of the product you want search about it");
            //     // String nameOfSearch = scan2.next(); 
            //     // Boolean b =false;
            //     // for(int i=0;i<items.size();i++){
            //     //     Object obj2 = items.get(i);
            //     //     Item item = (Item) obj2;
            //     //     if(item.name.equals(nameOfSearch)){
            //     //         b=true;
            //     //         System.out.println("item name : "+item.name + " item description : " + item.description + " item brand : " + item.brand + " item price : " + item.price + " item discount Percentage : " + item.discountPercentage);
            //     //         break;
            //     //     }
            //     // }
            //     // if(!b) {
            //     //     System.out.println("Sorry I don't fouund this item ");
            //     // }
            // }
            else if(proc == -1) {
                break;
            }
        }
    }


    // Start Of My Code
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
}
