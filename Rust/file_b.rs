pub fn indent(i: i32) {
    for _n in 0..i {
        print!("  ");
    }
}

pub fn file_b_function(i: i32) {
    indent(i);
    println!("file_b : file_b_function");
}
