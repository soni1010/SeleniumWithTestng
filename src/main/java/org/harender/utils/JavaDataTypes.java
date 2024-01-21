package org.harender.utils;
public class JavaDataTypes {

    public static void main(String[] args) {
        /*


-------------------------------| Data Types in Java |------------------------------------------------------
                 -------------------------------------------------
               /                                                   \
Primitive Data Type |                                        |  Non-Primitive Data Type or Object Data type
---------------------                                        ----------------------------------------------
          |                                                         String , Array , etc..
 ________/
|- Boolean Type
|     |- boolean
|      \-: true or false | false | 1 bit | true, false | true, false
|- Numeric Type
    |- Character Value
    |     |- char
    |      \-:|Unicode character | \u0000 | 16 bits | ‘a’,‘\u0041’,‘\101’,‘\\’,‘\’,‘\n’,‘β'|
    |         | characters representation of ASCII values | 0 to 255|
    |
    |- Integral Value
          |
 ________/
|- Integer    ___________________________________________________________________________________________
|     /      | Def |   Size  | Example Literals |                     Range of values                   |
|  |"'       |-----|---------|------------------|-------------------------------------------------------|
|  |- byte :-|  0  |  8 bits |      (none)      |                      -128 to 127                      |
|  |- short:-|  0  | 16 bits |  	(none)	    |                   -32,768 to 32,767                   |
|  |- int  :-|  0  | 32 bits |    -2,-1,0,1,2   |            -2,147,483,648 to 2,147,483,647            |
|  |- long :-|  0  | 64 bits | -2L,-1L,0L,1L,2L |-9,223,372,036,854,775,808 to 9,223,372,036,854,775,807|
|  ------------------------------------------------------------------------------------------------------
|- Floating-Point
    /____________________________________________________________________________________________________
   |- float :-| 0.0 | 32 bits | 1.23e100f , -1.23e-100f , .3f ,3.14F |        upto 7 decimal digits     |
   |- double:-| 0.0	| 64 bits | 1.23456e300d , -123456e-300d , 1e1d  |       upto 16 decimal digits     |
   ------------------------------------------------------------------------------------------------------

 */

// Data Type :- https://www.geeksforgeeks.org/data-types-in-java/

        boolean boolVar=true;
        byte byteVar=-128;
        short shortVar=32767;
        int intVar=2147483647;
        long longVar=9223372036854775807l;
        float floatVar=1.233333310f;
        double doubleVar=1.2345630153456785904;
        char charVar='H';

        System.out.println("boolVar  : "+boolVar+
                         "\nbyteVar  : "+byteVar+
                         "\nshortVar : "+shortVar+
                         "\nintVar   : "+intVar+
                         "\nlongVar  : "+longVar+
                         "\nfloatVar : "+floatVar+
                         "\ndoubleVar: "+doubleVar+
                         "\ncharVar  : "+charVar
                );
/*                                  -------------------------------------------------
                                   | Non-Primitive Data Type or Reference Data Types |
                                    -------------------------------------------------
The Reference Data Types will contain a memory address of variable values because the reference types won’t store the variable
value directly in memory. They are strings, objects, arrays, etc.

1- Strings : are defined as an array of characters. The difference between a character array and a string in Java is, that the string
     is designed to hold a sequence of characters in a single variable whereas, a character array is a collection of separate char-type
     entities. Unlike C/C++, Java strings are not terminated with a null character.
     Syntax: Declaring a string
     <String_Type> <string_variable> = “<sequence_of_string>”;

2. Class     : https://www.geeksforgeeks.org/data-types-in-java/
3. Object
4. Interface
5. Array

 */



    }

}
