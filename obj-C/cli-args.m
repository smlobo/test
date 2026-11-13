#import <Foundation/Foundation.h>

int main(int argc, char *argv[]) {
    NSUserDefaults *args = [NSUserDefaults standardUserDefaults];
    NSLog(@"arg1: %@", [args stringForKey:@"arg1"]);
    NSLog(@"%@", NSArgumentDomain);
    return 0;
}
