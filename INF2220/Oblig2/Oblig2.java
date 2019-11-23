public class Oblig2 {
    public static void main(String[] args) throws Exception{
        if(args.length != 1){
            System.out.println("Wrong number of arguments!!");
            return;
        }
        Planner planner = new Planner();
        planner.readfile(args[0]);
        if(planner.findCycle()){
            System.out.println("There is a cycle, therefore it is not realizable");
            System.out.println("Exiting program.");
        }else{
            System.out.println("There is not a cycle");
            planner.beginTasks();
            planner.setLatestStartAndSlack();
            planner.printAllAfterRun();
        }
    }
}
