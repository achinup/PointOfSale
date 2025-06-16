package sale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class oneitem {

private Sale sale;
private Display display;
    @Before

    public void setUp() throws  Exception{
        display=new Display();
        sale=new Sale(display,new HashMap<String,String>(){{
            put("12345","$7.44");
            put("23456","$5.44");
        }});
    }

    @Test

    public void t1() throws  Exception {

        sale.onBarcode("12345");
        assertEquals("$7.44",display.getText());
    }

    @Test

    public void t2() throws Exception{

        sale.onBarcode("23456");
        assertEquals("$5.44",display.getText());
    }

    @Test
    public  void t3() throws Exception{

        sale.onBarcode("9999");
        assertEquals("Product not found for 9999",display.getText());
    }

    @Test
    public void t4() throws  Exception{

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
        public Sale(Display display,Map pro) {
            this.display = display;
            this.pro=pro;
        }

        public void onBarcode(String barcode)
        {


            if(isNull(barcode))
            {
                displayNull();
                return ;
            }


            if (hasBarCode(barcode))

                    displayBarCode(barcode);

            else
                    displayProductNotFound(barcode);


        }

        private void displayNull() {
            display.setText("Scanning error:empty barcode");
        }

        private static boolean isNull(String barcode) {
            return "".equals(barcode);
        }

        private void displayProductNotFound(String barcode) {
            display.setText("Product not found for "+ barcode);
        }

        private void displayBarCode(String barcode) {
            display.setText(pro.get(barcode));
        }

        private boolean hasBarCode(String barcode) {
            return pro.containsKey(barcode);
        }
    }
}
