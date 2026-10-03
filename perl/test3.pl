#!/usr/bin/perl

@xx = ();
@yy = (10);

print "n = $#xx\n";
print "ny = $#yy\n";

for $i (0 .. $#xx) {
  print "here\n";
}

for $i (0 .. $#yy) {
  print "here: $i : $yy[$i]\n";
}

if ($#xx == -1) {
  print "ha\n";
}
else {
  print "oh no\n";
}

