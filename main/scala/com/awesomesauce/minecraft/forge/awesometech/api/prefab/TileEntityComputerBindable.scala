package com.awesomesauce.minecraft.forge.awesometech.api.prefab

import com.awesomesauce.minecraft.forge.awesometech.api.{TComputerBindable, TComputerCPU}

abstract class TileEntityComputerBindable extends TileEntityComputerLocatable with TComputerBindable {
  var cpu: TComputerCPU = null
  var name: String

  def bind(nucpu: TComputerCPU): Boolean = {
    cpu = nucpu
    true
  }

  def getName = name
}
