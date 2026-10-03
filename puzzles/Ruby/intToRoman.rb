#!/usr/bin/ruby

# @param {Integer} num
# @return {String}
def int_to_roman(num)
  print num, "\t = "
  _roman = '';
  iRTable = [[1000, "M"], [900, "CM"], [500, "D"], [400, "CD"], 
             [100, "C"], [90, "XC"], [50, "L"], [40, "XL"], 
             [10, "X"], [9, "IX"], [5, "V"], [4, "IV"], [1, "I"]];
  iRTable.each_index do |i|
    iRInst = iRTable[i]
    #print "[" , i , "] ", iRInst[0], iRInst[1], "\n"
    _count = (num/iRInst[0]).floor;  
    _roman += iRInst[1]*_count;
    num -= iRInst[0]*_count;
  end
  return _roman;    
end

puts int_to_roman(2534);
puts int_to_roman(3999);
puts int_to_roman(1500);
puts int_to_roman(890);
puts int_to_roman(77);
puts int_to_roman(89);

