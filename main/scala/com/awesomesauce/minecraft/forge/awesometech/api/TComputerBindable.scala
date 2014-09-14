package com.awesomesauce.minecraft.forge.awesometech.api

trait TComputerBindable extends TComputerLocatable {
  def bind(cpu: TComputerCPU): Boolean

  def getName: String
}
