package org.message_receiver.models;

public class Message {

    private String _title;
    private String _contents;

    public Message(String title, String contents) {
        _title = title;
        _contents = contents;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getContents() {
        return _contents;
    }

    public void setContents(String contents) {
        _contents = contents;
    }
}
