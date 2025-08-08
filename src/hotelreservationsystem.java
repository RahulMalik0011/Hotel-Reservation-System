import java.sql.*;
import  java.util.Scanner;

public class hotelreservationsystem{
    private static  final  String url = "jdbc:mysql://localhost:3306/Hostel_RM";
    private  static  final  String username = "root";
    private  static  final  String password = "Rahul@2001";
    public static void main(String[] args) {
          try{
              Class.forName("com.mysql.jdbc.Driver");
          } catch (ClassNotFoundException e) {
              throw new RuntimeException(e);
          }
          try {
              Connection connection = DriverManager.getConnection(url, username, password);
              while (true){
                  System.out.println();
                  System.out.println("Hotel Reservation System");
                  Scanner sc = new Scanner(System.in);
                  System.out.println("1 Reservation room");
                  System.out.println("2 View Reservation");
                  System.out.println("3 Get Room Nunmber");
                  System.out.println("4 Update Reservation");
                  System.out.println("5 Delete Reservation");
                  System.out.println("0 Exits");
                  System.out.print("Enter a number : ");
                  int choice = sc.nextInt();
                  switch (choice){
                      case 1 :
//                          reservationroom(connection, sc);
                          break;
                      case 2 :
//                          viewreservation(connection);
                          break;
                      case 3 :
//                          getroom(connection, sc);
                          break;
                      case 4 :
//                          updatereservation(connection, sc);
                          break;
                      case 5 :
//                          deletereservation(connection, sc);
                          break;
                      case 0 :
//                          exitreservation();
                          sc.close();
                          return;
                      default:
                          System.out.println("Invaled choice. Try again");
                  }
              }
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
    }

}
