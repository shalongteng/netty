package com.slt.netty.nettyChat.s02;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientFrame extends Frame {
	TextArea ta = new TextArea();
	TextField tf = new TextField();
    public ClientFrame(){
        this.setVisible(true);
        this.setSize(400,700);
        this.setResizable(true);

        this.setLocation(200,200);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(1);
            }
        });
		tf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//
				ta.setText(ta.getText() + tf.getText());
				tf.setText("");
			}
		});
    }
    public static void main(String[] args) {
        new ClientFrame();
    }
}
