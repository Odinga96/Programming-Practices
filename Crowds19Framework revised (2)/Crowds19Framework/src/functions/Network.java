/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;

/**
 *
 * @author user
 */
public class Network {
    public Network()
    {
        this.lookup = new HashMap<Integer, Node>();
    }
    
    public Map<Integer, Node> lookup;
    public MessageTrackCheck track;

    private int atIndex= 0; //Stores the index in the lookup

    public int getAtIndex() {
        return ++atIndex;
    }

    public void setAtIndex(int atID) {
        this.atIndex = atID;
    }
    
    
    Vector<String> readMessagesFromFile(String msgfilename)
    {
    	FileInputStream fi= null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        try{
            File f = new File(msgfilename);
            fi = new FileInputStream(f);
            isr = new InputStreamReader(fi);
            br = new BufferedReader(isr);
            
            String line, contents="";
            Vector<String> data = new Vector<String>();
            while((line = br.readLine()) != null)
            {
                contents += line;
                
                data.add(line);
            }
            return data;
        }
        catch(Exception e)
        {
            System.out.println("failed to read file: "+e.getMessage());
            return null;
        }
        finally{
            try{
                if(br != null)br.close();
                if(isr != null)isr.close();
                if(fi != null)fi.close();
                
            }catch(Exception e)
            {
                System.out.println(e);
            }
        }
        
    }
    Vector<Vector<Integer>> readNodesFromFile(String nodeFileInName) {
        FileInputStream fi= null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        try{
            File f = new File(nodeFileInName);
            fi = new FileInputStream(f);
            isr = new InputStreamReader(fi);
            br = new BufferedReader(isr);
            
            String line, contents="";
            Vector<Vector<Integer>> data = new Vector<Vector<Integer>>();
            while((line = br.readLine()) != null)
            {
                contents += line;
                String[] vals = line.split(" ");
                Vector<Integer> v = new Vector<Integer>();
                for(String s: vals)
                {
                    v.add(Integer.valueOf(s));
                }
                data.add(v);
            }
            return data;
        }
        catch(Exception e)
        {
            System.out.println("failed to read file: "+e.getMessage());
            return null;
        }
        finally{
            try{
                if(br != null)br.close();
                if(isr != null)isr.close();
                if(fi != null)fi.close();
                
            }catch(Exception e)
            {
                System.out.println(e);
            }
        }
        
        //To change body of generated methods, choose Tools | Templates.
    }
 
}
