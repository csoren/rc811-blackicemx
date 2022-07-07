package mylib

import spinal.core._

case class SevenSegments() extends Component {
	val io = new Bundle {
		val d0 = in (UInt(4 bits))
		val d1 = in (UInt(4 bits))
		val d2 = in (UInt(4 bits))

		val seg = out (Bits(7 bits))
		val dp  = out (Bool)
		val ca0 = out (Bool)
		val ca1 = out (Bool)
		val ca2 = out (Bool)
	}

	//   0
	//  +-+
	//5 | | 1
	//  +-+
	//4 | | 2
	//  +-+
	//   3

	val counter = RegInit(B(0x01, 3 bits))
	counter := counter(1 downto 0) ## counter(2)

	io.ca2 := counter(2)
	io.ca1 := counter(1)
	io.ca0 := counter(0)

	val digits = Vec(
		B"0111111", B"0000110", B"1011011", B"1001111",
		B"1100110", B"1101101", B"1111101", B"0000111",
		B"1111111", B"1101111", B"1110111", B"1111100",
		B"0111001", B"1011110", B"1111001", B"1110001")

	val digitsIndex =
		counter(0) ? io.d0 | 
		(counter(1) ? io.d1 | io.d2)

	io.seg := digits(digitsIndex)

	io.dp := False
}


class BlueLed extends Component {
	val io = new Bundle {
		val PMOD = out (Bits(48 bits))
	}

	val slowArea = new SlowArea(2 kHz) {
		val sevenSegments = SevenSegments()
		sevenSegments.io.d2 := U(14)
		sevenSegments.io.d1 := U(13)
		sevenSegments.io.d0 := U(15)
	}

	val a = io.PMOD(30) // 3
	val b = io.PMOD(31) // 4
	val c = io.PMOD(29) // 144
	val d = io.PMOD(28) // 143
	val e = io.PMOD(24) // 1
	val f = io.PMOD(27) // 8
	val g = io.PMOD(26) // 7
	val dp = io.PMOD(25) // 2
	val ca0 = io.PMOD(22) // 135
	val ca1 = io.PMOD(23) // 139
	val ca2 = io.PMOD(19) // 138

	io.PMOD := B(0)

	a := ~slowArea.sevenSegments.io.seg(0)
	b := ~slowArea.sevenSegments.io.seg(1)
	c := ~slowArea.sevenSegments.io.seg(2)
	d := ~slowArea.sevenSegments.io.seg(3)
	e := ~slowArea.sevenSegments.io.seg(4)
	f := ~slowArea.sevenSegments.io.seg(5)
	g := ~slowArea.sevenSegments.io.seg(6)
	dp := True
	ca0 := ~slowArea.sevenSegments.io.ca0
	ca1 := ~slowArea.sevenSegments.io.ca1
	ca2 := ~slowArea.sevenSegments.io.ca2
}

object BlueLed {
	def main(args: Array[String]) {
		BlackIceSpinalConfig.generateVerilog(new BlueLed)
	}
}

