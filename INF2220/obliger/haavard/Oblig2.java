import java.util.*;
import java.io.*;
public class Oblig2{
	static int antalloppgaver;
	//static ArrayList<Task> tasks = new ArrayList<Task>();
	static ArrayList<Task> queue = new ArrayList<Task>();
	//static ArrayList<Task> tasksFinal;
	static HashMap<Integer, Task> taskMap = new HashMap<Integer, Task>();
	static HashMap<Integer, Task> taskMapFinal = new HashMap<Integer, Task>();
	static boolean endring;
	static String skrivut;
	static int totalTid;
	static Task sisteTask;

	public static void main(String[] args) throws Exception{
		String filnavn = args[0];
		Scanner filleser = new Scanner(new File(filnavn));
		int manpower = Integer.parseInt(args[1]);
		//ArrayList<Task> tasks = new ArrayList<Task>();
		antalloppgaver = Integer.parseInt(filleser.nextLine());
		//skipper blank linje
		filleser.nextLine();

		//legger til tasks i taskMap
		for(int i=0; i<antalloppgaver; i++){
			String linje = filleser.nextLine();
			//System.out.println(linje);
			Task tmp = new Task(linje);
			//tasks.add(tmp); //kan fjernes
			//System.out.println(tmp.id + " " +tmp);
			taskMap.put(tmp.id, tmp);
		}
		//System.out.println("\n \n \n");
		//legger objekter inn i depend til Taskene
		for(Task t : taskMap.values()){
			for(int x=0; x<t.depend.size(); x++){
				//legger Task inn i dependTasks
				//System.out.println("TASK: "+ t);
				int key = t.depend.get(x);
				//System.out.println("KEEY: " + key);
				Task dep = taskMap.get(key);

				t.dependTasks.put(key, dep);
				//System.out.println("putter " + key+ " " +dep + " i " + t + " sin dependTasks");

			}
			//System.out.println("\n");

		}
		//System.out.println("\n \n \n");
		/*for(int i=0; i<tasks.size(); i++){
			for(int x=0; x<tasks.get(i).depend.size(); x++){
				Task tmpTask = tasks.get(i);
				tmpTask.dependTasks.put(tmpTask.depend.get(x),tasks.get(tmpTask.depend.get(x)-1));
			}
		}*/

		//legger inn tasks i dependOnMe
		for(Task t :taskMap.values()){
			t.addMe();
		}
		for(Task t : taskMap.values()){
			t.sikkerhetskopier();
		}
		/*for(int i=0; i<tasks.size(); i++){
			tasks.get(i).addMe();
		}*/

		//sikkerhetskopierer tasks
		//tasksFinal = new ArrayList<Task>(tasks);
		taskMapFinal = new HashMap<Integer, Task>(taskMap);

		//finn ut om prosjektet er gjennomførbart
		findTask();
		while(!queue.isEmpty()){
			//må fjerne task når den legges i queue
			queue.get(0).fjernDepends(false);
			queue.remove(0);
		}if(!taskMap.isEmpty()){
			System.out.println("prosjektet er ikke gjennomførbart!");
			findCycle();
			System.exit(0);
		}
		/*while(!queue.isEmpty()){
			findTask();
			if(!queue.isEmpty()){
				for(int i=0; i<tasks.size(); i++){
					tasks.get(i).fjernDepend(queue.get(0).id);
				}
				queue.remove(0);
			}else if(!tasks.isEmpty()){
				System.out.println("prosjektet er ikke gjennomførbart!");
				findCycle();
				System.exit(0);
			//}else if(tasks.isEmpty()&&queue.isEmpty()){
			//	System.out.println("prosjektet er gjennomførbart!");
			//	break;
			}//else{
			//	System.out.println("LOGISK FEIL");
			//	System.exit(200);
			//}
		}*/
		System.out.println("prosjektet er gjennomførbart!");
		reset();

		simulate();
		//finner latest start
		reset();
		setLatest();


		//skriver ut alle Taskene
		for(Task t: taskMapFinal.values()){
			t.skrivUt();
		}



	}
	static void simulate(){
		ArrayList<Task> workingTasks = new ArrayList<Task>();
		int time = 0;
		findTask();
		int staff=0;
		while (!taskMap.isEmpty()||!queue.isEmpty()||!workingTasks.isEmpty()){
			endring=false;
			skrivut ="Time: " + time;
			//System.out.println("Time: " + time);
			workingTasks=tellNed(workingTasks);
			if(!queue.isEmpty()){
				for(int i=0; i<queue.size(); i++){
					workingTasks.add(queue.get(i));
					queue.get(i).earliest=time;
					sisteTask=queue.get(i);
					skrivut+="\n	Starting: " + queue.get(i).id;// + " estimert tid: " + queue.get(i).estimertTid;
					endring=true;
					//System.out.println("Starting: " + queue.get(i).id + " estimert tid: " + queue.get(i).estimertTid);
				}
				queue = new ArrayList<Task>();
			}

			//finner staff: dette kan gjøres bedre min dritt
			staff=0;
			for(int i=0; i<workingTasks.size(); i++){
				staff+=workingTasks.get(i).manpower;
			}
			skrivut+="\n	current staff:" + staff;
			//System.out.println("current staff:" + staff);
			time++;
			if(endring){
				System.out.println(skrivut);
			}
		}
		System.out.println("**** Raskeste gjennomføring av prosjektet er " + (time-1) + " ****");
		totalTid=time-1;
		return;

	}
	static ArrayList<Task> tellNed(ArrayList<Task> a){
		for(int i=0; i<a.size(); i++){
			if(a.get(i).tellNed()){
				skrivut+="\n	finished: " + a.get(i);
				endring=true;
				//System.out.println("finished: " + a.get(i));
				a.remove(i);
				i--;
			}
		}
		return a;
	}
	static void reset(){
		taskMap= new HashMap<Integer,Task>(taskMapFinal);
		for(Task t : taskMap.values()){
			t.resetDepend();
		}
	}
	static void findTask(){
		ArrayList<Integer> tall = new ArrayList<Integer>();
		for(Task t : taskMap.values()){
			if(t.depend.isEmpty()){
				queue.add(t);
				tall.add(t.id);
			}
		}
		for(int i=0; i<tall.size(); i++){
			taskMap.remove(tall.get(i));
		}
		/*for(int i=0; i<tasks.size(); i++){
			if(tasks.get(i).depend.isEmpty()){
				queue.add(tasks.get(i));
				tasks.remove(i);
				i--;
			}
		}*/
	}
	static void findCycle(){
		/*System.out.println("queue " + queue.size());
		System.out.println("gjennværende noder:");
		for(int i=0; i<tasks.size(); i++){
			System.out.print(tasks.get(i).id + " " +tasks.get(i));
			for(int x=0; x<tasks.get(i).depend.size(); x++){
				System.out.print(" " + tasks.get(i).depend.get(x));
			}
			System.out.println("\n");
		}*/
		System.out.println("PRINTING CYCLE:");
		for(Task t : taskMap.values()){
			t.printCycle();
			break;
		}
	}
	static void setLatest(){
		sisteTask.latest=totalTid-sisteTask.estimertTid;
		taskMap.remove(sisteTask.id);
		for(Task t: sisteTask.dependTasks.values()){
			t.setLatest();
		}
		for(Task t: taskMap.values()){
			t.latest=totalTid-t.estimertTid;
		}
	}

}
/*
1 int id
2 String navn
3 int estimert tid
4 int manpower
resten de som må blir gjort før denne kan startes, avsluttes med 0
 */
