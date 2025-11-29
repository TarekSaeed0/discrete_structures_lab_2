package com.discreteteam.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

@Service
public class DiscreteMathService {


	public boolean isPrime(int n) {
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


	public Map<Integer, Integer> toPrimeFactors(int n) {
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


	public int fromPrimeFactors(Map<Integer, Integer> primeFactors) {
		return primeFactors.entrySet().stream()
				.map(e ->Integer.valueOf((int) Math.pow(e.getKey(), e.getValue())))
				.reduce((a, b) -> a * b).orElse(0);
	}

	public int gcdEuclidean(int a, int b) {
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

	public int lcmEuclidean(int a, int b) {
		return a * b / gcdEuclidean(a, b);
	}


	public int gcdPrimeFactors(int a, int b) {
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


	public int lcmPrimeFactors(int a, int b) {
		Map<Integer, Integer> primeFactorsA = toPrimeFactors(a);
		Map<Integer, Integer> primeFactorsB = toPrimeFactors(b);

		Map<Integer, Integer> primeFactorsMax = new HashMap<>(primeFactorsA);
		for (Entry<Integer, Integer> entry : primeFactorsB.entrySet()) {
			primeFactorsMax.put(entry.getKey(), Math.max(entry.getValue(),
					primeFactorsMax.getOrDefault(entry.getKey(), 0)));
		}

		return fromPrimeFactors(primeFactorsMax);
	}

}
