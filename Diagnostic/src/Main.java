public class Main {
    public static void main(String[] args) {

        Bank_Account jason_account = new Bank_Account(123456789, 1000.0, "Checking", 0.01f);
        Bank_Account jessica_account = new Bank_Account(987654321, 5000.0, "Savings", 0.02f);

        Bank_Account[] accounts = {jason_account, jessica_account};

        for (Bank_Account account : accounts) {
            System.out.println("Account Number: " + account.acct_num);
            System.out.println("Account Type: " + account.getType());
            System.out.println("Account Balance: " + account.getBalance());
            account.deposit(1000.0);
            System.out.println("Deposited $1000.0");
            System.out.println("Account Balance: " + account.getBalance());
            account.withdrawal(500.0);
            System.out.println("Withdrew $500.0");
            System.out.println("Account Balance: " + account.getBalance());
            account.applyInterest();
            System.out.println("Applied Interest");
            System.out.println("Account Balance: " + account.getBalance());
            System.out.println();
        }

        // CARS!

        Car my_car = new Car("Toyota", "Camry", 2015, 20000.0);
        Car your_car = new Car("Ford", "Fiesta", 2010, 10000.0);

        Car[] cars = {my_car, your_car};
        for (Car car : cars) {
            System.out.println("Driving the car...");
            car.gasUp();
            car.drive(100);
            System.out.println("Info: " + car.getInfo());
        }


    }
}