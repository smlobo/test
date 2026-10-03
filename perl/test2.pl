#!/usr/bin/perl

use POSIX qw(strftime);

$changes_dir = ".";
$username = "sml";
$www = POSIX::strftime("$changes_dir/%Y%m%d_$username", localtime);

print "dd: $www\n";

