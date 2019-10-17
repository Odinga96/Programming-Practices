/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.crypto.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author user
 */
public class Node {

    public Node(Integer n, Integer e, Integer d, Integer K, Boolean encrypt, Boolean useBI,
            Map<Integer, Node> m, MessageTrackCheck t) {
        //CONSTRUCTOR:
        // n is node ID,
        // e is the exponent for the function f()
        // d is the exponent for the function g()
        // K is the divisor in f() and g()
        // encrypt is true if messages are encrypted, false otherwise
        // useBI is true if BigInteger should be used for NodeTransitionFunction, false otherwise
        // m is a non-null map of node IDs to node objects
        // t is an instance of MessageTrackCheck

        this.nodeID = n;
        this.exponent = e;
        this.exponent2 = d;
        this.divisor = K;
        this.encrypt = encrypt;
        this.useBI = useBI;
        this.map = m;
        this.messageTrackCheck = t;
        this.receivedMessage = "";
        transmittedMessage =false;
        this.isCorrupt = false;



        try {
            publicKey = KeyGenerator.getInstance("AES").generateKey();
        } catch (NoSuchAlgorithmException ee) {
            ee.printStackTrace();
        }

    }
    private Integer nodeID, exponent, exponent2, divisor, nodeIDSender =-1;
    private Boolean encrypt, useBI;
    private Map<Integer, Node> map;
    private MessageTrackCheck messageTrackCheck;
    private Boolean transmittedMessage;
    private String receivedMessage;
    private boolean isCorrupt;
    private Key publicKey;
    private Network net;

    public Node getNodeNetwork(int lookupID) {

        if (net != null) {
            return net.lookup.get(lookupID);
        }
        //else get the nodes from the files
        net = new Network();
        net.track = new MessageTrackCheck(0);

        System.out.println(net.track.check());
        String nodeFileInName = "data/nodedef1.in";
        // CHANGE THIS FILENAME

        try {
            Vector<Vector<Integer>> inputNodes = net.readNodesFromFile(nodeFileInName);
            for (Vector<Integer> v : inputNodes) {
                Node n = new Node(v.get(0), v.get(1), v.get(2), v.get(3), Boolean.FALSE, Boolean.FALSE, net.lookup, net.track);
                net.lookup.put(v.get(0), n);
            }
            return net.lookup.get(0);
        } catch (Exception e) {
            System.out.println("in exception: " + e);
            return null;
        }

    }

    public Boolean isDestinationNode(String msg) {
        //PRE: msg is an augmented message( i.e containing 3 characters at the end indicating destination node)
        //POST: Returns true if this is the destination node, false otherwise
        // E.g For node 6, will return true for "hello006"

        //gethe last three chars
        String last3 = msg.substring(msg.length() - 3, msg.length());
        int lastval = Integer.parseInt(last3);


        if (lastval == nodeID) {
            return true;
        }
        return false;
    }

    public Integer getID() {
        //PRE: -
        //POST: Returns node ID
        return this.nodeID;
    }

    public Integer getE() {
        //PRE: -
        //POST: Returns value of e in this node's function f()
        return this.exponent;
    }

    public Integer getK() {
        //PRE: -
        //POST: Returns value of K in this node's function f()
        return this.divisor;
    }

    public Boolean transmittedMessage() {
        //PRE: -
        //POST: Returns true if this node has transmitted a message, false otherwise
        return this.transmittedMessage;
    }

    public String getMsg() {
        //PRE: - 
        //POST: Returns the current received (non_augmented) message, null i fno received message
        return this.receivedMessage == null ? null : this.receivedMessage;
    }

    public NodeTransitionFunction createForwardNodeTransitionFunction() {
        //PRE: -
        //POST:  Creates a NodeTransitionFunction using this node's public function f()
        // with parameters e, K
        NodeTransitionFunction ntf = new NodeTransitionFunction(this.getE(), this.getK());
        return ntf;
    }
    public NodeTransitionFunction createBackwardNodeTransitionFunction() {
        //PRE: -
        //POST:  Creates a NodeTransitionFunction using this node's public function f()
        // with parameters e, K
        NodeTransitionFunction ntf = new NodeTransitionFunction(this.exponent2, this.getK());
        return ntf;
    }

    
    //Below i'm adding methods for the credit level
    

   

    public BigInteger firstRForInitiatingMessage(Integer k, BigInteger v) {
        // PRE: v is destination node ID, k is number of steps as a BigInteger
        // POST: Uses the trapdoor function inverse, applied to destination node v with number of steps k,
        // to calculate the node path;
        // returns value of r that determines first step on node path

        //g(x) = x^exp mod k
        Integer r = v.intValue();
        int fnode = 0;
        
        for(int x=1; x<k;x++)
        {
            Integer val2 = this.createBackwardNodeTransitionFunction().apply((int) r);
            r= val2 ;
        }
        fnode = r;

        return BigInteger.valueOf(fnode);
    }

     

     public void sendMsgToNode(Node n, String msg, Integer r, NodeTransitionFunction f) {
        
        //Integer r1 = f.apply(r);
        if(f == null)
        {
        	f =  this.createBackwardNodeTransitionFunction();
        	
        }
        //Set transmitted messge to true
        this.transmittedMessage = true;
        
        n.receiveMsgFromNode(msg, this.getID(), r, f);
    }
     
     public void sendMsgToNode(Node n, String msg, BigInteger r, NodeTransitionFunction f) {
         
         //Integer r1 = f.apply(r);
         if(f == null)
         {
         	f =  this.createBackwardNodeTransitionFunction();
         	
         }
         //Set transmitted messge to true
         this.transmittedMessage = true;
         
         n.receiveMsgFromNode(msg, this.getID(), r, f);
     }
     public void receiveMsgFromNode(String msg, Integer id, Integer r, NodeTransitionFunction f) {
        // PRE: msg is an augmented message,
        // id is the ID of the sending node,
        // r is the current value of r from the forward transition function,
        // f is the forward transition function
        // POST: If this is the destination node, stop;
        // otherwise, send the message onwards.
        // Add ID of current (receiving) node to local MessageTrackCheck
        int idval = Integer.parseInt(msg.substring(msg.length() - 3, msg.length()));
        this.messageTrackCheck.add(this.nodeID);
        if (idval != this.nodeID) {
            //forward
            //get the next node to receive
            r = f.apply(r);
            
            this.sendMsgToNode(this.map.get(r %20), msg, r, f);
        } else {
            //System.out.println("got the message");
        	this.receivedMessage =  msg.substring(0, msg.length()-3);
        	if(this.isCorrupt) this.nodeIDSender =id;
        }
    }
     public void receiveMsgFromNode(String msg, Integer id, BigInteger r, NodeTransitionFunction f) {
         // PRE: msg is an augmented message,
         // id is the ID of the sending node,
         // r is the current value of r from the forward transition function,
         // f is the forward transition function
         // POST: If this is the destination node, stop;
         // otherwise, send the message onwards.
         // Add ID of current (receiving) node to local MessageTrackCheck
         int idval = Integer.parseInt(msg.substring(msg.length() - 3, msg.length()));
         this.messageTrackCheck.add(this.nodeID);
         if (idval != this.nodeID) {
             //forward
             //get the next node to receive
             r = f.apply(r);
             
             this.sendMsgToNode(this.map.get(r.intValue() %20), msg, r, f);
         } else {
             //System.out.println("got the message");
         	this.receivedMessage =  msg.substring(msg.length() -3, msg.length());
         	if(this.isCorrupt) this.nodeIDSender =id;
         }
     }
    public String addDestIDToMsg(String msg, Integer v) {
        // PRE: msg is a message, v is a node ID
        // POST: Returns a string that concatenates v as a 3-character string to the end of msg.
        // E.g. for msg="hello", v=6, returns "hello006"

        String last3 = String.valueOf(v);
        //make the string 3 chars
        while (last3.length() < 3) {
            last3 = "0" + last3;
        }
        String newMsg = msg + last3;
        return newMsg;
    }

    public void initiateMessage(String msg, Integer k, Integer v) {
        // PRE: msg is an original message, v is destination node ID, k is number of steps
        // POST: Adds destination ID to msg;
        // sends augmented msg to the next node, as determined by firstRForInitiatingMessage(k, v),
        // along with new forward transition function
        NodeTransitionFunction newTransitionFunc = this.createForwardNodeTransitionFunction();
        System.out.println("node id: " + nodeID);
        
        //append dest to  messagae
        String idstr =  String.valueOf(v);
        while(idstr.length() <3)
        {
            idstr = "0" + idstr;
        }
        msg = msg + idstr;
        int r1 = (int) this.messageTrackCheck.check();
        
        //get the first receipient r value
        Integer firstReceipient = this.firstRForInitiatingMessage(k, BigInteger.valueOf(v)).intValue();
        Node n = this.map.get(firstReceipient % 20);
        this.transmittedMessage = true;
        this.messageTrackCheck.add(this.nodeID);
        this.sendMsgToNode(n, msg, firstReceipient, newTransitionFunc);
    }

    //
    //Distinction Level
    //
    public void setCorrupt() {
        // PRE: -
        // POST: Sets a node to be corrupt
        this.isCorrupt = true;
    }

    public Integer lastSender() {
        // PRE: -
        // POST: If a node is not corrupt, returns -1;
        // if a node is corrupt, returns ID of node that last sent it a message,
        // -1 if it has not been sent any messages
        if (this.isCorrupt == false) {
            return -1;
        }
        if (this.receivedMessage == null || this.receivedMessage == "") {
            //not received any msg
            return this.nodeIDSender;
        }
        return this.nodeIDSender;
    }

    public Integer guessInitiator() {
        // PRE: -
        // POST: Guesses a node to be the initiator if it can track back through corrupted nodes
        // along two separate paths;
        // returns this node ID, or -1 if no guess

        //cant guess if not corrupt
        if (this.isCorrupt == false) {
            return -1;
        }

        //Track back through corrup nodes
        try {
        	Node initiator = this;
        	while(initiator.nodeIDSender >-1)
        	{
        		int id =this.nodeIDSender;
        		Node n = this.map.get(id);
        		if(n == null)
        		{
        			return initiator.nodeID;
        		}
        		initiator =n;
        	}
        	return initiator.nodeID;
        }catch(Exception e)
        {
        	System.out.println("error guessing: "+e);
        	return -1;
        }
        //int val = this.createForwardNodeTransitionFunction().apply(this.nodeID); //Apply the function to get the inverse
        

    }

    //
    //Distinction level 2
    //
    public Boolean hasMsgEncryption() {
        // PRE: -
        // POST: Returns true if messages are encrypted, false otherwise
        return this.encrypt;
    }

    public Key getPublicKey() {
        // PRE:
        // POST: Returns the node's public key (null if hasMsgEncryption() is false)

        if (this.hasMsgEncryption() == false) {
            return null;
        }


        //return the public key
        return this.publicKey;
    }

    public String basicHashFunction(String m) {
        // PRE: -
        // POST: Sums the numeric value of each character using Character.getNumericValue(),
        // takes mod 100 of the total; returns as a 3-char string
        int sum = 0;
        char[] chars = m.toCharArray();
        for (int x = 0; x < m.length(); x++) {
            int val = Character.getNumericValue(chars[x]);
            sum += val;
        }

        int rem = sum % 100;
        String retval = "" + rem;
        while (retval.length() < 3) {
            retval = "0" + retval;
        }
        return retval;
    }

    public String addCheckToMsg(String msg) {
        // PRE: msg is a message
        // POST: Returns a string that concatenates the basicHashFunction of msg
        // E.g. for msg="hello", returns "hello097"

        String retval = msg + this.basicHashFunction(msg);
        return retval;
    }


    public byte[] encryptedMsg(String msg, Key key){
        byte[] msgIn=msg.getBytes();
        byte[] encrypted=null;
        Cipher cipher=null;

        try {
            cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE,key);

            encrypted=cipher.doFinal(msgIn);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return encrypted;
    }

    public byte[] decryptedMsg(byte[] encryptedMsg){
        byte[] decrypted=null;
        try {
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,publicKey);
            decrypted=cipher.doFinal(encryptedMsg);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            byte[] wrong={1,4,7};

            return wrong;
        }

        return decrypted;
    }

}

