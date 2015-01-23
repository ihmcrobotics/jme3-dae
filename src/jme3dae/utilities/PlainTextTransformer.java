package jme3dae.utilities;

import jme3dae.transformers.ValueTransformer;

/**
 * Transforms a string in a string.
 * @author pgi
 */
public class PlainTextTransformer implements ValueTransformer<String, String>
{
   /**
    * Instance creator.
    * @return a new PlainTextTransformer
    */
   public static PlainTextTransformer create()
   {
      return new PlainTextTransformer();
   }

   private PlainTextTransformer()
   {
   }

   /**
    * Returns the given string if not null and not empty
    * @param value the string to transform
    * @return the given string or an undefined value if the string is null or
    * empty.
    */
   public TransformedValue<String> transform(String value)
   {
      if (value.isEmpty())
      {
         value = null;
      }

      return TransformedValue.create(value);
   }
}
