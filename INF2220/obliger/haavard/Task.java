import java.util.*;
public class Task{
	int id;
	String navn;
	int estimertTid;
	int manpower;
	int earliest;
	int latest=-1;
	boolean beenhere=false;
	//int estimertTidFinal;

	int nedtelling;
	//alle lister med Final er sikkerhetskopier av originale lister.
	//liste med integer over hvilke task this er avhengig av
	ArrayList<Integer> depend = new ArrayList<Integer>();
	ArrayList<Integer> dependFinal = new ArrayList<Integer>();

	//This er avhengig av disse
	HashMap<Integer, Task> dependTasks = new HashMap<Integer, Task>();
	HashMap<Integer, Task> dependTasksFinal;

	//disse er avhengig av this
	ArrayList<Task> dependOnMe = new ArrayList<Task>();
	ArrayList<Task> dependOnMeFinal;

	public Task(String data){
		String[] ord = data.split("\\s+");
		id=Integer.parseInt(ord[0]);
		navn=ord[1];
		estimertTid=Integer.parseInt(ord[2]);
		//estimertTidFinal=estimertTid; //kan sløyfes
		nedtelling = estimertTid;
		manpower=Integer.parseInt(ord[3]);

		for(int i=4; i<ord.length; i++){
			int tmp = Integer.parseInt(ord[i]);
			if(tmp!=0){
				depend.add(tmp);
				//System.out.println(tmp);
			}else{
				break;
			}
		}
	}
	public void sikkerhetskopier(){
		dependFinal= new ArrayList<Integer>(depend);
		dependTasksFinal = new HashMap<Integer, Task>(dependTasks);
		//denne må sikkerhetskopieres senere
		dependOnMeFinal = new ArrayList<Task>(dependOnMe);
	}
	public String toString(){
		return navn;
	}
	public void resetDepend(){
		depend= new ArrayList<Integer>(dependFinal);
		dependTasks= new HashMap<Integer, Task>(dependTasksFinal);
		dependOnMe = new ArrayList<Task>(dependOnMeFinal);
		//dependTasks=dependTasksFinal;
	}
	public void fjernDepend(int i){
		if(depend.contains(i)){
			for(int x=0; x<depend.size(); i++){
				if(depend.get(x) == i){
					depend.remove(x);
					break;
				}
			}
		}
	}
	public void fjernDepends(boolean sim){
		for(int i=0; i<dependOnMe.size();i++){
			//fjerne denne fra dependTask til task som er avhengig av denne
			dependOnMe.get(i).dependTasks.remove(id);
			dependOnMe.get(i).fjernDepend(id);

			if(dependOnMe.get(i).dependTasks.isEmpty()){
				//om tasken ikke er avhengig av noen mer legges den til i queue
				//og fjernes fra tasks
				//System.out.println("legger til i queue");
				Oblig2.queue.add(dependOnMe.get(i));
				if(!sim){
					Oblig2.taskMap.remove(dependOnMe.get(i).id);
				}
			}
		}
	}
	public void resetTid(){
		nedtelling=estimertTid;

	}
	public boolean tellNed(){
		nedtelling--;
		if(nedtelling==0){
			Oblig2.taskMap.remove(id);
			this.fjernDepends(false);

			return true;
		}
		return false;
	}
	public void printCycle(){
		if(beenhere==false){
			System.out.print(id + " ");
			beenhere=true;
			dependTasks.get(depend.get(0)).printCycle();
		}else{
			System.out.println(id +" \n");
		}

	}
	public void addMe(){
		for (Task t : dependTasks.values()){
			t.dependOnMe.add(this);
		}
	}
	public void skrivUt(){
		System.out.println(id + " " + navn);
		System.out.println("	Time needed: " + estimertTid);
		System.out.println("	Manpower required: " + manpower);
		System.out.println("	Earliest starting time " + earliest);
		System.out.println("	Latest starting time: " + latest);
		System.out.println("	Slack: " + (latest-earliest));
		System.out.print("	Tasks depending on this task:");
		for(int i=0; i<dependOnMe.size(); i++){
			System.out.print(" "+dependOnMe.get(i).id);
		}
		System.out.println("\n");

	}
	public void setLatest(){
		Oblig2.taskMap.remove(this.id);
		for(Task t: dependOnMe){
			//hent laveste latest
			if(t.latest!=-1&&(t.latest - estimertTid < latest )||latest==-1 &&t.latest!=-1){
				latest=t.latest-estimertTid;
			}
		}
		for(Task t: dependTasks.values()){
			t.setLatest();
		}

	}
}
