package analyzer;

public class Context {
    Algorithm algorithm;

    public Context(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public boolean execute(String text, String pattern) {
        System.out.println(text);
        System.out.println(pattern);
        return algorithm.execute(text, pattern);
    }
}
