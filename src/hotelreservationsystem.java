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
                          reservationroom(connection, sc);
                          break;
                      case 2 :
                          viewreservation(connection);
                          break;
                      case 3 :
                          getroom(connection, sc);
                          break;
                      case 4 :
                          updatereservation(connection, sc);
                          break;
                      case 5 :
                          deletereservation(connection, sc);
                          break;
                      case 0 :
                          exitreservation();
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
    private static void reservationroom(Connection connection, Scanner sc){
        try{
            System.out.print("Enter guest name : ");
            String gust_name = sc.next();
            sc.nextLine();
            System.out.print("Enter room number : ");
            int room_number = sc.nextInt();
            System.out.print("Enter contect number : ");
            String contect_number = sc.next();

            String sql = "insert into Reservations(guest_name, room_number, contuct_number) values('"+gust_name+"',"+room_number+",'"+contect_number+"')";
            Statement statement = connection.createStatement();
            int effectedrow = statement.executeUpdate(sql);
            if (effectedrow>0){
                System.out.println("Reservation successful");
            }else {
                System.out.println("Reservation faild");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private  static  void viewreservation(Connection connection){
        String sql = "select * from Reservations";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int reservationid = resultSet.getInt("reservation_id");
                String gustname = resultSet.getString("guest_name");
                int roomnumber = resultSet.getInt("room_number");
                String contectnumber = resultSet.getString("contuct_number");
                String reservationtime = resultSet.getString("reservation_time");
                System.out.println("Reservation Id : "+reservationid+", Guest Name : "+gustname+", Room No : "+roomnumber+", Contuct No : "+contectnumber+", Reservation Time : "+reservationtime);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private  static  void getroom(Connection connection, Scanner sc){
        try{
            System.out.print("Enter reservation id : ");
            int resid = sc.nextInt();
            System.out.print("Enter Guest Name : ");
            String guestName = sc.next();
            String sql = "SELECT room_number FROM Reservations WHERE reservation_id = " + resid + " AND guest_name = '" + guestName + "'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                int roomnumber = resultSet.getInt("room_number");
                System.out.println("Room number for reservation id "+resid+ " and guest name "+ guestName+" is = "+roomnumber);
            }else{
                System.out.printf("Reservation not found for given id and guest name ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private  static  void updatereservation(Connection connection, Scanner sc){
        try{
            System.out.print("Enter Reservation id to update : ");
            int reservationid = sc.nextInt();
//            if (!reservationexist(connection, reservationid)){
//                System.out.println("Reservaiton not found for the given id ");
//                return;
//            }
            System.out.print("Enter guest name : ");
            String guestname = sc.next();
            sc.nextLine();
            System.out.print("Enter room number : ");
            int roomnumber= sc.nextInt();
            System.out.print("Enter contect number : ");
            String contectnumber = sc.next();

            String sql = "update Reservations set guest_name = '"+guestname+"', room_number = "+roomnumber+", contuct_number = '"+contectnumber+"' where reservation_id = "+reservationid;
            Statement statement = connection.createStatement();
            int effectedrow = statement.executeUpdate(sql);
            if (effectedrow>0){
                System.out.println("Update is successful");
            }else{
                System.out.println("Update faild!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private  static  void  deletereservation(Connection connection, Scanner sc){
        try{
            System.out.println("Enter Reservation id to delete: ");
            int reservationid = sc.nextInt();
//            if (!reservationexist(connection, reservationid)){
//                System.out.println("Reservaiton not found for the given id ");
//                return;
//            }
            String sql = "delete from Reservations where reservation_id = "+reservationid;
            Statement statement = connection.createStatement();
            int effectedrow = statement.executeUpdate(sql);
            if (effectedrow>0){
                System.out.println("Delete is successful");
            }else {
                System.out.println("Delection is faild!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private  static  void exitreservation() throws InterruptedException {
        System.out.print("Exit System");
        int i = 5;
        while (i!=0){
            System.out.print(".");
            Thread.sleep(500);
            i--;
        }
        System.out.println();
        System.out.println("ThankYou for using Hotel Reservaiton System");
    }

}
