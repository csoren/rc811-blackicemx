// Generator : SpinalHDL v1.6.4    git head : 598c18959149eb18e5eee5b0aa3eef01ecaa41a1
// Component : BlueLed
// Git hash  : a600ee4f1973e8ebf2d0f9daeeec20d6a7e35d6a

`timescale 1ns/1ps 

module BlueLed (
  output reg [47:0]   io_PMOD,
  input               clk
);

  wire       [6:0]    slowArea_sevenSegments_io_seg;
  wire                slowArea_sevenSegments_io_dp;
  wire                slowArea_sevenSegments_io_ca0;
  wire                slowArea_sevenSegments_io_ca1;
  wire                slowArea_sevenSegments_io_ca2;
  reg        [13:0]   _zz_when_ClockDomain_l353;
  wire                when_ClockDomain_l353;
  reg                 when_ClockDomain_l353_regNext;
  wire                a;
  wire                b;
  wire                c;
  wire                d;
  wire                e;
  wire                f;
  wire                g;
  wire                dp;
  wire                ca0;
  wire                ca1;
  wire                ca2;

  SevenSegments slowArea_sevenSegments (
    .io_d0                            (4'b1111                             ), //i
    .io_d1                            (4'b1101                             ), //i
    .io_d2                            (4'b1110                             ), //i
    .io_seg                           (slowArea_sevenSegments_io_seg[6:0]  ), //o
    .io_dp                            (slowArea_sevenSegments_io_dp        ), //o
    .io_ca0                           (slowArea_sevenSegments_io_ca0       ), //o
    .io_ca1                           (slowArea_sevenSegments_io_ca1       ), //o
    .io_ca2                           (slowArea_sevenSegments_io_ca2       ), //o
    .clk                              (clk                                 ), //i
    .when_ClockDomain_l353_regNext    (when_ClockDomain_l353_regNext       )  //i
  );
  initial begin
    _zz_when_ClockDomain_l353 = 14'h0;
    when_ClockDomain_l353_regNext = 1'b0;
  end

  assign when_ClockDomain_l353 = (_zz_when_ClockDomain_l353 == 14'h30d3);
  assign a = io_PMOD[30];
  assign b = io_PMOD[31];
  assign c = io_PMOD[29];
  assign d = io_PMOD[28];
  assign e = io_PMOD[24];
  assign f = io_PMOD[27];
  assign g = io_PMOD[26];
  assign dp = io_PMOD[25];
  assign ca0 = io_PMOD[22];
  assign ca1 = io_PMOD[23];
  assign ca2 = io_PMOD[19];
  always @(*) begin
    io_PMOD = 48'h0;
    io_PMOD[30] = (! slowArea_sevenSegments_io_seg[0]);
    io_PMOD[31] = (! slowArea_sevenSegments_io_seg[1]);
    io_PMOD[29] = (! slowArea_sevenSegments_io_seg[2]);
    io_PMOD[28] = (! slowArea_sevenSegments_io_seg[3]);
    io_PMOD[24] = (! slowArea_sevenSegments_io_seg[4]);
    io_PMOD[27] = (! slowArea_sevenSegments_io_seg[5]);
    io_PMOD[26] = (! slowArea_sevenSegments_io_seg[6]);
    io_PMOD[25] = 1'b1;
    io_PMOD[22] = (! slowArea_sevenSegments_io_ca0);
    io_PMOD[23] = (! slowArea_sevenSegments_io_ca1);
    io_PMOD[19] = (! slowArea_sevenSegments_io_ca2);
  end

  always @(posedge clk) begin
    _zz_when_ClockDomain_l353 <= (_zz_when_ClockDomain_l353 + 14'h0001);
    if(when_ClockDomain_l353) begin
      _zz_when_ClockDomain_l353 <= 14'h0;
    end
    when_ClockDomain_l353_regNext <= when_ClockDomain_l353;
  end


endmodule

module SevenSegments (
  input      [3:0]    io_d0,
  input      [3:0]    io_d1,
  input      [3:0]    io_d2,
  output     [6:0]    io_seg,
  output              io_dp,
  output              io_ca0,
  output              io_ca1,
  output              io_ca2,
  input               clk,
  input               when_ClockDomain_l353_regNext
);

  reg        [6:0]    _zz_io_seg;
  reg        [2:0]    counter;
  wire       [6:0]    digits_0;
  wire       [6:0]    digits_1;
  wire       [6:0]    digits_2;
  wire       [6:0]    digits_3;
  wire       [6:0]    digits_4;
  wire       [6:0]    digits_5;
  wire       [6:0]    digits_6;
  wire       [6:0]    digits_7;
  wire       [6:0]    digits_8;
  wire       [6:0]    digits_9;
  wire       [6:0]    digits_10;
  wire       [6:0]    digits_11;
  wire       [6:0]    digits_12;
  wire       [6:0]    digits_13;
  wire       [6:0]    digits_14;
  wire       [6:0]    digits_15;
  wire       [3:0]    digitsIndex;

  initial begin
    counter = 3'b001;
  end

  always @(*) begin
    case(digitsIndex)
      4'b0000 : _zz_io_seg = digits_0;
      4'b0001 : _zz_io_seg = digits_1;
      4'b0010 : _zz_io_seg = digits_2;
      4'b0011 : _zz_io_seg = digits_3;
      4'b0100 : _zz_io_seg = digits_4;
      4'b0101 : _zz_io_seg = digits_5;
      4'b0110 : _zz_io_seg = digits_6;
      4'b0111 : _zz_io_seg = digits_7;
      4'b1000 : _zz_io_seg = digits_8;
      4'b1001 : _zz_io_seg = digits_9;
      4'b1010 : _zz_io_seg = digits_10;
      4'b1011 : _zz_io_seg = digits_11;
      4'b1100 : _zz_io_seg = digits_12;
      4'b1101 : _zz_io_seg = digits_13;
      4'b1110 : _zz_io_seg = digits_14;
      default : _zz_io_seg = digits_15;
    endcase
  end

  assign io_ca2 = counter[2];
  assign io_ca1 = counter[1];
  assign io_ca0 = counter[0];
  assign digits_0 = 7'h3f;
  assign digits_1 = 7'h06;
  assign digits_2 = 7'h5b;
  assign digits_3 = 7'h4f;
  assign digits_4 = 7'h66;
  assign digits_5 = 7'h6d;
  assign digits_6 = 7'h7d;
  assign digits_7 = 7'h07;
  assign digits_8 = 7'h7f;
  assign digits_9 = 7'h6f;
  assign digits_10 = 7'h77;
  assign digits_11 = 7'h7c;
  assign digits_12 = 7'h39;
  assign digits_13 = 7'h5e;
  assign digits_14 = 7'h79;
  assign digits_15 = 7'h71;
  assign digitsIndex = (counter[0] ? io_d0 : (counter[1] ? io_d1 : io_d2));
  assign io_seg = _zz_io_seg;
  assign io_dp = 1'b0;
  always @(posedge clk) begin
    if(when_ClockDomain_l353_regNext) begin
      counter <= {counter[1 : 0],counter[2]};
    end
  end


endmodule
