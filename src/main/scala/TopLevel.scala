import spinal.core._

import rc800.RC811

class TopLevel extends Component {
	val io = new Bundle {
		val PMOD = out (Bits(48 bits))
	}

    val memoryArea = new Area() {
        val address = UInt(16 bits)
        val dataIn = Bits(8 bits)
        val dataOut = Bits(8 bits)
        val enable = Bool()
        val write = Bool()

        private val ram = Mem(Bits(8 bits), 8 * 1024)
        private val bytes = java.util.Arrays.copyOf(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("bin/code.bin")), 8 * 1024)

        ram.init(bytes.map(v => B(v & 0xFF)))

        dataOut := ram.readWriteSync(address(12 downto 0), dataIn, enable, write)
    }

    val ioArea = new Area() {
        val address = UInt(16 bits)
        val dataIn = Bits(8 bits)
        val enable = Bool()
        val write = Bool()

        val seven_lo = Reg(Bits(8 bits))
        val seven_hi = Reg(Bits(8 bits))

        when (address === U(0, 16 bits) && enable && write) {
            seven_lo := dataIn
        } elsewhen (address === U(1, 16 bits) && enable && write) {
            seven_hi := dataIn
        }
    }

    val cpuArea = new Area() {
        implicit val components = rc800.lpm.generic.Components
        val cpu = new RC811()

        cpu.io.nmi := False
        cpu.io.irq := False
        cpu.io.dataIn := memoryArea.dataOut

        memoryArea.dataIn := cpu.io.dataOut
        memoryArea.address := cpu.io.address
        memoryArea.enable := cpu.io.busEnable && ~cpu.io.io
        memoryArea.write := cpu.io.write

        ioArea.dataIn := cpu.io.dataOut
        ioArea.address := cpu.io.address
        ioArea.enable := cpu.io.busEnable && cpu.io.io
        ioArea.write := cpu.io.write
    }

	val slowArea = new SlowArea(2 kHz) {
		val sevenSegments = SevenSegments()
		sevenSegments.io.d2 := ioArea.seven_hi(3 downto 0).asUInt
		sevenSegments.io.d1 := ioArea.seven_lo(7 downto 4).asUInt
		sevenSegments.io.d0 := ioArea.seven_lo(3 downto 0).asUInt
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

