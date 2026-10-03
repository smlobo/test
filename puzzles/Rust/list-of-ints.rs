use std::env;
use std::process::exit;
use std::fs::File;
use std::io::prelude::*;
use std::io::BufReader;
use std::collections::HashMap;

fn main() {
    let args: Vec<String> = env::args().collect();
    if args.len() != 2 {
        println!("Usage: list-of-ints <filename>");
        exit(-1);
    }

    println!("Reading file: {}", &args[1]);
    let file = File::open(&args[1]).expect("file not found");

    //let mut contents = String::new();
    //f.read_to_string(&mut contents)
    //  .expect("something went wrong reading the file");

    //println!("With text:\n{}", contents);

    // Collect all lines into a vector
    let reader = BufReader::new(file);
    let lines: Vec<_> = reader.lines().collect();

    // Convert to a number vector
    let mut numbers: Vec<i32> = Vec::new();

    // HashMap for mode
    let mut mode_map: HashMap<i32, u32> = HashMap::new();
    let mut key_value: (i32, u32) = (0, 0);

    // For average
    let mut total = 0;

    let mut min = <i32>::max_value();
    let mut max = <i32>::min_value();

    let mut counter = 0;
    for l in lines {
        //print!("{} -> {} ", counter, l.unwrap_or(String::from("null")));
        numbers.push(l.unwrap().trim().parse().unwrap());
        /*print!("{} -> {} ", counter, numbers[counter]);
        if (counter + 1) % 5 == 0 {
            println!();
        }*/
        total += numbers[counter];
        if numbers[counter] < min {
            min = numbers[counter];
        }
        if numbers[counter] > max {
            max = numbers[counter];
        }

        let count = mode_map.entry(numbers[counter]).or_insert(0);
        *count += 1;
        if *count > key_value.1 {
            key_value.0 = numbers[counter];
            key_value.1 = *count;
        }

        counter += 1;
    }

    numbers.sort();
    let mut median = 0;
    if numbers.len() % 2 != 0 {
        median = numbers[numbers.len() / 2];
    } else {
        median = (numbers[numbers.len() / 2 - 1] + numbers[numbers.len() / 2]) / 2;
    }
    println!("Lowest number:\t{}, {}", min, numbers.first().unwrap());
    println!("Highest number:\t{}, {}", max, numbers.last().unwrap());
    println!("Mean:\t\t{}", total / numbers.len() as i32);
    println!("Median:\t\t{}", median);
    println!("Mode:\t\t{} [{}]", key_value.0, key_value.1);
}
