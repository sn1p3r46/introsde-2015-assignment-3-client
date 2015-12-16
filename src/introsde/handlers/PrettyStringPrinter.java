package introsde.handlers;

public class PrettyStringPrinter{
  public static String HEADER = "\n\t\t\t##############\n"+
                                  "\t\t\t## METHOD %s ##\n"+
                                  "\t\t\t##############\n";

  public static String FUNCTION = "\n\t ||  FUNCTION: %s  || \n";

  public static String stringFormatter(String s, Object o){
    return String.format(s,o);
  }
}
