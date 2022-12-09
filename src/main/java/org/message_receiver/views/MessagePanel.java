package org.message_receiver.views;

import org.message_receiver.controllers.MessageServer;
import org.message_receiver.models.Message;
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
import java.util.Set;
import java.util.TreeSet;

public class MessagePanel extends JPanel {

    private final JTree _serverTree;
    private final ServerTreeCellRenderer _treeCellRenderer;
    private final ServerTreeCellEditor _treeCellEditor;

    private Set<Integer> _selectedServers;
    private MessageServer _messageServer;

    public MessagePanel() {

        _messageServer = new MessageServer();

        _selectedServers = new TreeSet<>();
        _selectedServers.add(1);
        _selectedServers.add(2);
        _selectedServers.add(4);

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

                int _serverId = _serverInfo.getId();

                if (_serverInfo.isChecked()) {
                    _selectedServers.add(_serverId);
                }
                else if (!_serverInfo.isChecked()) {
                    _selectedServers.remove(_serverId);
                }

                _messageServer.setSelectedServers(_selectedServers);

                System.out.println("Message waiting: " + _messageServer.getMessageCount());

                for (Message message : _messageServer) {
                    System.out.println("Message title: " + message.getTitle());

                }
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
        DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(
                new ServerInfo("New York", 1, _selectedServers.contains(1)));
        DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(
                new ServerInfo("Boston",2, _selectedServers.contains(2)));
        DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(
                new ServerInfo("California",3, _selectedServers.contains(3)));

        DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
        DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(
                new ServerInfo("London", 4, _selectedServers.contains(4)));
        DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(
                new ServerInfo("Manchester",5, _selectedServers.contains(5)));

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

