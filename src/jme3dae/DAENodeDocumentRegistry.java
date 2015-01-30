package jme3dae;

import java.util.HashMap;
import java.util.Map;

public class DAENodeDocumentRegistry
{
   /**
    * This is where collada elements with a unique id are published
    */
   private final Map<String, DAENode> documentRegistry = new HashMap<String, DAENode>();

   public DAENodeDocumentRegistry()
   {
   }

   public void addToDocumentRegistry(String id, DAENode n)
   {
      documentRegistry.put(id, n);
   }

   public DAENode getFromDocumentRegistry(String url)
   {
      DAENode daeNodeToReturn = documentRegistry.get(url);

      return daeNodeToReturn;
   }

   public boolean containsURLKey(String url)
   {
      return documentRegistry.containsKey(url);
   }

}
