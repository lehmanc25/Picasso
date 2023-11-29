public class AbsValue extends UnaryFunction {

    /**
     * Constructor for AbsValue.
     * @param param The expression node that this function will be applied to.
     */
    public AbsValue(ExpressionTreeNode param) {
        super(param);
    }

    /**
     * Evaluates the absolute value function on the parameter.
     * @param x The x-coordinate at which to evaluate the function.
     * @param y The y-coordinate at which to evaluate the function.
     * @return The result of applying the absolute value function to the parameter.
     */
    @Override
    public RGBColor evaluate(double x, double y) {
        // First, evaluate the parameter (expression node) at the given x and y
        RGBColor color = param.evaluate(x, y);

        // Apply the absolute value function to each component of the RGBColor
        double red = Math.abs(color.getRed());
        double green = Math.abs(color.getGreen());
        double blue = Math.abs(color.getBlue());

        // Return the new RGBColor with the absolute values
        return new RGBColor(red, green, blue);
    }
}
