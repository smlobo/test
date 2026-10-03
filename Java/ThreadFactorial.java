
class ThreadFactorial extends Thread {
    long limit;
    long answer;
    boolean complete;
    boolean joined;

    ThreadFactorial(long l) {
        limit = l;
    }
    
    public void run() {
        System.out.println("Thread: " + getName() + " started - calculating "
                           + "for: " + limit);
        answer = 1;
        for (long i = 1; i <= limit; i++)
            answer *= i;

        System.out.println("Thread: " + getName() + " completed - calculated "
                           + "answer: " + answer);

        complete = true;
    }

    public static void main(String[] args) {
        ThreadFactorial[] ftarray = new ThreadFactorial[args.length];
        
        for (int i = 0; i < args.length; i++) {
            System.out.println("[" + i + "] : " + args[i]);
            ftarray[i] = new ThreadFactorial(Long.parseLong(args[i]));
            ftarray[i].start();
        }

        boolean done = false;
        while (!done) {
            for (int i = 0; i < ftarray.length; i++) {
                if (ftarray[i].complete && !ftarray[i].joined) {
                    try {
                        ftarray[i].join();
                    }
                    catch (Exception e) {
                        System.out.println("ftarray[" + i + "].join() failed");
                    }
                    
                    System.out.println("Factorial of " + args[i] + " = " +
                                       ftarray[i].answer);
                    ftarray[i].joined = true;
                }
            }

            int j = 0;
            for (; j < ftarray.length; j++) {
                if (!ftarray[j].joined)
                    break;
            }
            if (j == ftarray.length)
                done = true;
        }
    }
}
