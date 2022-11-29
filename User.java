import java.util.Scanner;
import java.lang.Thread;

public class User {
    int menu = 0;
    Scanner myObj = new Scanner(System.in);
    Database db = new Database();
    boolean loginStatus = false;
    int globalID = 0;

    public User() {
        while (menu != 3) {
            if (!loginStatus) {
                if (menu == 0) {
                    System.out.print('\u000C');
                    System.out.println(
                            "====================================Welcome To Book Shop====================================\nPlease choose menu");
                    System.out.println("1) Register\n2) Login\n3) Exit");
                    System.out.print("Choose Menu: ");
                    int Menu = myObj.nextInt();
                    try {
                        Thread.sleep(1000);
                        System.out.print('\u000C');
                        menu = Menu;
                    } catch (Exception e) {
                        e.getMessage();
                    }
                } else if (menu == 1) {
                    boolean loopregis = true;

                    System.out.println(
                            "============================================================================================");
                    System.out.println(
                            "                                   Welcome to Register Page                                   ");
                    System.out.println(
                            "============================================================================================");

                    System.out.print("Enter Username : ");
                    String UsernameInput = myObj.next();

                    if (db.checkUserExist(UsernameInput)) {
                        try {
                            System.out.println("Username already exists...");
                            Thread.sleep(1000);
                            System.out.print('\u000C');
                            menu = 1;
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    } else {
                        System.out.print("Enter Password : ");
                        String PasswordInput = myObj.next();

                        while (loopregis) {
                            System.out.print("Enter Confirm Password : ");
                            String ConfirmPassInput = myObj.next();

                            if (PasswordInput.equals(ConfirmPassInput)) {
                                db.register(UsernameInput, PasswordInput);
                                System.out.print("Thank you for registration");
                                System.out.println(
                                        "============================================================================================");

                                menu = 0;
                                loopregis = false;
                            } else {
                                System.out
                                        .println("Password and confirm password does not match. Please try again.");
                            }
                        }
                    }

                } else if (menu == 2) {
                    boolean looplogin = true;
                    System.out.println(
                            "============================================================================================");
                    System.out.println(
                            "                                   Welcome to Login Page                                   ");
                    System.out.println(
                            "============================================================================================");
                    while (looplogin) {
                        System.out.println("Enter Username and Password");

                        System.out.print("Username : ");
                        String UsernameInput = myObj.next();

                        System.out.print("Password : ");
                        String PasswordInput = myObj.next();

                        if (db.Login(UsernameInput, PasswordInput)) {
                            System.out.println("Login Success");
                            System.out.println(
                                    "============================================================================================");

                            menu = 0;
                            looplogin = false;
                            loginStatus = true;
                            globalID = db.getId(UsernameInput);
                            try {
                                Thread.sleep(1000);
                                System.out.print('\u000C');
                                menu = 0;
                                looplogin = false;
                                loginStatus = true;
                                break;
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        } else {
                            try {
                                System.out.println("Username or Password incorrect");
                                Thread.sleep(1000);
                                System.out.print('\u000C');
                            } catch (Exception e) {
                                e.getMessage();
                            }
                        }
                    }
                } else {
                    System.out.print('\u000C');
                    break;
                }

            } else {
                while (menu != 3) {
                    if (menu == 0) {
                        System.out.print('\u000C');
                        System.out.println(
                                "====================================Welcome To Book Shop====================================\nPlease choose menu");
                        System.out.println("1) BookShop\n2) Cart\n3) Exit");
                        System.out.print("Choose Menu: ");
                        int Menu = myObj.nextInt();
                        try {
                            Thread.sleep(1000);
                            System.out.print('\u000C');
                            menu = Menu;
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    } else if (menu == 1) {
                        db.getAllBook();
                        System.out.println("1)Choose Book\t2) Back");
                        System.out.println("Choose Choice : ");
                        int ChooseChoice = myObj.nextInt();

                        if (ChooseChoice == 1) {
                            System.out.print('\u000C');
                            db.getAllBook();
                            System.out.print("ChooseBook :");
                            int chooseBook = myObj.nextInt();
                            int loopGetBook = 0;

                            if (db.getBook(chooseBook)) {
                                while (loopGetBook != 3) {
                                    System.out.print('\u000C');
                                    db.getBook(chooseBook);
                                    System.out.println("1) Buy\t2) Add to Cart\t3) Back");
                                    System.out.print("Choose Choice :");
                                    int chooseChoice = myObj.nextInt();
                                    if (chooseChoice == 1) {
                                        System.out.print('\u000C');
                                        System.out.println("Buy Success !!!");
                                        try {
                                            Thread.sleep(1000);
                                            System.out.print('\u000C');
                                            menu = 0;
                                            break;
                                        } catch (Exception e) {
                                            e.getMessage();
                                        }

                                    } else if (chooseChoice == 2) {
                                        if (db.checkItemInCart(chooseBook,globalID)) {
                                            try {
                                                System.out.print('\u000C');
                                                System.out.println("This book is already in your cart !!!");
                                                Thread.sleep(1000);
                                                System.out.print('\u000C');
                                                break;
                                            } catch (Exception e) {
                                                e.getMessage();
                                            }
                                        } else {
                                            System.out.print('\u000C');
                                            System.out.println("Add to Cart Success!!!");
                                            db.addCart(chooseBook, globalID);
                                            try {
                                                Thread.sleep(1000);
                                                System.out.print('\u000C');
                                                menu = 0;
                                                break;
                                            } catch (Exception e) {
                                                e.getMessage();
                                            }
                                        }
                                    } else if (chooseChoice == 3) {
                                        System.out.print('\u000C');
                                        break;
                                    }else{
                                        try{
                                            System.out.print("Wrong Choice...");
                                            Thread.sleep(1000);
                                            System.out.print('\u000C');
                                            break;
                                        }catch( Exception e ){
                                            e.getMessage();
                                        }
                                    }
                                }
                            } else {
                                try {
                                    System.out.println("Book not found");
                                    Thread.sleep(1000);
                                    System.out.print('\u000C');
                                    menu = 0;
                                } catch (Exception e) {
                                    e.getMessage();
                                }
                            }
                        } else {
                            System.out.print('\u000C');
                            menu = 0;
                        }

                    } else if (menu == 2) {
                        System.out.print('\u000C');
                        db.getAllCart(globalID);
                        System.out.println("1) Choose Item\t2) Back ");
                        System.out.print("Choose Choice : ");
                        int ChooseChoice = myObj.nextInt();

                        if (ChooseChoice == 1) {
                            System.out.print('\u000C');
                            db.getAllCart(globalID);
                            System.out.println("Choose Item in Cart : ");
                            int ChooseCart = myObj.nextInt();
                            int loopCart = 0;

                            if (db.getCart(ChooseCart)) {
                                while (loopCart != 3) {
                                    System.out.print('\u000C');
                                    db.getCart(ChooseCart);
                                    System.out.println("1) Buy\t2) Delete from cart\t3) Cancle");
                                    System.out.print("Choose Choice: ");
                                    int ChooseCartChoice = myObj.nextInt();

                                    if (ChooseCartChoice == 1) {
                                        try {
                                            db.DeleteFromCart(ChooseCart);
                                            System.out.print('\u000C');
                                            System.out.println("Buy Success!!! ");
                                            Thread.sleep(1000);
                                            menu = 0;
                                            break;
                                        } catch (Exception e) {
                                            e.getMessage();
                                        }
                                    } else if (ChooseCartChoice == 2) {
                                        try {
                                            db.DeleteFromCart(ChooseCart);
                                            System.out.print('\u000C');
                                            System.out.println("Delete Success!!! ");
                                            Thread.sleep(1000);
                                            menu = 0;
                                            break;
                                        } catch (Exception e) {
                                            e.getMessage();
                                        }
                                    } else if (ChooseCartChoice == 3) {
                                        System.out.print('\u000C');
                                        break;
                                    }
                                }
                            } else {
                                try {
                                    System.out.println("Not Found Item in your Cart...");
                                    Thread.sleep(1000);
                                    System.out.print('\u000C');
                                    menu = 0;
                                } catch (Exception e) {
                                    e.getMessage();
                                }
                            }
                        } else {
                            System.out.print('\u000C');
                            menu = 0;
                        }

                    } else if (menu == 3) {
                        System.out.print('\u000C');
                        break;
                    } else {
                        menu = 0;
                    }
                }

            }
        }

    }

}
