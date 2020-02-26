class Test{
    public static void main(String[] args){
        System.out.println("Trying with 1,3, 0, 2, 1, 0, 1, 0");
        int count[] = {1, 3, 0, 2, 1, 0, 1, 0};
        int indexes[] = new int[count.length];
        
        
        boolean doing = false;
        int indexCount = 0;
        for(int i = 0; i<=count.length;i++){
            if(count[i] != 0){
                if(!doing){
                    doing = true;
                    indexes[i] = count[i];
                    System.out.println("Hei1 : " + i);
                }else{
                    count[i]--;
                    i--;
                    System.out.println("Hei2 : " + i);
                }
            }else{
                doing = false;
                System.out.println("Hei3 : " + i);
            }
        }
                
                  
                for(int k = 0; k<indexes.length; k++){
                System.out.println(indexes[k]);}
        }
    }

