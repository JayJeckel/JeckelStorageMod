package jeckelstoragemod.content.barrel;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBarrel extends ModelBase
{
  //fields
    ModelRenderer front;
    ModelRenderer top;
    ModelRenderer bottom;
    ModelRenderer back;
    ModelRenderer right;
    ModelRenderer left;
  
  public ModelBarrel()
  {
    textureWidth = 32;
    textureHeight = 32;
    
      front = new ModelRenderer(this, -1, 15);
      front.addBox(-8F, 0F, -8F, 16, 16, 1);
      front.setRotationPoint(0F, 0F, 0F);
      front.setTextureSize(32, 32);
      front.mirror = true;
      setRotation(front, 0F, 0F, 0F);
      top = new ModelRenderer(this, 0, 0);
      top.addBox(-8F, 0F, -8F, 16, 1, 16);
      top.setRotationPoint(0F, 0F, 0F);
      top.setTextureSize(32, 32);
      top.mirror = true;
      setRotation(top, 0F, 0F, 0F);
      bottom = new ModelRenderer(this, 0, 16);
      bottom.addBox(-8F, 16F, -8F, 16, 1, 16);
      bottom.setRotationPoint(0F, 0F, 0F);
      bottom.setTextureSize(32, 32);
      bottom.mirror = true;
      setRotation(bottom, 0F, 0F, 0F);
      back = new ModelRenderer(this, -18, -1);
      back.addBox(-8F, 0F, 7F, 16, 16, 1);
      back.setRotationPoint(0F, 0F, 0F);
      back.setTextureSize(32, 32);
      back.mirror = true;
      setRotation(back, 0F, 0F, 0F);
      right = new ModelRenderer(this, -17, -16);
      right.addBox(-8F, 0F, -8F, 1, 16, 16);
      right.setRotationPoint(0F, 0F, 0F);
      right.setTextureSize(32, 32);
      right.mirror = true;
      setRotation(right, 0F, 0F, 0F);
      left = new ModelRenderer(this, -17, -16);
      left.addBox(7F, 0F, -8F, 1, 16, 16);
      left.setRotationPoint(0F, 0F, 0F);
      left.setTextureSize(32, 32);
      left.mirror = true;
      setRotation(left, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    front.render(f5);
    top.render(f5);
    bottom.render(f5);
    back.render(f5);
    right.render(f5);
    left.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
