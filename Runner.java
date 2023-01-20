
public class Runner {
    public static void main(String[] args) {
        try {
            System.out.println(LexicalAnalyser.analyse(".1"));
        }
        catch (NumberException e) {

        }
        catch (ExpressionException e) {

        }
    }
}
