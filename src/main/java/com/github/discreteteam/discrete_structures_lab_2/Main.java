package com.github.discreteteam.discrete_structures_lab_2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;

public class Main {
	public static boolean isPrime(int n) {
		boolean[] isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);

		isPrime[0] = isPrime[1] = false;

		for (int i = 2; i * i <= n; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j <= n; j += i) {
					isPrime[j] = false;
				}
			}
		}

		return isPrime[n];
	}

	public static Map<Integer, Integer> toPrimeFactors(int n) {
		Map<Integer, Integer> primeFactors = new HashMap<>();

		for (int i = 2; i * i <= n; i++) {
			while (n % i == 0) {
				primeFactors.compute(i, (k, v) -> v == null ? 1 : v + 1);
				n /= i;
			}
		}

		if (n > 1) {
			primeFactors.put(n, 1);
		}

		return primeFactors;
	}

	public static int fromPrimeFactors(Map<Integer, Integer> primeFactors) {
		return primeFactors.entrySet().stream()
				.map(e -> (int) Math.pow(e.getKey(), e.getValue()))
				.reduce((a, b) -> a + b).orElse(0);
	}


	public static int gcdEuclidean(int a, int b) {
		if (a < b) {
			int c = a;
			a = b;
			b = c;
		}

		if (b == 0) {
			return a;
		}

		return gcdEuclidean(b, a % b);
	}

	public static int lcmEuclidean(int a, int b) {
		return a * b / gcdEuclidean(a, b);
	}

	public static int gcdPrimeFactors(int a, int b) {
		Map<Integer, Integer> primeFactorsA = toPrimeFactors(a);
		Map<Integer, Integer> primeFactorsB = toPrimeFactors(b);

		Map<Integer, Integer> primeFactorsMin = new HashMap<>();
		for (Entry<Integer, Integer> entry : primeFactorsA.entrySet()) {
			if (primeFactorsB.containsKey(entry.getKey())) {
				primeFactorsMin.put(entry.getKey(),
						Math.min(entry.getValue(), primeFactorsB.get(entry.getKey())));
			}
		}

		return fromPrimeFactors(primeFactorsMin);
	}

	public static int lcmPrimeFactors(int a, int b) {
		Map<Integer, Integer> primeFactorsA = toPrimeFactors(a);
		Map<Integer, Integer> primeFactorsB = toPrimeFactors(b);

		Map<Integer, Integer> primeFactorsMax = primeFactorsA;
		for (Entry<Integer, Integer> entry : primeFactorsB.entrySet()) {
			primeFactorsMax.put(entry.getKey(), Math.max(entry.getValue(),
					primeFactorsMax.getOrDefault(entry.getKey(), 0)));
		}

		return fromPrimeFactors(primeFactorsMax);
	}

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("=======================================");
			System.out.println("1. Check if integer is prime");
			System.out.println("2. Compute the prime factorization of integer");
			System.out.println(
					"3. Calculate the GCD of two integers using Euclidean algorithm");
			System.out.println(
					"4. Calculate the LCM of two integers using Euclidean algorithm");
			System.out.println(
					"5. Calculate the GCD of two integers using Prime Factorization");
			System.out.println(
					"6. Calculate the LCM of two integers using Prime Factorization");
			System.out.println("=======================================");
			System.out.print("Choose an option: ");

			int option = scanner.nextInt();

			switch (option) {
				case 1: {
					System.out.print("Enter a positive integer: ");
					int n = scanner.nextInt();

					System.out.println(
							n + " " + (isPrime(n) ? "is" : "isn't") + " a prime number");
				}
					break;
				case 2: {
					System.out.print("Enter a positive integer: ");
					int n = scanner.nextInt();

					Map<Integer, Integer> primeFactors = toPrimeFactors(n);

					System.out.println("The prime factorization of " + n + ": "
							+ primeFactors.entrySet().stream()
									.map(e -> e.getKey() + " ^ " + e.getValue())
									.reduce((a, b) -> a + " * " + b).orElse(""));
				}
					break;
				case 3: {
					System.out.print("Enter the first positive integer: ");
					int a = scanner.nextInt();

					System.out.print("Enter the second positive integer: ");
					int b = scanner.nextInt();

					int gcd = gcdEuclidean(a, b);

					System.out.println("gcd(" + a + ", " + b + ") = " + gcd);
				}
					break;
				case 4: {
					System.out.print("Enter the first positive integer: ");
					int a = scanner.nextInt();

					System.out.print("Enter the second positive integer: ");
					int b = scanner.nextInt();

					int lcm = lcmEuclidean(a, b);

					System.out.println("lcm(" + a + ", " + b + ") = " + lcm);
				}
					break;
				case 5: {
					System.out.print("Enter the first positive integer: ");
					int a = scanner.nextInt();

					System.out.print("Enter the second positive integer: ");
					int b = scanner.nextInt();

					int gcd = gcdPrimeFactors(a, b);

					System.out.println("gcd(" + a + ", " + b + ") = " + gcd);
				}
					break;
				case 6: {
					System.out.print("Enter the first positive integer: ");
					int a = scanner.nextInt();

					System.out.print("Enter the second positive integer: ");
					int b = scanner.nextInt();

					int lcm = lcmPrimeFactors(a, b);

					System.out.println("lcm(" + a + ", " + b + ") = " + lcm);
				}
					break;
				default: {
					System.out.println("Invalid option selected.");
				}
			}
		}
	}
}

