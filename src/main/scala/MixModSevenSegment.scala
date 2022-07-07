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
