import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;
import java.util.Random;

public class ForkJoinSum {

    public static void main(String[] args) {
        // Розмір масиву
        final int SIZE = 1000000;
        // Генерація масиву випадкових чисел
        int[] array = new Random().ints(SIZE, 0, 101).toArray();

        // Використання ForkJoinPool для обчислення суми
        ForkJoinPool pool = new ForkJoinPool();
        ArraySumTask task = new ArraySumTask(array, 0, array.length);

        long startTime = System.currentTimeMillis();
        long sum = pool.invoke(task);
        long endTime = System.currentTimeMillis();

        System.out.println("Сума всіх елементів масиву: " + sum);
        System.out.println("Час виконання: " + (endTime - startTime) + " мс");
    }
}

class ArraySumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 20; // Поріг для поділу
    private int[] array;
    private int start;
    private int end;

    public ArraySumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;

        // Якщо розмір підмасиву менше порогу, обчислити суму безпосередньо
        if (length <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Розділити задачу на дві частини
            int mid = start + length / 2;
            ArraySumTask leftTask = new ArraySumTask(array, start, mid);
            ArraySumTask rightTask = new ArraySumTask(array, mid, end);

            // Запустити підзадачі
            leftTask.fork();
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();

            // Об'єднати результати
            return leftResult + rightResult;
        }
    }
}
