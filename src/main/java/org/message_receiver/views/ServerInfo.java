package org.message_receiver.views;

public class ServerInfo {
    private final String _name;
    private final int _id;
    private boolean _checked;

    public ServerInfo(String name, int id, boolean checked) {
        _name = name;
        _id = id;
        _checked = checked;
    }

    public int getId() {
        return _id;
    }

    public boolean isChecked() {
        return _checked;
    }

    public void setChecked(boolean checked) {
        _checked = checked;
    }

    @Override
    public String toString() {
        return _name;
    }
}
