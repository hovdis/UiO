import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Planner {
    private static boolean foundCycle = false;
    private int timeCounter = 0;
    private Task[] tasks;
    private ArrayList<Task> zeroDep = new ArrayList<>();
    private ArrayList<Task> doing = new ArrayList<>();
    private ArrayList<Task> done = new ArrayList<>();
    private ArrayList<Task> firstStart = new ArrayList<>(); //This is for the latest start calculation later
    private int count = 0;

    void beginTasks(){
        boolean someThingChanged = false;
        ArrayList<Task> started = new ArrayList<>();
        ArrayList<Task> doneTasks = new ArrayList<>();
        int currStaff = 0;

        while(existsUndoneTask()){
            for(int i = doing.size()-1; i >= 0 ;i--){
                if((doing.get(i).earliestStart+doing.get(i).time) == timeCounter){
                    doneTasks.add(doing.get(i));
                    done.add(doing.get(i));
                    currStaff-= doing.get(i).staff;
                    doing.get(i).removePredOnOutEdges(); // -- on all cntPred for the Tasks in outedge
                    doing.remove(i);
                    someThingChanged = true;
                }
            }

            zeroDep = getAllTasksWithZero(timeCounter);
            for(Task start : zeroDep){
                if(start == null){
                    break;
                }
                doing.add(start);
                started.add(start);
                currStaff += start.staff;
                removeTask(start.id);
                someThingChanged = true;
            }
            zeroDep.clear();
            if(someThingChanged){
                System.out.println("---------------------------------");
                System.out.println("Time:          " + timeCounter);
                if(!doneTasks.isEmpty()){
                    for(Task o : doneTasks){
                        System.out.println("Finished:      " + o.id);
                    }
                }
                if(!started.isEmpty()){
                    for(Task i : started){
                        System.out.println("Started:       " + i.id);
                    }
                }
                System.out.println("Manpower:      " + currStaff);
                started.clear();
                doneTasks.clear();
                someThingChanged = false;
                System.out.println();
            }
            timeCounter++;
        }
        timeCounter--;
        System.out.println("Done!");
    }

    private ArrayList<Task> getAllTasksWithZero(int starttime){
        ArrayList<Task> ret = new ArrayList<>();
        for(Task x : tasks){
            if(x != null && x.cntPredecessors == 0){
                ret.add(x);
                x.earliestStart = starttime;
            }
        }
        return ret;
    }

    void readfile(String filename)throws Exception{
        Scanner sc;
        sc = new Scanner(new File(filename));
        int numTasks = Integer.parseInt(sc.next());
        tasks = new Task[numTasks];

        while(sc.hasNext()){
            int id = Integer.parseInt(sc.next());
            String name = sc.next();
            int time = Integer.parseInt(sc.next());
            int staff = Integer.parseInt(sc.next());

            Task ny = getTask(id);
            ny.initialize(time, staff, name);
            int depId = Integer.parseInt(sc.next());
            while(depId != 0){
                ny.cntPredecessors++;
                Task t = getTask(depId);
                t.outEdges.add(ny);
                depId = Integer.parseInt(sc.next());
            }
            tasks[count++] = ny;
        }
        firstStart = getAllTasksWithZero(0);
    }

    void setLatestStartAndSlack(){ //hvis slack == 0 er den critical
        int lowestNumber = -1;
        int tmp;
        for(Task i : firstStart){
            tmp = i.getLatest(timeCounter);
            if(lowestNumber == -1){
                lowestNumber = tmp;
            }
            if(tmp < lowestNumber){
                lowestNumber = tmp;
            }
        }
        System.out.println("Critical tasks:");
        for(Task x : done) {
            if ((x.latestStart - x.earliestStart) == 0) {
                System.out.println(x.id);
            }
        }
    }

    void printAllAfterRun(){
        for(Task x : done){
            System.out.println("-------------------------------");
            System.out.println("ID: " + x.id);
            System.out.println("Name: " + x.name);
            System.out.println("Time: " + x.time);
            System.out.println("Manpower: " + x.staff);
            System.out.println("OutEdges: ");
            for(Task k : x.outEdges){
                System.out.println("ID: " + k.id);
            }
            System.out.println("Earliest start:  " + x.earliestStart);
            System.out.println("Latest   start:  " + x.latestStart);
            System.out.println("Slack :          " + (x.latestStart-x.earliestStart));
            System.out.println();
        }
    }

    private void printCycle(ArrayList<Task> x){
        int lastId = x.get(x.size()-1).id;
        boolean from = false;
        System.out.println("There is a cycle, and here it is:");
        for(Task i : x){
            if(from){
                System.out.println(i.id);
            }else if(i.id == lastId){
                System.out.println(i.id);
                from = true;
            }
        }
    }

    private boolean cycle(ArrayList<Task> seen, Task k){
        if(seen.contains(k)){
            return true;
        }
        ArrayList<Task> send = new ArrayList<>();
        send.addAll(seen);
        send.add(k);
        for(Task x : k.outEdges){
            if(cycle(send, x)){
                if(!foundCycle){
                    foundCycle = true;
                    send.add(x);
                    printCycle(send);
                }
                return true;
            }
        }
        return false;
    }

    boolean findCycle(){
        ArrayList<Task> kk = new ArrayList<>();
        for(Task k: firstStart){
            if(cycle(kk, k)){
                return true;
            }
        }
        return false;
    }

    private Task getTask(int id){
        if(tasks[id-1] == null){
            Task temp = new Task(id);
            tasks[id-1] = temp;
            return temp;
        }
        return tasks[id-1];
    }

    private boolean existsUndoneTask(){
        for(Task t : tasks){
            if(t != null){
                return true;
            }
        }
        for(Task k : doing){
            if(k != null){
                return true;
            }
        }
        return false;
    }

    private void removeTask(int x){
        for(int i = 0; i<tasks.length;i++){
            if(tasks[i] != null){
                if(tasks[i].id == x){
                    tasks[i] = null;
                }
            }
        }
    }



    private class Task {
        int         id, time, staff;
        int         earliestStart , latestStart = -1;
        int         cntPredecessors;
        String      name;
        List<Task>  outEdges = new ArrayList<>();
        boolean     initialized = false;

        Task(int id) {
            this.id = id;
        }

        void removePredOnOutEdges(){
            for(Task x: outEdges){
                x.cntPredecessors--;
            }
        }

        int getLatest(int totalTime){ //går inn i barnene og spør om latestStart, hvis den ikke har barn, setter man det til timeCount-time
            int latest = -1;
            int temp;
            if(latestStart != -1){
                return latestStart;
            }
            if(outEdges.isEmpty()){
                latestStart = (totalTime - time);
                return latestStart;
            }else{
                for(Task tmp : outEdges){
                    if(latest == -1){
                        latest = tmp.getLatest(totalTime);
                    }else{
                        temp = tmp.getLatest(totalTime);
                        if(temp < latest){
                            latest = temp;
                        }
                    }
                }
            }
            latestStart = (latest - time);
            return latestStart;
        }

        void initialize(int time, int staff, String name){
            this.time   = time;
            this.staff  = staff;
            this.name   = name;
            initialized = true;
        }
    }
}