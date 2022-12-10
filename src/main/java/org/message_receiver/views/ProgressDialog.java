package org.message_receiver.views;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JDialog {

    public ProgressDialog(Window parent) {
        super(parent, "Message Downloading...",ModalityType.APPLICATION_MODAL);

        setSize(400, 200);
    }
}
