#import <Foundation/Foundation.h>

int main(int argc, char *argv[]) {
    NSString *countStr = @(argv[1]);
    NSLog(@"Count (NSString): %@", countStr);
    int count = [countStr integerValue];
    NSLog(@"Count: %d", count);
    for (int i = 0; i < count; i++) {
        printf("  [%d]\n", i);
    }
    return 0;
}
