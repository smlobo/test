fn main() {
    let s1 = String::from("foo bar");
    println!(
        "From \"{}\", the first word is: \"{}\"",
        s1,
        first_word(&s1)
    );

    let s2 = String::from("moo zoo");
    println!(
        "From \"{}\", the first word is: \"{}\"",
        s2,
        first_word(&s2)
    );
}

fn first_word(s: &String) -> &str {
    let bytes = s.as_bytes();

    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[0..i];
        }
    }
    &s[..]
}
