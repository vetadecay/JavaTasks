import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private final int id;
    private int balance;
    private final Lock lock = new ReentrantLock();

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public Lock getLock() {
        return lock;
    }
}

class Bank {
    public void transfer(Account from, Account to, int amount) {
        Account firstLock = from.getId() < to.getId() ? from : to;
        Account secondLock = from.getId() < to.getId() ? to : from;

        firstLock.getLock().lock();
        try {
            secondLock.getLock().lock();
            try {
                from.withdraw(amount);
                to.deposit(amount);
            } finally {
                secondLock.getLock().unlock();
            }
        } finally {
            firstLock.getLock().unlock();
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int ACCOUNT_COUNT = 100;
        final int INITIAL_BALANCE = 1000;
        final int THREAD_COUNT = 1000;
        final int TRANSFER_COUNT = 10000;

        List<Account> accounts = new ArrayList<>();
        Bank bank = new Bank();
        Random random = new Random();

        // Створення рахунків із випадковими балансами
        for (int i = 0; i < ACCOUNT_COUNT; i++) {
            accounts.add(new Account(i, INITIAL_BALANCE));
        }

        // Підрахунок початкової суми грошей у банку
        int initialTotalBalance = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("Initial total balance: " + initialTotalBalance);

        // Створення потоків для переказів
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < TRANSFER_COUNT; i++) {
            executor.submit(() -> {
                Account from = accounts.get(random.nextInt(ACCOUNT_COUNT));
                Account to = accounts.get(random.nextInt(ACCOUNT_COUNT));
                int amount = random.nextInt(100);

                if (from != to) {
                    try {
                        bank.transfer(from, to, amount);
                    } catch (IllegalArgumentException e) {
                        // Недостатньо коштів, пропускаємо переказ
                    }
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.sleep(100);
        }

        // Підрахунок кінцевої суми грошей у банку
        int finalTotalBalance = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("Final total balance: " + finalTotalBalance);

        // Перевірка відповідності початкового та кінцевого балансу
        if (initialTotalBalance == finalTotalBalance) {
            System.out.println("Test passed: Total balance remains consistent.");
        } else {
            System.out.println("Test failed: Total balance mismatch!");
        }
    }
}
