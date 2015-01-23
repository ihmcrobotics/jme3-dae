package jme3dae.utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

import jme3dae.transformers.ValueTransformer;

/**
 * Transforms a sequence of white space separated int strings into an array
 * of int values
 * @author pgi
 */
public class IntegerListTransformer implements ValueTransformer<String, int[]>
{
   /**
    * Instance creator
    * @return a new IntegerListTransformer
    */
   public static IntegerListTransformer create()
   {
      return new IntegerListTransformer();
   }

   private IntegerListTransformer()
   {
   }

   /**
    * Transforms a string in a sequence of integers
    * @param value the string to transform
    * @return an array of int or an undefined value if parsing fails.
    */
   public TransformedValue<int[]> transform(String value)
   {
      int[] result = null;
      value = value.trim().replace('\n', ' ');
      String[] tokens = value.trim().split(" ");
      if (tokens.length > 0)
      {
         result = new int[tokens.length];

         try
         {
            for (int i = 0; i < tokens.length; i++)
            {
               String token = tokens[i];
               result[i] = Integer.parseInt(token);
            }
         }
         catch (NumberFormatException ex)
         {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "", ex);
            result = null;
         }
      }

      return TransformedValue.create(result);
   }
}
