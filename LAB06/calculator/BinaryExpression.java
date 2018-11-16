// import com.sun.javafx.fxml.expression.Expression;

public abstract class BinaryExpression implements Expression {

    private final Expression lft;
    private final Expression rht;
    private String operator;
 
    public BinaryExpression(final Expression lft, final Expression rht, String operator) {
       this.lft = lft;
       this.rht = rht;
       this.operator = operator;
    }

    public String toString() {
        return "(" + this.lft + " " + this.operator + " " + this.rht + ")";
    }

    public double evaluate(final Bindings bindings) {
        return _applyOperator(lft.evaluate(bindings), rht.evaluate(bindings));
    }

    protected abstract double _applyOperator(double l, double r);

}