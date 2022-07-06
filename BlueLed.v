// Generator : SpinalHDL v1.6.0    git head : 73c8d8e2b86b45646e9d0b2e729291f2b65e6be3
// Component : BlueLed
// Git hash  : 193268df6319c76b346702b17c54c72cb87bb04d



module BlueLed (
  output reg [47:0]   io_PMOD
);
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
  function [47:0] zz_io_PMOD(input dummy);
    begin
      zz_io_PMOD = 48'h0;
      zz_io_PMOD[30] = 1'b1;
      zz_io_PMOD[31] = 1'b0;
      zz_io_PMOD[29] = 1'b1;
      zz_io_PMOD[28] = 1'b0;
      zz_io_PMOD[24] = 1'b1;
      zz_io_PMOD[27] = 1'b0;
      zz_io_PMOD[26] = 1'b1;
      zz_io_PMOD[25] = 1'b0;
      zz_io_PMOD[19] = 1'b0;
      zz_io_PMOD[23] = 1'b1;
      zz_io_PMOD[22] = 1'b0;
    end
  endfunction
  wire [47:0] _zz_1;

  assign a = io_PMOD[30];
  assign b = io_PMOD[31];
  assign c = io_PMOD[29];
  assign d = io_PMOD[28];
  assign e = io_PMOD[24];
  assign f = io_PMOD[27];
  assign g = io_PMOD[26];
  assign dp = io_PMOD[25];
  assign ca0 = io_PMOD[19];
  assign ca1 = io_PMOD[23];
  assign ca2 = io_PMOD[22];
  assign _zz_1 = zz_io_PMOD(1'b0);
  always @(*) io_PMOD = _zz_1;

endmodule
