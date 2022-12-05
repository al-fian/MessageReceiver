package org.message_receiver.views;

import java.util.EventObject;

public class FormEvent extends EventObject {

    private String _name;
    private String _job;

    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String name, String job) {
        super(source);

        _name = name;
        _job = job;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getJob() {
        return _job;
    }

    public void setJob(String job) {
        _job = job;
    }
}
