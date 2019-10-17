package blockchainfyp;

public class GUI extends javax.swing.JFrame {

    public GUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
            
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabBar = new javax.swing.JTabbedPane();
        jPanelHome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelTrans = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanelLog = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextLog = new javax.swing.JTextArea();
        btnLogClear = new javax.swing.JButton();
        btnShowChain = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnCheckValid = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnChangeHash = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GreCoin - Blockchain Application");

        jTabBar.setBackground(new java.awt.Color(255, 255, 51));
        jTabBar.setToolTipText("");

        jPanelHome.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("GreCoin - A Blockchain Application");

        javax.swing.GroupLayout jPanelHomeLayout = new javax.swing.GroupLayout(jPanelHome);
        jPanelHome.setLayout(jPanelHomeLayout);
        jPanelHomeLayout.setHorizontalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(501, Short.MAX_VALUE))
        );
        jPanelHomeLayout.setVerticalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
        );

        jTabBar.addTab("Home", jPanelHome);

        jPanelTrans.setBackground(new java.awt.Color(102, 102, 102));

        jButton1.setBackground(new java.awt.Color(255, 255, 0));
        jButton1.setText("Add a Block");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setBackground(new java.awt.Color(255, 255, 0));
        jTextField1.setText("jTextField1");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanelTransLayout = new javax.swing.GroupLayout(jPanelTrans);
        jPanelTrans.setLayout(jPanelTransLayout);
        jPanelTransLayout.setHorizontalGroup(
            jPanelTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTransLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanelTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanelTransLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(458, Short.MAX_VALUE))
        );
        jPanelTransLayout.setVerticalGroup(
            jPanelTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTransLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanelTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(57, 57, 57))
        );

        jTabBar.addTab("Transactions", jPanelTrans);

        jPanelLog.setBackground(new java.awt.Color(102, 102, 102));

        jTextLog.setBackground(new java.awt.Color(255, 255, 0));
        jTextLog.setColumns(20);
        jTextLog.setLineWrap(true);
        jTextLog.setRows(5);
        jScrollPane1.setViewportView(jTextLog);

        btnLogClear.setBackground(new java.awt.Color(255, 255, 0));
        btnLogClear.setText("Clear");
        btnLogClear.setToolTipText("Clear the panel displaying the current blockchain.");
        btnLogClear.setPreferredSize(new java.awt.Dimension(180, 25));
        btnLogClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogClearActionPerformed(evt);
            }
        });

        btnShowChain.setBackground(new java.awt.Color(255, 255, 0));
        btnShowChain.setText("Display Current Blockchain");
        btnShowChain.setToolTipText("Shows the user the curent blockchain");
        btnShowChain.setPreferredSize(new java.awt.Dimension(180, 25));
        btnShowChain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowChainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLogLayout = new javax.swing.GroupLayout(jPanelLog);
        jPanelLog.setLayout(jPanelLogLayout);
        jPanelLogLayout.setHorizontalGroup(
            jPanelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLogLayout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addComponent(btnLogClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addComponent(btnShowChain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171))
            .addComponent(jScrollPane1)
        );
        jPanelLogLayout.setVerticalGroup(
            jPanelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLogLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowChain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabBar.addTab("Log", jPanelLog);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        btnCheckValid.setBackground(new java.awt.Color(255, 255, 51));
        btnCheckValid.setText("Check Validity of the Current Chain");
        btnCheckValid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckValidActionPerformed(evt);
            }
        });

        jTextArea1.setBackground(new java.awt.Color(255, 255, 51));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        btnChangeHash.setBackground(new java.awt.Color(255, 255, 51));
        btnChangeHash.setText("Change Hash of Previous Block");
        btnChangeHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeHashActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCheckValid, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(498, Short.MAX_VALUE)
                    .addComponent(btnChangeHash, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(129, 309, Short.MAX_VALUE)
                        .addComponent(btnCheckValid)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(261, Short.MAX_VALUE)
                    .addComponent(btnChangeHash)
                    .addGap(59, 59, 59)))
        );

        jTabBar.addTab("Validity", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabBar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabBar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogClearActionPerformed
        jTextLog.setText(""); // Clear the text in the text area
    }//GEN-LAST:event_btnLogClearActionPerformed

    private void btnShowChainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowChainActionPerformed
        String chainData="";
        jTextLog.setText("");
        
        for(int i=0; i<Grechain.chain.size(); i++) {
            chainData +="Block: "+ i +"\n";
            chainData +="Time: "+Grechain.chain.get(i).getTime()+"\n";
            chainData +="PreviousHash: "+Grechain.chain.get(i).getPreviousHash()+"\n";
            chainData +="Hash: "+Grechain.chain.get(i).getHash()+"\n";
            chainData +="\n\n";
		
            jTextLog.setText(jTextLog.getText()+""+chainData);
            chainData="";
            
	}
        
    }//GEN-LAST:event_btnShowChainActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         Block b=new Block(new java.util.Date(),jTextField1.getText());
         Grechain.addBlock(b);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCheckValidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckValidActionPerformed

        if(Grechain.isValid())
            jTextArea1.setText("Chain is valid");
        else
            jTextArea1.setText("Chain is not valid");
    }//GEN-LAST:event_btnCheckValidActionPerformed

    private void btnChangeHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeHashActionPerformed
        
        Grechain.getLatestBlock().setPreviousHash(jTextArea1.getText());
    }//GEN-LAST:event_btnChangeHashActionPerformed

           BlockChainUtil Grechain = new BlockChainUtil();
    public static void main(String args[]) {
       
       BlockChainUtil Grechain = new BlockChainUtil();
		
		Block a = new Block(new java.util.Date(), "<transactions>");
		Block b = new Block(new java.util.Date(), "<transactions>");
		Block c = new Block(new java.util.Date(), "<transactions>");
		
		Grechain.addBlock(a);
		Grechain.addBlock(b);
		Grechain.addBlock(c);
		
	        Grechain.getLatestBlock().setPreviousHash("BADHASH");
                
		Grechain.displayChain();		
		Grechain.isValid(); 
                
     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangeHash;
    private javax.swing.JButton btnCheckValid;
    private javax.swing.JButton btnLogClear;
    private javax.swing.JButton btnShowChain;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPanel jPanelLog;
    private javax.swing.JPanel jPanelTrans;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabBar;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea jTextLog;
    // End of variables declaration//GEN-END:variables
}
