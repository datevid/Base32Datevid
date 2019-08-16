/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.datevid.base32datevid.test;

import static net.datevid.base32datevid.Base32.decode;
import static net.datevid.base32datevid.Base32.encode;

/**
 *
 * @author @datevid
 */
public class TestBase32Datevid {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String secret = "secret";

        Long num1=0L;
        Long num2=2865L;
        Long num3=32L;
        Long num4=63L;
        Long num5=64L;
        Long num6=3428631L;
        Long num7=3428632L;
        Long num8= 0L;//min value long
        Long num9= 9223372036854775807L;//max value long
        Long num10=999999999993428696L;
        
        System.out.println(num1+"->"+encode(num1)+"->"+decode(encode(num1).toLowerCase()));
        System.out.println(num2+"->"+encode(num2)+"->"+decode(encode(num2).toLowerCase()));
        System.out.println(num3+"->"+encode(num3)+"->"+decode(encode(num3).toLowerCase()));
        System.out.println(num4+"->"+encode(num4)+"->"+decode(encode(num4).toLowerCase()));
        System.out.println(num5+"->"+encode(num5)+"->"+decode(encode(num5).toLowerCase()));
        System.out.println(num6+"->"+encode(num6)+"->"+decode(encode(num6).toLowerCase()));
        System.out.println(num7+"->"+encode(num7)+"->"+decode(encode(num7).toLowerCase()));
        System.out.println(num8+"->"+encode(num8)+"->"+decode(encode(num8).toLowerCase()));
        System.out.println(num9+"->"+encode(num9)+"->"+decode(encode(num9).toLowerCase()));
        System.out.println(num10+"->"+encode(num10)+"->"+decode(encode(num10)));
        System.out.println(num1+"->"+encode(num1,secret)+"->"+decode(encode(num1,secret),secret));
        System.out.println(num2+"->"+encode(num2,secret)+"->"+decode(encode(num2,secret),secret));
        System.out.println(num3+"->"+encode(num3,secret)+"->"+decode(encode(num3,secret),secret));
        System.out.println(num4+"->"+encode(num4,secret)+"->"+decode(encode(num4,secret),secret));
        System.out.println(num5+"->"+encode(num5,secret)+"->"+decode(encode(num5,secret),secret));
        System.out.println(num6+"->"+encode(num6,secret)+"->"+decode(encode(num6,secret),secret));
        System.out.println(num7+"->"+encode(num7,secret)+"->"+decode(encode(num7,secret),secret));
        System.out.println(num8+"->"+encode(num8,secret)+"->"+decode(encode(num8,secret),secret));
        System.out.println(num9+"->"+encode(num9,secret)+"->"+decode(encode(num9,secret),secret));
        System.out.println(num10+"->"+encode(num10,secret)+"->"+decode(encode(num10,secret),secret));
    }

}
