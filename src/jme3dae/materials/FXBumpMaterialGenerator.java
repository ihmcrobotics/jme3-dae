package jme3dae.materials;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import jme3dae.utilities.NormalMapFilter;
import jme3tools.converters.ImageToAwt;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;

public class FXBumpMaterialGenerator
{
   private static final Map<Texture, Texture> NORMAL_MAPS = new ConcurrentHashMap<Texture, Texture>();

   public static FXBumpMaterialGenerator create(AssetManager am, boolean autobump)
   {
      return new FXBumpMaterialGenerator(am, autobump);
   }

   private final AssetManager ASSET_MANAGER;
   private final Material MATERIAL;
   private final boolean autobump;
   private boolean hasTexture = false;

   private FXBumpMaterialGenerator(AssetManager am, boolean autobump)
   {
      Logger.getLogger(getClass().getName()).log(Level.INFO, "Generating Normal Maps? " + autobump);
      ASSET_MANAGER = am;
      this.autobump = autobump;
      MATERIAL = new Material(am, "Common/MatDefs/Light/Lighting.j3md");
      MATERIAL.setFloat("Shininess", 8.0f);
   }

   public void setTexture(Texture texture)
   {
      if (texture != null)
      {
         MATERIAL.setTexture("DiffuseMap", texture);

         if (autobump)
         {
            MATERIAL.setTexture("NormalMap", getNormalMap(texture));
         }

         MATERIAL.setBoolean("UseMaterialColors", false);
         hasTexture = true;
      }
   }

   public void setAmbient(ColorRGBA color)
   {
      if (!autobump && (color != null))
      {
         MATERIAL.setBoolean("UseMaterialColors", true);
         MATERIAL.setColor("Ambient", color);
      }
   }

   public void setDiffuse(ColorRGBA color)
   {
      if (!autobump && (color != null))
      {
         MATERIAL.setBoolean("UseMaterialColors", true);
         MATERIAL.setColor("Diffuse", color);
      }
   }

   public void setShininess(Float value)
   {
      if (value != null)
      {
         MATERIAL.setFloat("Shininess", value);
      }
   }

   public Material get()
   {
      return MATERIAL.clone();
   }

   private Texture getNormalMap(Texture texture)
   {
      Texture nmap = NORMAL_MAPS.get(texture);
      if (nmap == null)
      {
         NORMAL_MAPS.put(texture, nmap = generateNormalMap(texture));
      }

      return nmap;
   }

   private Texture generateNormalMap(Texture texture)
   {
      BufferedImage image = ImageToAwt.convert(texture.getImage(), false, false, 0);
      BufferedImage normal = NormalMapFilter.create().filter(image, 0.01f);
      Image jme = new AWTLoader().load(normal, false);
      Texture2D jmeTexture = new Texture2D(jme);
      jmeTexture.setWrap(Texture.WrapAxis.S, texture.getWrap(Texture.WrapAxis.S));
      jmeTexture.setWrap(Texture.WrapAxis.T, texture.getWrap(Texture.WrapAxis.T));
      jmeTexture.setMagFilter(texture.getMagFilter());
      jmeTexture.setMinFilter(texture.getMinFilter());

      return jmeTexture;
   }
}
