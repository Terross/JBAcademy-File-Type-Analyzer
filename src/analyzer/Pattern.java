package analyzer;

public class Pattern {
    private int priority;
    private String indication;
    private String answer;

    public Pattern(String data) {
        String[] subString = data.split(";");
        priority = Integer.parseInt(subString[0]);
        indication = subString[1].replaceAll("\"", "");
        answer = subString[2].replaceAll("\"", "");
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
