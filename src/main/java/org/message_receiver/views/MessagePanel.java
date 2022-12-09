package org.message_receiver.views;

import org.message_receiver.models.Utils;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class MessagePanel extends JPanel {

    private final JTree _serverTree;
    private final ServerTreeCellRenderer _treeCellRenderer;
    private final ServerTreeCellEditor _treeCellEditor;

    public MessagePanel() {

        _treeCellRenderer = new ServerTreeCellRenderer();
        _treeCellEditor = new ServerTreeCellEditor();

        _serverTree = new JTree(createTree());
        _serverTree.setCellRenderer(_treeCellRenderer);
        _serverTree.setCellEditor(_treeCellEditor);
        _serverTree.setEditable(true);

        _serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        _treeCellEditor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                ServerInfo _serverInfo = (ServerInfo) _treeCellEditor.getCellEditorValue();

                System.out.println(_serverInfo + ": " + _serverInfo.getId() + ": " + _serverInfo.isChecked());
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
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

