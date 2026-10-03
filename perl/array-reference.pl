#!/usr/bin/env perl

my @array = (1,2,3,4,5,6,7);

print "My array: (@array)\n";
remove4(\@array);
print "My array after delete: (@array)\n";

my $arrayStr = join(":", @array);
print "Print array w join: $arrayStr\n";

sub remove4 {
    my $arrayRef = $_[0];

    print "  Array in remove4: (@$arrayRef)\n";

    my $numElements = @$arrayRef;
    print "  # elements: $numElements\n";

    splice(@$arrayRef, 3, 1);
}
