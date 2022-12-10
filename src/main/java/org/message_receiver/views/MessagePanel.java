package org.message_receiver.views;

import org.message_receiver.controllers.MessageServer;
import org.message_receiver.models.Message;
import org.message_receiver.models.Utils;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MessagePanel extends JPanel implements IProgressDialogListener {

    private final JTree _serverTree;
    private final ServerTreeCellRenderer _treeCellRenderer;
    private final ServerTreeCellEditor _treeCellEditor;

    private Set<Integer> _selectedServers;
    private MessageServer _messageServer;
    private ProgressDialog _progressDialog;
    private SwingWorker<List<Message>, Integer> _worker;
    private TextPanel _textPanel;
    private JList _messageList;
    private JSplitPane _upperPane;
    private JSplitPane _lowerPane;
    private DefaultListModel _defaultListModel;

    public MessagePanel(JFrame parent) {

        _messageServer = new MessageServer();
        _progressDialog = new ProgressDialog(parent,"Message Downloading...");
        _defaultListModel = new DefaultListModel();

        _progressDialog.setProgressDialogListener(this);

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

        _messageServer.setSelectedServers(_selectedServers);

        _treeCellEditor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                ServerInfo _serverInfo = (ServerInfo) _treeCellEditor.getCellEditorValue();

                int _serverId = _serverInfo.getId();

                if (_serverInfo.isChecked()) {
                    _selectedServers.add(_serverId);
                }
                else if (!_serverInfo.isChecked()) {
                    _selectedServers.remove(_serverId);
                }

                _messageServer.setSelectedServers(_selectedServers);

                retrieveMessages();
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });

        setLayout(new BorderLayout());

        _textPanel = new TextPanel();
        _messageList = new JList(_defaultListModel);
        _messageList.setCellRenderer(new MessageListRenderer());

        _messageList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Message message = (Message) _messageList.getSelectedValue();

                _textPanel.setText(message.getContents());
            }
        });

        _lowerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(_messageList), _textPanel);
        _upperPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(_serverTree), _lowerPane);

        _textPanel.setMinimumSize(new Dimension(10,100));
        _messageList.setMinimumSize(new Dimension(10,100));

        _upperPane.setResizeWeight(0.5);
        _lowerPane.setResizeWeight(0.5);

        add(_upperPane, BorderLayout.CENTER);
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

    private void retrieveMessages() {

        _progressDialog.setMaximum(_messageServer.getMessageCount());

        _progressDialog.setVisible(true);

        _worker = new SwingWorker<List<Message>, Integer>() {
            @Override
            protected List<Message> doInBackground() throws Exception {

                List<Message> _retrievedMessages = new ArrayList<>();

                int count = 0;

                for (Message message : _messageServer) {

                    if (isCancelled()) break;

                    System.out.println("Message title: " + message.getTitle());

                    _retrievedMessages.add(message);

                    count++;

                    publish(count);

                }

                return _retrievedMessages;
            }

            @Override
            protected void process(List<Integer> counts) {
                int retrieved = counts.get(counts.size() - 1);
                _progressDialog.setValue(retrieved);
            }

            @Override
            protected void done() {

                _progressDialog.setVisible(false);

                if (isCancelled()) return;

                try {
                    List<Message> _retrievedMessages = get();

                    _defaultListModel.removeAllElements();

                    for(Message message : _retrievedMessages) {
                        _defaultListModel.addElement(message);

                    }

                    _messageList.setSelectedIndex(0);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }


            }
        };

        _worker.execute();

    }

    @Override
    public void progressDialogCancelled() {
        if (_worker != null) {
            _worker.cancel(true);
        }
    }

    public void refresh() {
        retrieveMessages();
    }
}

