import torch


def main():
    dim3 = (2, 4, 3)
    random3d = torch.randint(0, 100, dim3, dtype=torch.int32)
    print(f'Random {dim3[0]}x{dim3[1]}x{dim3[2]} tensor:\n{random3d}')


if __name__ == "__main__":
    main()
