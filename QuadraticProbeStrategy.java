public class QuadraticProbeStrategy implements ProbeStrategy {

  public int probe(int arraySize, int originalHash, int attemptNum) {
    // Quadratic probing formula: (originalHash + attemptNum^2) % arraySize
    return (originalHash + attemptNum * attemptNum) % arraySize;
  }
}
