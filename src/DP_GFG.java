import java.util.*;

import static java.lang.Math.*;

public class DP_GFG {
    public static void main(String[] args) {
//        SubsetSum ss = new SubsetSum();
//        ss.main(null);
//        Rod_cutting rr = new Rod_cutting();
//        rr.main(null);
//        Coin_change cc=new Coin_change();
//        cc.main(null);
//        Coin_Change_MIN cc_m=new Coin_Change_MIN();
//        cc_m.main(null);
//        LCS l=new LCS();
//        l.main(null);
//        longest_palindromic_substring l=new longest_palindromic_substring();
//        l.main(null);
        //next_permutation.main(null);
        //maximise_func.main(null);
//        negative_Side.main(null);
       // minJumps.main(null);
    }
}
class SubsetSum
{

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the size of array:");
        int n=sc.nextInt();
        System.out.println("Enter the array elements:");
        int arr[]=new int[n];
        int sum=0;
        for(int i=0; i<n; i++)
        {
            arr[i]=sc.nextInt();
            sum+=arr[i];
        }
        boolean solve=solve_subset_sum(n,sum/2,arr);
    }

    private static boolean solve_subset_sum(int N, int Sum,int a[])
    {
        boolean t[][]=new boolean[N+1][Sum+1];
        for(int j=0; j<=Sum; j++)
        {
          t[0][j]=false;
        }
        for(int k=0; k<=N; k++)
        {
            t[k][0] = true;
        }
        for(int i=1; i<=N; i++)
        {
            for(int j=1; j<=Sum; j++)
            {
                if(a[i-1]<=j)
                    t[i][j] = t[i-1][j] || t[i-1][j-a[i-1]];
                else
                    t[i][j] = t[i-1][j];
            }
        }
        return t[N][Sum];
    }
    }
class ReachScore
{
    public long count(int n)
    {
        long[] dp = new long[(int) n + 1];   //all values are 0 here
        Arrays.fill(dp, 0);
        dp[0] = 1;

        // Add your code here.
        return dp[n];
    }
}
class Rod_cutting
{
    //Unbounded Knapsack Problem
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int prices[]=new int[n];
        for(int i=0; i<n; i++){
            prices[i]=sc.nextInt();
        }
        int len[]=new int[n];
        for(int i=0; i<n; i++){
            len[i]=i+1;
        }
        int t[][]=new int[n+1][n+1];

        int ans=solve_rod_cutt(prices,len,n,n,t);
        System.out.println(ans);
    }
    public static int solve_rod_cutt(int val[], int wt[],int n, int w,int t[][]){

        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(wt[i-1]<=j) t[i][j]=max(val[i-1]+t[i][j-wt[i-1]],t[i-1][j]);

                else t[i][j]=t[i-1][j];
            }
        }
        return t[n][n];
        }
}
class Coin_change {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int SUM = sc.nextInt();
        int coin[] = new int[SUM];
        for (int i = 0; i < SUM; i++) {
            coin[i] = sc.nextInt();
        }
        long ans = count(coin,n,SUM);
        System.out.println(ans);
    }
    public static long count(int coin[], int n, int SUM)
    {
        //code here.

        long t[][]=new long[n+1][SUM+1];
        for(int i=1; i<n+1; i++){
            //jb sirf elment sirf ek he array mein aur sum=0 he to ek choice to hogi
            t[i][0]=1;
        }
        //kitne tarekko se add krke target milega
        //hr element ke liye choice he
        //weight array==coin array
        //W==SUM sum to hme kaise bhi chaihye yani multiple occurences he
        for(int i=1; i<n+1; i++)
        {
            for(int j=1; j<SUM+1; j++)
            {
                //jha pr bhi number of ways ya count ho wha pr maximum hta dena he and + lga dena he
                if(coin[i-1]<=j)
                {
                    t[i][j] = t[i][j-coin[i-1]] + t[i-1][j];
                }
                //last element pe answer aa jaega jaise t[5][5] pe ajega answer
                else
                {
                    t[i][j]=t[i-1][j];
                }
            }
        }
        return t[n][SUM];
    }
}
class Coin_Change_MIN
{
    //nikalegein min number of coins
    public static void main(String[] args)
    {
        //jitne bhi number of ways nikale the unmein se min number of coins kis mein lge he
        //wt==coin[]   W==sum
        Scanner sc=new Scanner(System.in);
        int V=sc.nextInt();
        int M=sc.nextInt();
        int coins[]=new int[M];
        for(int i=0; i<M; i++) coins[i]=sc.nextInt();
        int ans=minCoins(coins,M,V);
        System.out.println(ans);
    }
    public static int minCoins(int[] coins, int M, int V)
    {
        int t[][]=new int[M+1][V+1];
        //V dimension mien sum milega like sum 0, 1,2
        //M dimension mein size of array milega
        //is question mein twist he initialization krne ka
        //jha coin array hi empty hoo to sum==1 kaise askta hein mtlb infinte coins chahiye to vha
        //INT_MAX-1 rkh denge 2147483646
        //sum 0 lane ke liye ek bhi coin nhi chahiye to 1st column ko 0
        //abs 2nd row mwin array size 1 he to sum bhi utne hi coins aeenge to value 1 rkh denge
        //age array ka size 4 he to sum 4 kaise aega to mtlb divide nhi honge to divide nhi hoga to int_max bhrdenge
        for (int i=0; i<V+1; i++)
        {
            t[0][i]=2147483646;
        }
        //initialization of second row
        for(int j=1; j<V+1; j++)
        {
            int i=1;
            if(j%coins[0]==0){
                t[i][j]=j/coins[0];
            }
            else{
                t[i][j]=2147483646;
            }
        }
        //2nd row se start krenge
        for(int i=2; i<M+1; i++)
        {
            for(int j=1; j<V+1; j++)
            {

                if(coins[i-1]<=j)
                {
                    //agr coin liya to 1 add hojega
                    t[i][j] = min(1 + t[i][j-coins[i-1]], t[i-1][j]);
                }
                else
                {
                    t[i][j]=t[i-1][j];
                }
            }
        }
        return t[M][V];
    }
}
/*LCS
I/P:
X: a b c d g h
Y: a b e d g h r
jo inmein common he wo chahie
SUBSTRING mtlb continuos he
SUBSEQUENCE mtlb consecutive nhi h

RECURSIVE
Think of Base Condition(THINK OF SMALLEST VALID INPUT)
Choice diagram
I/p must be small
Sbse phle last index check krenge

agr ek string bhi khali he
if(X==0 || Y==0){}

agr match krgya mtlb X[n-1]==Y[n-1]
to length ek km krdo

agr match nhi kra to
2 choices he ek string hta ke lein str1 mein se aur str2 mein as it is
to hm choice mein max leleinge
hme basically length of longest subsequence return krni he
 */
class LCS{
    static int t[][]=new int[5][5];
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String X=sc.next();
        String Y=sc.next();
//        int ans=solve_lcs_recursive(X,Y,X.length(),Y.length());
//        System.out.println(ans);
        //for DP we create table MEMOIZED
//        for(int row_array[] :t){
//            Arrays.fill(row_array,-1);
//
//        }
//        int ans=solve_lcs_memoized(X,Y,X.length(),Y.length());
//        System.out.println(ans);
        //BOTTOM UP
         //int ans=solve_lcs_bottomUP();
    }
    //kyuki sizes change ho rhe he to size change hoga is liye size n+1 and m+1 liya
    // agr -1 mila to pta chljeaga ki value nhi he vha
    public static int solve_lcs_recursive(String X, String Y, int size_x, int size_y)
    {
        //agr last ka match krrha ya nhi
        if(size_x==0 || size_y==0)
        {
            return 0;
        }

        if(X.charAt(size_x-1)==Y.charAt(size_y-1))
        {
            //to ek +1 krdegein aur length increase krdenge

            return 1+solve_lcs_recursive(X,Y,size_x-1,size_y-1);
        }
        else{
            //yha pe +1 isliye nhi kra kyuki common nhi h wo upr hojega
            return max(solve_lcs_recursive(X,Y,size_x,size_y-1),solve_lcs_recursive(X,Y,size_x-1,size_y));
        }
    }
    /*
    DP hogi ye
    why do we need it because it saves time
     */
    public static int solve_lcs_memoized(String X, String Y, int size_x, int size_y){
            //agr last ka match krrha ya nhi
            if(size_x==0 || size_y==0){
                return 0;
            }
            if(t[size_x][size_y]!=-1){
             return t[size_x][size_y];
            }
            if(X.charAt(size_x-1)==Y.charAt(size_y-1)) {
                //to ek +1 krdegein aur length increase krdenge
                //aur jo mhnet krke ke value hoi wo store hojainge iskrke t[][]
                //phle store fir return krega
                return t[size_x][size_y]=1 + solve_lcs_memoized(X, Y, size_x - 1, size_y - 1);
            }
            else {
                //yha pe +1 isliye nhi kra kyuki common nhi h wo upr hojega
                return t[size_x][size_y]=max(solve_lcs_memoized(X, Y, size_x, size_y - 1), solve_lcs_memoized(X, Y, size_x - 1, size_y));
            }
    }
    /*Bottom UP LCS
    why do we need it stack overflow bhi ho_skta he risk he
    ->Base condition of Recursive becomes Initialization in Bottom UP
    if(m==0 || n==0) 0;
    hr block sub_problem represent kr_rha he table ki
    last mein answer milaega table ke like t[5][5]
    */
    public static int solve_lcs_bottomUP(int x, int y, String s1, String s2)
    {
        int dp[][]=new int[x+1][y+1];

        for(int i = 0; i <= x; i++)
        {
            dp[i][0] = 0;
        }

        for(int j = 0; j <= y; j++)
        {
            dp[0][j] = 0;
        }

        for(int i = 1; i <= x; i++)
        {
            for(int j = 1; j <= y; j++)
            {
                if(s1.charAt(i-1) == s2.charAt(j-1))
                {
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else
                {
                    dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[x][y];
    }

}
class cpl
{
    public static void main (String[] args) throws Exception
    {
        Scanner scan=new Scanner(System.in);
        int t=scan.nextInt();
        while(t-->0)
        {
            int n=scan.nextInt();
            int k=scan.nextInt();
            int arr[]=new int[n];
            LinkedHashSet<Integer> hash1=new LinkedHashSet<Integer>();

            int sumofarray=0;
            for(int i=0;i<n;i++){
                arr[i]=scan.nextInt();
            }
            Arrays.sort(arr);
            hash1.add(arr[n-1]);
            sumofarray=arr[n-1];
            int flag=-1;
            for(int i=n-2;i>=0;i--)
            {
                LinkedHashSet<Integer> hash2=new LinkedHashSet<Integer>();
                sumofarray=sumofarray+arr[i];
                Iterator iterator1=hash1.iterator();
                while(iterator1.hasNext()==true)
                {
                    int valueofhash1=(int)(iterator1.next());
                    hash2.add(valueofhash1);
                    hash2.add(arr[i]);
                    hash2.add(valueofhash1+arr[i]);
                    if(((arr[i])>=k)&&((sumofarray-arr[i])>=k))
                    {
                        flag=n-i;
                        break;
                    }
                    if(((valueofhash1+arr[i])>=k)&&((sumofarray-valueofhash1-arr[i])>=k))
                    {
                        flag=n-i;
                        break;
                    }
                    
                }
                if(flag!=-1){
                    break;
                }
                hash1=hash2;
            }
            System.out.println(flag);
        }
    }

}
//Maximise function (NOTDP)
class maximise_func{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n;
          //  System.out.println("Enter the size of array:");
            n = sc.nextInt();
            Integer[] arr = new Integer[n];
           // System.out.println("Elements:");
            for (int o = 0; o < arr.length; o++) {
                arr[o] = sc.nextInt();
            }
            Arrays.sort(arr, Collections.reverseOrder());
            int x=0,y=(arr.length-1), z=0;

            System.out.println(Math.abs(arr[x]-arr[y])+Math.abs(arr[y]-arr[z])+Math.abs(arr[z]-arr[x]));
        }
    }
}
//https://www.youtube.com/watch?v=LuLCLgMElus  TUF(Y.T)
//TUF CODE(NOT DP)
//static List<Integer> nextPermutation(int N, int arr[])
//{
//    // code here
//    List<Integer> list=new ArrayList<Integer>();
//
//    if(arr.length==1){
//        list.add(0);
//        return list;
//    }
//    int i=(arr.length-2);
//
//    while(i>=0 && arr[i]>=arr[i+1]) i--;
//
//    if(i>=0)
//    {
//        int j = (arr.length-1);
//        while(arr[j] <= arr[i]) j--;
//        swap(arr, i, j);
//    }
//    reverse(arr, i+1, arr.length-1);
//
//    for (int m =0; m<arr.length; m++){
//        list.add(arr[m]);
//    }
//    return list;
//}
//
//    public static void swap(int[] arr, int i, int j)
//    {
//        int temp=arr[j];
//        arr[j]=arr[i];
//        arr[i]=temp;
//    }
//
//    public static void reverse(int[] arr, int i, int j){
//        while(i<j) swap(arr, i++, j--);
//    }
class next_permutation
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("Enter the size of array:");
        n=sc.nextInt();
        int arr[] = new int[n];
        System.out.println("Elements:");
        for (int o=0; o< arr.length; o++){
            arr[o]=sc.nextInt();
        }
        int i;
        for (i=(arr.length-2); i>=0; i--){
            //finding the first index
            if(arr[i]<arr[i+1]){
                break;
            }
        }
        int j;
        for ( j=(arr.length-1); j>=0; j--){
            //finding the array element that is the breakpoint
            if(arr[j]>arr[i]){
                break;
            }
        }

        int temp=0;
        temp=arr[j];
        arr[j]=arr[i];
        arr[i]=temp;

//        //reverse the array
//        for(int k=i+1; k<arr.length; k++)
//        {
//            int l= arr[k];
//            arr[k] = arr[arr.length -k -1];
//            arr[arr.length -k -1] = l;
//        }
        //
        for (int m =0; m<i+1; m++){
            System.out.print(arr[m]+" ");
        }
        //System.out.println();
        for (int m=(arr.length-1); m>i; m--){
            System.out.print(arr[m]+" ");
        }
        System.out.println();
    }
}

//pending getting string
class longest_palindromic_substring
{
    public static boolean check(int i,int j,String str, boolean table[][]){
        int length = str.substring(i, j+1).length();
        String str1=str.substring(i,j+1);  //debug
        if(table[i][j]){
            return true;
        }
        if((length == 3) && (str.charAt(i) == str.charAt(j)) && (table[i + 1][j - 1])){
            return true;
        }
        else if (length==3){
            return false;
        }
        boolean ans = check(i+1, j-1, str, table);
        return ans;
    }
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the String:");
        String str=sc.next();
        int start=0;
        int end=str.length();
        boolean table[][]=new boolean[end+1][end+1];
        int max_length=1;
        for (int i=0; i<end; i++)
        {
                table[i][i]=true;

        }
        int next_row=1;
        while(next_row<str.length())
        {
            int i=0;
            int j=(i+next_row);
            while(j<=str.length()-1){
                String str1=str.substring(i,j+1);  //debug
                int length = str.substring(i, j+1).length();
                if(str.charAt(i)==str.charAt(j) &&  length==2)
                {
                    table[i][j]=true;
                    max_length=max(max_length,length);
                }
                if(length>2) {
                    if (str.charAt(i) == str.charAt(j) && table[i + 1][j - 1]) {
                        table[i][j] = check(i, j, str, table);
                        max_length=max(max_length,length);
                    }
                }
                j++;
                i++;
            }
            next_row++;
        }
        System.out.println("The longest palindromic Substring length is:"+max_length);
        for (int j=str.length();j>=0; j--)
        {
            int i=0;
            boolean flag=false;
            while(i<str.length())
            {
                if(table[i][j] && i!=j){
                    System.out.println("The Substring is:" + str.substring(i,j+1));
                    flag=true;
                }
                if(flag){
                    break;
                }
            i=i+1;
        }
            if(flag){
                break;
            }
        }
        }
    }
//ARRAY PROGRAM
class negative_Side {

   static void partition(int arr[], int start, int end)
    {

            int pivot = arr[end];
            int pIndex = start;
            int temp = 0;
            for (int i = start; i < end; i++) {
                if (arr[i] < pivot) {
                    temp = arr[i];
                    arr[i] = arr[pIndex];
                    arr[pIndex] = temp;
                    pIndex++;
                }
            }
            temp = arr[end];
            arr[end] = arr[pIndex];
            arr[pIndex] = temp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("Enter the size of array:");
        n = sc.nextInt();
        int arr[] = new int[n];
        System.out.println("Elements:");
        for (int o = 0; o < arr.length; o++) {
            arr[o] = sc.nextInt();
        }
        for (int i=0; i<n; i++){
            if(arr[i]<0){
                partition(arr,0,i);
            }
        }
        for (int o = 0; o < arr.length; o++) {
            System.out.print(arr[o]+" ");
        }
        System.out.println();
    }
}
class minJumps {
    static int minJumps(int arr[]) {
        if (arr.length <= 1)
            return 0;

        // Return -1 if not possible to jump
        if (arr[0] == 0)
            return -1;

        // initialization
        int maxReach = arr[0];
        int step = arr[0];
        int jump = 1;

        // Start traversing array
        for (int i = 1; i < arr.length; i++) {
            // Check if we have reached
// the end of the array
            if (i == arr.length - 1)
                return jump;

            // updating maxReach
            maxReach = Math.max(maxReach, i + arr[i]);

            // we use a step to get to the current index
            step--;

            // If no further steps left
            if (step == 0) {
                // we must have used a jump
                jump++;

                // Check if the current
                // index/position or lesser index
                // is the maximum reach point
                // from the previous indexes
                if (i >= maxReach)
                    return -1;

                // re-initialize the steps to the amount
                // of steps to reach maxReach from position i.
                step = maxReach - i;
            }
        }

        return -1;
    }
//    static int minJumps(int[] arr, int n) {
//        if(n<=1){
//            return 1;
//        }
//        if(arr[0]==0){
//            return -1;
//        }
//        int maxrange=arr[0];
//        int jumps=1;
//        int steps=arr[0];
//        int i=0;
//        while(i<n){
//            steps--;
//            i++;
//            maxrange=max(maxrange,i+arr[i]);
//            steps=i+arr[i];
//        }
//        return maxrange;
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();
        int arr[] = new int[n];
        for (int o = 0; o < arr.length; o++) {
            arr[o] = sc.nextInt();
        }
        System.out.println(minJumps(arr));
    }
}
class minimize_height
{
    static int minimiz(int arr[], int n, int k) {
        Arrays.sort(arr);
        int ans = arr[n-1] - arr[0];
        int smallest = arr[0] + k, largest = arr[n-1]-k;
        for(int i = 0; i < n-1; i++){
            int min = Math.min(smallest, arr[i+1]-k);
            int max = Math.max(largest, arr[ i ] + k);
            if(min < 0) continue;
            ans = Math.min(ans, max-min);
        }
        return ans;

    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();
        int arr[] = new int[n];
        int k;
        for (int o = 0; o < arr.length; o++) {
            arr[o] = sc.nextInt();
        }
        k=sc.nextInt();
        System.out.println(minimiz(arr,n,k));
    }
}
class find_dup
{
    static int findDuplicate(int arr[]) {
//        Arrays.sort(arr);
//        int dup=0;
//        for (int i=1 ;i<n; i++){
//            if(arr[i-1]==arr[i]){
//                dup=arr[i];
//            }
//        }
//        return dup;
//        int original_sum=0;
//        int n=arr.length;
//        for(int i=1; i<n; i++){
//            original_sum=original_sum+i;
//        }
//        int flag=0;
//        for(int i=1; i<n; i++){
//            if(arr[i-1]==arr[i]){
//                flag++;
//            }
//        }
//        if(flag==n){
//            return arr[0];
//        }
//        int arr_sum=0;
//        for (int i=0; i<n; i++){
//            arr_sum=arr_sum+arr[i];
//        }
//        return arr_sum-original_sum;
        int n=arr.length;
        if(n<3)
        {
            return arr[0];
        }
        int h=0;
        int t=0;
        for(int i=0; i<n; i++){
            if(h<n-1){
                h=h+2;
            }
            if(arr[h]==arr[t]){
                return arr[h];
            }
            t=t+1;
        }
        return arr[h];
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();
        int arr[] = new int[n];
        for (int o = 0; o < arr.length; o++) {
            arr[o] = sc.nextInt();
        }
        System.out.println(findDuplicate(arr));
    }

}
class lunchtime_feb {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        int t;
        t = sc.nextInt();
        while (t-- > 0) {
            n = sc.nextInt();
            int arr[] = new int[n];
            for (int o = 0; o < arr.length; o++) {
                arr[o] = sc.nextInt();
            }
        }
    }
}
