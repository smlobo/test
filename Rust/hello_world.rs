use std::fmt;

#[derive(Debug)]
struct Structure(i32);

#[derive(Debug)]
struct Deep(Structure);

#[derive(Debug)]
struct Person<'a> {
    name: &'a str,
    age: u8,
}

#[derive(Debug)]
struct MinMax(i64, i64);

impl fmt::Display for MinMax {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        // Use `self.number` to refer to each positional data point.
        write!(f, "<{}, {}>", self.0, self.1)
    }
}

struct List(Vec<i32>);

impl fmt::Display for List {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        // Extract the value using tuple indexing
        // and create a reference to `vec`.
        let vec = &self.0;

        write!(f, "<")?;

        // Iterate over `vec` in `v` while enumerating the iteration
        // count in `count`.
        for (count, v) in vec.iter().enumerate() {
            // For every element except the first, add a comma.
            // Use the ? operator, or try!, to return on errors.
            if count != 0 {
                write!(f, ", ")?;
            }
            write!(f, "{}: {}", count, v)?;
        }

        // Close the opened bracket and return a fmt::Result value
        write!(f, ">")
    }
}

fn main() {
    print!("Hello World\n");
    println!("I'm a Rustacean!");

    let x = 5 + 5;
    println!("x = {}", x);

    println!("{0}, this is {1}. {1}, this is {0}!", "Alice", "Bob");

    println!(
        "{subject} {verb} {object}",
        object = "the lazy dog",
        subject = "the quick brown fox",
        verb = "jumps over"
    );

    // Special formatting can be specified after a `:`.
    println!(
        "{} of {:b} people know binary, the other half doesn't",
        1, 2
    );

    // You can right-align text with a specified width. This will output
    // "     1". 5 white spaces and a "1".
    println!("{number:>width$}", number = 1, width = 6);

    // You can pad numbers with extra zeroes. This will output "000001".
    println!("{number:>0width$}", number = 1, width = 6);

    let pi = 3.141592;
    println!("pi = {:.3}", pi);

    // Structure/Deep is printable because of derive(Debug)
    println!();
    println!("Print Structure(3): {:?}", Structure(3));
    println!("Now {:?} will print!", Deep(Structure(7)));

    // Pretty Debug print
    let name = "Sheldon";
    let age = 42;
    let myself = Person { name, age };
    println!("{:#?}", myself);

    // Compare prints
    println!();
    let minmax = MinMax(-3, 22);
    println!("MinMax Debug, Pretty Debug, implemented Display:");
    println!("Debug: {:?}", minmax);
    println!("{:#?}", minmax);
    println!("Display: {}", minmax);

    // List Display print
    println!();
    let mylist = List(vec![10, 20, 30, 40]);
    println!("-> {} <-", mylist);

    // Learn some collection iteration
    print!("\nCollection Iteration: [");
    for num in [100, 200, 300, 400].iter() {
        print!("{}, ", *num);
    }
    println!("]");
}
