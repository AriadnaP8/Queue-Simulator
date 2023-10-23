package BusinessLogic;

import Model.Server;
import Model.Task;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimulationManager implements Runnable{
    private int n = 4; // numarul N de task-uri
    private int q = 2; // numarul Q de cozi
    private int timpSimulare = 60;      // timpul de simulare a proiectului
    private int minArrivalTime = 2;     // timpul minim de sosire
    private int maxArrivalTime = 30;    // timpul maxim de sosire
    private int minServiceTime = 2;     // timpul minim de servire
    private int maxServiceTime = 4;     // timpul maxim de servire
    private int totalServiceTime = 0;   // timpul total de servire
    private int timpCurent = 0;     // timpul curent
    private int maxTaskPerServer = 0;   // nr max task-uri per server
    private int nrMaxTask = 0;  // nr maxim de task-uri
    private int timpMax = 0;    // timpu maxim pt un task
    private int sumaTaskWait = 0;       // suma timpilor de asteptare
    private List<Task> taskuri;     // lista de task-uri
    private Task taskCurent;
    private Scheduler scheduler;     // scheduler
    private JTextArea textArea;     // textArea pentru a-l lega de interfață

    public SimulationManager(int n, int q, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime, int timpSimulare, JTextArea textArea) {
        this.n = n;
        this.q = q;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.timpSimulare = timpSimulare;
        this.textArea = textArea;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void generateTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();

        for(int id = 1; id <= n; id++)
        {
            // generare random pt time
            int arrivalTime = (int)Math.floor(Math.random() *(maxArrivalTime - minArrivalTime + 1) + minArrivalTime);
            int serviceTime = (int)Math.floor(Math.random() *(maxServiceTime - minServiceTime + 1) + minServiceTime);

            Task task = new Task(id, arrivalTime, serviceTime);
            tasks.add(task);
            totalServiceTime += serviceTime;    // timpul total de servire
        }
        taskuri = tasks;
        n = taskuri.size();

        // afisam task-urile generate
        for (Task x : taskuri) {
            System.out.println(x);
            textArea.append(x.toString()+"\n");
        }
    }

    public void run() {
        // creeam un scheduler nou cu un numar dat de servere si nr maxim de task-uri per server
        scheduler = new Scheduler(q, maxTaskPerServer);
        // generam primul task
        generateTasks();
        // parcurgem pana cand timpul de simulare ajunge la limita
        while (timpCurent < timpSimulare) {
            // verificam daca vreun task nou a ajuns la timpul curent
            for (Task tsk : taskuri) {
                if (tsk.getArrivalTime() == timpCurent) {
                    // vom pune/asigna un task la un server folosind scheduler
                    int idServer = scheduler.dispatchTask(tsk);
                    // apoi updatam timpul total de asteptare pentru task
                    sumaTaskWait += scheduler.getServers().get(idServer).getWaitingPeriod().get() - tsk.getServiceTime();
                }
            }
            // calculam numarul total de task-uri din toate serverele
            int sumTasks = 0;
            for (Server svr : scheduler.getServers()) {
                sumTasks += svr.getTasks().size();
            }
            // actualizam numarul orelor maxime/de varf dacă ora curentă are mai multe task-uri decât ora de varf anterioara
            if (sumTasks > nrMaxTask) {
                nrMaxTask = sumTasks;
                timpMax = timpCurent;
            }
            System.out.println(timpCurent);
            textArea.append(String.valueOf(timpCurent) + "\n");
            System.out.println(scheduler.listServers());
            textArea.append(String.valueOf(scheduler.listServers()) + "\n");

            // crestem timpul de simulare curent cu unu
            timpCurent++;
            // apoi vom actualiza timpul de asteptare pentru fiecare server in parte
            for (Server svr : scheduler.getServers()) {
                if (svr.getWaitingPeriod().get() > 0) {
                    svr.setWaitingPeriod(svr.getWaitingPeriod().get() - 1);
                }
            }
            try {
                // adormim thread-ul pentru o secunda pentru a simula in timp real
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // vom genera statistica ceruta intr un string pentru a l afisa pe ecran
        String s = new String("~ Valoarea timpului mediu de asteptare a fost " + ((double) sumaTaskWait / n) + "secunde. ~\n" +
                "~ Ora de varf a fost " + timpMax + " secunde, cand s-au aflat " + nrMaxTask + " task-uri la servere. ~\n" +
                "~ Timpul de servire/asteptare mediu a fost de " + ((double) totalServiceTime / n) + " secunde. ~");

        for (Server svr : scheduler.getServers()) {
           System.out.println(svr.getTasks());
           textArea.append(String.valueOf(svr.getTasks()) + "\n");
        }

        // apoi afisam acest string de statistica si lista de task-uri pentru fiecare server in parte
        textArea.append(s);

        try {
            FileWriter myWriter = new FileWriter("test3.txt");
            myWriter.write(textArea.getText());
            myWriter.close();
            System.out.println("S-a executat cu succes!");
        } catch (IOException exceptie) {
            System.out.println("!!!EROARE!!!");
            exceptie.printStackTrace();
        }
    }
}