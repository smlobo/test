fn main() {
    // Increment via closures and functions.
    fn function(i: i32) -> i32 {
        i + 1
    }

    // Closures are anonymous, here we are binding them to references
    // Annotation is identical to function annotation but is optional
    // as are the `{}` wrapping the body. These nameless functions
    // are assigned to appropriately named variables.
    let closure_annotated = |i: i32| -> i32 { i + 1 };
    let closure_inferred = |i| i + 1;

    // Call the function and closures.
    println!("function: {} + 1 = {}", 100, function(100));
    println!(
        "closure_annotated: {} + 1 = {}",
        200,
        closure_annotated(200)
    );
    println!("closure_inferred: {} + 1 = {}", 300, closure_inferred(300));

    // A closure taking no arguments which returns an `i32`.
    // The return type is inferred.
    let one = || 1;
    println!("closure returning one: {}", one());
}
