package Model;

public class Task
{
    private int id;
    private int arrivalTime;
    private int serviceTime;

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task(int id, int arrivalTime, int serviceTime)
    {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    // conditiile pentru sortarea din arrayList-ul in care sunt task-urile
    public int compareTo(Task task)
    {
        if(this.arrivalTime == task.getArrivalTime()) return 0;
        else
        if (this.arrivalTime > task.getArrivalTime()) return 1;
        else return -1;
    }

    @Override
    public String toString() {
        return " (" +
                " " + id +
                ", " + arrivalTime +
                ", " + serviceTime +
                ") ";
    }
}
