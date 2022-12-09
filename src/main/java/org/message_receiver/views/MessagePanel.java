package org.message_receiver.views;

import org.message_receiver.models.Utils;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class MessagePanel extends JPanel {

    private final JTree _serverTree;
    private final ServerTreeCellRenderer _treeCellRenderer;

    public MessagePanel() {

        _treeCellRenderer = new ServerTreeCellRenderer();

        _serverTree = new JTree(createTree());
        _serverTree.setCellRenderer(_treeCellRenderer);
        _serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        _serverTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)_serverTree.getLastSelectedPathComponent();

            // UserObject is whatever the object passed to DefaultMutableTreeNode
            // in this case, it's a string. ie, "USA" or "UK"
            // String userObject = (String)node.getUserObject();

            Object userObject = node.getUserObject();

            System.out.println(userObject);
        });

        setLayout(new BorderLayout());

        add(new JScrollPane(_serverTree), BorderLayout.CENTER);
    }

    private DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");

        DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York", 1, true));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston",2, true));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("California",3, false));

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London", 4, true));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Manchester",5, false));

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

class ServerInfo {
    private final String _name;
    private final int _id;
    private boolean _checked;

    public ServerInfo(String name, int id, boolean checked) {
        _name = name;
        _id = id;
        _checked = checked;
    }

    public int getId() {
        return _id;
    }

    public boolean isChecked() {
        return _checked;
    }

    public void setChecked(boolean checked) {
        _checked = checked;
    }

    @Override
    public String toString() {
        return _name;
    }
}
