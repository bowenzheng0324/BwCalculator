package com.example.bwcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btone;
    private Button bttwo;
    private Button btthree;
    private Button btfour;
    private Button btfive;
    private Button btsix;
    private Button btseven;
    private Button bteight;
    private Button btnine;
    private Button btzero;
    private Button btclear;
    private Button btequal;
    private Button btlfbkt;
    private Button btrgbkt;
    private Button btadd;
    private Button btminus;
    private Button btdivide;
    private Button btmultiply;
    private Button btpoint;
    private EditText editText;

    String Formula="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btone=(Button)findViewById(R.id.one);
        bttwo=(Button)findViewById(R.id.two);
        btthree=(Button)findViewById(R.id.three);
        btfour=(Button)findViewById(R.id.four);
        btfive=(Button)findViewById(R.id.five);
        btsix=(Button)findViewById(R.id.six);
        btseven=(Button)findViewById(R.id.seven);
        bteight=(Button)findViewById(R.id.eight);
        btnine=(Button)findViewById(R.id.nine);
        btzero=(Button)findViewById(R.id.zero);
        btpoint=(Button)findViewById(R.id.point);
        btadd=(Button)findViewById(R.id.add);
        btminus=(Button)findViewById(R.id.minus);
        btmultiply=(Button)findViewById(R.id.multiply);
        btdivide=(Button)findViewById(R.id.divide);
        btequal=(Button)findViewById(R.id.equal_sign);
        btclear=(Button)findViewById(R.id.clear);
        btlfbkt=(Button)findViewById(R.id.left_bracket);
        btrgbkt=(Button)findViewById(R.id.right_bracket);
        editText=(EditText)findViewById(R.id.formula);

        btone.setOnClickListener(new Click());
        bttwo.setOnClickListener(new Click());
        btthree.setOnClickListener(new Click());
        btfour.setOnClickListener(new Click());
        btfive.setOnClickListener(new Click());
        btsix.setOnClickListener(new Click());
        btseven.setOnClickListener(new Click());
        bteight.setOnClickListener(new Click());
        btnine.setOnClickListener(new Click());
        btzero.setOnClickListener(new Click());
        btclear.setOnClickListener(new Click());
        btequal.setOnClickListener(new Click());
        btadd.setOnClickListener(new Click());
        btminus.setOnClickListener(new Click());
        btdivide.setOnClickListener(new Click());
        btmultiply.setOnClickListener(new Click());
        btpoint.setOnClickListener(new Click());
        btlfbkt.setOnClickListener(new Click());
        btrgbkt.setOnClickListener(new Click());

    }
    class Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.one:
                    Formula +="1";
                    editText.setText(Formula);
                    break;
                case R.id.two:
                    Formula +="2";
                    editText.setText(Formula);
                    break;
                case R.id.three:
                    Formula +="3";
                    editText.setText(Formula);
                    break;
                case R.id.four:
                    Formula +="4";
                    editText.setText(Formula);
                    break;
                case R.id.five:
                    Formula +="5";
                    editText.setText(Formula);
                    break;
                case R.id.six:
                    Formula +="6";
                    editText.setText(Formula);
                    break;
                case R.id.seven:
                    Formula +="7";
                    editText.setText(Formula);
                    break;
                case R.id.eight:
                    Formula +="8";
                    editText.setText(Formula);
                    break;
                case R.id.nine:
                    Formula +="9";
                    editText.setText(Formula);
                    break;
                case R.id.zero:
                    Formula +="0";
                    editText.setText(Formula);
                    break;
                case R.id.add:
                    Formula +=" + ";
                    editText.setText(Formula);
                    break;
                case R.id.minus:
                    Formula +=" - ";
                    editText.setText(Formula);
                    break;
                case R.id.multiply:
                    Formula +=" x ";
                    editText.setText(Formula);
                    break;
                case R.id.divide:
                    Formula +=" / ";
                    editText.setText(Formula);
                    break;
                case R.id.point:
                    Formula +=".";
                    editText.setText(Formula);
                    break;
                case R.id.left_bracket:
                    Formula +="( ";
                    editText.setText(Formula);
                    break;
                case R.id.right_bracket:
                    Formula +=" )";
                    editText.setText(Formula);
                    break;
                case R.id.equal_sign:
                    Formula+=" ";
                    getResult();
                    editText.setText(Formula);
                    break;
                case R.id.clear:
                    Formula ="";
                    editText.setText(Formula);
                    break;
            }
        }

        public boolean isOp(String str){
            return (str.equals("+"))||(str.equals("-"))||(str.equals("x"))||(str.equals("/"));
        }

        public void getResult(){
            if(Formula.equals(" ")||Formula.isEmpty()||Formula==null) {
                Formula="";
                return;
            }//狂按等号的情况

            //将Formula转化为逆波兰表达式
            List<String> s1=new ArrayList<String>();
            List<String> s2=new ArrayList<String>();
            String str;
            while(true){
                str=Formula.substring(0, Formula.indexOf(" "));//第一个元素
                Formula=Formula.substring(Formula.indexOf(" ")+1);
                if(str.equals("")&&!Formula.isEmpty()){
                    //开头一个符号的情况
                    str=Formula.substring(0, Formula.indexOf(" "));
                    if(str.equals("*")||str.equals("/")){
                        System.out.println("输入错误！");
                        return;
                    }
                    Formula=Formula.substring(Formula.indexOf(" ")+1);
                    str+=Formula.substring(0, Formula.indexOf(" "));
                }
                if(str.equals(" "))continue;
                if(!isOp(str)){
                    if(str.equals("(")){
                        s2.add("(");
                    }else if(str.equals(")")){
                        while(!s2.get(s2.size()-1).equals("(")){
                            s1.add(s2.get(s2.size()-1));
                            s2.remove(s2.size()-1);
                        }
                        s2.remove(s2.size()-1);//去掉左括号
                    }else{
                        s1.add(str);
                    }
                }else{
                    if(s2.isEmpty()){
                        s2.add(str);
                    }else if(isOp(s2.get(s2.size()-1))) {
                        //前面是运算符，判断优先级。
                        if(str.equals("x")||str.equals("/")){
                            if (s2.get(s2.size() - 1).equals("x") || s2.get(s2.size() - 1).equals("/")) {
                                s1.add(s2.get(s2.size() - 1));
                                s2.remove(s2.size() - 1);
                            }
                            s2.add(str);
                        } else{
                            s1.add(s2.get(s2.size()-1));
                            s2.remove(s2.size()-1);
                            s2.add(str);
                        }
                    }else{
                        s2.add(str);//前面是左括号直接压栈。
                    }
                }
                if(Formula.isEmpty()){
                    while (!s2.isEmpty()){
                        s1.add(s2.get(s2.size()-1));
                        s2.remove(s2.size()-1);
                    }
                    break;
                }
            }



            for(int i=0;i<s1.size();i++){
                Toast.makeText(getApplicationContext(),s1.get(i),Toast.LENGTH_SHORT).show();
            }



            //计算后缀表达式（逆波兰表达式）s1
            List<Double> result=new ArrayList<Double>();
            double a,b,c,t;
            for(int i=0;i<s1.size();i++){
                if(!isOp(s1.get(i))){
                    t=Double.parseDouble(s1.get(i));
                    result.add(t);
                }else {
                    switch (s1.get(i)){
                        case "+":
                            a=result.remove(result.size()-1);
                            b=result.remove(result.size()-1);
                            result.add(a+b);
                            break;
                        case "-":
                            a=result.remove(result.size()-1);
                            b=result.remove(result.size()-1);
                            result.add(b-a);
                            break;
                        case "x":
                            a=result.remove(result.size()-1);
                            b=result.remove(result.size()-1);
                            result.add(a*b);
                            break;
                        case "/":
                            a=result.remove(result.size()-1);
                            b=result.remove(result.size()-1);
                            result.add(b/a);
                            break;
                    }
                }
            }
            Formula=result.get(0).toString();
            editText.setText(Formula);

        }

    }

}
