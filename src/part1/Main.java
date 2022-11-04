package part1;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Area<Circle> circleArea = (x) -> (float) (Math.PI * Math.pow(x.getRadius(), 2)); //x e Circle
        Area<Square> squareArea = (x) -> (float) Math.pow(x.getSide(), 2); //x e Square

        Circle circle = new Circle(3);
        Circle circle2 = new Circle(1);

        Square square = new Square(5.5f);
        Square square2 = new Square(2);

        List<Circle> circles = Arrays.asList(circle, circle2);
        List<Square> squares = Arrays.asList(square, square2);

        AreaHelper.printArea(circles, circleArea);
        AreaHelper.printArea(squares, squareArea);

        Messages();
        function();
        streams();
    }

    public static void Messages() {
        Message message1 = new Message("hello1", "mircea", "fenesan", LocalDateTime.now());
        Message message2 = new Message("Hello", "GordonFreeman", "fenesan", LocalDateTime.now());
        Message message3 = new Message("Heyo", "mircea", "fenesan", LocalDateTime.now());
        Message message4 = new Message("Yellow", "mircea", "fenesan", LocalDateTime.now());
        Message message5 = new Message("Ayo", "mircea", "fenesan", LocalDateTime.now());

        LocalDateTime now = LocalDateTime.now();

        List<Message> messages = Arrays.asList(message1, message2, message3, message4, message5);

        //filter by subject
        System.out.println("Filter by Subject");
        PredicateHelper predicateHelperSubject = new PredicateHelper();
        Predicate<Message> messagePredicateSubject = (message -> Objects.equals(message.getMessage().toLowerCase(), "hello1"));
        Iterable<Message> filterListBySubject = predicateHelperSubject.filter(messages, messagePredicateSubject);
        filterListBySubject.forEach(System.out::println);

        //filterBySender
        System.out.println("Filter by Sender");
        PredicateHelper predicateHelperBySender = new PredicateHelper();
        Predicate<Message> messagePredicateSender = (message -> Objects.equals(message.getFrom(), "GordonFreeman"));
        Iterable<Message> filterListBySender = predicateHelperBySender.filter(messages, messagePredicateSender);
        filterListBySender.forEach(System.out::println);

        //filterByDate&Sender
        System.out.println("Filter by Date and Sender");
        PredicateHelper predicateHelperByDateSender = new PredicateHelper();
        Predicate<Message> messagePredicateDate= message -> message.getDate().compareTo(LocalDateTime.now()) >= 0;
        Predicate<Message> messagePredicateDateSender= messagePredicateSender.and(messagePredicateDate);
        Iterable<Message> filterBySenderDate = predicateHelperByDateSender.filter(messages, messagePredicateDateSender);
        filterBySenderDate.forEach(System.out::println);
    }

    public static void function(){
        Function<String, Integer> converterLambda = x->Integer.valueOf(x);
        Function<String, Integer> converterMethodReference = Integer::valueOf;

        System.out.println(converterLambda.apply("12"));
        System.out.println(converterMethodReference.apply("12"));
    }

    public static void streams(){
        List<String> list = Arrays.asList("abc", "cda", "bad", "bbb");
        String res = list.stream()
                .filter(x -> x.startsWith("b"))
                .map(String::toUpperCase)
                .reduce("", (x,y)->x+y);
        System.out.println(res);
    }
}

