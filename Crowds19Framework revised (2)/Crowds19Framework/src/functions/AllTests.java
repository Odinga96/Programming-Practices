import static org.junit.Assert.*;


import java.io.IOException;
import java.security.Key;
import java.util.Vector;
import java.math.*;

/*
 * 
 * For Distinction-level tasks only
 * 
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
*/


import org.junit.Test;

public class AllTests {

	// PASS-LEVEL TESTS
	
	String nodeFileName = "data/nodedef1.in";
	String msgFileName = "data/msg0.in" ;

	
	@Test
	public void testMessageTrackCheckZeroOneAdd() {
		MessageTrackCheck t = new MessageTrackCheck(0);
		t.add(1);
		assertEquals('b', t.check());
	}
	
	@Test
	public void testMessageTrackCheckZeroThreeAdds() {
		MessageTrackCheck t = new MessageTrackCheck(0);
		t.add(1);
		t.add(22);
		t.add(17);
		assertEquals('o', t.check());
	}
	
	@Test
	public void testMessageTrackCheckFiveThreeAdds() {
		MessageTrackCheck t = new MessageTrackCheck(5);
		t.add(1);
		t.add(22);
		t.add(17);
		assertEquals('t', t.check());
	}
	
	@Test
	public void testMessageTrackCheckReset() {
		MessageTrackCheck t = new MessageTrackCheck(5);
		t.reset(9);
		assertEquals('j', t.check());
	}
	
	@Test
	public void testNodeTransitionFunction() {
		NodeTransitionFunction f = new NodeTransitionFunction(3, 33);
		assertEquals(Integer.valueOf(26), f.apply(5));
	}

	
	@Test
	public void testIsDestinationTrue() {
		Node n2 = new Node(2, 3, 7, 33, Boolean.FALSE, Boolean.FALSE, null, null);
		assertTrue(n2.isDestinationNode("hello002"));
	}
	
	@Test
	public void testIsDestinationFalse() {
		Node n2 = new Node(2, 3, 7, 33, Boolean.FALSE, Boolean.FALSE, null, null);
		assertFalse(n2.isDestinationNode("hello001"));
	}

	@Test
	public void testNodeID() {
		Node n = new Node(1, 3, 7, 33, Boolean.FALSE, Boolean.FALSE, null, null);
		assertEquals(Integer.valueOf(1), n.getID());
	}

	@Test
	public void testGenerateTransitionFunction() {

		Node n1 = new Node(1, 3, 7, 33, Boolean.FALSE, Boolean.FALSE, null, null);
		NodeTransitionFunction f = n1.createForwardNodeTransitionFunction();
		
		assertEquals(Integer.valueOf(26), f.apply(5));
	}

	
	@Test
	public void testOneStepTransmitA() {
		Network net = new Network();
		net.track = new MessageTrackCheck(0);
		
		Node n1 = new Node(1, 3, 7, 33, Boolean.FALSE, Boolean.FALSE, net.lookup, net.track);
		net.lookup.put(1, n1);
		net.track.add(1);
		Node n2 = new Node(2, 3, 7, 33, Boolean.FALSE, Boolean.FALSE, net.lookup, net.track);
		net.lookup.put(2, n2);
		n1.sendMsgToNode(n2, "hello002", 1, null);
		assertEquals('d', net.track.check());
	}
	
	
	@Test
	public void testOneStepTransmitB() {
		Network net = new Network();
		net.track = new MessageTrackCheck(0);
		
		Node n1 = new Node(1, 3, 7, 33, Boolean.FALSE, Boolean.FALSE, net.lookup, net.track);
		net.lookup.put(1, n1);
		net.track.add(1);
		Node n2 = new Node(2, 3, 7, 33, Boolean.FALSE, Boolean.FALSE, net.lookup, net.track);
		net.lookup.put(2, n2);
		n1.sendMsgToNode(n2, "hello002", 1, null);
		assertEquals("hello",(n2.getMsg()));
	}
	
	@Test
	public void testAddDestID() {
		Node n1 = new Node(1, 3, 7, 33, Boolean.FALSE, Boolean.FALSE, null, null);
		assertEquals("hello006", n1.addDestIDToMsg("hello", 6));
	}

	@Test
	public void testCalcFirstR() {

		Node n1 = new Node(1, 3, 7, 33, Boolean.FALSE, Boolean.FALSE, null, null);
		//assertEquals(Integer.valueOf(24), n1.firstRForInitiatingMessage(3, 6));
	}
	
	
	
	@Test
	public void testBasicTransmissionTrack() {

		Network net = new Network();
		net.track = new MessageTrackCheck(0);

		String nodeFileInName = nodeFileName;
		// CHANGE THIS FILENAME
		
		try {
			Vector<Vector<Integer> > inputNodes = net.readNodesFromFile(nodeFileInName);
			for (Vector<Integer> v : inputNodes) {
				Node n = new Node(v.get(0), v.get(1), v.get(2), v.get(3), Boolean.FALSE, Boolean.FALSE, net.lookup, net.track);
				net.lookup.put(v.get(0), n);
			}
			net.lookup.get(1).initiateMessage("hello", 3, 6);
		}
		catch (Exception e) {
			System.out.println("in exception: " + e);
		}
		
		assertEquals('v', net.track.check());
	}

	@Test
	public void testBasicTransmissionMsgTransmittedTrue() {

		Network net = new Network();
		net.track = new MessageTrackCheck(0);

		String nodeFileInName = nodeFileName;
		try {
			Vector<Vector<Integer> > inputNodes = net.readNodesFromFile(nodeFileInName);
			for (Vector<Integer> v : inputNodes) {
				Node n = new Node(v.get(0), v.get(1), v.get(2), v.get(3), Boolean.FALSE, Boolean.FALSE, net.lookup, net.track);
				net.lookup.put(v.get(0), n);
			}
			net.lookup.get(1).initiateMessage("hello", 3, 6);
		}
		catch (Exception e) {
			System.out.println("in exception: " + e);
		}
		
		assertTrue(net.lookup.get(10).transmittedMessage());
	}

	@Test
	public void testBasicTransmissionMsgTransmittedFalse() {

		Network net = new Network();
		net.track = new MessageTrackCheck(0);

		String nodeFileInName = nodeFileName;
		try {
			Vector<Vector<Integer> > inputNodes = net.readNodesFromFile(nodeFileInName);
			for (Vector<Integer> v : inputNodes) {
				Node n = new Node(v.get(0), v.get(1), v.get(2), v.get(3), Boolean.FALSE, Boolean.FALSE, net.lookup, net.track);
				net.lookup.put(v.get(0), n);
			}
			net.lookup.get(1).initiateMessage("hello", 3, 6);
		}
		catch (Exception e) {
			System.out.println("in exception: " + e);
		}
		
		assertFalse(net.lookup.get(11).transmittedMessage());
	}

	// CREDIT-LEVEL TESTS

	@Test
	public void testBigIntR() {
		NodeTransitionFunction f = new NodeTransitionFunction(3, 33);
		BigInteger r = new BigInteger("5");
		assertEquals(new BigInteger("26"), f.apply(r));
	}

	@Test
	public void testCalcFirstBigIntR() {

		Node n1 = new Node(1, 3, 7, 33, Boolean.FALSE, Boolean.FALSE, null, null);
		assertEquals(new BigInteger("24"), n1.firstRForInitiatingMessage(3, new BigInteger("6")));
	}
	

	@Test
	public void testOneStepTransmitBigInt() {
		Network net = new Network();
		net.track = new MessageTrackCheck(0);
		
		Node n1 = new Node(1, 3, 7, 33, Boolean.FALSE, Boolean.TRUE, net.lookup, net.track);
		net.lookup.put(1, n1);
		net.track.add(1);
		Node n2 = new Node(2, 3, 7, 33, Boolean.FALSE, Boolean.TRUE, net.lookup, net.track);
		net.lookup.put(2, n2);
		n1.sendMsgToNode(n2, "hello002", new BigInteger("1"), null);
		assertEquals('d', net.track.check());
	}

	// (HIGH) DISTINCTION-LEVEL TESTS

	@Test
	public void testSetCorruptA() {
		Network net = new Network();
		net.track = new MessageTrackCheck(0);
		String nodeFileInName = nodeFileName;

		try {
			Vector<Vector<Integer> > inputNodes = net.readNodesFromFile(nodeFileInName);
			for (Vector<Integer> v : inputNodes) {
				Node n = new Node(v.get(0), v.get(1), v.get(2), v.get(3), Boolean.FALSE, Boolean.TRUE, net.lookup, net.track);
				net.lookup.put(v.get(0), n);
			}
			
			int[] corruptNodes = {2, 8, 13, 19};
			for (int i = 0; i < corruptNodes.length; i++)
				net.lookup.get(corruptNodes[i]).setCorrupt();

			net.lookup.get(2).sendMsgToNode(net.lookup.get(8), "hello008", new BigInteger("2"), net.lookup.get(8).createBackwardNodeTransitionFunction());
		}
		catch (Exception e) {
			System.out.println("in exception: " + e);
		}
		
		assertEquals(Integer.valueOf(2), net.lookup.get(8).lastSender());

	}
	
	@Test
	public void testSetCorruptB() {
		Network net = new Network();
		net.track = new MessageTrackCheck(0);
		String nodeFileInName = nodeFileName;

		try {
			Vector<Vector<Integer> > inputNodes = net.readNodesFromFile(nodeFileInName);
			for (Vector<Integer> v : inputNodes) {
				Node n = new Node(v.get(0), v.get(1), v.get(2), v.get(3), Boolean.FALSE, Boolean.TRUE, net.lookup, net.track);
				net.lookup.put(v.get(0), n);
			}
			
			int[] corruptNodes = {2, 8, 13, 19};
			for (int i = 0; i < corruptNodes.length; i++)
				net.lookup.get(corruptNodes[i]).setCorrupt();

			net.lookup.get(2).sendMsgToNode(net.lookup.get(9), "hello009", new BigInteger("2"), null);
		}
		catch (Exception e) {
			System.out.println("in exception: " + e);
		}
		
		assertEquals(Integer.valueOf(-1), net.lookup.get(9).lastSender());

	}
	
	@Test
	public void testGuessInitiator() {
		Network net = new Network();
		net.track = new MessageTrackCheck(0);
		String nodeFileInName = nodeFileName;
		String msgFileInName = msgFileName;

		try {
			Vector<Vector<Integer> > inputNodes = net.readNodesFromFile(nodeFileInName);
			for (Vector<Integer> v : inputNodes) {
				Node n = new Node(v.get(0), v.get(1), v.get(2), v.get(3), Boolean.FALSE, Boolean.TRUE, net.lookup, net.track);
				net.lookup.put(v.get(0), n);
			}
			
			int[] corruptNodes = {2, 8, 13, 19};
			for (int i = 0; i < corruptNodes.length; i++)
				net.lookup.get(corruptNodes[i]).setCorrupt();

			Vector<String> messages = net.readMessagesFromFile(msgFileInName);
			net.lookup.get(1).initiateMessage(messages.get(0), 2, 8);
			net.lookup.get(1).initiateMessage(messages.get(0), 4, 7);
		}
		catch (Exception e) {
			System.out.println("in exception: " + e);
		}

		assertNotEquals(Integer.valueOf(1), net.lookup.get(8).guessInitiator());


	}
	

	@Test
	public void testMsgEncryptionA() {
		Network net = new Network();
		net.track = new MessageTrackCheck(0);

		String nodeFileInName = nodeFileName;
		String msgFileInName = msgFileName;
		String msg = "hello";
		byte[] encryptedMsg = null;

		Vector<Vector<Integer> > inputNodes = net.readNodesFromFile(nodeFileInName);
		for (Vector<Integer> v : inputNodes) {
			Node n = new Node(v.get(0), v.get(1), v.get(2), v.get(3), Boolean.TRUE, Boolean.TRUE, net.lookup, net.track);
			net.lookup.put(v.get(0), n);
		}

		Vector<String> messages = net.readMessagesFromFile(msgFileInName);

		Key pubKey;

		pubKey = net.lookup.get(1).getPublicKey();  // will decrypt

		encryptedMsg = net.lookup.get(1).encryptedMsg(msg, pubKey);

		assertEquals(Boolean.TRUE, msg.contentEquals(new String(net.lookup.get(1).decryptedMsg(encryptedMsg))));

	}

	@Test
	public void testMsgEncryptionB() {
		Network net = new Network();
		net.track = new MessageTrackCheck(0);

        String nodeFileInName = nodeFileName;
        String msgFileInName = msgFileName;

		String msg = "hello";
		byte[] encryptedMsg = null;

		Vector<Vector<Integer> > inputNodes = net.readNodesFromFile(nodeFileInName);
		for (Vector<Integer> v : inputNodes) {
			Node n = new Node(v.get(0), v.get(1), v.get(2), v.get(3), Boolean.TRUE, Boolean.TRUE, net.lookup, net.track);
			net.lookup.put(v.get(0), n);
		}

		Vector<String> messages = net.readMessagesFromFile(msgFileInName);

		Key pubKey;

		pubKey = net.lookup.get(2).getPublicKey();  // won't decrypt

		encryptedMsg = net.lookup.get(1).encryptedMsg(msg, pubKey);

		assertEquals(Boolean.FALSE, msg.contentEquals(new String(net.lookup.get(1).decryptedMsg(encryptedMsg))));

	}

	@Test
	public void testMsgEncryptionC() {
		Network net = new Network();
		net.track = new MessageTrackCheck(0);

        String nodeFileInName = nodeFileName;
        String msgFileInName = msgFileName;

		String msg = "hello";
		byte[] encryptedMsg = null;

		Vector<Vector<Integer> > inputNodes = net.readNodesFromFile(nodeFileInName);
		for (Vector<Integer> v : inputNodes) {
			Node n = new Node(v.get(0), v.get(1), v.get(2), v.get(3), Boolean.TRUE, Boolean.TRUE, net.lookup, net.track);
			net.lookup.put(v.get(0), n);
		}

		Vector<String> messages = net.readMessagesFromFile(msgFileInName);

		Key pubKey;

		pubKey = net.lookup.get(1).getPublicKey();  // will decrypt

		String augMsg = net.lookup.get(1).addCheckToMsg(msg);

		encryptedMsg = net.lookup.get(1).encryptedMsg(augMsg, pubKey);

		String decrypt=new String(net.lookup.get(1).decryptedMsg(encryptedMsg));


		assertEquals(Boolean.FALSE, net.lookup.get(1).isDestinationNode(decrypt));



	}

	@Test
	public void testMsgEncryptionD() {
		Network net = new Network();
		net.track = new MessageTrackCheck(0);

        String nodeFileInName = nodeFileName;
        String msgFileInName = msgFileName;

		String msg = "hello";
		byte[] encryptedMsg = null;

		Vector<Vector<Integer> > inputNodes = net.readNodesFromFile(nodeFileInName);
		for (Vector<Integer> v : inputNodes) {
			Node n = new Node(v.get(0), v.get(1), v.get(2), v.get(3), Boolean.TRUE, Boolean.TRUE, net.lookup, net.track);
			net.lookup.put(v.get(0), n);
		}

		Vector<String> messages = net.readMessagesFromFile(msgFileInName);

		Key pubKey;

		pubKey = net.lookup.get(2).getPublicKey();  // won't decrypt

		String augMsg = net.lookup.get(1).addCheckToMsg(msg);
		encryptedMsg = net.lookup.get(1).encryptedMsg(augMsg, pubKey);

		String decrypt=new String(net.lookup.get(2).decryptedMsg(encryptedMsg));

		assertEquals(Boolean.FALSE, net.lookup.get(1).isDestinationNode(decrypt));

	}
	
}
