package com.github.discreteteam.discrete_structures_lab_2;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/math")
@CrossOrigin(origins = "http://localhost:4200")
public class MathController {

	private final DiscreteMathService mathService;

	public MathController(DiscreteMathService mathService) {
		this.mathService = mathService;
	}


	@GetMapping("/isPrime/{n}")
	public ResponseEntity<Boolean> isPrime(@PathVariable int n) {
		if (n <= 0) {
			return ResponseEntity.badRequest().body(false);
		}
		return ResponseEntity.ok(mathService.isPrime(n));
	}


	@GetMapping("/factors/{n}")
	public ResponseEntity<Map<Integer, Integer>> getPrimeFactors(
			@PathVariable int n) {
		if (n <= 0) {
			return ResponseEntity.badRequest().body(Map.of());
		}
		return ResponseEntity.ok(mathService.toPrimeFactors(n));
	}


	@GetMapping("/gcd/euclidean/{a}/{b}")
	public ResponseEntity<Integer> gcdEuclidean(@PathVariable int a,
			@PathVariable int b) {
		if (a <= 0 || b <= 0) {
			return ResponseEntity.badRequest().body(0);
		}
		return ResponseEntity.ok(mathService.gcdEuclidean(a, b));
	}


	@GetMapping("/lcm/euclidean/{a}/{b}")
	public ResponseEntity<Integer> lcmEuclidean(@PathVariable int a,
			@PathVariable int b) {
		if (a <= 0 || b <= 0) {
			return ResponseEntity.badRequest().body(0);
		}
		return ResponseEntity.ok(mathService.lcmEuclidean(a, b));
	}


	@GetMapping("/gcd/factors/{a}/{b}")
	public ResponseEntity<Integer> gcdPrimeFactors(@PathVariable int a,
			@PathVariable int b) {
		if (a <= 0 || b <= 0) {
			return ResponseEntity.badRequest().body(0);
		}
		return ResponseEntity.ok(mathService.gcdPrimeFactors(a, b));
	}


	@GetMapping("/lcm/factors/{a}/{b}")
	public ResponseEntity<Integer> lcmPrimeFactors(@PathVariable int a,
			@PathVariable int b) {
		if (a <= 0 || b <= 0) {
			return ResponseEntity.badRequest().body(0);
		}
		return ResponseEntity.ok(mathService.lcmPrimeFactors(a, b));
	}
}


