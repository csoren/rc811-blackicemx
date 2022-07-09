TOPLEVEL = TopLevel
VERILOG ?= TopLevel.v
PCF ?= leds.pcf

prog : bin/toplevel.bin
	sudo stty -F /dev/ttyACM0 raw
	sudo cp bin/toplevel.bin /dev/ttyACM0

${VERILOG}: $(shell find -name '*.scala') bin/code.bin
	sbt "runMain ${TOPLEVEL}"

bin/code.bin : code.rc8
	motorrc8 -fb -o$@ $<

bin/toplevel.json : ${VERILOG}
	mkdir -p bin
	yosys -r ${TOPLEVEL} -v3 -p "synth_ice40 -json bin/toplevel.json" ${VERILOG}

bin/toplevel.asc : ${PCF} bin/toplevel.json
	nextpnr-ice40 --top ${TOPLEVEL} --freq 25 --hx8k --package tq144:4k --json bin/toplevel.json --pcf ${PCF} --asc bin/toplevel.asc --opt-timing

bin/toplevel.bin : bin/toplevel.asc
	icepack bin/toplevel.asc bin/toplevel.bin

compile : bin/toplevel.bin

time: bin/toplevel.bin
	icetime -tmd hx8k bin/toplevel.asc

clean :
	rm -rf bin
