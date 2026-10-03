fn main() {
    // MUTABILITY
    let _immutable_binding = 1;
    let mut mutable_binding = 1;

    println!("Before mutation: {}", mutable_binding);

    // Ok
    mutable_binding += 1;

    println!("After mutation: {}", mutable_binding);

    // Error!
    //_immutable_binding += 1;
    // FIXME ^ Comment out this line

    // SCOPE & SHADOWING
    // This binding lives in the main function
    let mut long_lived_binding = 1;

    // This is a block, and has a smaller scope than the main function
    {
        // This binding only exists in this block
        let short_lived_binding = 2;

        println!("inner short: {}", short_lived_binding);

        // Modify the outer variable
        long_lived_binding = 11;

        println!("inner long (original modified): {}", long_lived_binding);

        // This binding *shadows* the outer one
        let long_lived_binding = 5_f32;

        println!("inner long: {}", long_lived_binding);
    }
    // End of the block

    // Error! `short_lived_binding` doesn't exist in this scope
    //println!("outer short: {}", short_lived_binding);
    // FIXME ^ Comment out this line

    println!("outer long: {}", long_lived_binding);

    // This binding also *shadows* the previous binding
    let long_lived_binding = 'a';

    println!("outer long: {}", long_lived_binding);

    // Set type to the variable
    let a: i32 = 2 ^ 10;    // 0x2 XOR 0xa == 0010 XOR 1010 == 100 == 0x8
    println!("a = {0} OR {0:b} OR {0:#o}", a);
    let b: u8 = 66;
    println!("b = {0} OR {0:#x} OR {0:x}", b);
    println!("b (as char) = {}", b as char);
    let c: f64 = 3.1415;
    println!("c = {:08.3}", c);
    // Rust uses Unicode (like Go -> Go book code)
    let mut d: char = '\u{261D}';
    println!("d = {}", d);
    d = '\u{0930}';
    println!("Now d = {}", d);

}
