extern crate file_b;

fn file_a_function(i: i32) {
    file_b::indent(i);
    println!("file_a : file_a_function");
    file_b::file_b_function(i + 1);
}

fn main() {
    println!("file_a : main");
    file_a_function(1);
}
