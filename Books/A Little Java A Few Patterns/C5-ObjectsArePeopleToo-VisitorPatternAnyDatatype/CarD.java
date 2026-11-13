abstract public class CarD {
    AddV addFunction = new AddV();
    RemoveV removeFunction = new RemoveV();
    abstract public CarD add(CarPartI carPartI, CarPartI before);
    abstract public CarD remove(CarPartI carPartI);
    abstract public String toString();
}
