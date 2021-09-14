package com.company;

import java.util.*;
import java.io.*;

//class Solution {
//    static void permute(java.util.List<Integer> arr, int k) {
//        for (int i = k; i < arr.size(); i++) {
//            java.util.Collections.swap(arr, i, k);
//            permute(arr, k + 1);
//            java.util.Collections.swap(arr, k, i);
//        }
//        if (k == arr.size() - 1) {
//            System.out.println(java.util.Arrays.toString(arr.toArray()));
//        }}
//        public static void main(String[] args)
//        {
//            Solution.permute(java.util.Arrays.asList(1,2,3), 0);
//        }
//    }
class Solution{
    public static void main(String[] args) {
        if(args.length==5){
            try {
                File myObj = new File("file1.txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                    Arrays.sort(args);

                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            try {
                FileWriter myWriter = new FileWriter("file1.txt");
                for(String vals: args) {
                    myWriter.write(vals);
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        else if(args.length==0){
            String[] str=new String[5];
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter 5 words sentence");
            for(int i=0; i<5; i++){
                str[i]=sc.next();
            }
            System.out.println("The 5 words are:");
            for(int i=0; i<5; i++){
                System.out.print(str[i]+" ");
            }
            System.out.println();
        }
        else{
            try {
                System.out.println("Error: Number of arguments is not Valid!");
            }
            catch (Exception e){
               e.printStackTrace();
            }
        }
    }
}
