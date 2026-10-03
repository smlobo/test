use std::ops::Div;

#[derive(Debug, Clone)]
struct Divider<T: std::ops::Div> {
    numerator: T,
    denominator: T,
}

impl<T: Div<T, Output = T>> Divider<T> {
    fn divide(self) -> T {
        self.numerator / self.denominator
    }
}

fn divider<T: Div<T, Output = T>>(x: T, y: T) -> T {
    x / y
}

fn main() {
    let i_divider = Divider {
        numerator: 3,
        denominator: 2,
    };
    let f_divider = Divider {
        numerator: 3.0,
        denominator: 2.0,
    };

    // Function generics
    println!(
        "Generic function: {:?} = {}",
        i_divider,
        divider(i_divider.numerator, i_divider.denominator)
    );
    println!(
        "Generic function: {:?} = {}",
        f_divider,
        divider(f_divider.numerator, f_divider.denominator)
    );

    // Struct impl generics
    println!(
        "Struct impl: {:?} = {}",
        i_divider.clone(),
        i_divider.divide()
    );
    println!(
        "Struct impl: {:?} = {}",
        f_divider.clone(),
        f_divider.divide()
    );
}
