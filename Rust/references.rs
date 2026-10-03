fn main() {
    let mut s: String = String::from("foo");

    {
        let r1s: &String = &s;
        let r2s: &String = &s;
        println!("  2 immutable references allowed: {}, {}", r1s, r2s);
    }
    {
        let r3s: &mut String = &mut s;
        r3s.push_str(" bar");
        println!("  1 mutable references allowed (new scope): {}", r3s);
    }
    println!("original scope - s modified: {}", s);

    // numeric literals
    let mut n = 10;
    {
        let r1n = &n;
        let r2n = &n;
        println!("  2 immutable references allowed: {}, {}", *r1n, *r2n);
    }
    {
        let r3n = &mut n;
        *r3n += 100;
        println!(" 1 mutable reference allowed: {}", *r3n);
    }
    println!("original scope - n modified: {}", n);
}
