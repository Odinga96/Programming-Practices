package acctMgr.view;

import acctMgr.controller.Controller;
import acctMgr.controller.ErrorController;
import acctMgr.model.Error;
import acctMgr.model.Model;
import acctMgr.model.ModelEvent;
import acctMgr.view.JFrameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorView extends JFrameView {
    /**
     * Constructor for JFrameView
     *
     * @param model      - model to be set to view.
     * @param controller controller to be set to view.
     */
    public ErrorView(Model model, Controller controller) {
        super(model, controller);

        JLabel jLabel =new JLabel(((Error)getModel()).getMessage());
        jLabel.setBounds(10,10,500,100);

        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setSize(600,250);
        panel.setBackground(Color.YELLOW);

        JButton jButton=new JButton("OK");
        jButton.setBounds(320,130,150,40);
        jButton.addActionListener(new Handler());


        panel.add(jButton);
        panel.add(jLabel);

        this.getContentPane().add(panel, BorderLayout.CENTER);
    }

    @Override
    public void modelChanged(ModelEvent me) {

    }

    // Inner classes for Event Handling
    class Handler implements ActionListener {
        // Event handling is handled locally
        public void actionPerformed(ActionEvent e) {
            ((ErrorController)getController()).operation();
        }
    }
}
