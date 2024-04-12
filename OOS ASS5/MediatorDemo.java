import java.util.ArrayList;
import java.util.List;

// Mediator interface
interface ChatroomMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

// Concrete Mediator
class Chatroom implements ChatroomMediator {
    private List<User> users;

    public Chatroom() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receiveMessage(message);
            }
        }
    }
}

// Colleague interface
abstract class User {
    protected ChatroomMediator mediator;

    public User(ChatroomMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void sendMessage(String message);

    public abstract void receiveMessage(String message);
}

// Concrete Colleague
class BasicUser extends User {
    private String name;

    public BasicUser(String name, ChatroomMediator mediator) {
        super(mediator);
        this.name = name;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(name + " sends message: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " receives message: " + message);
    }
}

public class MediatorDemo {
    public static void main(String[] args) {
        ChatroomMediator chatroom = new Chatroom();

        User user1 = new BasicUser("User1", chatroom);
        User user2 = new BasicUser("User2", chatroom);
        User user3 = new BasicUser("User3", chatroom);

        chatroom.addUser(user1);
        chatroom.addUser(user2);
        chatroom.addUser(user3);

        user1.sendMessage("Hello, everyone!");
        user2.sendMessage("Hi there!");
    }
}
