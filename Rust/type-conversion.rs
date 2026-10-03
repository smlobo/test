use std::convert::From;
use std::string::ToString;
use std::str::FromStr;
use std::num::ParseIntError;
//use std::fmt;

#[derive(Debug)]
struct Number {
    value: i32,
}

impl From<i32> for Number {
    fn from(item: i32) -> Self {
        Number { value: item }
    }
}

#[derive(Debug)]
struct Circle {
    radius: i32,
}

impl ToString for Circle {
    fn to_string(&self) -> String {
        format!("Circle of radius: {}", self.radius)
    }
}

impl FromStr for Circle {
    type Err = ParseIntError;

    fn from_str(s: &str) -> Result<Self, Self::Err> {
        Ok(Circle {
            radius: s.parse().unwrap(),
        })
    }
}

/*impl fmt::Display for Circle {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        write!(f, "Circle: {}", self.radius)
    }
}*/

fn main() {
    // FROM / INTO
    // From
    let num = Number::from(30);
    println!("My number is {:?}", num);

    // You get into for free when From is implemented
    let int = 5;
    let num: Number = int.into();
    println!("My number is {:?}", num);

    // TO STRING
    let circle = Circle { radius: 6 };
    println!("\nToString trait: {}", circle.to_string());
    println!("Debug: {:?}", circle);
    // Cannot implement Display *and* ToString
    //println!("Display: {}", circle);

    // FROM STRING
    let parsed: i32 = "5".parse().unwrap();
    let turbo_parsed = "10".parse::<i32>().unwrap();

    let sum = parsed + turbo_parsed;
    println!{"\nSum: {:?}", sum};

    let c: Circle = "22".parse().unwrap();
    println!("FromStr Circle - ToString trait: {}", c.to_string());
    println!("FromStr Circle - Debug: {:?}", c);
}
