/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os1;
import java.io.*;
import java.util.*;

/**
 *
 * @author mohanad ghozlan
 */
public class Os1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int choice;
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter which algorithm you want : 1-fifo 2-optimal 3-LRU");
        choice= Integer.parseInt(b.readLine());
        if(choice==1){
        int n ;
                int f;
                float rat;
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the number of FRAMES :");
                f=Integer.parseInt(br.readLine());
                int fifo[]=new int[f];
                System.out.println("Enter the number of INPUTS :");
                n=Integer.parseInt(br.readLine());
                int input[]=new int[n];
                System.out.println("Enter INPUT:");
                for(int i=0;i<n;i++)
                input[i]=Integer.parseInt(br.readLine());
                System.out.println("----------------------");
                for(int i=0;i<f;i++)
                        fifo[i]=-1;
                int Hit=0;
                int Fault=0;
                int j=0;
                boolean check;
                for(int i=0;i<n;i++)
                {
                        check=false;


                                for(int k=0;k<f;k++)
                                if(fifo[k]==input[i])
                                {
                                        check=true;
                                        Hit=Hit+1;
                                }
                                if(check==false)
                                {
                                        fifo[j]=input[i];
                                        j++;
                                        if(j>=f)
                                        j=0;
                                        Fault=Fault+1;
                                }

                }
                rat = (float)Hit/(float)n;
                System.out.println("HIT:"+Hit+"  FAULT:"+Fault+"   HIT RATIO:"+rat);
        
    }
        else if(choice == 2){
              Scanner in = new Scanner(System.in);
        int frames = 0;
        int pointer = 0;
        int numFault = 0;
        int ref_len;
        boolean isFull = false;
        int buffer[];
        boolean hit[];
        int fault[];
        int reference[];
        int mem_layout[][];

        System.out.println("Please enter the number of frame: ");
        frames = Integer.parseInt(in.nextLine());

        System.out.println("Please enter the number of input : ");
        ref_len = Integer.parseInt(in.nextLine());

        reference = new int[ref_len];
        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        hit = new boolean[ref_len];
        fault = new int[ref_len];
        for(int j = 0; j < frames; j++)
        {
            buffer[j] = -1;
        }

        System.out.println("Please enter the  input : ");
        for(int i = 0; i < ref_len; i++)
        {
            reference[i] = Integer.parseInt(in.nextLine());
        }
        System.out.println();
        for(int i = 0; i < ref_len; i++)
        {
            int search = -1;
            for(int j = 0; j < frames; j++)
            {
                if(buffer[j] == reference[i])
                {
                    search = j;
                    hit[i] = true;
                    fault[i] = numFault;
                    break;
                }
            }

            if(search == -1)
            {
                if(isFull)
                {
                    int index[] = new int[frames];
                    boolean index_flag[] = new boolean[frames];
                    for(int j = i + 1; j < ref_len; j++)
                    {
                        for(int k = 0; k < frames; k++)
                        {
                            if((reference[j] == buffer[k]) && (index_flag[k] == false))
                            {
                                index[k] = j;
                                index_flag[k] = true;
                                break;
                            }
                        }
                    }
                    int max = index[0];
                    pointer = 0;
                    if(max == 0)
                    {
                        max = 200;
                    }

                    for(int j = 0; j < frames; j++)
                    {
                        if(index[j] == 0)
                        {
                            index[j] = 200;
                        }

                        if(index[j] > max)
                        {
                            max = index[j];
                            pointer = j;
                        }
                    }
                }
                buffer[pointer] = reference[i];
                numFault++;
                fault[i] = numFault;
                if(!isFull)
                {
                    pointer++;
                    if(pointer == frames)
                    {
                        pointer = 0;
                        isFull = true;
                    }
                }
            }

            for(int j = 0; j < frames; j++)
            {
                mem_layout[i][j] = buffer[j];
            }
        }

        
        System.out.println("Total Number of Page Faults: " + numFault);
        }
        else if (choice==3){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int frames,pointer = 0, hit = 0, fault = 0,ref_len;
        Boolean isFull = false;
        int buffer[];
        ArrayList<Integer> stack = new ArrayList<Integer>();
        int reference[];
        int mem_layout[][];
        
        System.out.println("Please enter the number of Frames: ");
        frames = Integer.parseInt(br.readLine());
        
        System.out.println("Please enter the length of input: ");
        ref_len = Integer.parseInt(br.readLine());
        
        reference = new int[ref_len];
        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        for(int j = 0; j < frames; j++)
                buffer[j] = -1;
        
        System.out.println("Please enter input: ");
        for(int i = 0; i < ref_len; i++)
        {
            reference[i] = Integer.parseInt(br.readLine());
        }
        System.out.println();
        for(int i = 0; i < ref_len; i++)
        {
            if(stack.contains(reference[i]))
            {
             stack.remove(stack.indexOf(reference[i]));
            }
            stack.add(reference[i]);
            int search = -1;
            for(int j = 0; j < frames; j++)
            {
                if(buffer[j] == reference[i])
                {
                    search = j;
                    hit++;
                    break;
                }
            }
            if(search == -1)
            {
             if(isFull)
             {
              int min_loc = ref_len;
                    for(int j = 0; j < frames; j++)
                    {
                     if(stack.contains(buffer[j]))
                        {
                            int temp = stack.indexOf(buffer[j]);
                            if(temp < min_loc)
                            {
                                min_loc = temp;
                                pointer = j;
                            }
                        }
                    }
             }
                buffer[pointer] = reference[i];
                fault++;
                pointer++;
                if(pointer == frames)
                {
                 pointer = 0;
                 isFull = true;
                }
            }
            for(int j = 0; j < frames; j++)
                mem_layout[i][j] = buffer[j];
        }
        
        
        System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float)((float)hit/ref_len));
        System.out.println("The number of Faults: " + fault);
    }
    
}
        }
    


