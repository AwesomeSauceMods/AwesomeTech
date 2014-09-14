package com.awesomesauce.minecraft.forge.awesometech.tile.computer

import com.awesomesauce.minecraft.forge.awesometech.api.data.{DataItem, DataPosition}
import com.awesomesauce.minecraft.forge.awesometech.api.prefab.TileEntityComputerEffector
import net.minecraft.item.ItemBlock

class TileEntityStoneBlockPlacer extends TileEntityComputerEffector {
  var itemDataName: String = ""
  var positionDataName: String = ""
  var name = "stoneBlockPlacer"

  def activate(): Boolean = {
    val itemData = cpu.getData(itemDataName)
    if (itemData.isInstanceOf[DataItem]) {
      val item = itemData.asInstanceOf[DataItem].stack.getItem
      if (item.isInstanceOf[ItemBlock]) {
        val block = item.asInstanceOf[ItemBlock].field_150939_a
        val positionData = cpu.getData(positionDataName)
        if (positionData.isInstanceOf[DataPosition]) {
          val pData = positionData.asInstanceOf[DataPosition]
          val x = getX + pData.x
          val y = getY + pData.y
          val z = getZ + pData.z
          if (block.isReplaceable(worldObj, x, y, z)) {
            worldObj.setBlock(x, y, z, block)
            true
          }
          else false
        }
        else false
      }
      else false
    }
    else false
  }
}
