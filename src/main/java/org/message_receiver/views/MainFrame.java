package org.message_receiver.views;

import org.message_receiver.controllers.Controller;
import org.message_receiver.models.PersonFileFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class MainFrame extends JFrame {

    private final FormPanel _formPanel;
    private final JFileChooser _fileChooser;
    private final Controller _controller;
    private final TablePanel _tablePanel;
    private final PreferenceDialog _preferenceDialog;
    private final Preferences _preferenceProperties;
    private final JSplitPane _splitPane;

    public MainFrame()
    {
        super("Message Receiver");

        // handle window closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                _controller.disconnect();
                dispose();
                System.gc();
            }
        });

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);

        setLayout(new BorderLayout());

        Toolbar _toolbar = new Toolbar();
        TextPanel _textPanel = new TextPanel();
        _formPanel = new FormPanel();
        _fileChooser = new JFileChooser();
        _controller = new Controller();
        _tablePanel = new TablePanel();
        _preferenceDialog = new PreferenceDialog(this);

        _splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, _formPanel, _tablePanel);
        _splitPane.setOneTouchExpandable(true);

        _preferenceProperties = Preferences.userRoot().node("db");

        _fileChooser.addChoosableFileFilter(new PersonFileFilter());

        setJMenuBar(createMenuBar());

        add(_toolbar, BorderLayout.PAGE_START);
        add(_splitPane, BorderLayout.CENTER);

        _toolbar.setToolbarListener(new IToolbarListener() {
            @Override
            public void saveEventOccurred() {
                connect();

                try {
                    _controller.save();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Error Message","Could not save to database.",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

            @Override
            public void refreshEventOccurred() {
                connect();

                try {
                    _controller.load();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Error Message","Unable to load from database.",
                            JOptionPane.ERROR_MESSAGE);
                }

                _tablePanel.refresh();
            }
        });

        _formPanel.setFormListener(e -> {
            _controller.addPerson(e);
            _tablePanel.refresh();
        });

        _tablePanel.setData(_controller.getPeople());

        _tablePanel.setPersonTableListener(row -> {
            _controller.removePerson(row);
        });

        _preferenceDialog.setPreferenceListener((user, password, port) -> {

            _preferenceProperties.put("user", user);
            _preferenceProperties.put("password", password);
            _preferenceProperties.putInt("port", port);

        });

        String user = _preferenceProperties.get("user", "");
        String password = _preferenceProperties.get("password", "");
        Integer port = _preferenceProperties.getInt("port", 3306);
        _preferenceDialog.setDefaults(user, password, port);
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
        JMenuItem _preferenceMenuItem = new JMenuItem("Preferences...");

        _windowMenu.add(_showMenu);
        _windowMenu.add(_preferenceMenuItem);

        JCheckBoxMenuItem _showFormItem = new JCheckBoxMenuItem("Person Form");
        _showFormItem.setSelected(true);

        _preferenceMenuItem.addActionListener(e -> {
            _preferenceDialog.setVisible(true);
        });

        _showFormItem.addActionListener(e -> {
            JCheckBoxMenuItem _menuItem = (JCheckBoxMenuItem)e.getSource();

            if (_menuItem.isSelected()) {
                _splitPane.setDividerLocation((int)_formPanel.getMinimumSize().getWidth());
            }

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
        _preferenceMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
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
                WindowListener[] _windowListeners = getWindowListeners();

                for(WindowListener windowListeners : _windowListeners) {
                    windowListeners.windowClosing(new WindowEvent(MainFrame.this, 0));
                }
            }
        });

        return _menuBar;
    }

    private void connect() {
        try {
            _controller.connect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(MainFrame.this,
                    "Error Message","Could not connect to database.",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
