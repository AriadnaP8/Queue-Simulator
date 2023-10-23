package Model;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable
{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private Thread thread;

    public Server()
    {
        this.tasks = new ArrayBlockingQueue<Task>(1000);
        this.waitingPeriod = new AtomicInteger(0);
        this.thread = new Thread(this);
        thread.start();
    }

    public void addTask(Task newTask)
    {
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());   // incrementam timpul total de asteptare din coada cand se adauga un nou task(client)
    }

    public void run()
    {
        while(true)
        {
            if (tasks.size() > 0)   // daca avem cel putin un element
            {
                Task t = this.tasks.peek();    // se ia varful cozii
                try {
                    // oprim thread-ul timp de cate secunde are fiecare task in parte
                    // inmultim cu 1000 pentru ca este in milisecunde
                    thread.sleep(t.getServiceTime()*1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                tasks.remove(); // din coada, vom scoate primul task dupa ce este procesat
            }
        }
    }

    public ArrayList<Task> getTasks()
    {
        // adaug toate task-urile intr-o lista
        ArrayList<Task> vect = new ArrayList<Task>();
        for(Task x:tasks) {
            vect.add(x);
        }
        return vect;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(int waitingPeriod) {
        this.waitingPeriod.set(waitingPeriod);
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
