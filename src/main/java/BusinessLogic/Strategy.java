package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public class Strategy {
    public int addTask(List<Server> servers, Task t) {
        int minim = 10000;    // initializez cu un nr mai mare
        // luam primul element din coada la care ii luam timpul de asteptare
        Server coada = servers.get(0); // initializam cu prima coada
        for (Server svr :servers) {
            if (svr.getWaitingPeriod().get() < minim) {
                minim = svr.getWaitingPeriod().get();
                coada = svr;
            }
        }
        coada.addTask(t);
        return servers.indexOf(coada);
    }
}
