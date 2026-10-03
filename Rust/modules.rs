fn indent(i: i32) {
    for _n in 0..i {
        print!("  ");
    }
}

mod module {
    // 'pub' overrides default private visibility
    pub fn function(i: i32) {
        super::indent(i);
        println!("module::function");
        private_function(i + 1);
    }

    fn private_function(i: i32) {
        super::indent(i);
        println!("module::private_function");
    }
}

pub fn function(i: i32) {
    indent(i);
    println!("function");
}

fn main() {
    println!("main");
    function(1);
    module::function(1);
}
