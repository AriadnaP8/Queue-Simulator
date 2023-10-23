package BusinessLogic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler
{
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    private int id;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.strategy = new Strategy();
        servers = new ArrayList<Server>();
        for (int i = 0; i < maxNoServers; i++)
        {
            servers.add(new Server());
        }
    }

    public int dispatchTask(Task t)
    {
        // id-ul cozii cu timpul de procesare cel mai mic
        id = strategy.addTask(servers, t);
        return id;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public int getMaxNoServers() {
        return maxNoServers;
    }

    public void setMaxNoServers(int maxNoServers) {
        this.maxNoServers = maxNoServers;
    }

    public int getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public void setMaxTasksPerServer(int maxTasksPerServer) {
        this.maxTasksPerServer = maxTasksPerServer;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public String listServers(){
        String string = new String();
        for(Server svr: servers){
            string = string + "Server " +(servers.indexOf(svr) + 1) +  ":";
            for (Task x: svr.getTasks()){
                string = string + " " + x.toString() + " ";
            }
            string = string + "---- Waiting period = " + svr.getWaitingPeriod().get() + "\n";
        }
        return string+"\n";
    }
}
