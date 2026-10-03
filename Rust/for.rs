fn main() {
    // `n` will take the values: 1, 2, ..., 100 in each iteration
    for n in 1..31 {
        if n % 15 == 0 {
            println!("fizzbuzz");
        } else if n % 3 == 0 {
            print!("fizz, ");
        } else if n % 5 == 0 {
            println!("buzz, ");
        } else {
            print!("{}, ", n);
        }
    }

    // reverse iteration over a range
    print!("Reverse range: ");
    for n in (0..4).rev() {
        print!("{}, ", n);
    }
    println!();

    for_iter();
    for_into_iter();
    for_iter_mut();
}

fn for_iter() {
    println!("\nFOR ITER");
    let names = vec!["Bob", "Frank", "Ferris"];

    for name in names.iter() {
        match name {
            &"Ferris" => println!("There is a rustacean among us!"),
            _ => println!("Hello {}", name),
        }
    }
}

fn for_into_iter() {
    println!("\nFOR INTO ITER");
    let names = vec!["Bob", "Frank", "Ferris"];

    for name in names.into_iter() {
        match name {
            "Ferris" => println!("There is a rustacean among us!"),
            _ => println!("Hello {}", name),
        }
    }
}

fn for_iter_mut() {
    println!("\nFOR ITER MUT");
    let mut names = vec!["Bob", "Frank", "Ferris"];

    for name in names.iter_mut() {
        match name {
            &mut "Ferris" => {
                println!("There is a rustacean among us!");
            }
            _ => println!("Hello {}", name),
        }
    }
}
