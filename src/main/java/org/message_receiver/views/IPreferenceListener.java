package org.message_receiver.views;

public interface IPreferenceListener {
    void preferenceSet(String user, String password, int port);
}
