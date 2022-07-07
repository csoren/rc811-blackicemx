import spinal.core._

class TopLevel extends Component {
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

object TopLevel {
	def main(args: Array[String]) {
		BlackIceSpinalConfig.generateVerilog(new TopLevel)
	}
}

