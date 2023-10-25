package utils;

import java.util.ArrayList;
import java.util.List;

public class ValuesCheck {
    private float xRangeStartValue;
    private float xRangeEndValue;
    private float yRangeStartValue;
    private float yRangeEndValue;
    private float rRangeStartValue;
    private float rRangeEndValue;

    public ValuesCheck(float xRangeStartValue, float xRangeEndValue, float yRangeStartValue, float yRangeEndValue,
                       float rRangeStartValue, float rRangeEndValue) {
        this.xRangeStartValue = xRangeStartValue;
        this.xRangeEndValue = xRangeEndValue;
        this.yRangeStartValue = yRangeStartValue;
        this.yRangeEndValue = yRangeEndValue;
        this.rRangeStartValue = rRangeStartValue;
        this.rRangeEndValue = rRangeEndValue;
    }

    public ValuesCheck() {
        this(-2, 2, -5, 3, 1, 3);
    }
    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    public boolean xyrCheck(String xStr, String yStr, String rStr) {
        if (isFloat(xStr) && isFloat(yStr) && isFloat(rStr)) {
            float xVal = Float.parseFloat(xStr);
            float yVal = Float.parseFloat(yStr);
            float rVal = Float.parseFloat(rStr);
            List<Float> rAvailableValues = new ArrayList<>();
            for (float i = rRangeStartValue; i <= rRangeEndValue; i = i + (float) 0.5) {
                rAvailableValues.add(i);
            }
            if (xRangeStartValue <= xVal && xVal <= xRangeEndValue && yRangeStartValue < yVal && yVal < yRangeEndValue && rAvailableValues.contains(rVal)) {
                return true;
            }
        }
        return false;
    }

    public float getxRangeStartValue() {
        return xRangeStartValue;
    }

    public void setxRangeStartValue(float xRangeStartValue) {
        this.xRangeStartValue = xRangeStartValue;
    }

    public float getxRangeEndValue() {
        return xRangeEndValue;
    }

    public void setxRangeEndValue(float xRangeEndValue) {
        this.xRangeEndValue = xRangeEndValue;
    }

    public float getyRangeStartValue() {
        return yRangeStartValue;
    }

    public void setyRangeStartValue(float yRangeStartValue) {
        this.yRangeStartValue = yRangeStartValue;
    }

    public float getyRangeEndValue() {
        return yRangeEndValue;
    }

    public void setyRangeEndValue(float yRangeEndValue) {
        this.yRangeEndValue = yRangeEndValue;
    }

    public float getrRangeStartValue() {
        return rRangeStartValue;
    }

    public void setrRangeStartValue(float rRangeStartValue) {
        this.rRangeStartValue = rRangeStartValue;
    }

    public float getrRangeEndValue() {
        return rRangeEndValue;
    }

    public void setrRangeEndValue(float rRangeEndValue) {
        this.rRangeEndValue = rRangeEndValue;
    }
}
