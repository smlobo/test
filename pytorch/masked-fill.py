import torch


def main():
    n: Int = 4
    m: Int = 6

    print(f'Square: ({n}x{n})')
    ones = torch.ones((n, n), dtype=torch.bool)
    lMask = ones.tril()
    print(f'Lower mask {n}x{n}:\n{lMask}')
    random2d = torch.randint(0, 100, (n, n), dtype=torch.int32)
    print(f'Random int {n}x{n}:\n{random2d}')
    masked = random2d.masked_fill(lMask, -1)
    print(f'Masked {n}x{n}:\n{masked}')

    print(f'Rectangular: ({n}x{m})')
    ones = torch.ones((n, m), dtype=torch.bool)
    lMask = ones.tril()
    print(f'Lower mask {n}x{m}:\n{lMask}')
    random2d = torch.randint(0, 100, (n, m), dtype=torch.int32)
    print(f'Random int {n}x{m}:\n{random2d}')
    masked = random2d.masked_fill(lMask, -1)
    print(f'Masked {n}x{m}:\n{masked}')


if __name__ == "__main__":
    main()
