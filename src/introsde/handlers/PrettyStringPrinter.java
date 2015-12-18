package introsde.handlers;

// simple helper to make strings more Pretty :)

public class PrettyStringPrinter{
  public static String HEADER = "\n############################################" +
                              "\n\n\t\t\t##############\n"+
                                  "\t\t\t## METHOD %s ##\n"+
                                  "\t\t\t##############";

  public static String FUNCTION = "\n\t ||  FUNCTION: %s  || \n";

  public static String stringFormatter(String s, Object o){
    return String.format(s,o);
  }
}
