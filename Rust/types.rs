// `NanoSecond` is a new name for `u64`.
type NanoSecond = u64;
type Inch = u64;

// Use an attribute to silence warning.
#[allow(non_camel_case_types)]
type u64_t = u64;

fn main() {
    // LITERALS
    // Suffixed literals, their types are known at initialization
    let x = 1u8;
    let y = 2u32;
    let z = 3f32;
    let w: i16 = 4;
    let aa: bool = true;

    // Unsuffixed literal, their types depend on how they are used
    let i = 1;
    let f = 1.0;

    // `size_of_val` returns the size of a variable in bytes
    println!("size of `x` (u8) in bytes: {}", std::mem::size_of_val(&x));
    println!("size of `y` (u32) in bytes: {}", std::mem::size_of_val(&y));
    println!("size of `z` (f32) in bytes: {}", std::mem::size_of_val(&z));
    println!("size of `w` (i16) in bytes: {}", std::mem::size_of_val(&w));
    println!(
        "size of `aa` (bool) in bytes: {}",
        std::mem::size_of_val(&aa)
    );
    println!(
        "size of `i` (infer i32) in bytes: {}",
        std::mem::size_of_val(&i)
    );
    println!(
        "size of `f` (infer f64) in bytes: {}\n",
        std::mem::size_of_val(&f)
    );

    // INFERENCE
    // Because of the annotation, the compiler knows that `elem` has type u8.
    let elem = 5u8;

    // Create an empty vector (a growable array).
    let mut vec = Vec::new();
    // At this point the compiler doesn't know the exact type of `vec`, it
    // just knows that it's a vector of something (`Vec<_>`).

    // Insert `elem` in the vector.
    vec.push(elem);
    // Aha! Now the compiler knows that `vec` is a vector of `u8`s (`Vec<u8>`)
    // TODO ^ Try commenting out the `vec.push(elem)` line

    println!("{:?}\n", vec);

    // ALIASING
    // `NanoSecond` = `Inch` = `u64_t` = `u64`.
    let nanoseconds: NanoSecond = 5 as u64_t;
    let inches: Inch = 2 as u64_t;

    // Note that type aliases *don't* provide any extra type safety, because
    // aliases are *not* new types
    println!(
        "{} nanoseconds + {} inches = {} unit?",
        nanoseconds,
        inches,
        nanoseconds + inches
    );
}
