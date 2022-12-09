package org.message_receiver.views;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class MessagePanel extends JPanel {

    private final JTree _serverTree;
    public MessagePanel() {

        _serverTree = new JTree(createTree());

        setLayout(new BorderLayout());

        add(new JScrollPane(_serverTree), BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Server");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode("New York");
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode("Boston");
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode("California");

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode("London");
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode("Manchester");

        branch1.add(server1);
        branch1.add(server2);
        branch1.add(server3);

        branch2.add(server4);
        branch2.add(server5);

        top.add(branch1);
        top.add(branch2);

        return top;
    }
}
