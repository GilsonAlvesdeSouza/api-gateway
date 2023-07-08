package br.com.gilson.apigateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

	@GetMapping("/sum/{num1}/{num2}")
	public ResponseEntity<Double> sum(@PathVariable(value = "num1") String num1, @PathVariable(value = "num2") String num2)
			throws Exception {

		if (!isNumeric(num1) || !isNumeric(num2)) {
			System.out.println("entrou"+ num1 + num2);
			throw new UnsupportedOperationException("Please set a numeric value!");
		}
		var result = Double.parseDouble(num1) + Double.parseDouble(num2);
		return ResponseEntity.ok(result);
	}

	private boolean isNumeric(String value) {
		if (value == null) {
			System.out.println("entrou");
			return false;
		}
		var number = value.replace(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

}
