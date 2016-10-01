package main.java.sbt.lessons.multhithreading;

class AffableThread extends Thread {
    private boolean isDone = false;

    public void stopThis() {
        isDone = !isDone;
    }

    @Override
    public void run()    //Этот метод будет выполнен в побочном потоке
    {
        double count = 0;
        int j = 0;
        while (!isDone) {
            for (int i = 0; i < 100000; ++i) {
                count *= Math.PI;
                count += Math.atan(count);
            }
            System.out.print("\ri = " + j++);
        }
        System.out.println("\nПривет из побочного потока!");
    }
}


class mysupertth implements Runnable {
    private boolean isDone = false;

    public void stopThis() {
        isDone = !isDone;
    }

    @Override
    public void run() {
        double count = 0;
        int j = 0;
        while (!isDone) {
            for (int i = 0; i < 100000; ++i) {
                count *= Math.PI;
                count += Math.atan(count);
            }
            System.out.print("\ri = " + j++);
        }
        System.out.println("\nПривет из побочного потока!");
    }
}


public class Main {

    public static final mysupertth target = new mysupertth();
    //static AffableThread mSecondThread;

    public static void main(String[] args) throws InterruptedException {
        //mSecondThread = new AffableThread();	//Создание потока

        Thread mSecondThread = new Thread(target);

        mSecondThread.start();                    //Запуск потока
        Thread.sleep(100000);
        //mSecondThread.stopThis();
        target.stopThis();
        System.out.println("Главный поток завершён...");
    }
}