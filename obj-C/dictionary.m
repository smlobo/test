#import <Foundation/Foundation.h>

int main() {
    NSDictionary *myDict = @{
        @0: @"Sunday",
        @1: @"Monday",
        @2: @"Tuesday",
        @3: @"Wednesday"
    };

    for (id key in myDict) {
        NSLog(@"key: %@, Value: %@", key, myDict[key]);
    }
}
