package jme3dae.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jme3dae.transformers.ValueTransformer;

/**
 * Transformed a list of white space separated float strings in a float array
 * @author pgi
 */
public class FloatListTransformer implements ValueTransformer<String, float[]>
{
   /**
    * Instance creator
    * @return a new FloatListTransformer instance.
    */
   public static FloatListTransformer create()
   {
      return new FloatListTransformer();
   }

   private FloatListTransformer()
   {
   }

   /**
    * Transforms a string in a float array
    * @param value the string to transform
    * @return an array of float or an undefined value if parsing fails.
    */
   public TransformedValue<float[]> transform(String value)
   {
      float[] data = null;
      value = value.trim();
      value = value.replace('\n', ' ');
      String[] elements = value.trim().split(" ");
      List<String> es = new ArrayList<String>(elements.length);
      for (String string : elements)
      {
         if (!string.trim().isEmpty())
         {
            es.add(string);
         }
      }

      if (es.size() > 0)
      {
         try
         {
            data = new float[es.size()];

            for (int i = 0; i < es.size(); i++)
            {
               String string = es.get(i);
               data[i] = Float.parseFloat(string);
            }
         }
         catch (NumberFormatException ex)
         {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "", ex);
            data = null;
         }
      }

      return TransformedValue.create(data);
   }
}
