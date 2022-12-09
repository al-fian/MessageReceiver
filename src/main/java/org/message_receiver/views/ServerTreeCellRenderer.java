package org.message_receiver.views;

import org.message_receiver.models.Utils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class ServerTreeCellRenderer implements TreeCellRenderer {

    private final JCheckBox _leafRenderer;
    private final DefaultTreeCellRenderer _nonLeafRenderer;

    private Color _textForeground;
    private Color _textBackground;
    private Color _selectionForeground;
    private Color _selectionBackground;

    public ServerTreeCellRenderer() {
        _leafRenderer = new JCheckBox();
        _nonLeafRenderer = new DefaultTreeCellRenderer();

        _nonLeafRenderer.setLeafIcon(Utils.createIcon("/images/Server16.gif"));
        _nonLeafRenderer.setOpenIcon(Utils.createIcon("/images/WebComponent16.gif"));
        _nonLeafRenderer.setClosedIcon(Utils.createIcon("/images/WebComponentAdd16.gif"));

        _textForeground = UIManager.getColor("Tree.textForeground");
        _textBackground = UIManager.getColor("Tree.textBackground");
        _selectionForeground = UIManager.getColor("Tree.selectionForeground");
        _selectionBackground = UIManager.getColor("Tree.selectionBackground");
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                  boolean expanded, boolean leaf, int row,
                                                  boolean hasFocus) {
        if (leaf) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            ServerInfo nodeInfo = (ServerInfo)node.getUserObject();

            if (selected) {
                _leafRenderer.setForeground(_selectionForeground);
                _leafRenderer.setBackground(_selectionBackground);
            }
            else {
                _leafRenderer.setForeground(_textForeground);
                _leafRenderer.setBackground(_textBackground);
            }

            _leafRenderer.setText(nodeInfo.toString());
            _leafRenderer.setSelected(nodeInfo.isChecked());

            return _leafRenderer;
        }
        else {
            return _nonLeafRenderer.getTreeCellRendererComponent(tree, value, selected,
                    expanded,false, row, hasFocus);
        }
    }
}
