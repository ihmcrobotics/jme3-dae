package jme3dae;

import jme3dae.utilities.ExplicitAnimationNode;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.UrlLocator;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class ExAnimTest extends SimpleApplication
{
   public static void main(String[] args)
   {
      new ExAnimTest().start();
   }

   @Override
   public void simpleInitApp()
   {
      ColladaDocumentFactory.setFXEnhance(FXEnhancerInfo.create(FXEnhancerInfo.IgnoreMeasuringUnit.ON));
      String base = "file:///home/pgi/3d models/";
      assetManager.registerLoader(ColladaLoader.class, "dae");
      assetManager.registerLocator(base, UrlLocator.class);
      Spatial node = assetManager.loadModel(base + "mech001.dae");
      final ExplicitAnimationNode animation = ExplicitAnimationNode.createFrom((Node) node);
      rootNode.attachChild(animation);
      animation.setCurrentSequence("walk");
      animation.setAnimationLength(1f);
      animation.setEnabled(true);
   }
}
