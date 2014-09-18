package com.awesomesauce.minecraft.forge.awesometech.item

import com.awesomesauce.minecraft.forge.awesometech.api.{TComputerBindable, TComputerCPU, TComputerEffector}
import com.awesomesauce.minecraft.forge.core.lib.item.ItemDescription
import com.awesomesauce.minecraft.forge.core.lib.util.PlayerUtil
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World


class ItemToolBinder extends ItemDescription {
  override def onItemUse(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, px: Float, py: Float, pz: Float) = {
    if (!stack.hasTagCompound) {
      stack.setTagCompound(new NBTTagCompound)
    }
    val te = world.getTileEntity(x, y, z)
    if (te.isInstanceOf[TComputerCPU]) {
      stack.getTagCompound.setInteger("x", x)
      stack.getTagCompound.setInteger("y", y)
      stack.getTagCompound.setInteger("z", z)
      PlayerUtil.sendChatMessage(player, "Bound CPU.")
      true
    }
    else if (te.isInstanceOf[TComputerBindable]) {
      val bindable = te.asInstanceOf[TComputerBindable]
      val cx = stack.getTagCompound.getInteger("x")
      val cy = stack.getTagCompound.getInteger("y")
      val cz = stack.getTagCompound.getInteger("z")
      if (world.getTileEntity(cx, cy, cz).isInstanceOf[TComputerCPU]) {
        val cpu = world.getTileEntity(cx, cy, cz).asInstanceOf[TComputerCPU]
        if ((cx - cpu.range < x && cx + cpu.range > x) && (cy - cpu.range < y && cy + cpu.range > y) && (cz - cpu.range < z && cz + cpu.range > z)) {
          bindable.bind(cpu)
          if (bindable.isInstanceOf[TComputerEffector]) {
            cpu.addEffector(bindable.getName, bindable.asInstanceOf[TComputerEffector])
          }
          PlayerUtil.sendChatMessage(player, "Bound CPU to " + bindable.getName)
          true
        }
        else {
          PlayerUtil.sendChatMessage(player, "CPU out of range.")
          false
        }
      }
      else {
        PlayerUtil.sendChatMessage(player, "Missing CPU")
        false
      }
    }
    else {
      PlayerUtil.sendChatMessage(player, "Not a bindable block.")
      false
    }
  }
}
