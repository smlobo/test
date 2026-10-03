import torch


def main():
    n: Int = 4
    ones = torch.ones((n, n), dtype=torch.bool)
    print(f'Ones {n}x{n} matrix:\n{ones}')
    lMask = ones.tril()
    print(f'Lower mask {n}x{n}:\n{lMask}')
    invertLMask = ~lMask
    print(f'Inverted lower mask {n}x{n}:\n{invertLMask}')
    uMask = ones.triu()
    print(f'Upper mask {n}x{n}:\n{uMask}')


if __name__ == "__main__":
    main()
