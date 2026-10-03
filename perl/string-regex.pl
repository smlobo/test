#!/usr/bin/env perl

my $test_string = "foo bar";

if ($test_string =~ ".*ba.") {
    print "Found: $test_string\n";
} else {
    print "not found: $test_string\n";
}

if ($test_string =~ ".*ma.*") {
    print "Found: $test_string\n";
} else {
    print "not found: $test_string\n";
}
