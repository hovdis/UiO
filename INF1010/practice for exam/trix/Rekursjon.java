public class Rekursjon {
    public static void main(String[] args){
       triangel(3, 5); 
       teddy(250);
       teddy(53);
       teddy(41);
       teddy(42);
    }

    static int fakultet(int tall){
        if (tall <= 1){
            return 1;
        }
        return (tall * (fakultet(tall-1)));   
    }
    static int fakultetIterasjon(int tall){
        int sum = 1;
        while(tall > 1){
            sum *= tall;
            tall--;
        }
        return sum;
    }
    static int gcd(int a, int b){
        if (b == 0){
            return a;

        }else{
            return gcd(b, (a % b));
        }
    }
    static int gcdIterativ(int a, int b){
        while(b != 0){
            int temp = b;
            b = a % b;
            b = temp;
        }
        return a;
    }
    static void triangel(int m, int n){
        //2⋅(n−m+1)
        if(m == n){
            String tmp = "";
            for(int i = 0; i<2; i++){
                for(int k = 0; k < n; k++){
                    tmp += "*";
                }
                System.out.println(tmp);
                tmp = "";
            }
        }else{
            String tmp = "";
            for(int i = 0; i<m; i++){
                tmp +="*";
            }
            System.out.println(tmp);
            triangel((m+1), n);
            System.out.println(tmp);
        }
    }

    static boolean teddy(int n){
        if(n == 0){
            return true;
        }else if((n%2) == 0){
            teddy(n/2);
        }else if((n%5) == 0){
            teddy(n-42);
        }else if(((n%3) == 0)||((n%4) == 0)){
            teddy(n-((n%10)*(n%100/100)));
        }else{
            return false;
        }
        return false;
    }
}
