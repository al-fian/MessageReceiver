package org.message_receiver.views;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ServerTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {

    private final ServerTreeCellRenderer _serverTreeCellRenderer;
    private JCheckBox _checkBox;
    private ServerInfo _serverInfo;
    public ServerTreeCellEditor() {

        _serverTreeCellRenderer = new ServerTreeCellRenderer();

    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected,
                                                boolean expanded, boolean leaf, int row) {

        Component component = _serverTreeCellRenderer.getTreeCellRendererComponent(tree,value,
                isSelected, expanded, leaf, row, true);

        if (leaf) {
            DefaultMutableTreeNode _treeNode = (DefaultMutableTreeNode)value;
            _serverInfo = (ServerInfo) _treeNode.getUserObject();

            _checkBox = (JCheckBox)component;

            ItemListener _itemListener = new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    fireEditingStopped();
                    _checkBox.removeItemListener(this);
                }
            };

            _checkBox.addItemListener(_itemListener);
        }

        return component;
    }

    @Override
    public Object getCellEditorValue() {
        _serverInfo.setChecked(_checkBox.isSelected());
        return _serverInfo;
    }

    @Override
    public boolean isCellEditable(EventObject e) {

        if (!(e instanceof MouseEvent _mouseEvent)) return false;

        JTree _tree = (JTree)e.getSource();
        TreePath _treePath = _tree.getPathForLocation(_mouseEvent.getX(),_mouseEvent.getY());

        if (_treePath == null) return false;

        Object _lastPathComponent = _treePath.getLastPathComponent();

        if (_lastPathComponent == null) return false;

        DefaultMutableTreeNode _treeNode = (DefaultMutableTreeNode) _lastPathComponent;

        return _treeNode.isLeaf();
    }
}
