package org.message_receiver.views;

import java.util.EventListener;

public interface IFormListener extends EventListener {
    void formEventOccurred(FormEvent formEvent);
}
