package sale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class oneitem {


    @Before

//    public void setUp() throws  Exception{
//        display=new Display();
//    }

    @Test

    public void productfound() throws  Exception {
        final Display display=new Display();
        final  Sale sale=new Sale(display);
        sale.onBarcode("12345");
        assertEquals("$7.44",display.getText());
    }

    @Test

    public void t2() throws Exception{
        final Display display=new Display();
        final  Sale sale=new Sale(display);
        sale.onBarcode("23456");
        assertEquals("$5.44",display.getText());
    }

    @Test
    public  void t3() throws Exception{
        final Display display=new Display();
        final  Sale sale=new Sale(display);
        sale.onBarcode("9999");
        assertEquals("Product not found for 9999",display.getText());
    }

    @Test
    public void t4() throws  Exception{
        final Display display=new Display();
        final Sale sale=new Sale(display);
        sale.onBarcode("");
        assertEquals("Scanning error:empty barcode",display.getText());
    }






    public static class Display{
        private String text;
        public void setText(String text) {
            this.text = text;
        }
       Display()
       {
           System.out.println("d");
       }

        public String getText(){

            return text;
        }

    }
    public static class  Sale{
        private Display display;
        private Map<String,String> pro;
        public Sale(Display display) {
            this.display = display;
            this.pro=new HashMap<>(){{
                put("12345","$7.44");
                put("23456","$5.44");
            }};
        }

        public void onBarcode(String barcode)
        {


            if("".equals(barcode))
            {
                display.setText("Scanning error:empty barcode");
                return ;
            }







                if (pro.containsKey(barcode))

                   display.setText(pro.get(barcode));

                else
                    display.setText("Product not found for "+barcode);



        }
    }
}
