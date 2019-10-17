import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.Viewer;

/*
 * Program to visualize huffman trees
 * @author miabelar 18fa
 */


public class HuffmanVisualizer {
    public static final int SCREEN_WIDTH = 1250;
    public static final int SCREEN_HEIGHT = 860;

    protected static String styleSheet = "node {" + "   fill-color: #d3d3d3;" + "size: 23px;"
            + "fill-mode: dyn-plain;" + "stroke-color: black;" + "stroke-width: 1px;"
            + "text-size: 21px;" + "}" + "edge {" + "text-size: 25px;" + "}"
            + "node.marked {" + "   fill-color: red;" + "}" + "edge.marked {"
            + "   fill-color: red;" + "   size: 4px;" + "}";
    
    static JFrame mainFrame;
    static org.graphstream.graph.implementations.SingleGraph huffmanTree;
    
    static JButton constructHuffmanTreeButton;
    static JButton clearHuffmanTreeButton;
    static JButton compressStringButton;
    static JButton decompressStringButton;
    
    static HintTextField stringToDeOrCompress;
    static HintTextField alphabetSeed;
    
    static JLabel expectedEncodingLength;
    static JLabel compressRatioSoFar;
    
    static Huffman huffman;
    static Map<String, Character> encodingToChar;
    
    static int nodeCounter = 0;
    
    static JPanel topBar;
    
    
    public static void main(String[] args) {

        System.setProperty("org.graphstream.ui.renderer",
                        "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        huffmanTree = new org.graphstream.graph.implementations.SingleGraph("");
        huffmanTree.addAttribute("ui.stylesheet", styleSheet);

        Viewer viewer = new Viewer(huffmanTree, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.disableAutoLayout();
        JPanel view = viewer.addDefaultView(false);
        

        // ui construction
        mainFrame = new JFrame("Huffman Visualizer");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setResizable(true);
        mainFrame.add(view, BorderLayout.CENTER);

        initUI();
        
        mainFrame.setVisible(true);
    }
    
    private static void drawHuffmanTree(String seed) {
        // construct the tree from encodings
        HuffmanTree tree = new HuffmanTree(encodingToChar);
        int width = tree.getWidth() / 2;
        int height = tree.getDepth();
        int totalWidth = SCREEN_WIDTH;
        int totalHeight = SCREEN_HEIGHT - topBar.getHeight();
        float widthPerNode = ((float) totalWidth) / ((float) width);
        float heightPerNode = ((float) totalHeight) / ((float) height);
        drawTree(widthPerNode, heightPerNode, totalWidth, totalHeight, tree.root);
    }
    
    private static void drawTree(float widthPerNode, float heightPerNode, 
            int totalWidth, int totalHeight, HuffmanTreeNode root) {
        float startingY = SCREEN_HEIGHT - totalHeight;
        float startingX = ((float) totalWidth) / 2;
        
        recursiveDraw(startingX, startingY, 
                widthPerNode, heightPerNode, totalWidth, totalHeight, root, -1, -1);
    }
    
    private static void recursiveDraw(float currX, float currY, 
            float widthPerNode, float heightPerNode, int totalWidth, int totalHeight,
            HuffmanTreeNode currNode, int parentId, int parentRelation) {
        if (currNode.character == '\u0000') {
            int parentCounter = nodeCounter;
            Node node = huffmanTree.addNode(Integer.toString(nodeCounter));
            node.addAttribute("x", currX);
            node.addAttribute("y", currY);
            // draw edge
            if (parentId != -1) {
                Edge edge = huffmanTree.addEdge(Integer.toString(nodeCounter)
                        + Integer.toString(parentId),
                        Integer.toString(nodeCounter), Integer.toString(parentId));
                edge.addAttribute("ui.label", Integer.toString(parentRelation));
            }
            nodeCounter = nodeCounter + 1;
            // left
            recursiveDraw(currX - widthPerNode, currY - heightPerNode, widthPerNode / 2, heightPerNode, 
                    totalWidth, totalHeight, currNode.left, parentCounter, 0);
            // right
            recursiveDraw(currX + widthPerNode, currY - heightPerNode, widthPerNode / 2, heightPerNode, 
                    totalWidth, totalHeight, currNode.right, parentCounter, 1);
        } else {
            Node node = huffmanTree.addNode(currNode.character + "");
            node.addAttribute("x", currX);
            node.addAttribute("y", currY);
            node.addAttribute("ui.label", currNode.character + "");
            // add edge
            Edge edge = huffmanTree.addEdge(currNode.character + "" + Integer.toString(parentId),
                    currNode.character + "", Integer.toString(parentId));
            edge.addAttribute("ui.label", Integer.toString(parentRelation));
            
        }
    }
    
    private static void populateEncodingToChar(String seed) {
        // get each char in the seed string
        encodingToChar = new HashMap<String, Character>();
        Set<Character> alreadySeenCharacters = new HashSet<Character>();
        for (int i = 0; i < seed.length(); i++) {
            char c = seed.charAt(i);
            if (!alreadySeenCharacters.contains(c)) {
                String encoding = huffman.compress(c + "");
                if (encoding == null) {
                    throw new IllegalArgumentException();
                }
                encodingToChar.put(encoding, c);
                alreadySeenCharacters.add(c);
            }
        }
    }
    
    private static void constructHuffmanTree(String seed) {
        
        try {
            huffman = new Huffman(seed);
            
            try {
                populateEncodingToChar(seed);
                // reset because we needed to call compress
                huffman = new Huffman(seed);
                drawHuffmanTree(seed);
                // set EEL
                expectedEncodingLength.setText("EEL: " + 
                    Double.toString(round(huffman.expectedEncodingLength(), 5)));
                compressRatioSoFar.setText("Compression Ratio:");
            } catch (IllegalArgumentException exception ) {
                showErrorMessage("Compress returned null ");
                clearHuffmanTree();
            }
            
        } catch (IllegalArgumentException e) {
            showErrorMessage("Error thrown in constructing Huffman");
        }
        
    }
    private static void clearHuffmanTree() {
        alphabetSeed.setText("");
        huffman = null;
        huffmanTree.clear();
        huffmanTree.addAttribute("ui.stylesheet", styleSheet);
        encodingToChar.clear();
        nodeCounter = 0;
        expectedEncodingLength.setText("EEL:");
        compressRatioSoFar.setText("Compression Ratio:");
    }
    private static void compressString(String string) {
        if (huffman == null) {
            showErrorMessage("Alphabet seed not yet provided");
        } else {
            try {
                showTextDialog(string, huffman.compress(string), true);
                // update the field
                compressRatioSoFar.setText("Compression Ratio: " +
                    Double.toString(round(huffman.compressionRatio(), 5)));
            } catch (Exception e) {
                showErrorMessage("An exception was thrown during execution");
            }
        }
    }
    private static void decompressString(String string) {
        if (huffman == null) {
            showErrorMessage("Alphabet seed not yet provided");
        } else {
            try {
                showTextDialog(string, huffman.decompress(string), false);
            } catch (Exception e) {
                showErrorMessage("An exception was thrown during execution");
            }
        }
    }
    
    static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
    static void showTextDialog(String input, String content, Boolean isCompression) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                                | UnsupportedLookAndFeelException ex) {
                }

                JTextArea ta = new JTextArea(20, 20);
                ta.setText(content);
                ta.setWrapStyleWord(true);
                ta.setLineWrap(true);
                ta.setCaretPosition(0);
                ta.setEditable(false);

                Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

                String functionName = isCompression ? "Compression for " : "Decompression for ";
                String inputName = input;
                if (inputName.length() > 10) {
                    inputName = inputName.substring(0, 10) + "...";
                }
                
                String title = functionName + inputName;
                
                JOptionPane.showMessageDialog(null, new JScrollPane(ta),
                        title, JOptionPane.INFORMATION_MESSAGE,
                                new ImageIcon(image));
            }
        });
    }
    
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    @SuppressWarnings("serial")
    private static void initUI() {
        // init all of the UI elements
        constructHuffmanTreeButton = new JButton("Construct Tree");
        clearHuffmanTreeButton = new JButton("Clear Tree");
        compressStringButton = new JButton("Compress String");
        decompressStringButton = new JButton("Decompress string");
        stringToDeOrCompress = new HintTextField("String to compress/decompress");
        stringToDeOrCompress.setColumns(17);
        alphabetSeed = new HintTextField("Alphabet Seed");
        alphabetSeed.setColumns(10);
        expectedEncodingLength = new JLabel("EEL: ");
        compressRatioSoFar = new JLabel("Compression Ratio:");
        
        // add the action listeners
        constructHuffmanTreeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nodeCounter != 0) {
                    showErrorMessage("Please clear the tree before entering additional text");
                }
                else if (alphabetSeed.getText().length() == 0) {
                    showErrorMessage("Empty seed");
                } else {
                    constructHuffmanTree(alphabetSeed.getText());
                    alphabetSeed.setColumns(10);
                }
                
            }
        });
        
        clearHuffmanTreeButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearHuffmanTree();
            }
        });
        
        compressStringButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stringToDeOrCompress.getText().length() == 0) {
                    showErrorMessage("Field is empty");
                } else {
                    compressString(stringToDeOrCompress.getText());
                    stringToDeOrCompress.setColumns(17);
                }
            }
        });
        
        decompressStringButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stringToDeOrCompress.getText().length() == 0) {
                    showErrorMessage("Field is empty");
                } else {
                    decompressString(stringToDeOrCompress.getText());
                    stringToDeOrCompress.setColumns(17);
                }
            }
        });
        
        alphabetSeed.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nodeCounter != 0) {
                    showErrorMessage("Please clear the tree before entering additional text");
                } else {
                    constructHuffmanTree(alphabetSeed.getText());
                }
            }
        });
        
        // construct the UI
        topBar = new JPanel(new GridLayout(2, 1));
        JPanel huffmanConstructionPanel = new JPanel();
        JPanel huffmanConstructionAndClearPanel = new JPanel();
        JPanel compressAndDecompressPanel = new JPanel();
        JPanel compressButtonsAndFieldPanel = new JPanel();
        JPanel dataFieldsPanel = new JPanel();
        JPanel buttonsAndDataFieldsPanel = new JPanel();
        
        dataFieldsPanel.add(expectedEncodingLength, BorderLayout.WEST);
        dataFieldsPanel.add(compressRatioSoFar, BorderLayout.EAST);
        
        huffmanConstructionPanel.add(alphabetSeed, BorderLayout.WEST);
        huffmanConstructionPanel.add(constructHuffmanTreeButton, BorderLayout.EAST);
        huffmanConstructionAndClearPanel.add(huffmanConstructionPanel, BorderLayout.WEST);
        huffmanConstructionAndClearPanel.add(clearHuffmanTreeButton, BorderLayout.EAST);
        
        compressAndDecompressPanel.add(compressStringButton, BorderLayout.WEST);
        compressAndDecompressPanel.add(decompressStringButton, BorderLayout.EAST);
        buttonsAndDataFieldsPanel.add(compressAndDecompressPanel, BorderLayout.WEST);
        buttonsAndDataFieldsPanel.add(dataFieldsPanel, BorderLayout.WEST);
        compressButtonsAndFieldPanel.add(stringToDeOrCompress, BorderLayout.WEST);
        compressButtonsAndFieldPanel.add(buttonsAndDataFieldsPanel, BorderLayout.EAST);
        
        topBar.add(huffmanConstructionAndClearPanel, BorderLayout.NORTH);
        topBar.add(compressButtonsAndFieldPanel, BorderLayout.SOUTH);
        
        mainFrame.add(topBar, BorderLayout.NORTH);
        
        
        
    }
    
}

class HuffmanTreeNode {
    public char character;
    public HuffmanTreeNode left;
    public HuffmanTreeNode right;
    
    public HuffmanTreeNode(char c) {
        this.character = c;
    }
    
    
    public HuffmanTreeNode() {}
}

class HuffmanTree {
    
    public HuffmanTreeNode root = new HuffmanTreeNode();
    public HuffmanTree(Map<String, Character> encodings) {
        huffmanTreeInsertConstruction(root, encodings);
    }
    
    public int getDepth() {
        return getDepthHelper(root, 1);
    }
    public int getWidth() {
        return Math.abs(getWidthHelper(root, 0)) + 1;
    }
    
    private int getWidthHelper(HuffmanTreeNode node, int currWidth) {
        int leftWidth = 0;
        int rightWidth = 0;
        if (node.left != null) {
            leftWidth = getDepthHelper(node.left, currWidth - 1);
        } 
        if (node.right != null) {
            rightWidth = getDepthHelper(node.right, currWidth + 1);
        } 
        if (leftWidth == 0 && rightWidth == 0) {
            return currWidth;
        } else {
            if (Math.abs(rightWidth) > Math.abs(leftWidth)) {
                return rightWidth;
            } else {
                return leftWidth;
            }
        }
    }
    
    private int getDepthHelper(HuffmanTreeNode node, int currDepth) {
        int leftDepth = 0;
        int rightDepth = 0;
        if (node.left != null) {
            leftDepth = getDepthHelper(node.left, currDepth + 1);
        } 
        if (node.right != null) {
            rightDepth = getDepthHelper(node.right, currDepth + 1);
        } 
        if (leftDepth == 0 && rightDepth == 0) {
            return currDepth;
        } else {
            return Math.max(leftDepth, rightDepth);
        }
    }
    
    private void huffmanTreeInsertConstruction(HuffmanTreeNode root, 
            Map<String, Character> encodings) {
        for (Map.Entry<String, Character> entry : encodings.entrySet()) {
            String encoding = entry.getKey();
            char character = entry.getValue();
            insertTree(root, encoding, character);
        }
    }
    
    private void insertTree(HuffmanTreeNode root, String encoding, char character) {
        HuffmanTreeNode currNode = root;
        for (int i = 0; i < encoding.length(); i++) {
            boolean isLastChar = (i == encoding.length() - 1);
            char c = encoding.charAt(i);        
            if (c == '0') {
                HuffmanTreeNode leftChild = currNode.left;
                if (leftChild == null) {
                    leftChild = new HuffmanTreeNode();
                }
                if (isLastChar) {
                    leftChild.character = character;
                }
                currNode.left = leftChild;
                currNode = leftChild;
            } else if (c == '1') {
                HuffmanTreeNode rightChild = currNode.right;
                if (rightChild == null) {
                    rightChild = new HuffmanTreeNode();
                }
                if (isLastChar) {
                    rightChild.character = character;
                }
                currNode.right = rightChild;
                currNode = rightChild;
            }
        }
    }
    
}


@SuppressWarnings("serial")
class HintTextField extends JTextField implements FocusListener {

    private final String hint;
    private boolean showingHint;

    public HintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText("");
            showingHint = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText(hint);
            showingHint = true;
        }
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }
}
