public class Hello
{
    public static void main(String[] args)
    {
        System.out.println("Hello World");
        System.out.println("Number of args: " + args.length );
        if( args.length > 0 )
        {
            System.out.println("Arg[0] = " + args[0] );
        }
    }
}