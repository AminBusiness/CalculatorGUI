package edu.ranken.aelhasan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.DoubleBinaryOperator;

public class Calculator
{
    private JTextField results;
    private JButton clearBtn;
    private JButton signBtn;
    private JButton percentBtn;
    private JButton divideBtn;
    private JButton sevenBtn;
    private JButton fourBtn;
    private JButton zeroBtn;
    private JButton eightBtn;
    private JButton fiveBtn;
    private JButton twoBtn;
    private JButton nineBtn;
    private JButton sixBtn;
    private JButton threeBtn;
    private JButton digitBtn;
    private JButton multiplyBtn;
    private JButton minusBtn;
    private JButton addBtn;
    private JButton equalBtn;
    private JButton oneBtn;
    private JPanel calculatorView;
    private Double leftoperand;
    private Double rightoperand;
    private Operation calcOperation;


    public Calculator()
    {
        sevenBtn.addActionListener(new
                NumberBtnClicked(sevenBtn.getText()));
        eightBtn.addActionListener(new
                NumberBtnClicked(eightBtn.getText()));
        nineBtn.addActionListener(new
                NumberBtnClicked(nineBtn.getText()));
        fourBtn.addActionListener(new
                NumberBtnClicked(fourBtn.getText()));
        fiveBtn.addActionListener(new
                NumberBtnClicked(fiveBtn.getText()));
        sixBtn.addActionListener(new
                NumberBtnClicked(sixBtn.getText()));
        oneBtn.addActionListener(new
                NumberBtnClicked(oneBtn.getText()));
        twoBtn.addActionListener(new
                NumberBtnClicked(twoBtn.getText()));
        threeBtn.addActionListener(new
                NumberBtnClicked(threeBtn.getText()));
        zeroBtn.addActionListener(new
                NumberBtnClicked(zeroBtn.getText()));

        //OPEARATIONS
        percentBtn.addActionListener(new
                OperationBtnClicked(Operation.PERCENTAGE));
        multiplyBtn.addActionListener(new
                OperationBtnClicked(Operation.MULTIPLICATION));
        divideBtn.addActionListener(new
                OperationBtnClicked(Operation.DIVISION));
        minusBtn.addActionListener(new
                OperationBtnClicked(Operation.SUBTRACTION));
        addBtn.addActionListener(new
                OperationBtnClicked(Operation.ADDITION));


        equalBtn.addActionListener(new EqualBtnClicked());
        clearBtn.addActionListener(new ClearBtnClicked());
        signBtn.addActionListener(new SignBtnClicked());
        digitBtn.addActionListener(new DigitBtnClicked());
    }

    private class NumberBtnClicked implements ActionListener
    {
        private String value;

        public NumberBtnClicked(String value)
        {
            this.value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(leftoperand == null || leftoperand == 0.0)
            {
                value = results.getText() + value;
            } else
                {
                    rightoperand = Double.valueOf(value);
                }
                results.setText(value);
        }
    }

    private class OperationBtnClicked implements ActionListener
    {
        private Operation operation;

        public OperationBtnClicked(Operation operation)
        {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
           calcOperation = operation;
           leftoperand = Double.valueOf(results.getText());
        }
    }


    private class ClearBtnClicked implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            results.setText(" ");
            leftoperand = 0.0;
            rightoperand = 0.0;
        }
    }

    private class DigitBtnClicked implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
           results.setText(results.getText() + ". ");
        }
    }

    private class EqualBtnClicked implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Double output = calcOperation.getOperator().applyAsDouble(leftoperand, rightoperand);
        results.setText(output% 1 == 0? String.valueOf(output.intValue()): String.valueOf(output));
        leftoperand = 0.0;
        rightoperand = 0.0;
    }
}

    private class SignBtnClicked implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            results.setText("-"+ results.getText());
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().calculatorView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
//X is number one and y is number two
    public enum Operation
    {
        ADDITION((x, y) -> x+y), //So you can add numbers in the calculator
        SUBTRACTION((x, y) -> x-y), //So you can subtract numbers in the calculator
        DIVISION((x, y) -> x/y), //So you can divide numbers in the calculator
        MULTIPLICATION((x, y) -> x*y), //So you can multiply numbers in the calculator
        PERCENTAGE((x, y) -> x%y),; //So you can use percentages

        private DoubleBinaryOperator operator;

        Operation(DoubleBinaryOperator operator)
        {
            this.operator = operator;
        }

        public DoubleBinaryOperator getOperator()
        {
            return operator;
        }
    }
}
