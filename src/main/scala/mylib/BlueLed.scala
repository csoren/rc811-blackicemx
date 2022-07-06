package mylib

import spinal.core._

class BlueLed extends Component {
  val io = new Bundle {
    val PMOD = out (Bits(48 bits))
  }

  val a = io.PMOD(30) // 3
  val b = io.PMOD(31) // 4
  val c = io.PMOD(29) // 144
  val d = io.PMOD(28) // 143
  val e = io.PMOD(24) // 1
  val f = io.PMOD(27) // 8
  val g = io.PMOD(26) // 7
  val dp = io.PMOD(25) // 2
  val ca0 = io.PMOD(19) // 135
  val ca1 = io.PMOD(23) // 139
  val ca2 = io.PMOD(22) // 138

  io.PMOD := B(0)

  a := True
  b := False
  c := True
  d := False
  e := True
  f := False
  g := True
  dp := False
  ca0 := False
  ca1 := True
  ca2 := False
}

object BlueLed {
  def main(args: Array[String]) {
    BlackIceSpinalConfig.generateVerilog(new BlueLed)
  }
}

