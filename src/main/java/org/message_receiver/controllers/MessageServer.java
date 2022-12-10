package org.message_receiver.controllers;

import org.message_receiver.models.Message;

import java.util.*;
import java.util.function.Consumer;

public class MessageServer implements Iterable<Message>{

    private Map<Integer, List<Message>> _messages;
    private List<Message> _selected;

    public MessageServer() {

        _selected = new ArrayList<>();
        _messages = new TreeMap<>();

        List<Message> _list = new ArrayList<>();
        _list.add(new Message("The cat is missing", "Have you seen Felix?"));
        _list.add(new Message("See you later", "Are we still meeting in the pub?"));
        _list.add(new Message("Watching a film tonight?", "Pick your best film and watch it with me tonight."));
        _messages.put(1, _list);

        _list = new ArrayList<>();
        _list.add(new Message("Dinner tonight", "Bring your own wine. Let me cook the meal."));
        _messages.put(2, _list);

        _list = new ArrayList<>();
        _list.add(new Message("Dinner tonight", "Bring your own wine. Let me cook the meal."));
        _messages.put(5, _list);

        _list = new ArrayList<>();
        _list.add(new Message("Watching football group", "Andy and Chung will also come over. Bring your own beers."));
        _list.add(new Message("Shuffling snow rotation", "Jane, it's your turn today. The shuffle is in the backyard, near the door."));
        _messages.put(4, _list);
    }

    public void setSelectedServers(Set<Integer> servers) {

        _selected.clear();

        for(Integer id : servers) {
            if (_messages.containsKey(id)) {
                _selected.addAll(_messages.get(id));
            }
        }
    }

    public int getMessageCount() {
        return _selected.size();
    }

    @Override
    public Iterator<Message> iterator() {
        return new MessageIterator(_selected);
    }

}

class MessageIterator implements Iterator {

    private Iterator<Message> _iterator;
    public MessageIterator(List<Message> messages) {
        _iterator = messages.iterator();
    }

    @Override
    public boolean hasNext() {
        return _iterator.hasNext();
    }

    @Override
    public Object next() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        return _iterator.next();
    }

    @Override
    public void remove() {
        _iterator.remove();
    }
}
