fn main() {
    let s1 = String::from("test");
    let s2 = s1;

    //println!("Cannot access s1: {}", s1);
    println!("s2 owns the heap memory: {}", s2);

    // Functions behave the same as variables
    let s3 = take_and_give_ownership(s2);
    //println!("s2 does NOT own the memory: {}", s2);
    println!("s3 was given ownership: {}", s3);
}

fn take_and_give_ownership(mut s: String) -> String {
    println!("  function owns string passed: {}", s);
    s.push_str(" check");
    s
}
