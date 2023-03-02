package fr.ensicaen.lv223.model.agent.robot.message;
public class Message implements Comparable<Message> {
    private int value;
    private MessageType type;

    public Message(){

    }

    public Message(MessageType type, int value){
        this.type = type;
        this.value = value;
    }

    @Override
    public int compareTo(Message o) {
        return this.value - o.value;
    }

    public MessageType getType(){
        return type;
    }
}
