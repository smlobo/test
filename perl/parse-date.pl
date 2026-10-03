#!/net/iraquoy/export/nsPerl5.005_03/bin/nsperl

use Date::Manip qw(ParseDate UnixDate);

$sdate = "Sun Jun  7 19:23:07 2009 PDT";

$s2 = substr($sdate, 3);
print "s2 = $s2\n";

$s3 = substr($sdate, 0, -3);
print "s3 = $s3\n";


$date = ParseDate($s3);
if (!$date) {
  print "bad date\n";
}
else {
  ($yy, $mm, $dd, $hh) = UnixDate($date, "%Y", "%m", "%d", "%T");
  print "date: $yy, $mm, $dd, $hh\n";
  $webrev_out_dir = ".../$yy$mm$dd\_$hh\_sml";
  print "www : $webrev_out_dir\n";
}

print UnixDate("today", "It is: %T, %b, %e, %Y\n");

