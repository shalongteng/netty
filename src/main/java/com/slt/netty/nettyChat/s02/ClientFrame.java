package com.slt.netty.nettyChat.s02;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientFrame extends Frame {
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
    }
    public static void main(String[] args) {
        new ClientFrame();
    }
}
