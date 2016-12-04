/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritms;

import java.util.Scanner;
import java.util.*;


/**
 *
 * @author Nurtahirah
 */
 
public class DiskOne {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        int n, maxcylinder, i;
        int head,mark,total=0;
        Scanner input=new Scanner(System.in);
       
        
        System.out.println("Enter Number of Maximum Cylinders :");
           maxcylinder=input.nextInt();

        System.out.println("Enter Size of Cylinders :");
            n=input.nextInt();
            
        int cylinder[]=new int[n];
        //int disk[]=new int[n];
        
        System.out.println("Enter Number of Elements in Queue in Each Cylinder : ");
                            
            for(i=0;i<n;i++){
                cylinder[i]=input.nextInt();
            }
            
         System.out.println("Enter The Head Position");
            head=input.nextInt();
            mark=head;
        
            
        System.out.println("Choose the Algorithm between 1-6");
	System.out.println("        1-FCFS");
  	System.out.println("        2-SSTF");
  	System.out.println("        3-SCAN");
  	System.out.println("        4-CSCAN");
        System.out.println("        5-LOOK");
        System.out.println("        6-CLOOK");
  	System.out.println("        0-clear");
        int choice=-1; 
        choice=input.nextInt();
  	
  					
  	switch (choice) {
            case 1:{
                System.out.println("FCFS chosen");
                FCFS(mark, cylinder);
            }
            break;
  	
            case 2:{
                System.out.println("SSTF chosen");
                STFF(mark, cylinder);
            }
            break;
  	
            case 3:{
                System.out.println("SCAN chosen");
  		/*System.out.println("Enter the direction which the current head moves(1 OR 0)");
  		System.out.println("1-Direction of track number Increased");
  		System.out.println("2-Direction of track number decreased");
  		int diresction=-1;*/
  		
  		}
                    break;
  					
            case 4:{
                System.out.println("CSCAN chosen");
  		/*System.out.println("Enter the direction the current head moves(1 OR 0)");
  		System.out.println("1-Direction of track number Increased");
  		System.out.println("2-Direction of track number decreased");
  		int diresction=-1;*/
  	
  	  	}
  		break;
                
                case 5:{
                System.out.println("LOOK chosen");
            }
            break;
            
            case 6:{
                System.out.println("CLOOK chosen");
            }
            break;
            
             case 0:{
                System.out.println("clear chosen");
            }
            break;

  		default:
                    break;
  		
  				}
    }
 	
    public static void FCFS(int head , int[] array){
        //int n, maxcylinder, i;
        int distance[]=new int[array.length];
        int total=0;
        
        for(int i=0;i< array.length ;i++){
            if(array[i] > head){
                distance[i]=array[i]-head;
            }
            else{
                distance[i]=head-array[i];
            }
            head=array[i];
        }
              
        for(int i=0;i< array.length;i++){
            System.out.println("The next request is: " +array[i]+"\tMoving Distance: " +distance[i]);
            total=total+distance[i];
        }       
        System.out.println("The total disk movement is:"+ total);
  	}
    
    public static void STFF(int head , int[] array){
        
        Arrays.sort(array);
        int temp;
        int temparray = -1;
        int flag = 0;
        int distance[]=new int[array.length];
        int total=0;
    
            temp=-1;
            for(int i=0; i<array.length;i++){
            if(temp==-1 && distance[i]==-1){
                temp=Math.abs(array[i]-head);
                temparray=1;
            }
            if(temp!=-1 && Math.abs(array[i]-head) < temp && distance[i]==-1){
                temp=Math.abs(array[i]-head);
                temparray=i;
            }
            else continue;
        }
            //distance[temparray]=temp;
            //head = array[temparray];
       for(int i=0;i< array.length;i++){
            System.out.println("The next request is: " +array[i]+"\tMoving Distance: " +distance[i]);
            total=total+distance[i];
        }       
        System.out.println("The total disk movement is:"+ total);
  	}
        }

  		
    
   
