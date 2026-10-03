use std::collections::HashMap;

fn main() {
    // Vectors
    let mut v1: Vec<i32> = Vec::new();
    let v2 = vec![1, 2, 3];

    for i in 0..100 {
        v1.push(i);
    }

    println!("v2: {:?}", v2);
    println!("v1: {:?}", v1);

    for i in &mut v1 {
        *i += 100;
        print!("{}, ", *i);
    }
    println!();

    // Strings
    let mut s1 = "Hello".to_string();
    s1.push(',');
    let s = format!("{} ", s1);
    let s2 = String::from("world!");
    let mut s3 = s + &s2;
    s3.push('?');
    println!("{}", s3);

    // Hash Maps
    let mut scores: HashMap<String, i32> = HashMap::new();
    scores.insert(String::from("Blue"), 10);
    scores.insert(String::from("Yellow"), 50);
    scores.insert(String::from("Green"), 25);

    for (i, j) in &scores {
        println!("  {} -> {}", i, j);
    }
}
