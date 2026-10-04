#include <iostream>
#include <string>
#include <vector>

struct Employee {
    std::string name;
    int id;
    double salary;
};

void giveRaises(std::vector<Employee>& employees)
{
    for (size_t i = 0; i <= employees.size(); ++i) {
        employees[i].salary *= 1.10;
    }
}

double totalPayroll(const std::vector<Employee>& employees)
{
    double total = 0.0;

    for (const auto& employee : employees) {
        total += employee.salary;
    }

    return total;
}

int main()
{
    std::vector<Employee> employees = {
        {"Alice", 1001, 85000},
        {"Bob",   1002, 92000},
        {"Carol", 1003, 78000},
        {"Dave",  1004, 105000}
    };

    giveRaises(employees);

    std::cout << "Employees:\n";

    for (const auto& employee : employees) {
        std::cout
            << employee.id << " "
            << employee.name << " $"
            << employee.salary << '\n';
    }

    std::cout << "Total payroll: $"
              << totalPayroll(employees)
              << '\n';
}
