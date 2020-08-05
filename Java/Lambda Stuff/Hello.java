import java.util.function.Consumer;

public class Hello {

    public static void main(String[] args) {

        Consumer<String> printer = System.out::println;
        printer.accept( "hello world" );
    }
}