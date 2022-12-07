package org.message_receiver.views;

import org.message_receiver.controllers.Controller;
import org.message_receiver.models.PersonFileFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MainFrame extends JFrame {

    private final FormPanel _formPanel;
    private final JFileChooser _fileChooser;
    private final Controller _controller;
    private final TablePanel _tablePanel;

    public MainFrame()
    {
        super("Message Receiver");

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new BorderLayout());

        Toolbar _toolbar = new Toolbar();
        TextPanel _textPanel = new TextPanel();
        _formPanel = new FormPanel();
        _fileChooser = new JFileChooser();
        _controller = new Controller();
        _tablePanel = new TablePanel();

        _fileChooser.addChoosableFileFilter(new PersonFileFilter());

        setJMenuBar(createMenuBar());

        add(_toolbar, BorderLayout.PAGE_START);
        add(_tablePanel, BorderLayout.CENTER);
        add(_formPanel, BorderLayout.LINE_START);

        _toolbar.setStringListener(_textPanel::appendText);

        _formPanel.setFormListener(e -> {
            _controller.addPerson(e);
            _tablePanel.refresh();
        });

        _tablePanel.setData(_controller.getPeople());
    }

    public JMenuBar createMenuBar() {
        JMenuBar _menuBar = new JMenuBar();

        JMenu _fileMenu = new JMenu("File");
        JMenu _windowMenu = new JMenu("Window");

        JMenuItem _exportDataItem = new JMenuItem("Export Data...");
        JMenuItem _importDataItem = new JMenuItem("Import Data...");
        JMenuItem _exitItem = new JMenuItem("Exit");

        _fileMenu.add(_exportDataItem);
        _fileMenu.add(_importDataItem);
        _fileMenu.addSeparator();
        _fileMenu.add(_exitItem);

        JMenu _showMenu = new JMenu("Show");
        _windowMenu.add(_showMenu);

//        JMenuItem _showFormItem = new JMenuItem("Person Form");
        JCheckBoxMenuItem _showFormItem = new JCheckBoxMenuItem("Person Form");
        _showFormItem.setSelected(true);

        _showFormItem.addActionListener(e -> {
            JCheckBoxMenuItem _menuItem = (JCheckBoxMenuItem)e.getSource();

            _formPanel.setVisible(_menuItem.isSelected());
        });

        _showMenu.add(_showFormItem);

        _menuBar.add(_fileMenu);
        _menuBar.add(_windowMenu);

        _fileMenu.setMnemonic(KeyEvent.VK_F);
        _exitItem.setMnemonic(KeyEvent.VK_X);

        // accelerator
        _exportDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        _importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        _exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));

        _importDataItem.addActionListener(e -> {
            if (_fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {

                try {
                    _controller.loadFromFile(_fileChooser.getSelectedFile());
                    _tablePanel.refresh();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Could not load file.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        _exportDataItem.addActionListener(e -> {
            if (_fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {

                try {
                    _controller.saveToFile(_fileChooser.getSelectedFile());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Could not save to file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        _exitItem.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(MainFrame.this,
                    "Do you really want to exit?", "Confirm Exit",
                    JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });

        return _menuBar;
    }
}
